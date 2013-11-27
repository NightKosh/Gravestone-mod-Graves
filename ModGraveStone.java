package gravestone;

import gravestone.core.proxy.CommonProxy;
import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSMemorial;
import gravestone.block.BlockGSTrap;
import gravestone.block.BlockGSWitherSpawner;
import gravestone.block.EnumGraves;
import gravestone.block.EnumMemorials;
import gravestone.block.GraveStoneHelper;
import gravestone.core.localization.GraveStoneLocalizationHandler;
import gravestone.gui.GuiHandler;
import gravestone.item.ItemBlockGSGraveStone;
import gravestone.item.ItemBlockGSMemorial;
import gravestone.item.ItemGSChisel;
import gravestone.tileentity.TileEntityGSGraveStone;
import gravestone.tileentity.TileEntityGSMemorial;
import gravestone.tileentity.TileEntityGSWitherSpawner;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, packetHandler = GraveStonePacketHandler.class, channels = {"GSDeathText"})
public class ModGraveStone {

    @Instance("GraveStone")
    public static ModGraveStone instance;
    @SidedProxy(clientSide = "gravestone.core.proxy.ClientProxy", serverSide = "gravestone.core.proxy.CommonProxy")
    public static CommonProxy proxy;
    // block GraveStone
    public static BlockGSGraveStone graveStone;
    // Block wither spawer
    public static BlockGSWitherSpawner witherSpawner;
    // Block Time Trap
    public static BlockGSTrap trap;
    // block memorial
    public static BlockGSMemorial memorial;
    // item chisel
    public static Item chisel;
    // creative tab
    public static CreativeTabs creativeTab;

    public ModGraveStone() {
        instance = this;
    }

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        GraveStoneLogger.preinit();
        GraveStoneConfig.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");
        GraveStoneLocalizationHandler.init();
        GraveStoneStructures.preInit();
    }

    @Init
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new EventHookGSGraveStone());
        
        // creative tab
        creativeTab = new CreativeTabs("tabGraveStone") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) 0);
                stack.setTagCompound(nbt);
                return stack;
            }
        };
        LanguageRegistry.instance().addStringLocalization("itemGroup.tabGraveStone", "en_US", "Gravestone");
        
        // gravestone
        graveStone = new BlockGSGraveStone(GraveStoneConfig.graveStoneID);
        GameRegistry.registerBlock(graveStone, ItemBlockGSGraveStone.class);

        for (byte i = 0; i < EnumGraves.GRAVES_COUNT; i++) {
            ItemStack graveStoneStack = new ItemStack(graveStone, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);

            if (GraveStoneHelper.isSwordGrave(i)) {
                nbt.setByte("SwordType", GraveStoneHelper.graveTypeToSwordType(i));
            }

            graveStoneStack.setTagCompound(nbt);
            LanguageRegistry.addName(graveStoneStack, EnumGraves.getByID(i).getName());
        }

        MinecraftForge.setBlockHarvestLevel(graveStone, "pickaxe", 1);
        
        // wither spawner
        witherSpawner = new BlockGSWitherSpawner(GraveStoneConfig.witherSpawnerID);
        GameRegistry.registerBlock(witherSpawner, "GSWitherSpawner");
        LanguageRegistry.addName(witherSpawner, "Wither spawner");
        MinecraftForge.setBlockHarvestLevel(witherSpawner, "pickaxe", 1);
        
        // trap
        trap = new BlockGSTrap(GraveStoneConfig.timeTrapID);
        GameRegistry.registerBlock(trap, "GSTimeTrap");
        LanguageRegistry.addName(trap, "Night stone");
        MinecraftForge.setBlockHarvestLevel(trap, "pickaxe", 1);
        
        // memorials
        memorial = new BlockGSMemorial(GraveStoneConfig.memorialID);
        GameRegistry.registerBlock(memorial, "GSMemorial");
        LanguageRegistry.addName(memorial, "Memorial");
        GameRegistry.registerBlock(memorial, ItemBlockGSMemorial.class);

        for (byte i = 0; i < EnumMemorials.MEMORIALS_COUNT; i++) {
            ItemStack memorialStack = new ItemStack(memorial, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", i);
            memorialStack.setTagCompound(nbt);
            LanguageRegistry.addName(memorialStack, EnumMemorials.getByID(i).getName());
        }

        MinecraftForge.setBlockHarvestLevel(memorial, "pickaxe", 2);
        
        // chisel
        chisel = new ItemGSChisel(GraveStoneConfig.chiselId);
        LanguageRegistry.addName(chisel, "Chisel");
        
        // chisel reciep
        GameRegistry.addRecipe(new ItemStack(chisel), "   ", "y  ", "x  ", 'x', Item.stick, 'y', Item.ingotIron);
        
        // register GraveStone tile entity
        GameRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GraveStoneTE");
        
        // register Memorial tile entity
        GameRegistry.registerTileEntity(TileEntityGSMemorial.class, "Memorial");
        
        // register Wither Spawner tile entity
        GameRegistry.registerTileEntity(TileEntityGSWitherSpawner.class, "GSWither Spawner");
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        
        // register structures
        GraveStoneStructures.getInstance();
        
        // register entitys
        GraveStoneEntity.getInstance();
        proxy.registerRenderers();
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // adding foreign mobs
        if (Loader.isModLoaded("MoCreatures")) {
            GraveStoneMobSpawn.addMoCreaturesMobs();
        }

        if (Loader.isModLoaded("TwilightForest")) {
            GraveStoneMobSpawn.addTwilightForestMobs();
        }

        // adding foreign bioms
        if (Loader.isModLoaded("Highlands")) {
            GraveStoneBiomes.addHighlandsBiomes();
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            GraveStoneBiomes.addBiomsOPlentyBiomes();
        }

        if (Loader.isModLoaded("ExtrabiomesXL")) {
            GraveStoneBiomes.addExtrabiomsXLBiomes();
        }

        /*
         // adding Thaumcraft aspects
         if (Loader.isModLoaded("ExtrabiomesXL")) {
         GSThaumcraft.addAspects();
         }
         * */
        //GraveStoneLogger.logInfo(ModInfo.NAME + " has loaded successfully.");
    }
}