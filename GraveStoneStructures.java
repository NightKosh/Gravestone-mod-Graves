package GraveStone;

import GraveStone.structures.GraveStoneWorldGenerator;
import GraveStone.structures.VillageHandlerGSCemetery;
import GraveStone.structures.VillageHandlerGSUndertaker;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GraveStoneStructures {

    private static GraveStoneStructures instance;

    private GraveStoneStructures() {
        generateStructures();
    }

    public static GraveStoneStructures getInstance() {
        if (instance == null) {
            return new GraveStoneStructures();
        } else {
            return instance;
        }
    }

    private void generateStructures() {
        // register cemeteries
        if (GraveStoneConfig.generateCemeteries) {
            VillageHandlerGSCemetery villageCemeteryHandler = new VillageHandlerGSCemetery();
            VillagerRegistry.instance().registerVillageCreationHandler(villageCemeteryHandler);
        }

        // register Undertaker
        if (GraveStoneConfig.generateUndertaker) {
            VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
            VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
            VillagerRegistry.instance().registerVillagerType(385, "/GraveStone/resources/textures/undertaker.png");
            VillagerRegistry.instance().registerVillageTradeHandler(385, villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator());
    }
}
