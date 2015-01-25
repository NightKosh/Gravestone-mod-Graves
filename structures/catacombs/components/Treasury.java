package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
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
public class Treasury extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 7;

    public Treasury(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goForward = false;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 2, 5, 3, 6);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 6, 0, 7, false, random, getCemeteryCatacombsStones());

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 1, 1, 1, 5, 3, 6, Blocks.web.getDefaultState(), false);

        //block ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 4, 1, 6, 4, 7, false, random, getCemeteryCatacombsStones());

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 2, 0, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 1, 2, 6, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 6, 3, 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 7, 6, 3, 7, false, random, getCemeteryCatacombsStones());

        // clear entrance
        this.fillWithAir(world, boundingBox, 2, 1, 1, 4, 3, 1);

        // block entrance
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 4, 3, 0, false, random, getCemeteryCatacombsStones());

        // nether entrance
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.nether_brick.getDefaultState(), false);

        // graves
        byte graveType = GraveStoneHelper.getGraveType(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0)), random, BlockGSGraveStone.EnumGraveType.ALL_GRAVES);
        IBlockState graveState = GSBlock.graveStone.getDefaultState();
        IBlockState leftGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState rightGraveState = graveState.withProperty(BlockGSGraveStone.FACING, this.getRightDirection(this.coordBaseMode));

        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 4, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 6, leftGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 2, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 4, rightGraveState, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 6, rightGraveState, graveType, sword, true);

        // TNT
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 1, 0, 3, Blocks.tnt.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 5, 1, 0, 5, Blocks.tnt.getDefaultState(), false);
        this.placeBlockAtCurrentPosition(world, Blocks.tnt.getDefaultState(), 0, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 6, 0, 3, Blocks.tnt.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 6, 0, 5, Blocks.tnt.getDefaultState(), false);
        this.placeBlockAtCurrentPosition(world, Blocks.tnt.getDefaultState(), 6, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 0, 6, 3, 0, 7, Blocks.tnt.getDefaultState(), false);

        // treasury chests
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 3, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 5, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 3, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 5, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 1, 6, this.coordBaseMode, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);

        // treasury column
        this.fillWithBlocks(world, boundingBox, 3, 1, 4, 3, 3, 4, getValuableBlock(random).getDefaultState(), false);

        return true;
    }
}
