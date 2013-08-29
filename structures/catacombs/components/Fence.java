package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Fence extends CatacombsBaseComponent {

    private static enum FENCE_DIRECTION {

        LEFT,
        RIGHT
    }
    private final boolean haveEntrance;
    private final boolean haveCorners;

    public Fence(int direction, Random random, StructureBoundingBox structureBoundingBox, boolean haveEntrance, boolean haveCorners) {
        super(direction);
        boundingBox = structureBoundingBox;
        this.haveEntrance = haveEntrance;
        this.haveCorners = haveCorners;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        if (haveEntrance) {
            createEntrance(world, random);
        } else {
            createCenterFence(world, random);
        }

        for (int i = 0; i < 10; i++) {
            createPartOfFence(world, random, FENCE_DIRECTION.RIGHT, i * 4 + 48);
            createPartOfFence(world, random, FENCE_DIRECTION.LEFT, 41 - i * 4);
        }

        createGrate(world, 1);
        createGrate(world, 88);

        if (haveCorners) {
            createCornerFence(world, random, true);
            createCornerFence(world, random, false);
        }

        return true;
    }

    private void createPartOfFence(World world, Random random, FENCE_DIRECTION direction, int x) {
        int y;

        switch (direction) {
            case LEFT:
                createGrate(world, x);
                createGrate(world, x - 1);
                createGrate(world, x - 2);
                y = getGroundY(world, x - 3);

                if (checkGround(world, x - 3, y)) {
                    this.fillWithRandomizedBlocks(world, boundingBox, x - 3, y, 0, x - 3, y + 3, 0, false, random, getCemeteryCatacombsStones());
                }
                break;
            case RIGHT:
                createGrate(world, x);
                createGrate(world, x + 1);
                createGrate(world, x + 2);
                y = getGroundY(world, x + 3);

                if (checkGround(world, x + 3, y)) {
                    this.fillWithRandomizedBlocks(world, boundingBox, x + 3, y, 0, x + 3, y + 3, 0, false, random, getCemeteryCatacombsStones());
                }
                break;
        }
    }

    private void createCenterFence(World world, Random random) {
        int y = getGroundY(world, 42);

        if (checkGround(world, 42, y)) {
            fillWithRandomizedBlocks(world, boundingBox, 42, y, 0, 42, y + 3, 0, false, random, getCemeteryCatacombsStones());
        }

        createGrate(world, 43);
        createGrate(world, 44);
        createGrate(world, 45);
        createGrate(world, 46);
        y = getGroundY(world, 47);

        if (checkGround(world, 47, y)) {
            fillWithRandomizedBlocks(world, boundingBox, 47, y, 0, 47, y + 3, 0, false, random, getCemeteryCatacombsStones());
        }
    }

    private void createCornerFence(World world, Random random, boolean left) {
        byte x;

        if (left) {
            x = 89;
        } else {
            x = 0;
        }

        int y = getGroundY(world, x);

        if (checkGround(world, x, y)) {
            fillWithRandomizedBlocks(world, boundingBox, x, y, 0, x, y + 3, 0, false, random, getCemeteryCatacombsStones());
        }
    }

    private int getGroundY(World world, int x) {
        return world.getTopSolidOrLiquidBlock(getXWithOffset(x, 0), getZWithOffset(x, 0));
    }

    private boolean checkGround(World world, int x, int y) {
        int blockId = world.getBlockId(getXWithOffset(x, 0), y, getZWithOffset(x, 0));

        if (blockId > 0) {
            return (blockId != Block.waterStill.blockID && blockId != Block.lavaStill.blockID);
        } else {
            return true;
        }
    }

    private boolean checkGround(World world, int startX, int endX, int y) {
        int blockId;

        for (int x = startX; x <= endX; x++) {
            blockId = world.getBlockId(getXWithOffset(x, 0), y, getZWithOffset(x, 0));

            if (blockId > 0) {
                if (blockId == Block.waterStill.blockID || blockId == Block.lavaStill.blockID) {
                    return false;
                }
            }
        }

        return true;
    }

    private void createGrate(World world, int x) {
        int y = getGroundY(world, x);

        if (checkGround(world, x, y)) {
            this.fillWithBlocks(world, boundingBox, x, y, 0, x, y + 3, 0, Block.fenceIron.blockID, 0, false);
        }
    }

    private void createEntrance(World world, Random random) {
        int y = getAverageGroundLevel(world, BoundingBoxHelper.getCorrectBox(coordBaseMode, getXWithOffset(42, 0), 0, getZWithOffset(42, 0), 5, 7, 0, xShift));

        if (checkGround(world, 42, 47, y)) {
            // blocks
            this.fillWithRandomizedBlocks(world, boundingBox, 42, y, 0, 42, y + 3, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 47, y, 0, 47, y + 3, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 43, y + 4, 0, 43, y + 4, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 46, y + 4, 0, 46, y + 4, 0, false, random, getCemeteryCatacombsStones());
            
            // fence
            this.fillWithBlocks(world, boundingBox, 43, y, 0, 43, y + 3, 0, Block.fenceIron.blockID, 0, false);
            this.fillWithBlocks(world, boundingBox, 46, y, 0, 46, y + 3, 0, Block.fenceIron.blockID, 0, false);
            this.fillWithBlocks(world, boundingBox, 44, y + 3, 0, 45, y + 4, 0, Block.fenceIron.blockID, 0, false);
            
            // slabs
            this.fillWithMetadataBlocks(world, boundingBox, 44, y + 5, 0, 45, y + 5, 0, Block.stoneSingleSlab.blockID, 5, Block.stoneSingleSlab.blockID, 5, false);
            this.placeBlockAtCurrentPosition(world, Block.stoneSingleSlab.blockID, 5, 42, y + 4, 0, boundingBox);
            this.placeBlockAtCurrentPosition(world, Block.stoneSingleSlab.blockID, 5, 47, y + 4, 0, boundingBox);
        }
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the
     * supplied BoundingBox. (A median of all the levels in the BB's horizontal
     * rectangle).
     */
    protected int getMinimumGroundLevel(World world, StructureBoundingBox structureBoundingBox) {
        int height = 250;
        int curHeight = 0;

        for (int x = boundingBox.minX; x <= boundingBox.maxX; x++) {
            curHeight += Math.max(world.getTopSolidOrLiquidBlock(x, 0), world.provider.getAverageGroundLevel());

            if (curHeight < height) {
                height = curHeight;
            }
        }

        return height;
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the
     * supplied BoundingBox. (A median of all the levels in the BB's horizontal
     * rectangle).
     */
    protected int getAverageGroundLevel(World world, StructureBoundingBox structureBoundingBox) {
        int height = 0;
        int count = 0;

        for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; ++x) {
            height += Math.max(world.getTopSolidOrLiquidBlock(x, 0), world.provider.getAverageGroundLevel());
            count++;
        }

        if (count == 0) {
            return -1;
        } else {
            return height / count;
        }
    }
}
