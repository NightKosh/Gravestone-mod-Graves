package gravestone;

import gravestone.core.GSEntity;
import gravestone.core.event.EventHookGSGraveStone;
import gravestone.core.ModInfo;
import gravestone.core.GSPacketHandler;
import gravestone.core.GSStructures;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSItem;
import gravestone.core.proxy.CommonProxy;
import gravestone.core.localization.GraveStoneLocalizationHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import gravestone.core.GSBlock;
import gravestone.core.GSGui;
import gravestone.core.GSReciepes;
import gravestone.core.GSTileEntity;
import gravestone.core.commands.GSCommands;
import gravestone.core.compatibility.GSCompatibility;
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
@NetworkMod(clientSideRequired = true, serverSideRequired = false, packetHandler = GSPacketHandler.class, channels = {"GSDeathText"})
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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GraveStoneLogger.preinit();
        GraveStoneConfig.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");
        GraveStoneLocalizationHandler.init();
        GSStructures.preInit();
    }

    @Mod.EventHandler
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
        GSStructures.getInstance();
        
        // register entitys
        GSEntity.getInstance();
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
        GSCompatibility.getInstance().checkMods();
        //GraveStoneLogger.logInfo(ModInfo.NAME + " has loaded successfully.");
    }
    
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        GSCommands.getInstance(event);
    }
}