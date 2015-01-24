package gravestone.structures.memorials;

import gravestone.block.BlockGSMemorial;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.MemorialGenerationHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSMemorial extends ComponentGraveStone {

    public static final int X_LENGTH = 3;
    public static final int HEIGHT = 7;
    public static final int Z_LENGTH = 3;

    public ComponentGSMemorial(int componentType, EnumFacing direction, Random random, int x, int z) {
        super(componentType, direction);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, 64, z, X_LENGTH, HEIGHT, Z_LENGTH, 0);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = BoundingBoxHelper.getAverageGroundLevel(world, boundingBox);

        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + HEIGHT - 1, 0);

        IBlockState groundState, undergroundState;
        BlockPos pos = new BlockPos(getXWithOffset(0, 0), getYWithOffset(0), getZWithOffset(0, 0));
        BiomeGenBase biome = world.getBiomeGenForCoords(pos);

        if (biome.biomeID == BiomeGenBase.desert.biomeID || biome.biomeID == BiomeGenBase.desertHills.biomeID || biome.biomeID == BiomeGenBase.beach.biomeID) {
            groundState = Blocks.sand.getDefaultState();
            undergroundState = Blocks.sand.getDefaultState();
        } else {
            groundState = Blocks.grass.getDefaultState();
            undergroundState = Blocks.dirt.getDefaultState();
        }

        this.fillWithAir(world, boundingBox, 0, 0, 2, 0, 6, 2);
        this.func_175804_a(world, boundingBox, 0, 0, 0, 2, 0, 2, groundState, groundState, false);
        byte memorialType = BlockGSMemorial.getMemorialType(world, pos, random, 0);
        MemorialGenerationHelper.placeMemorial(this, world, random, 1, 1, 1,
                GSBlock.memorial.getDefaultState().withProperty(BlockGSMemorial.FACING, this.coordBaseMode.getOpposite()), memorialType);

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.func_175808_b(world, undergroundState, x, -1, z, boundingBox);
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, boundingBox);
            }
        }

        return true;
    }
}
