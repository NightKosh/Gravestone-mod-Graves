
package GraveStone.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import GraveStone.GraveStoneConfig;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MemorialGenerator {
    
    // list of allowed bioms for structure generator
    private static final ArrayList<BiomeGenBase> DISALLOWED_BIOMS = new ArrayList<BiomeGenBase>(Arrays.asList(BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.ocean, BiomeGenBase.river, BiomeGenBase.swampland, BiomeGenBase.jungle, BiomeGenBase.jungleHills));
    // chance to generate a structure
    private static final double CHANCE = 0.05D;
    private static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
    private static final short RANGE = 300;

    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance)) {
            new ComponentGSMemorial(rand.nextInt(4), rand, x, z).addComponentParts(world, rand);

            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }
        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && !DISALLOWED_BIOMS.contains(world.getBiomeGenForCoords(x, z)) && noAnyInRange(x, z);
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
