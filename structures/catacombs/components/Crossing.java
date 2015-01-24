package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneBrick;
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
public class Crossing extends CatacombsBaseComponent {

    public static final int X_LENGTH = 13;
    public static final int HEIGHT = 6;
    public static final int Z_LENGTH = 13;

    public Crossing(int componentType, EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(componentType, facing, level);
        xShift = 4;
        topXEnd = 4;
        topZEnd = 12;

        switch (facing) {
            case SOUTH:
                leftXEnd = 12;
                leftZEnd = 4;
                rightXEnd = 0;
                rightZEnd = 4;
                break;
            case NORTH:
                leftXEnd = 0;
                leftZEnd = 4;
                rightXEnd = 12;
                rightZEnd = 4;
                break;
            case WEST:
                leftXEnd = 12;
                leftZEnd = 8;
                rightXEnd = 0;
                rightZEnd = 8;
                break;
            case EAST:
                leftXEnd = 0;
                leftZEnd = 8;
                rightXEnd = 12;
                rightZEnd = 8;
                break;
        }

        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 11, 4, 11);

        this.randomlyFillWithBlocks(world, boundingBox, random, PILE_OF_BONES_GENERATION_CHANCE, 1, 1, 1, 12, 1, 12, GSBlock.pileOfBones.getDefaultState(), false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 0, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 4, 0, 0, 4, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 0, 8, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 0, 0, 12, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 11, 0, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 4, 11, 0, 4, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 8, 11, 0, 8, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 12, 11, 0, 12, Blocks.nether_brick.getDefaultState(), false);

        // stoneBrick floor
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 3, 0, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 5, 3, 0, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 9, 3, 0, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 0, 1, 7, 0, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 0, 9, 7, 0, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 1, 11, 0, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 5, 11, 0, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 9, 11, 0, 11, false, random, getCemeteryCatacombsStones());

        // nether ceiling
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 0, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 4, 5, 0, 4, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 5, 0, 8, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 5, 0, 12, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 0, 11, 5, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 4, 11, 5, 4, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 8, 11, 5, 8, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 12, 11, 5, 12, Blocks.nether_brick.getDefaultState(), false);

        // stoneBrick ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 1, 3, 5, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 5, 3, 5, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 9, 3, 5, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 5, 1, 7, 5, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 5, 9, 7, 5, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 1, 11, 5, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 5, 11, 5, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 5, 9, 11, 5, 11, false, random, getCemeteryCatacombsStones());

        // cutted stoneBrick floor and ceiling
        IBlockState chiseledStoneState = Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 7, 0, 7, chiseledStoneState, false);
        this.fillWithBlocks(world, boundingBox, 5, 5, 5, 7, 5, 7, chiseledStoneState, false);

        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 12, 11, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 11, 4, 0, Blocks.nether_brick.getDefaultState(), false);

        // columns
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 8, 4, 4, 8, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 8, 8, 4, 8, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 4, 8, 4, 4, false, random, getCemeteryCatacombsStones());

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 1, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 1, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 11, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 11, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 1, 2, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 1, 2, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 11, 2, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 11, 2, 1, boundingBox);

        // fire stairs
        IBlockState netherBrickStairsState = Blocks.nether_brick_stairs.getDefaultState();
        IBlockState netherBrickStairsTopState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode.getOpposite());
        IBlockState netherBrickStairsBotState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode);
        IBlockState netherBrickStairsLeftState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState netherBrickStairsRightState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getRightDirection(this.coordBaseMode));
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 1, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 11, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 1, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 2, 1, 11, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 11, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 10, 1, 11, boundingBox);

        // fill exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 12, 7, 3, 12, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 5, 0, 3, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 1, 5, 12, 3, 7, false, random, getCemeteryCatacombsStones());

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 7, 2, 1, 7, 2, 1, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 3, 2, 5, 3, 2, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 1, 5, 3, 1, 5, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 2, 6, 3, 2, 6, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 3, 7, 1, 3, 7, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 6, 2, 5, 6, 2, 5, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 8, 3, 5, 8, 3, 5, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 1, 5, 10, 1, 5, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 1, 7, 5, 1, 7, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 11, 2, 7, 11, 2, 7, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 9, 3, 9, 9, 3, 9, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 1, 11, 5, 1, 11, Blocks.web.getDefaultState(), false);

        // clear enter
        this.fillWithAir(world, boundingBox, 5, 1, 0, 7, 3, 0);

        // spawner
        ObjectsGenerationHelper.generateSpawner(this, world, random, 6, 1, 6);

        return true;
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}
