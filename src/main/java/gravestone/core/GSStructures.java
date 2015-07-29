package gravestone.core;

import gravestone.config.GSConfig;
import gravestone.core.logger.GSLogger;
import gravestone.structures.GraveStoneWorldGenerator;
import gravestone.structures.village.cemetery.ComponentGSVillageCemetery;
import gravestone.structures.village.cemetery.VillageHandlerGSCemetery;
import gravestone.structures.village.memorial.ComponentGSVillageMemorial;
import gravestone.structures.village.memorial.VillageHandlerGSMemorial;
import gravestone.structures.village.undertaker.ComponentGSVillageUndertaker;
import gravestone.structures.village.undertaker.VillageHandlerGSUndertaker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSStructures {

    public static final Block[] VALUABLE_BLOCKS = {
            Blocks.gold_block, Blocks.lapis_block, Blocks.redstone_block,
            Blocks.gold_block, Blocks.lapis_block, Blocks.redstone_block,
            Blocks.diamond_block, Blocks.emerald_block
    };
    private static GSStructures instance;

    private GSStructures() {
        generateStructures();
    }

    public static GSStructures getInstance() {
        if (instance == null) {
            return new GSStructures();
        } else {
            return instance;
        }
    }

    public static void preInit() {
//TODO remove!
//        if (GSConfig.generateCemeteries) {
//            try {
//                MapGenStructureIO.registerStructure(ComponentGSVillageCemetery.class, "GSVillageCemetery");
//            } catch (Throwable e) {
//                GSLogger.logError("Can not register ComponentGSVillageCemetery");
//                e.printStackTrace();
//            }
//        }

        // register memorials
        if (GSConfig.generateVillageMemorials) {
            try {
                MapGenStructureIO.registerStructure(ComponentGSVillageMemorial.class, "GSVillageMemorial");
            } catch (Throwable e) {
                GSLogger.logError("Can not register ComponentGSVillageMemorial");
                e.printStackTrace();
            }
        }

        // register Undertaker
        if (GSConfig.generateUndertaker) {
            try {
                MapGenStructureIO.registerStructure(ComponentGSVillageUndertaker.class, "GSUndertakerHouse");
            } catch (Throwable e) {
                GSLogger.logError("Can not register ComponentGSVillageUndertaker");
                e.printStackTrace();
            }
        }
    }

    private void generateStructures() {
        //TODO remove!
        // register cemeteries
//        if (GSConfig.generateCemeteries) {
//            VillageHandlerGSCemetery villageCemeteryHandler = new VillageHandlerGSCemetery();
//            VillagerRegistry.instance().registerVillageCreationHandler(villageCemeteryHandler);
//        }

        // register memorials
        if (GSConfig.generateVillageMemorials) {
            VillageHandlerGSMemorial villageMemorialHandler = new VillageHandlerGSMemorial();
            VillagerRegistry.instance().registerVillageCreationHandler(villageMemorialHandler);
        }

        // register Undertaker
        if (GSConfig.generateUndertaker) {
            VillageHandlerGSUndertaker villageUndertakerHandler = new VillageHandlerGSUndertaker();
            VillagerRegistry.instance().registerVillageCreationHandler(villageUndertakerHandler);
            //TODO
//            VillagerRegistry.instance().registerVillagerId(VillageHandlerGSUndertaker.UNDERTAKER_ID);
//            ModGraveStone.proxy.registerVillagers();
//            VillagerRegistry.instance().register(new UndertakerProfession());
            //.registerVillageTradeHandler(VillageHandlerGSUndertaker.UNDERTAKER_ID, villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator(), 50);
    }
}
