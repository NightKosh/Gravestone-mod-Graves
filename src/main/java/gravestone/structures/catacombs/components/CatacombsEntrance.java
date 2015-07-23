package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsEntrance extends CatacombsBaseComponent {

    public static final int X_LENGTH = 4;
    private int stairsLength;
    private int corridorLength;

    public CatacombsEntrance(EnumFacing direction, Random random, int x, int y, int z) {
        super(0, direction);
        stairsLength = 4 + random.nextInt(4);
        corridorLength = 2 + random.nextInt(2);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y - stairsLength * 3, z, X_LENGTH, stairsLength * 3, (stairsLength + corridorLength) * 3 + 5, xShift);

        switch (direction) {
            case SOUTH:
                leftXEnd = 3;
                leftZEnd = (stairsLength + corridorLength) * 3;
                rightXEnd = 0;
                rightZEnd = leftZEnd;
                break;
            case NORTH:
                leftXEnd = 0;
                leftZEnd = (stairsLength + corridorLength) * 3 + 4;
                rightXEnd = 3;
                rightZEnd = leftZEnd;
                break;
            case EAST:
                leftXEnd = 0;
                leftZEnd = (stairsLength + corridorLength) * 3;
                rightXEnd = 3;
                rightZEnd = leftZEnd;
                break;
            case WEST:
                leftXEnd = 3;
                leftZEnd = (stairsLength + corridorLength) * 3 + 4;
                rightXEnd = 0;
                rightZEnd = leftZEnd;
                break;
        }
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int top = boundingBox.maxY - boundingBox.minY - 1;
        IBlockState netherBrickStairsBotState = Blocks.nether_brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, this.coordBaseMode.getOpposite());
        IBlockState stairsTopState = Blocks.stone_brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, this.coordBaseMode)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        int shiftY = top;
        int shiftZ = 0;
        int i;

        for (i = 0; i < stairsLength; i++) {
            shiftZ = i * 3;
            shiftY = top - i * 3 + 1;
            this.fillWithAir(world, boundingBox, 1, shiftY, shiftZ, 2, shiftY, shiftZ + 3);
            this.fillWithAir(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 2, shiftY - 1, shiftZ + 4);
            this.fillWithAir(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 2, shiftY - 2, shiftZ + 5);

            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY, shiftZ, 0, shiftY + 4, shiftZ, Blocks.nether_brick.getDefaultState(), false);
            this.fillWithBlocks(world, boundingBox, 3, shiftY, shiftZ, 3, shiftY + 4, shiftZ, Blocks.nether_brick.getDefaultState(), false);

            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 2, shiftZ + 1, 0, shiftY + 3, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 3, shiftY - 2, shiftZ + 1, 3, shiftY + 3, shiftZ + 2, false, random, getCemeteryCatacombsStones());

            // nether stairs
            this.fillWithBlocks(world, boundingBox, 1, shiftY, shiftZ, 2, shiftY, shiftZ, netherBrickStairsBotState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 2, shiftY - 1, shiftZ + 1, netherBrickStairsBotState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 2, shiftY - 2, shiftZ + 2, netherBrickStairsBotState, false);

            // block stairs
            this.fillWithBlocks(world, boundingBox, 1, shiftY, shiftZ + 4, 2, shiftY, shiftZ + 4, stairsTopState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 5, 2, shiftY - 1, shiftZ + 5, stairsTopState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 6, 2, shiftY - 2, shiftZ + 6, stairsTopState, false);
        }

        shiftY += 1;
        shiftZ += 3;
        int zLength = corridorLength * 3;
        this.fillWithAir(world, boundingBox, 1, shiftY - 2, shiftZ, 2, shiftY - 1, shiftZ);
        this.fillWithAir(world, boundingBox, 1, shiftY - 3, shiftZ + 1, 2, shiftY - 1, shiftZ + zLength + 4);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 0, shiftY, shiftZ, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 3, shiftY, shiftZ, boundingBox);

        // ceiling
        this.fillWithBlocks(world, boundingBox, 0, shiftY, shiftZ + 1, 3, shiftY, shiftZ + zLength + 4, Blocks.nether_brick.getDefaultState(), false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, shiftY - 4, shiftZ, 3, shiftY - 4, shiftZ + zLength + 4, GSBlock.trap.getDefaultState(), false);

        for (int j = 0; j < corridorLength; j++) {
            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY - 3, shiftZ, 0, shiftY - 1, shiftZ, Blocks.nether_brick.getDefaultState(), false);
            this.fillWithBlocks(world, boundingBox, 3, shiftY - 3, shiftZ, 3, shiftY - 1, shiftZ, Blocks.nether_brick.getDefaultState(), false);

            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 3, shiftZ + 1, 0, shiftY - 1, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 3, shiftY - 3, shiftZ + 1, 3, shiftY - 1, shiftZ + 2, false, random, getCemeteryCatacombsStones());

            // web
            this.randomlyFillWithBlocks(world, boundingBox, random, this.WEB_GENERATION_CHANCE, 1, shiftY - 3, shiftZ, 2, shiftY - 1, shiftZ + 2, Blocks.web.getDefaultState(), false);
            shiftZ += 3;
        }

        shiftZ += 4;
        this.fillWithRandomizedBlocks(world, boundingBox, 1, shiftY - 3, shiftZ, 2, shiftY - 1, shiftZ, false, random, getCemeteryCatacombsStones());

        return true;
    }

    @Override
    public boolean canGoOnlyForward() {
        return false;
    }
}