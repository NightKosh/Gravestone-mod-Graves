package GraveStone.structures.catacombs;

import GraveStone.GraveStoneConfig;
import GraveStone.GraveStoneStructures;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsGenerator {

    // chance to generate a structure
    private static final double CHANCE = 0.0005D;
    protected static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();

    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateCatacombs && canSpawnStructureAtCoords(world, x, z, chance)) {
            int direction = rand.nextInt(4);
            CatacombsSurface surface = new CatacombsSurface(world, rand, x, z, direction);
            System.out.println("Catacombs " + x + "x" + z);
            
            if (surface.getMausoleumY() > 55) {
                new CatacombsUnderground(world, rand, direction, surface.getMausoleumX(), surface.getMausoleumY(), surface.getMausoleumZ());
            }
            
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }
        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && GraveStoneStructures.getCatacombsBioms().contains(world.getBiomeGenForCoords(x, z).biomeID) && noAnyInRange(x, z, 700);
    }

    protected boolean noAnyInRange(int x, int z, int range) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - range && position.chunkXPos < x + range
                    && position.chunkZPos > z - range && position.chunkZPos < z + range) {
                return false;
            }
        }

        return true;
    }
    
    public static LinkedList<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }
}
