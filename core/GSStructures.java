package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.GraveStoneLogger;
import gravestone.ModGraveStone;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.GraveStoneWorldGenerator;
import gravestone.structures.village.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenStructureIO;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSStructures {

    public static final Block[] VALUEBLE_BLOCKS = {
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
        if (GraveStoneConfig.generateCemeteries) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageCemetery.class, "GSVillageCemetery");
            } catch (Throwable e) {
                GraveStoneLogger.logError("Can not register ComponentGSVillageCemetery");
                e.printStackTrace();
            }
        }

        // register memorials
        if (GraveStoneConfig.generateVillageMemorials) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageMemorial.class, "GSVillageMemorial");
            } catch (Throwable e) {
                GraveStoneLogger.logError("Can not register ComponentGSVillageMemorial");
                e.printStackTrace();
            }
        }

        // register Undertaker
        if (GraveStoneConfig.generateUndertaker) {
            try {
                MapGenStructureIO.func_143031_a(ComponentGSVillageUndertaker.class, "GSUndertakerHouse");
            } catch (Throwable e) {
                GraveStoneLogger.logError("Can not register ComponentGSVillageUndertaker");
                e.printStackTrace();
            }
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
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator(), 50);
    }
}
