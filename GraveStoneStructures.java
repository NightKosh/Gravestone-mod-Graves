package gravestone;

import gravestone.structures.GraveStoneWorldGenerator;
import gravestone.structures.village.VillageHandlerGSCemetery;
import gravestone.structures.village.VillageHandlerGSMemorial;
import gravestone.structures.village.VillageHandlerGSUndertaker;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.structures.village.ComponentGSVillageCemetery;
import gravestone.structures.village.ComponentGSVillageMemorial;
import gravestone.structures.village.ComponentGSVillageUndertaker;
import net.minecraft.block.Block;
import net.minecraft.world.gen.structure.MapGenStructureIO;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneStructures {

    private static GraveStoneStructures instance;
    public static final int[] VALUEBLE_BLOCKS = {
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockDiamond.blockID, Block.blockEmerald.blockID
    };

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

        // register memorials
        if (GraveStoneConfig.generateVillageMemorials) {
            VillageHandlerGSMemorial villageMemorialHandler = new VillageHandlerGSMemorial();
            VillagerRegistry.instance().registerVillageCreationHandler(villageMemorialHandler);
        }

        // register Undertaker
        if (GraveStoneConfig.generateUndertaker) {
            VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
            VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
            VillagerRegistry.instance().registerVillagerId(385);
            ModGraveStone.proxy.registerVillagers();
            VillagerRegistry.instance().registerVillageTradeHandler(385, villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator());
    }

    public static void preInit() {
        if (GraveStoneConfig.generateCemeteries) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageCemetery.class, "GSVillageCemetery");
            } catch (Throwable e) {
                System.out.println("Can not register ComponentGSVillageCemetery");
            }
        }

        // register memorials
        if (GraveStoneConfig.generateVillageMemorials) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageMemorial.class, "GSVillageMemorial");
            } catch (Throwable e) {
                System.out.println("Can not register ComponentGSVillageMemorial");
            }
        }

        // register Undertaker
        if (GraveStoneConfig.generateUndertaker) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageUndertaker.class, "GSUndertakerHouse");
            } catch (Throwable e) {
                System.out.println("Can not register ComponentGSVillageUndertaker");
            }
        }
    }
}
