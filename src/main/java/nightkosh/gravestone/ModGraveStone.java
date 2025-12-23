package nightkosh.gravestone;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nightkosh.gravestone.api.IGraveGeneration;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSBlockEntities;
import nightkosh.gravestone.core.GSBlocks;
import nightkosh.gravestone.core.GSMenu;
import nightkosh.gravestone.core.logger.GravesLogger;
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

    public static final IGraveStoneHelper gravestoneHelper = GraveGenerationHelper.INSTANCE;
    public static final IGraveGeneration apiGraveGeneration = APIGraveGeneration.INSTANCE;

    public ModGraveStone() {
        INSTANCE = this;

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GSConfigs.SPEC, ModInfo.ID + ".toml");

        var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GSBlocks.register(eventBus);
        GSBlockEntities.register(eventBus);
        GSMenu.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    //TODO
//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event) {
//        // API
//        GraveStoneAPI.graveStone = gravestoneHelper;
//        GraveStoneAPI.graveGenerationAtDeath = apiGraveGeneration;
//    }
//
//    @Mod.EventHandler
//    public void postInit(FMLPostInitializationEvent event) {
//        Compatibility.getInstance();
//    }

}
