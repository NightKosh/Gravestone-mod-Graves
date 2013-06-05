
package GraveStone.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import GraveStone.GraveStoneConfig;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class SingleGraveGenerator {
    
    // list of allowed bioms for structure generator
    private static final ArrayList DISALLOWED_BIOMS = new ArrayList(Arrays.asList(BiomeGenBase.frozenOcean.biomeID, BiomeGenBase.frozenRiver.biomeID, BiomeGenBase.ocean.biomeID, BiomeGenBase.river.biomeID));
    // chance to generate a structure
    private static final double CHANCE = 0.1D;
    private static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
    private static final byte RANGE = 100;

    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance)) {
            new ComponentGSSingleGrave(rand.nextInt(4), rand, x, z).addComponentParts(world, rand);

            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }
        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && !DISALLOWED_BIOMS.contains(world.getBiomeGenForCoords(x, z).biomeID) && noAnyInRange(x, z);
    }

    protected boolean noAnyInRange(int x, int z) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }
        
        for (ChunkCoordIntPair position : CatacombsGenerator.structuresList) {
            if (position.chunkXPos > x - GraveStoneWorldGenerator.CATACOMBS_RANGE && position.chunkXPos < x + GraveStoneWorldGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - GraveStoneWorldGenerator.CATACOMBS_RANGE && position.chunkZPos < z + GraveStoneWorldGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }
}
