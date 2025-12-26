package nightkosh.gravestone;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import nightkosh.gravestone.api.IGraveGeneration;
import nightkosh.gravestone.api.IGraveStoneHelper;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.core.GSBackups;
import nightkosh.gravestone.core.GSTabs;
import nightkosh.gravestone.core.config.GSConfigs;
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

    public ModGraveStone(IEventBus eventBus, ModContainer container) {
        INSTANCE = this;

        container.registerConfig(ModConfig.Type.COMMON, GSConfigs.SPEC, ModInfo.ID + ".toml");

        GSTabs.register(eventBus);
        GSBlocks.register(eventBus);
        GSBlockEntities.register(eventBus);
        GSMenu.register(eventBus);

        GSBackups.register(eventBus);
    }

    //TODO
//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event) {
//        // API
//        GraveStoneAPI.graveStone = gravestoneHelper;
//        GraveStoneAPI.graveGenerationAtDeath = apiGraveGeneration;
//    }

}
