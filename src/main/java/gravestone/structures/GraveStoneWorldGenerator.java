package gravestone.structures;

import gravestone.config.GSConfig;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.graves.SingleGraveGenerator;
import gravestone.structures.memorials.MemorialGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneWorldGenerator implements IWorldGenerator {

    public static final int DEFAULT_DIMENSION_ID = 0;

    public GraveStoneWorldGenerator() {
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionId() == GSConfig.structuresDimensionId) {
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateSurface(World world, Random rand, int x, int z) {
        double chance = rand.nextDouble();
        EnumFacing direction = EnumFacing.Plane.HORIZONTAL.facings()[rand.nextInt(EnumFacing.Plane.HORIZONTAL.facings().length)];

        if (!CatacombsGenerator.getInstance().generate(world, rand, x, z, direction, chance, false)) {
            if (!MemorialGenerator.getInstance().generate(world, rand, x, z, direction, chance, false)) {
                SingleGraveGenerator.getInstance().generate(world, rand, x, z, direction, chance, false);
            }
        }
    }
}