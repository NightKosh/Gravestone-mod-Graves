package gravestone;

import gravestone.core.GraveStoneEntity;
import gravestone.core.EventHookGSGraveStone;
import gravestone.core.ModInfo;
import gravestone.core.GraveStonePacketHandler;
import gravestone.core.GraveStoneStructures;
import gravestone.core.GraveStoneBiomes;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GraveStoneMobSpawn;
import gravestone.core.GSItem;
import gravestone.core.proxy.CommonProxy;
import gravestone.core.localization.GraveStoneLocalizationHandler;
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
import gravestone.core.GSBlock;
import gravestone.core.GSGui;
import gravestone.core.GSReciepes;
import gravestone.core.GSTileEntity;
import net.minecraft.creativetab.CreativeTabs;
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
                ItemStack stack = new ItemStack(GSBlock.graveStone, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte("GraveType", (byte) 0);
                stack.setTagCompound(nbt);
                return stack;
            }
        };
                
        // blocks registration
        GSBlock.registration();
        
        // items registration
        GSItem.registration();
        
        // reciepes registration
        GSReciepes.registration();
        
        // tileEntities registration
        GSTileEntity.registration();
        
        // Gui registration
        GSGui.registration();
        
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
            GraveStoneBiomes.loadHighlandsBiomes();
            GraveStoneBiomes.addHighlandsBiomes();
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            GraveStoneBiomes.loadBiomsOPlentyBiomes();
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