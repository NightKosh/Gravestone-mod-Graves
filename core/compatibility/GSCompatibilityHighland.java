package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import gravestone.core.GSBiomes;
//import highlands.api.HighlandsBiomes;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityHighland {

    protected static boolean isInstalled = false;
    private static final String NAME = "Highlands";
    private static BiomeGenBase[] CATACOMBS_BIOMES;
    private static BiomeGenBase[] MEMORIAL_BIOMES;
    private static BiomeGenBase[] GRAVES_BIOMES;

    private GSCompatibilityHighland() {
    }

    protected static void loadBiomes() {
//        try {
//            CATACOMBS_BIOMES = new BiomeGenBase[]{
//                HighlandsBiomes.autumnForest, HighlandsBiomes.birchHills, HighlandsBiomes.glacier,
//                HighlandsBiomes.outback, HighlandsBiomes.pinelands, HighlandsBiomes.sahel,
//                HighlandsBiomes.savannah, HighlandsBiomes.tundra, HighlandsBiomes.shrubland
//            };
//            MEMORIAL_BIOMES = new BiomeGenBase[]{
//                HighlandsBiomes.bog, HighlandsBiomes.dunes, HighlandsBiomes.estuary,
//                HighlandsBiomes.tropicalIslands, HighlandsBiomes.lake
//            };
//            GRAVES_BIOMES = new BiomeGenBase[]{
//                HighlandsBiomes.lake
//            };
//        } catch (Exception e) {
//            GraveStoneLogger.logError("Can't load Highlands biomes");
//            e.printStackTrace();
//        }
    }

    protected static void addBiomes() {
        for (int i = 0; i < CATACOMBS_BIOMES.length; i++) {
            if (CATACOMBS_BIOMES[i] != null) {
                GSBiomes.addCatacombsBiome(CATACOMBS_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(NAME, i);
            }
        }

        for (int i = 0; i < MEMORIAL_BIOMES.length; i++) {
            if (MEMORIAL_BIOMES[i] != null) {
                GSBiomes.addMemorialsBiome(MEMORIAL_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(NAME, i);
            }
        }

        for (int i = 0; i < GRAVES_BIOMES.length; i++) {
            if (GRAVES_BIOMES[i] != null) {
                GSBiomes.addGravesBiome(GRAVES_BIOMES[i].biomeID);
            } else {
                unNamedBiomeError(NAME, i);
            }
        }
    }

    private static void unNamedBiomeError(String modName, int biomeNum) {
        namedBiomeError(modName, biomeNum + " biome");
    }

    private static void namedBiomeError(String modName, String biomeName) {
        GraveStoneLogger.logError("Error loading " + biomeName + " from " + modName);
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
