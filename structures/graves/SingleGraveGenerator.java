package gravestone.structures.graves;

import gravestone.config.GraveStoneConfig;
import gravestone.structures.GSStructureGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SingleGraveGenerator implements GSStructureGenerator {
    private static SingleGraveGenerator instance;

    private SingleGraveGenerator() {
        instance = this;
    }

    public static SingleGraveGenerator getInstance() {
        if (instance == null) {
            return new SingleGraveGenerator();
        } else {
            return instance;
        }
    }

    // chance to generate a structure
    public static final double CHANCE = 0.1D;
    public static final byte RANGE = 100;
    private static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList<ChunkCoordIntPair>();

    @Override
    public boolean generate(World world, Random rand, int x, int z, double chance, boolean isCommand) {
        if (isCommand || (GraveStoneConfig.generateSingleGraves && canSpawnStructureAtCoords(world, x, z, chance))) {
            if (!isCommand) {
                x += 7;
                z += 7;
            }
            new ComponentGSSingleGrave(rand.nextInt(4), rand, x, z).addComponentParts(world, rand);
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && isBiomeAllowed(world, x, z) && noAnyInRange(x, z);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        LinkedList<BiomeDictionary.Type> biomeTypesList = new LinkedList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z)))));//TODO world.getBiomeGenForCoords(x, z))));
        return !biomeTypesList.contains(BiomeDictionary.Type.WATER) &&
                (GraveStoneConfig.generateGravesInMushroomBiomes || !BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z))).equals(BiomeDictionary.Type.MUSHROOM));//TODO world.getBiomeGenForCoords(x, z))));
    }

    protected static boolean noAnyInRange(int x, int z) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }

        for (ChunkCoordIntPair position : CatacombsGenerator.getStructuresList()) {
            if (position.chunkXPos > x - CatacombsGenerator.CATACOMBS_RANGE && position.chunkXPos < x + CatacombsGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - CatacombsGenerator.CATACOMBS_RANGE && position.chunkZPos < z + CatacombsGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }

    public static LinkedList<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }
}
