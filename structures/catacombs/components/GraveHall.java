package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.init.Blocks;
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

    public GraveHall(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        xShift = 6;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = true;
        topXEnd = 6;
        topZEnd = Z_LENGTH;
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

        // nether floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 1, 1, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 0, 1, 15, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 5, 0, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 3, 11, 0, 15, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether floor lines
        this.fillWithBlocks(world, boundingBox, 2, 0, 1, 14, 0, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 6, 14, 0, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 12, 14, 0, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 17, 14, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);

        // floor entrance
        this.fillWithBlocks(world, boundingBox, 5, 0, 0, 11, 0, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 16, 11, 0, 18, Blocks.nether_brick, Blocks.nether_brick, false);

        // block foolr
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
        this.fillWithBlocks(world, boundingBox, 1, 6, 1, 1, 6, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 6, 1, 15, 6, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 6, 3, 5, 6, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 6, 3, 11, 6, 15, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether ceiling lines
        this.fillWithBlocks(world, boundingBox, 2, 6, 1, 14, 6, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 6, 14, 6, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 12, 14, 6, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 6, 17, 14, 6, 17, Blocks.nether_brick, Blocks.nether_brick, false);

        // ceiling entrance
        this.fillWithBlocks(world, boundingBox, 5, 4, 0, 11, 6, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 4, 16, 11, 6, 18, Blocks.nether_brick, Blocks.nether_brick, false);

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
        this.fillWithBlocks(world, boundingBox, 1, 1, 1, 1, 5, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 1, 15, 5, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 6, 1, 5, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 6, 15, 5, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 12, 1, 5, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 12, 15, 5, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 17, 1, 5, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 1, 17, 15, 5, 17, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether walls lines
        this.fillWithBlocks(world, boundingBox, 1, 3, 1, 1, 3, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 15, 3, 1, 15, 3, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 3, 1, 4, 3, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 3, 1, 14, 3, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 3, 17, 4, 3, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 3, 17, 14, 3, 17, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether wall entrance
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 6, 3, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 0, 11, 3, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 16, 6, 3, 18, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 16, 11, 3, 18, Blocks.nether_brick, Blocks.nether_brick, false);

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
        byte graveType = GraveStoneHelper.getGraveType(random, BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES);
        int metaLeft = GraveStoneHelper.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = GraveStoneHelper.getMetaDirection(getRightItemDirection(coordBaseMode));
        int metaTop = GraveStoneHelper.getMetaDirection(coordBaseMode);
        int metaBot = GraveStoneHelper.getMetaDirection(getInvertDirection(coordBaseMode));

        // left wall
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 5, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 8, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 10, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 13, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 16, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 2, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 5, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 8, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 10, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 13, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 4, 16, metaLeft, graveType, true);

        // right wall
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 2, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 5, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 8, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 10, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 13, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 1, 16, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 2, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 5, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 8, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 10, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 13, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 15, 4, 16, metaRight, graveType, true);

        // top walls
        GraveGenerationHelper.placeGrave(this, world, random, 3, 1, 17, metaTop, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 1, 17, metaTop, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 3, 4, 17, metaTop, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 4, 17, metaTop, graveType, true);

        // bot walls
        GraveGenerationHelper.placeGrave(this, world, random, 3, 1, 1, metaBot, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 1, 1, metaBot, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 3, 4, 1, metaBot, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 13, 4, 1, metaBot, graveType, true);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 7, 0, 6, 9, 0, 6, GSBlock.trap, GSBlock.trap, false);
        this.fillWithBlocks(world, boundingBox, 7, 0, 12, 9, 0, 12, GSBlock.trap, GSBlock.trap, false);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 7, 1, 0, 7, 1, 0, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 9, 2, 0, 9, 2, 0, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 8, 3, 1, 8, 3, 1, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 7, 2, 2, 7, 2, 2, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 8, 2, 2, 8, 2, 2, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 9, 1, 2, 9, 1, 2, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 7, 3, 16, 7, 3, 16, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 8, 2, 16, 8, 2, 16, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 7, 1, 17, 7, 1, 17, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 8, 3, 17, 8, 3, 17, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 9, 1, 17, 9, 1, 17, Blocks.web, Blocks.web, true);

        return true;
    }

    private void buildColumn(World world, int x, int z) {
        this.fillWithBlocks(world, boundingBox, x, 1, z, x, 5, z, Blocks.nether_brick, Blocks.nether_brick, false);
        int metaTop = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 2);
        int metaBot = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 3);
        int metaRight = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 1);
        int metaLeft = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 0);

        // stairs
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, x - 1, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, x, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, x + 1, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaLeft, x - 1, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaRight, x + 1, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, x - 1, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, x, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, x + 1, 1, z + 1, boundingBox);

        // stairs top
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot + 4, x - 1, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot + 4, x, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot + 4, x + 1, 5, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaLeft + 4, x - 1, 5, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaRight + 4, x + 1, 5, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop + 4, x - 1, 5, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop + 4, x, 5, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop + 4, x + 1, 5, z + 1, boundingBox);
    }
}
