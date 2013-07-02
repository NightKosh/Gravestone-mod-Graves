package GraveStone;

import GraveStone.structures.GraveStoneWorldGenerator;
import GraveStone.structures.village.VillageHandlerGSCemetery;
import GraveStone.structures.village.VillageHandlerGSMemorial;
import GraveStone.structures.village.VillageHandlerGSUndertaker;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import extrabiomes.api.Biomes;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneStructures {

    private static GraveStoneStructures instance;
    // list of allowed bioms for catacombs generator
    private static ArrayList CATACOMBS_BIOMS = new ArrayList(Arrays.asList(
            BiomeGenBase.plains.biomeID, BiomeGenBase.forest.biomeID,
            BiomeGenBase.taiga.biomeID, BiomeGenBase.icePlains.biomeID));
    // list of disallowed bioms for memorials generator
    private static final ArrayList MEMORIAL_BIOMS = new ArrayList(Arrays.asList(
            BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.ocean.biomeID,
            BiomeGenBase.river.biomeID, BiomeGenBase.swampland.biomeID));
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
            VillagerRegistry.instance().registerVillagerType(385, "/mods/GraveStone/textures/entity/undertaker.png");
            VillagerRegistry.instance().registerVillageTradeHandler(385, villageUndertakerHandler);
        }

        // structure generator
        GameRegistry.registerWorldGenerator(new GraveStoneWorldGenerator());
    }

    public static void addHighlandsBioms() {
        if (GraveStoneConfig.useHighlandsBiomes) {
            CATACOMBS_BIOMS.addAll(Arrays.asList(
                    151, 153, 160, 164, 165, 169, 170, 176, 193));

            MEMORIAL_BIOMS.add(Arrays.asList(157, 158, 175));
        }
    }

    public static void addBiomsOPlentyBioms() {
        CATACOMBS_BIOMS.addAll(Arrays.asList(
                biomesoplenty.api.Biomes.arctic.get().biomeID,
                biomesoplenty.api.Biomes.bambooForest.get().biomeID,
                biomesoplenty.api.Biomes.birchForest.get().biomeID,
                biomesoplenty.api.Biomes.borealForest.get().biomeID,
                biomesoplenty.api.Biomes.brushland.get().biomeID,
                biomesoplenty.api.Biomes.chaparral.get().biomeID,
                biomesoplenty.api.Biomes.cherryBlossomGrove.get().biomeID,
                biomesoplenty.api.Biomes.deadForest.get().biomeID,
                biomesoplenty.api.Biomes.deadForestSnow.get().biomeID,
                biomesoplenty.api.Biomes.deciduousForest.get().biomeID,
                biomesoplenty.api.Biomes.dunes.get().biomeID,
                biomesoplenty.api.Biomes.fen.get().biomeID,
                biomesoplenty.api.Biomes.field.get().biomeID,
                biomesoplenty.api.Biomes.frostForest.get().biomeID,
                biomesoplenty.api.Biomes.grove.get().biomeID,
                biomesoplenty.api.Biomes.heathland.get().biomeID,
                biomesoplenty.api.Biomes.mapleWoods.get().biomeID,
                biomesoplenty.api.Biomes.meadow.get().biomeID,
                biomesoplenty.api.Biomes.mysticGrove.get().biomeID,
                biomesoplenty.api.Biomes.ominousWoods.get().biomeID,
                biomesoplenty.api.Biomes.orchard.get().biomeID,
                biomesoplenty.api.Biomes.originValley.get().biomeID,
                biomesoplenty.api.Biomes.prairie.get().biomeID,
                biomesoplenty.api.Biomes.savanna.get().biomeID,
                biomesoplenty.api.Biomes.scrubland.get().biomeID,
                biomesoplenty.api.Biomes.seasonalForest.get().biomeID,
                biomesoplenty.api.Biomes.shrubland.get().biomeID,
                biomesoplenty.api.Biomes.steppe.get().biomeID,
                biomesoplenty.api.Biomes.thicket.get().biomeID,
                biomesoplenty.api.Biomes.woodland.get().biomeID));

        MEMORIAL_BIOMS.add(Arrays.asList(
                biomesoplenty.api.Biomes.deadlands.get().biomeID,
                biomesoplenty.api.Biomes.beachGravel.get().biomeID,
                biomesoplenty.api.Biomes.mangrove.get().biomeID,
                biomesoplenty.api.Biomes.marsh.get().biomeID,
                biomesoplenty.api.Biomes.polar.get().biomeID,
                biomesoplenty.api.Biomes.volcano.get().biomeID,
                biomesoplenty.api.Biomes.wasteland.get().biomeID));
    }

    public static void addExtrabiomsXLBioms() {
        CATACOMBS_BIOMS.addAll(Arrays.asList(
                Biomes.getBiome("AutumnWoods").get().biomeID,
                Biomes.getBiome("BirchForest").get().biomeID,
                Biomes.getBiome("ForestedHills").get().biomeID,
                Biomes.getBiome("IceWasteland").get().biomeID,
                Biomes.getBiome("Meadow").get().biomeID,
                Biomes.getBiome("PineForest").get().biomeID,
                Biomes.getBiome("Savanna").get().biomeID,
                Biomes.getBiome("Shrubland").get().biomeID,
                Biomes.getBiome("SnowyForest").get().biomeID,
                Biomes.getBiome("Tundra").get().biomeID,
                Biomes.getBiome("Woodlands").get().biomeID));

        MEMORIAL_BIOMS.add(Arrays.asList(
                Biomes.getBiome("Marsh").get().biomeID,
                Biomes.getBiome("Wasteland").get().biomeID));
    }

    public static ArrayList getCatacombsBioms() {
        return CATACOMBS_BIOMS;
    }

    public static ArrayList getMemorialBioms() {
        return MEMORIAL_BIOMS;
    }
}
