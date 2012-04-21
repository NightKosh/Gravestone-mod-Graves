package net.minecraft.GraveStone;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.GraveStone.block.BlockGSGraveStone;
import net.minecraft.GraveStone.block.BlockGSMemorial;
import net.minecraft.GraveStone.block.BlockGSTimeTrap;
import net.minecraft.GraveStone.block.BlockGSWitherSpawner;
import net.minecraft.GraveStone.gui.GuiHandler;
import net.minecraft.GraveStone.item.ItemBlockGSGraveStone;
import net.minecraft.GraveStone.item.ItemBlockGSMemorial;
import net.minecraft.GraveStone.item.ItemGSChisel;
import net.minecraft.GraveStone.structures.GraveStoneWorldGenerator;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.GraveStone.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.GraveStone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.GraveStone.structures.VillageHandlerGSCemetery;
import net.minecraft.GraveStone.structures.VillageHandlerGSUndertaker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "GraveStone", name = "GraveStone", version = "2.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mod_GraveStone {

    @Instance("GraveStone")
    public static mod_GraveStone instance;
    
    @SidedProxy(clientSide = "net.minecraft.GraveStone.client.ClientProxy", serverSide = "net.minecraft.GraveStone.CommonProxy")
    public static CommonProxy proxy;
    // block GraveStone
    public static BlockGSGraveStone graveStone;
    // Block wither spawer
    public static BlockGSWitherSpawner witherSpawner;
    // Block Time Trap
    public static BlockGSTimeTrap timeTrap;
    // block memorial
    public static BlockGSMemorial memorial;
    // item chisel
    public static Item chisel;
    // creative tab
    public static CreativeTabs creativeTab;

    public mod_GraveStone() {
        instance = this;
    }

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        GraveStoneConfig.getInstance(new Configuration(event.getSuggestedConfigurationFile())).getConfigs();
    }

    @Init
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new EventHookGSGraveStone());

        // creative tab
        creativeTab = new CreativeTabs("tabGraveStone") {

            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) 0);
                stack.setTagCompound(nbt);
                return stack;
            }
        };
        LanguageRegistry.instance().addStringLocalization("itemGroup.tabGraveStone", "en_US", "Gravestone");

        // create gravestone
        graveStone = new BlockGSGraveStone(GraveStoneConfig.graveStoneID);
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class);
        for (byte i = 0; i < BlockGSGraveStone.GRAVE_TYPE_COUNT; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, BlockGSGraveStone.blockNames[i]);
        }
        MinecraftForge.setBlockHarvestLevel(graveStone, "pickaxe", 1);


        // create wither spawner
        witherSpawner = new BlockGSWitherSpawner(GraveStoneConfig.witherSpawnerID);
        GameRegistry.registerBlock(witherSpawner, "GSWitherSpawner");
        LanguageRegistry.addName(witherSpawner, "Wither spawner");
        MinecraftForge.setBlockHarvestLevel(witherSpawner, "pickaxe", 1);


        // create time trap
        timeTrap = new BlockGSTimeTrap(GraveStoneConfig.timeTrapID);
        GameRegistry.registerBlock(timeTrap, "GSTimeTrap");
        LanguageRegistry.addName(timeTrap, "Time trap");
        MinecraftForge.setBlockHarvestLevel(timeTrap, "pickaxe", 1);

        // create memorial
        memorial = new BlockGSMemorial(GraveStoneConfig.memorialID);
        GameRegistry.registerBlock(memorial, "GSMemorial");
        LanguageRegistry.addName(memorial, "Memorial");
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class);
        for (byte i = 0; i < BlockGSMemorial.MEMORIAL_TYPE_COUNT; i++) {
            ItemStack memorialStack = new ItemStack(memorial, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            memorialStack.setTagCompound(nbt);
            LanguageRegistry.addName(memorialStack, BlockGSMemorial.blockNames[i]);
        }
        MinecraftForge.setBlockHarvestLevel(memorial, "pickaxe", 2);

        // create chisel
        chisel = new ItemGSChisel(GraveStoneConfig.chiselId);
        LanguageRegistry.addName(chisel, "Chisel");

        // chisel reciep
        GameRegistry.addRecipe(new ItemStack(chisel), "y", "x", 'x', Item.stick, 'y', Item.ingotIron);

        
        // register GraveStone tile entity
        GameRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GraveStone");
        // register Memorial tile entity 
        GameRegistry.registerTileEntity(TileEntityGSMemorial.class, "Memorial");
        // register Wither Spawner tile entity
        GameRegistry.registerTileEntity(TileEntityGSWitherSpawner.class, "GSWither Spawner");

        // register cemeteries
        VillageHandlerGSCemetery villageCemeteryHandler = new VillageHandlerGSCemetery();
        VillagerRegistry.instance().registerVillageCreationHandler(villageCemeteryHandler);

        // register Undertaker
        VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
        VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
        VillagerRegistry.instance().registerVillagerType(385, "/GraveStone/resources/textures/undertaker.png");
        VillagerRegistry.instance().registerVillageTradeHandler(385, villageUndertakerHandler);

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator());

        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

        proxy.registerRenderers();
    }
}