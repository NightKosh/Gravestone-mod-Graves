package gravestone.core;

import extrabiomes.api.Biomes;
import gravestone.GraveStoneLogger;
import highlands.api.HighlandsBiomes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBiomes {

    private GSBiomes() {
    }
    // list of allowed biomes for catacombs generator
    private static List<Integer> CATACOMBS_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.plains.biomeID, BiomeGenBase.forest.biomeID,
            BiomeGenBase.taiga.biomeID, BiomeGenBase.icePlains.biomeID));
    // list of disallowed biomes for memorials generator
    private static List<Integer> MEMORIAL_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.ocean.biomeID,
            BiomeGenBase.river.biomeID, BiomeGenBase.swampland.biomeID));
    // list of disallowed biomes for single graves generator
    private static List<Integer> GRAVES_BIOMES = new ArrayList(Arrays.asList(
            BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID,
            BiomeGenBase.ocean.biomeID, BiomeGenBase.river.biomeID));

    public static void addCatacombsBiome(int biomeId) {
        CATACOMBS_BIOMES.add(biomeId);
    }

    public static void addMemorialsBiome(int biomeId) {
        MEMORIAL_BIOMES.add(biomeId);
    }

    public static void addGravesBiome(int biomeId) {
        GRAVES_BIOMES.add(biomeId);
    }
    
    public static List getCatacombsBiomes() {
        return CATACOMBS_BIOMES;
    }

    public static List getMemorialBiomes() {
        return MEMORIAL_BIOMES;
    }

    public static List getGravesBiomes() {
        return GRAVES_BIOMES;
    }
}
