
package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import gravestone.core.GSBiomes;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityExtraBiomesXL {

    protected static boolean isInstalled = false;
    
    private static final String NAME = "ExtrabiomsXL";
    private static final String[] CATACOMBS_BIOMES = {
        "AutumnWoods", "BirchForest", "ForestedHills", "IceWasteland",
        "Meadow", "PineForest", "Savanna", "Shrubland", "SnowyForest",
        "Tundra", "Woodlands"
    };
    private static final String[] MEMORIAL_BIOMES = {
        "Marsh", "Wasteland"
    };
    private static final String[] GRAVES_BIOMES = {
        "Marsh"
    };
    
    private GSCompatibilityExtraBiomesXL() {}    

    protected static void addBiomes() {
//        for (int i = 0; i < CATACOMBS_BIOMES.length; i++) {
//            if (Biomes.getBiome(CATACOMBS_BIOMES[i]).isPresent()) {
//                if (Biomes.getBiome(CATACOMBS_BIOMES[i]).get() != null) {
//                    GSBiomes.addCatacombsBiome(Biomes.getBiome(CATACOMBS_BIOMES[i]).get().biomeID);
//                } else {
//                    namedBiomeError(NAME, CATACOMBS_BIOMES[i]);
//                }
//            }
//        }
//
//        for (int i = 0; i < MEMORIAL_BIOMES.length; i++) {
//            if (Biomes.getBiome(MEMORIAL_BIOMES[i]).isPresent()) {
//                if (Biomes.getBiome(MEMORIAL_BIOMES[i]).get() != null) {
//                    GSBiomes.addMemorialsBiome(Biomes.getBiome(MEMORIAL_BIOMES[i]).get().biomeID);
//                } else {
//                    namedBiomeError(NAME, MEMORIAL_BIOMES[i]);
//                }
//            }
//        }
//
//        for (int i = 0; i < GRAVES_BIOMES.length; i++) {
//            if (Biomes.getBiome(GRAVES_BIOMES[i]).isPresent()) {
//                if (Biomes.getBiome(GRAVES_BIOMES[i]).get() != null) {
//                    GSBiomes.addGravesBiome(Biomes.getBiome(GRAVES_BIOMES[i]).get().biomeID);
//                } else {
//                    namedBiomeError(NAME, GRAVES_BIOMES[i]);
//                }
//            }
//        }
    }

    private static void namedBiomeError(String modName, String biomeName) {
        GraveStoneLogger.logError("Error loading " + biomeName + " from " + modName);
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
