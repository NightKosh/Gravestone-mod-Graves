package nightkosh.gravestone;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.IGraveGeneration;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.GSTileEntity;
import nightkosh.gravestone.core.GuiHandler;
import nightkosh.gravestone.core.Tabs;
import nightkosh.gravestone.core.commands.Commands;
import nightkosh.gravestone.core.compatibility.Compatibility;
import nightkosh.gravestone.core.event.EventHandlerNetwork;
import nightkosh.gravestone.core.event.EventsHandler;
import nightkosh.gravestone.core.proxy.CommonProxy;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, updateJSON = "https://raw.githubusercontent.com/NightKosh/GraveStone-mod/master/update.json")
public class ModGraveStone {

    @Instance("GraveStone")
    public static ModGraveStone instance;
    @SidedProxy(clientSide = "nightkosh.gravestone.core.proxy.ClientProxy", serverSide = "nightkosh.gravestone.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final IGraveStoneHelper gravestoneHelper = GraveGenerationHelper.INSTANCE;
    public static final IGraveGeneration apiGraveGeneration = APIGraveGeneration.INSTANCE;

    public ModGraveStone() {
        instance = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");

        // API
        GraveStoneAPI.graveStone = gravestoneHelper;
        GraveStoneAPI.graveGenerationAtDeath = apiGraveGeneration;

        Tabs.registration();
        GSBlock.registration();
        GSTileEntity.registration();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        FMLCommonHandler.instance().bus().register(new EventHandlerNetwork());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerBlocksModels();
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Compatibility.getInstance();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        Commands.registration(event);
    }
}