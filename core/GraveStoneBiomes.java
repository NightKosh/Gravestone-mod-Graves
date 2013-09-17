package gravestone.core;

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
    private static final String HIGHLANDS_NAME = "Highlands";
    private static BiomeGenBase[] HIGHLANDS_CATACOMBS_BIOMES;
    private static BiomeGenBase[] HIGHLANDS_MEMORIAL_BIOMES;
    private static BiomeGenBase[] HIGHLANDS_GRAVES_BIOMES;

    public static void loadHighlandsBiomes() {
        try {
            HIGHLANDS_CATACOMBS_BIOMES = new BiomeGenBase[]{
                HighlandsBiomes.autumnForest, HighlandsBiomes.birchHills, HighlandsBiomes.glacier,
                HighlandsBiomes.outback, HighlandsBiomes.pinelands, HighlandsBiomes.sahel,
                HighlandsBiomes.savannah, HighlandsBiomes.tundra, HighlandsBiomes.shrubland
            };
            HIGHLANDS_MEMORIAL_BIOMES = new BiomeGenBase[]{
                HighlandsBiomes.bog, HighlandsBiomes.dunes, HighlandsBiomes.estuary,
                HighlandsBiomes.tropicalIslands, HighlandsBiomes.lake
            };
            HIGHLANDS_GRAVES_BIOMES = new BiomeGenBase[]{
                HighlandsBiomes.lake
            };
        } catch (Exception e) {
            GraveStoneLogger.logError("Can't load Highlands biomes");
            e.printStackTrace();
        }
    }

    public static void addHighlandsBiomes() {
        for (int i = 0; i < HIGHLANDS_CATACOMBS_BIOMES.length; i++) {
            if (HIGHLANDS_CATACOMBS_BIOMES[i] != null) {
                CATACOMBS_BIOMES.add(HIGHLANDS_CATACOMBS_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(HIGHLANDS_NAME, i);
            }
        }

        for (int i = 0; i < HIGHLANDS_MEMORIAL_BIOMES.length; i++) {
            if (HIGHLANDS_MEMORIAL_BIOMES[i] != null) {
                MEMORIAL_BIOMES.add(HIGHLANDS_MEMORIAL_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(HIGHLANDS_NAME, i);
            }
        }

        for (int i = 0; i < HIGHLANDS_GRAVES_BIOMES.length; i++) {
            if (HIGHLANDS_GRAVES_BIOMES[i] != null) {
                GRAVES_BIOMES.add(HIGHLANDS_GRAVES_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(HIGHLANDS_NAME, i);
            }
        }
    }
    private static final String BIOMES_OPLENTY_NAME = "Biomes o plenty";
    private static BiomeGenBase[] BIOMES_OPLENTY_CATACOMBS_BIOMES;
    private static BiomeGenBase[] BIOMES_OPLENTY_MEMORIAL_BIOMES;
    private static BiomeGenBase[] BIOMES_OPLENTY_GRAVES_BIOMES;

    public static void loadBiomsOPlentyBiomes() {
        try {
            BIOMES_OPLENTY_CATACOMBS_BIOMES = new BiomeGenBase[]{
                biomesoplenty.api.Biomes.arctic.get(),
                biomesoplenty.api.Biomes.bambooForest.get(),
                biomesoplenty.api.Biomes.birchForest.get(),
                biomesoplenty.api.Biomes.borealForest.get(),
                biomesoplenty.api.Biomes.brushland.get(),
                biomesoplenty.api.Biomes.chaparral.get(),
                biomesoplenty.api.Biomes.cherryBlossomGrove.get(),
                biomesoplenty.api.Biomes.deadForest.get(),
                biomesoplenty.api.Biomes.deadForestSnow.get(),
                biomesoplenty.api.Biomes.deciduousForest.get(),
                biomesoplenty.api.Biomes.dunes.get(),
                biomesoplenty.api.Biomes.fen.get(),
                biomesoplenty.api.Biomes.field.get(),
                biomesoplenty.api.Biomes.frostForest.get(),
                biomesoplenty.api.Biomes.grove.get(),
                biomesoplenty.api.Biomes.heathland.get(),
                biomesoplenty.api.Biomes.mapleWoods.get(),
                biomesoplenty.api.Biomes.meadow.get(),
                biomesoplenty.api.Biomes.mysticGrove.get(),
                biomesoplenty.api.Biomes.ominousWoods.get(),
                biomesoplenty.api.Biomes.orchard.get(),
                biomesoplenty.api.Biomes.originValley.get(),
                biomesoplenty.api.Biomes.prairie.get(),
                biomesoplenty.api.Biomes.savanna.get(),
                biomesoplenty.api.Biomes.scrubland.get(),
                biomesoplenty.api.Biomes.seasonalForest.get(),
                biomesoplenty.api.Biomes.shrubland.get(),
                biomesoplenty.api.Biomes.steppe.get(),
                biomesoplenty.api.Biomes.thicket.get(),
                biomesoplenty.api.Biomes.woodland.get()
            };
            BIOMES_OPLENTY_MEMORIAL_BIOMES = new BiomeGenBase[]{
                biomesoplenty.api.Biomes.deadlands.get(),
                biomesoplenty.api.Biomes.beachGravel.get(),
                biomesoplenty.api.Biomes.mangrove.get(),
                biomesoplenty.api.Biomes.marsh.get(),
                biomesoplenty.api.Biomes.polar.get(),
                biomesoplenty.api.Biomes.volcano.get(),
                biomesoplenty.api.Biomes.wasteland.get()
            };
            BIOMES_OPLENTY_GRAVES_BIOMES = new BiomeGenBase[]{
                biomesoplenty.api.Biomes.mangrove.get()
            };
        } catch (Exception e) {
            GraveStoneLogger.logError("Can't load Bioms O Plenty biomes");
            e.printStackTrace();
        }
    }

    public static void addBiomsOPlentyBiomes() {
        for (int i = 0; i < BIOMES_OPLENTY_CATACOMBS_BIOMES.length; i++) {
            if (BIOMES_OPLENTY_CATACOMBS_BIOMES[i] != null) {
                CATACOMBS_BIOMES.add(BIOMES_OPLENTY_CATACOMBS_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(BIOMES_OPLENTY_NAME, i);
            }
        }

        for (int i = 0; i < BIOMES_OPLENTY_MEMORIAL_BIOMES.length; i++) {
            if (BIOMES_OPLENTY_MEMORIAL_BIOMES[i] != null) {
                MEMORIAL_BIOMES.add(BIOMES_OPLENTY_MEMORIAL_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(BIOMES_OPLENTY_NAME, i);
            }
        }

        for (int i = 0; i < BIOMES_OPLENTY_GRAVES_BIOMES.length; i++) {
            if (BIOMES_OPLENTY_GRAVES_BIOMES[i] != null) {
                GRAVES_BIOMES.add(BIOMES_OPLENTY_GRAVES_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(BIOMES_OPLENTY_NAME, i);
            }
        }
    }
    private static final String EXTRABIOMES_XL_NAME = "ExtrabiomsXL";
    private static final String[] EXTRABIOMES_XL_CATACOMBS_BIOMES = {
        "AutumnWoods", "BirchForest", "ForestedHills", "IceWasteland",
        "Meadow", "PineForest", "Savanna", "Shrubland", "SnowyForest",
        "Tundra", "Woodlands"
    };
    private static final String[] EXTRABIOMES_XL_MEMORIAL_BIOMES = {
        "Marsh", "Wasteland"
    };
    private static final String[] EXTRABIOMES_XL_GRAVES_BIOMES = {
        "Marsh"
    };

    public static void addExtrabiomsXLBiomes() {
        for (int i = 0; i < EXTRABIOMES_XL_CATACOMBS_BIOMES.length; i++) {
            if (Biomes.getBiome(EXTRABIOMES_XL_CATACOMBS_BIOMES[i]).isPresent()) {
                if (Biomes.getBiome(EXTRABIOMES_XL_CATACOMBS_BIOMES[i]).get() != null) {
                    CATACOMBS_BIOMES.add(Biomes.getBiome(EXTRABIOMES_XL_CATACOMBS_BIOMES[i]).get().biomeID);
                } else {
                    namedBiomeError(EXTRABIOMES_XL_NAME, EXTRABIOMES_XL_CATACOMBS_BIOMES[i]);
                }
            }
        }

        for (int i = 0; i < EXTRABIOMES_XL_MEMORIAL_BIOMES.length; i++) {
            if (Biomes.getBiome(EXTRABIOMES_XL_MEMORIAL_BIOMES[i]).isPresent()) {
                if (Biomes.getBiome(EXTRABIOMES_XL_MEMORIAL_BIOMES[i]).get() != null) {
                    MEMORIAL_BIOMES.add(Biomes.getBiome(EXTRABIOMES_XL_MEMORIAL_BIOMES[i]).get().biomeID);
                } else {
                    namedBiomeError(EXTRABIOMES_XL_NAME, EXTRABIOMES_XL_MEMORIAL_BIOMES[i]);
                }
            }
        }

        for (int i = 0; i < EXTRABIOMES_XL_GRAVES_BIOMES.length; i++) {
            if (Biomes.getBiome(EXTRABIOMES_XL_GRAVES_BIOMES[i]).isPresent()) {
                if (Biomes.getBiome(EXTRABIOMES_XL_GRAVES_BIOMES[i]).get() != null) {
                    GRAVES_BIOMES.add(Biomes.getBiome(EXTRABIOMES_XL_GRAVES_BIOMES[i]).get().biomeID);
                } else {
                    namedBiomeError(EXTRABIOMES_XL_NAME, EXTRABIOMES_XL_GRAVES_BIOMES[i]);
                }
            }
        }
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

    private static void unNamedBiomeError(String modName, int biomeNum) {
        namedBiomeError(modName, biomeNum + " biome");
    }

    private static void namedBiomeError(String modName, String biomeName) {
        GraveStoneLogger.logError("Error loading " + biomeName + " from " + modName);
    }
}
