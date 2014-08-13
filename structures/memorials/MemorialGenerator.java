package gravestone.structures.memorials;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import gravestone.GraveStoneLogger;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.GSStructureGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import net.minecraft.block.material.Material;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MemorialGenerator implements GSStructureGenerator {

    private static MemorialGenerator instance;

    private MemorialGenerator() {
        instance = this;
    }

    public static MemorialGenerator getInstance() {
        if (instance == null) {
            return new MemorialGenerator();
        } else {
            return instance;
        }
    }
    public static final double CHANCE = 0.05D;
    public static final short RANGE = 400;
    private static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();

    @Override
    public boolean generate(World world, Random rand, int x, int z, double chance, boolean isCommand) {
        if (!isCommand) {
            x = x + (16 - ComponentGSMemorial.X_LENGTH) / 2;
            z = z + (16 - ComponentGSMemorial.Z_LENGTH) / 2;
        }
        if (isCommand || (GraveStoneConfig.generateMemorials && canSpawnStructureAtCoords(world, x, z, chance) && isNoWarterUnder(world, x, z))) {
            new ComponentGSMemorial(rand.nextInt(4), rand, x, z).addComponentParts(world, rand);
            GraveStoneLogger.logInfo("Generate memorial at " + x + "x" + z);
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && isBiomeAllowed(world, x, z) && noAnyInRange(x, z);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        LinkedList<BiomeDictionary.Type> biomeTypesList = new LinkedList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(x, z))));
        return !biomeTypesList.contains(BiomeDictionary.Type.WATER);
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

    private static boolean isNoWarterUnder(World world, int x, int z) {
        int y = world.getTopSolidOrLiquidBlock(x, z);
        return !world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }
}
