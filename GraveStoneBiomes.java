package gravestone;

import extrabiomes.api.Biomes;
import highlands.api.HighlandsBiomes;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneBiomes {

    private GraveStoneBiomes() {
    }
    // list of allowed biomes for catacombs generator
    private static ArrayList CATACOMBS_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.plains.biomeID, BiomeGenBase.forest.biomeID,
            BiomeGenBase.taiga.biomeID, BiomeGenBase.icePlains.biomeID));
    // list of disallowed biomes for memorials generator
    private static final ArrayList MEMORIAL_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.ocean.biomeID,
            BiomeGenBase.river.biomeID, BiomeGenBase.swampland.biomeID));
    // list of disallowed biomes for single graves generator
    private static final ArrayList GRAVES_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID,
            BiomeGenBase.ocean.biomeID, BiomeGenBase.river.biomeID));

    public static void addHighlandsBiomes() {
        CATACOMBS_BIOMES.addAll(Arrays.asList(
                HighlandsBiomes.autumnForest.biomeID,
                HighlandsBiomes.birchHills.biomeID,
                HighlandsBiomes.glacier.biomeID,
                HighlandsBiomes.outback.biomeID,
                HighlandsBiomes.pinelands.biomeID,
                HighlandsBiomes.sahel.biomeID,
                HighlandsBiomes.savannah.biomeID,
                HighlandsBiomes.tundra.biomeID,
                HighlandsBiomes.shrubland.biomeID));
        MEMORIAL_BIOMES.add(Arrays.asList(
                HighlandsBiomes.bog.biomeID,
                HighlandsBiomes.dunes.biomeID,
                HighlandsBiomes.estuary.biomeID,
                HighlandsBiomes.tropicalIslands.biomeID,
                HighlandsBiomes.lake.biomeID));
        GRAVES_BIOMES.add(Arrays.asList(
                HighlandsBiomes.lake.biomeID));
    }

    public static void addBiomsOPlentyBiomes() {
        CATACOMBS_BIOMES.addAll(Arrays.asList(
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
        MEMORIAL_BIOMES.add(Arrays.asList(
                biomesoplenty.api.Biomes.deadlands.get().biomeID,
                biomesoplenty.api.Biomes.beachGravel.get().biomeID,
                biomesoplenty.api.Biomes.mangrove.get().biomeID,
                biomesoplenty.api.Biomes.marsh.get().biomeID,
                biomesoplenty.api.Biomes.polar.get().biomeID,
                biomesoplenty.api.Biomes.volcano.get().biomeID,
                biomesoplenty.api.Biomes.wasteland.get().biomeID));
        GRAVES_BIOMES.add(Arrays.asList(
                biomesoplenty.api.Biomes.mangrove.get().biomeID));
    }

    public static void addExtrabiomsXLBiomes() {
        CATACOMBS_BIOMES.addAll(Arrays.asList(
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
        MEMORIAL_BIOMES.add(Arrays.asList(
                Biomes.getBiome("Marsh").get().biomeID,
                Biomes.getBiome("Wasteland").get().biomeID));
        GRAVES_BIOMES.add(Arrays.asList(
                Biomes.getBiome("Marsh").get().biomeID));
    }

    public static ArrayList getCatacombsBiomes() {
        return CATACOMBS_BIOMES;
    }

    public static ArrayList getMemorialBiomes() {
        return MEMORIAL_BIOMES;
    }

    public static ArrayList getGravesBiomes() {
        return GRAVES_BIOMES;
    }
}
