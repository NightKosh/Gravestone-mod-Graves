package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Fence extends CatacombsBaseComponent {

    private final boolean haveEntrance;
    private final boolean haveCorners;
    public static final int ENTRANCE_HEIGHT = 7;

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
        int xPos = getXWithOffset(x, 0);
        int zPos = getZWithOffset(x, 0);
        int y = world.getTopSolidOrLiquidBlock(xPos, zPos);
        while (world.getBlock(xPos, y, zPos).getMaterial().equals(Material.wood) || world.getBlock(xPos, y, zPos).getMaterial().equals(Material.leaves)) {
            y--;
        }

        return y;
    }

    private boolean checkGround(World world, int x, int y) {
        Block block = world.getBlock(getXWithOffset(x, 0), y, getZWithOffset(x, 0));

        if (block != null) {
            return (!block.equals(Blocks.water) && !block.equals(Blocks.lava));
        } else {
            return true;
        }
    }

    private boolean checkGround(World world, int startX, int endX, int y) {
        Block block;

        for (int x = startX; x <= endX; x++) {
            block = world.getBlock(getXWithOffset(x, 0), y, getZWithOffset(x, 0));

            if (block != null) {
                if (block.equals(Blocks.water) || block.equals(Blocks.lava)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void createGrate(World world, int x) {
        int y = getGroundY(world, x);

        if (checkGround(world, x, y)) {
            this.fillWithBlocks(world, boundingBox, x, y, 0, x, y + 3, 0, Blocks.iron_bars, Blocks.iron_bars, false);
        }
    }

    private void createEntrance(World world, Random random) {
        int y = 0;
        for (int x = 42; x <= 47; x++) {
            int xPos = getXWithOffset(x, 0);
            int zPos = getZWithOffset(x, 0);
            int yPos = world.getTopSolidOrLiquidBlock(xPos, zPos);
            while (world.getBlock(xPos, y, zPos).getMaterial().equals(Material.wood) || world.getBlock(xPos, y, zPos).getMaterial().equals(Material.leaves)) {
                yPos--;
            }
            y += yPos;
        }
        y /= 6;

        if (checkGround(world, 42, 47, y)) {
            // blocks
            this.fillWithRandomizedBlocks(world, boundingBox, 42, y, 0, 42, y + 3, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 47, y, 0, 47, y + 3, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 43, y + 4, 0, 43, y + 4, 0, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 46, y + 4, 0, 46, y + 4, 0, false, random, getCemeteryCatacombsStones());

            // fence
            this.fillWithBlocks(world, boundingBox, 43, y, 0, 43, y + 3, 0, Blocks.iron_bars, Blocks.iron_bars, false);
            this.fillWithBlocks(world, boundingBox, 46, y, 0, 46, y + 3, 0, Blocks.iron_bars, Blocks.iron_bars, false);
            this.fillWithBlocks(world, boundingBox, 44, y + 3, 0, 45, y + 4, 0, Blocks.iron_bars, Blocks.iron_bars, false);

            // slabs
            this.fillWithMetadataBlocks(world, boundingBox, 44, y + 5, 0, 45, y + 5, 0, Blocks.stone_slab, 5, Blocks.stone_slab, 5, false);
            this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 42, y + 4, 0, boundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 5, 47, y + 4, 0, boundingBox);
        }
    }

    private static enum FENCE_DIRECTION {

        LEFT,
        RIGHT
    }
}
