package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.MobSpawnHelper;
import gravestone.structures.ObjectsGenerationHelper;
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
public class GraveCorridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 7;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public GraveCorridor(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goForward = true;
        frontXEnd = 1;
        frontZEnd = Z_LENGTH - 1;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 5, 3, 3);
        this.fillWithAir(world, boundingBox, 2, 1, 0, 4, 3, 0);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 1, 4, 0, 3, false, random, getCemeteryCatacombsStones());

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 2, 1, 2, 5, 3, 3, Blocks.web.getDefaultState(), false);
        // piles of bones
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 2, 5, 1, 3, false, random, getPileOfBonesSelector());

        // trap floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, GSBlock.trap.getDefaultState(), false);

        // neter floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 4, 5, 0, 4, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 1, 1, 0, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 1, 5, 0, 3, Blocks.nether_brick.getDefaultState(), false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 4, Blocks.nether_brick.getDefaultState(), false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 0, 4, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 1, 6, 4, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 4, 1, 3, 4, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 4, 5, 3, 4, Blocks.nether_brick.getDefaultState(), false);

        // graves
        int graveType = GraveStoneHelper.getGraveType(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0)), random, EnumGraveType.ALL_GRAVES);
        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
        IBlockState graveState = GSBlock.graveStone.getDefaultState();
        IBlockState leftGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState rightGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getRightDirection(this.coordBaseMode));
        GraveGenerationHelper.fillGraves(this, world, random, 1, 1, 1, 1, 1, 3, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.fillGraves(this, world, random, 5, 1, 1, 5, 1, 3, rightGraveState, graveType, sword, true);

        // chest
        if (random.nextInt(5) < 2) {
            ObjectsGenerationHelper.generateChest(this, world, random, 3, 1, 2, this.coordBaseMode, true, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        }

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        return true;
    }
}
