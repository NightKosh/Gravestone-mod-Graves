package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveHall extends CatacombsBaseComponent {

    public static final int X_LENGTH = 16;
    public static final int HEIGHT = 6;
    public static final int Z_LENGTH = 18;

    public GraveHall(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        xShift = 6;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goForward = true;
        frontXEnd = 6;
        frontZEnd = Z_LENGTH;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 15, 5, 17);
        this.fillWithAir(world, boundingBox, 7, 1, 0, 9, 3, 1);
        this.fillWithAir(world, boundingBox, 7, 1, 17, 9, 3, 17);

        // fill exit
        this.fillWithRandomizedBlocks(world, boundingBox, 7, 1, 18, 9, 3, 18, false, random, getCemeteryCatacombsStones());

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 3, 1, 3, 13, 5, 15, Blocks.web.getDefaultState(), false);
        // piles of bones
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 1, 3, 13, 1, 15, false, random, getPileOfBonesSelector());

        // nether floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 1, 1, 0, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 0, 1, 15, 0, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 5, 0, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 3, 11, 0, 15, Blocks.nether_brick.getDefaultState(), false);

        // nether floor lines
        this.fillWithBlocks(world, boundingBox, 2, 0, 1, 14, 0, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 6, 14, 0, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 12, 14, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 17, 14, 0, 17, Blocks.nether_brick.getDefaultState(), false);

        // floor entrance
        this.fillWithBlocks(world, boundingBox, 5, 0, 0, 11, 0, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 16, 11, 0, 18, Blocks.nether_brick.getDefaultState(), false);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 2, 4, 0, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 3, 10, 0, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 0, 2, 14, 0, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 7, 4, 0, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 7, 10, 0, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 0, 7, 14, 0, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 13, 4, 0, 16, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 13, 10, 0, 15, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 0, 13, 14, 0, 16, false, random, getCemeteryCatacombsStones());

        // nether ceiling
        this.fillWithBlocks(world, boundingBox, 1, 6, 1, 1, 6, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 6, 1, 15, 6, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 6, 3, 5, 6, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 6, 3, 11, 6, 15, Blocks.nether_brick.getDefaultState(), false);

        // nether ceiling lines
        this.fillWithBlocks(world, boundingBox, 2, 6, 1, 14, 6, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 6, 14, 6, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 12, 14, 6, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 17, 14, 6, 17, Blocks.nether_brick.getDefaultState(), false);

        // ceiling entrance
        this.fillWithBlocks(world, boundingBox, 5, 4, 0, 11, 6, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 4, 16, 11, 6, 18, Blocks.nether_brick.getDefaultState(), false);

        // block ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 6, 2, 4, 6, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 6, 3, 10, 6, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 6, 2, 14, 6, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 6, 7, 4, 6, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 6, 7, 10, 6, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 6, 7, 14, 6, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 6, 13, 4, 6, 16, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 6, 13, 10, 6, 15, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 6, 13, 14, 6, 16, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 1, 1, 1, 1, 5, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 1, 15, 5, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 6, 1, 5, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 6, 15, 5, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 12, 1, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 12, 15, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 17, 1, 5, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 17, 15, 5, 17, Blocks.nether_brick.getDefaultState(), false);

        // nether walls lines
        this.fillWithBlocks(world, boundingBox, 1, 3, 1, 1, 3, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 15, 3, 1, 15, 3, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 3, 1, 4, 3, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 3, 1, 14, 3, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 3, 17, 4, 3, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 3, 17, 14, 3, 17, Blocks.nether_brick.getDefaultState(), false);

        // nether wall entrance
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 6, 3, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 0, 11, 3, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 16, 6, 3, 18, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 16, 11, 3, 18, Blocks.nether_brick.getDefaultState(), false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 0, 0, 6, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 16, 0, 0, 16, 6, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 0, 4, 6, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 0, 0, 16, 6, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 18, 4, 6, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 0, 18, 16, 6, 18, false, random, getCemeteryCatacombsStones());

        // columns
        buildColumn(world, 5, 6);
        buildColumn(world, 11, 6);
        buildColumn(world, 5, 12);
        buildColumn(world, 11, 12);

        // graves
        byte graveType = GraveStoneHelper.getGraveType(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0)), random, BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES);
        IBlockState graveState = GSBlock.graveStone.getDefaultState();
        IBlockState leftGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState rightGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getRightDirection(this.coordBaseMode));
        IBlockState topGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.coordBaseMode.getOpposite());
        IBlockState botGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.coordBaseMode);
        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);

        // left wall
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 5, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 8, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 10, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 13, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 16, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 2, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 5, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 8, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 10, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 13, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 16, leftGraveState, graveType, sword, true);

        // right wall
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 2, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 5, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 8, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 10, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 13, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 16, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 2, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 5, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 8, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 10, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 13, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 16, rightGraveState, graveType, sword, true);

        // top walls
        GraveGenerationHelper.placeGrave(this, world, random, 3, 1, 17, topGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 1, 17, topGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 3, 4, 17, topGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 4, 17, topGraveState, graveType, sword, true);

        // bot walls
        GraveGenerationHelper.placeGrave(this, world, random, 3, 1, 1, botGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 1, 1, botGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 3, 4, 1, botGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 4, 1, botGraveState, graveType, sword, true);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 7, 0, 6, 9, 0, 6, GSBlock.trap.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 7, 0, 12, 9, 0, 12, GSBlock.trap.getDefaultState(), false);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        return true;
    }

    private void buildColumn(World world, int x, int z) {
        this.fillWithBlocks(world, boundingBox, x, 1, z, x, 5, z, Blocks.nether_brick.getDefaultState(), false);
        IBlockState netherBrickStairsState = Blocks.nether_brick_stairs.getDefaultState();
        IBlockState netherBrickStairsTopState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode.getOpposite());
        IBlockState netherBrickStairsBotState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode);
        IBlockState netherBrickStairsLeftState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState netherBrickStairsRightState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getRightDirection(this.coordBaseMode));

        // stairs
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x - 1, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x + 1, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, x - 1, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, x + 1, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x - 1, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x + 1, 1, z + 1, boundingBox);

        // stairs top
        netherBrickStairsTopState = netherBrickStairsTopState.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        netherBrickStairsBotState = netherBrickStairsBotState.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        netherBrickStairsLeftState = netherBrickStairsLeftState.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        netherBrickStairsRightState = netherBrickStairsRightState.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x - 1, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, x + 1, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, x - 1, 5, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, x + 1, 5, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x - 1, 5, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x, 5, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, x + 1, 5, z + 1, boundingBox);
    }
}
