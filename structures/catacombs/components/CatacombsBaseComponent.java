package gravestone.structures.catacombs.components;

import gravestone.core.GSStructures;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.catacombs.CatacombsLevel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class CatacombsBaseComponent extends ComponentGraveStone {

    protected static final float PILE_OF_BONES_GENERATION_CHANCE = 0.35F;

    public boolean goTop = true;
    protected int leftXEnd = 0;
    protected int rightXEnd = 0;
    protected int topXEnd = 0;
    protected int leftZEnd = 0;
    protected int rightZEnd = 0;
    protected int topZEnd = 0;
    protected int yEnd = 0;
    protected int xShift = 0;
    protected int zShift = 0;
    protected int level = 0;
    protected CatacombsBaseComponent prevComponent;
    protected CatacombsBaseComponent[] nextComponents;

    protected CatacombsBaseComponent(int direction) {
        this(direction, 0);
    }

    protected CatacombsBaseComponent(int direction, int level) {
        super(direction);
        //TODO
//        this.coordBaseMode = direction;
        this.level = level;
    }

    /**
     * Return left direction
     *
     * @param direction Component direction
     */
    public static int getLeftDirection(int direction) {
        direction -= 1;

        if (direction < 0) {
            direction = 3;
        }

        return direction;
    }

    /**
     * Return right direction
     *
     * @param direction Component direction
     */
    public static int getRightDirection(int direction) {
        direction += 1;

        if (direction > 3) {
            direction = 0;
        }

        return direction;
    }

    /**
     * Return Inverted direction
     *
     * @param direction Component direction
     */
    public static int getInvertDirection(int direction) {
        direction += 2;

        if (direction > 3) {
            direction -= 4;
        }

        return direction;
    }

    /**
     * Return left direction for items
     *
     * @param direction Component direction
     */
    // TODO - remove
//    public static int getLeftItemDirection(int direction) {
//        if (direction == 0 || direction == 1) {
//            direction += 1;
//
//            if (direction > 3) {
//                direction = 0;
//            }
//        } else {
//            direction -= 1;
//
//            if (direction < 0) {
//                direction = 3;
//            }
//        }
//
//        return direction;
//    }

    /**
     * Return right direction for items
     *
     * @param direction Component direction
     */
    // TODO - remove
//    public static int getRightItemDirection(int direction) {
//        if (direction == 0 || direction == 1) {
//            direction -= 1;
//
//            if (direction < 0) {
//                direction = 3;
//            }
//        } else {
//            direction += 1;
//
//            if (direction > 3) {
//                direction = 0;
//            }
//        }
//
//        return direction;
//    }

    /**
     * Return valuable block
     */
    public static Block getValuableBlock(Random random) {
        return GSStructures.VALUEBLE_BLOCKS[random.nextInt(GSStructures.VALUEBLE_BLOCKS.length)];
    }

    /*
     * return ground level at x z coordinates
     */
    protected int getGroundLevel(World world, int x, int z) {
        return 64;//TODO world.getTopSolidOrLiquidBlock(x, z);
    }

    protected int invertDirection(int direction) {
        return 0;
    }

    /**
     * Return Left X end coord for next component
     */
    public int getLeftXEnd() {
        return this.getXWithOffset(leftXEnd, leftZEnd);
    }

    /**
     * Return Left Z end coord for next component
     */
    public int getLeftZEnd() {
        return this.getZWithOffset(leftXEnd, leftZEnd);
    }

    /**
     * Return right X end coord for next component
     */
    public int getRightXEnd() {
        return this.getXWithOffset(rightXEnd, rightZEnd);
    }

    /**
     * Return right Z end coord for next component
     */
    public int getRightZEnd() {
        return this.getZWithOffset(rightXEnd, rightZEnd);
    }

    /**
     * Return forward Z end coord for next component
     */
    public int getTopZEnd() {
        return this.getZWithOffset(topXEnd, topZEnd);
    }

    /**
     * Return forward X end coord for next component
     */
    public int getTopXEnd() {
        return this.getXWithOffset(topXEnd, topZEnd);
    }

    /**
     * Return Y end coord for next component
     */
    public int getYEnd() {
        return this.boundingBox.minY + yEnd;
    }

    /**
     * Return StructureGSCemeteryCatacombsStones instance
     */
    public BlockSelector getCemeteryCatacombsStones() {
        return CatacombsLevel.getCatacombsStones(this.level);
    }

    /**
     * Is component can be places here
     *
     * @param boundingBox Component bounding box
     */
    public boolean canBePlacedHere(StructureBoundingBox boundingBox) {
        if (coordBaseMode == EnumFacing.SOUTH || coordBaseMode == EnumFacing.EAST) {
            return this.boundingBox.maxX > boundingBox.minX && this.boundingBox.minX < boundingBox.maxX
                    && this.boundingBox.maxZ - 1 > boundingBox.minZ && this.boundingBox.minZ + 1 < boundingBox.maxZ;
        } else {
            return this.boundingBox.maxX - 1 > boundingBox.minX && this.boundingBox.minX + 1 < boundingBox.maxX
                    && this.boundingBox.maxZ > boundingBox.minZ && this.boundingBox.minZ < boundingBox.maxZ;
        }
    }

    /**
     * Is component have only forvard exit
     */
    public boolean canGoOnlyTop() {
        return true;
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at
     * hitting anything else.
     */
    //TODO
//    @Override
//    protected void func_151554_b(World world, IBlockState blockState, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
//        int x = this.getXWithOffset(xCoord, zCoord);
//        int y = this.getYWithOffset(yCoord);
//        int z = this.getZWithOffset(xCoord, zCoord);
//
//        BlockPos pos = new BlockPos(x, y, z);
//        while ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().getMaterial().isLiquid() || world.getBlockState(pos).getBlock().getMaterial().isReplaceable()) && y > 1) {
//            world.setBlockState(pos, blockState, 2);
//            --y;
//        }
//    }

    /**
     * Return component direction
     */
    public int getDirection() {
        return 0; //TODO coordBaseMode;
    }
}
