package nightkosh.gravestone;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.IGraveGeneration;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.capability.BackupStorage;
import nightkosh.gravestone.capability.Backups;
import nightkosh.gravestone.capability.IBackups;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.*;
import nightkosh.gravestone.core.commands.Commands;
import nightkosh.gravestone.core.compatibility.Compatibility;
import nightkosh.gravestone.core.event.EventHandlerNetwork;
import nightkosh.gravestone.core.event.EventsHandler;
import nightkosh.gravestone.core.logger.GravesLogger;
import nightkosh.gravestone.core.proxy.CommonProxy;
import nightkosh.gravestone.helper.GraveGenerationHelper;
import nightkosh.gravestone.helper.api.APIGraveGeneration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(ModInfo.ID)
public class ModGraveStone {

    public static ModGraveStone INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(ModInfo.ID);
    public static final Logger GRAVE_LOGGER = new GravesLogger();

    @SidedProxy(clientSide = "nightkosh.gravestone.core.proxy.ClientProxy", serverSide = "nightkosh.gravestone.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final IGraveStoneHelper gravestoneHelper = GraveGenerationHelper.INSTANCE;
    public static final IGraveGeneration apiGraveGeneration = APIGraveGeneration.INSTANCE;

    public ModGraveStone() {
        INSTANCE = this;

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GSConfigs.SPEC, ModInfo.ID + ".toml");

        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GSBlocks.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GSConfigs.getInstance(event.getModConfigurationDirectory().getAbsolutePath() + "/GraveStoneMod/", "GraveStone.cfg");

        // API
        GraveStoneAPI.graveStone = gravestoneHelper;
        GraveStoneAPI.graveGenerationAtDeath = apiGraveGeneration;

        GSTabs.registration();
        GSTileEntity.registration();

        CapabilityManager.INSTANCE.register(IBackups.class, new BackupStorage(), Backups.class);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        // register death event
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        FMLCommonHandler.instance().bus().register(new EventHandlerNetwork());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
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
