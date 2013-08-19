
package GraveStone.structures.memorials;

import GraveStone.GraveStoneBiomes;
import GraveStone.structures.catacombs.CatacombsGenerator;
import java.util.LinkedList;
import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.structures.GraveStoneWorldGenerator;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MemorialGenerator {
    
    // chance to generate a structure
    public static final double CHANCE = 0.05D;
    public static final short RANGE = 300;
    private static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();

    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance)) {
            new ComponentGSMemorial(rand.nextInt(4), rand, x, z).addComponentParts(world, rand);
            System.out.println("Memorial " + x + "x" + z);

            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }
        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && !GraveStoneBiomes.getMemorialBiomes().contains(world.getBiomeGenForCoords(x, z).biomeID) && noAnyInRange(x, z);
    }

    protected boolean noAnyInRange(int x, int z) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }
        
        for (ChunkCoordIntPair position : CatacombsGenerator.getStructuresList()) {
            if (position.chunkXPos > x - GraveStoneWorldGenerator.CATACOMBS_RANGE && position.chunkXPos < x + GraveStoneWorldGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - GraveStoneWorldGenerator.CATACOMBS_RANGE && position.chunkZPos < z + GraveStoneWorldGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }
    
    public static LinkedList<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }
}
