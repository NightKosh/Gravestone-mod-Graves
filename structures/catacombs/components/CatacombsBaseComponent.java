package gravestone.structures.catacombs.components;

import gravestone.core.GSStructures;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.catacombs.CatacombsStones;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class CatacombsBaseComponent extends ComponentGraveStone {

    private static final CatacombsStones cemeteryCatacombsStones = new CatacombsStones();
    protected int leftXEnd = 0;
    protected int rightXEnd = 0;
    protected int topXEnd = 0;
    protected int leftZEnd = 0;
    protected int rightZEnd = 0;
    protected int topZEnd = 0;
    protected int yEnd = 0;
    protected int xShift = 0;
    protected int zShift = 0;
    public boolean goTop = true;
    protected CatacombsBaseComponent prevComponent;
    protected CatacombsBaseComponent[] nextComponents;

    protected CatacombsBaseComponent(int direction) {
        super(direction);
        coordBaseMode = direction;
    }

    /*
     * return ground level at x z coordinates
     */
    protected int getGroundLevel(World world, int x, int z) {
        return world.getTopSolidOrLiquidBlock(x, z);
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
    public static int getLeftItemDirection(int direction) {
        if (direction == 0 || direction == 1) {
            direction += 1;

            if (direction > 3) {
                direction = 0;
            }
        } else {
            direction -= 1;

            if (direction < 0) {
                direction = 3;
            }
        }

        return direction;
    }

    /**
     * Return right direction for items
     *
     * @param direction Component direction
     */
    public static int getRightItemDirection(int direction) {
        if (direction == 0 || direction == 1) {
            direction -= 1;

            if (direction < 0) {
                direction = 3;
            }
        } else {
            direction += 1;

            if (direction > 3) {
                direction = 0;
            }
        }

        return direction;
    }

    /**
     * Return StructureGSCemeteryCatacombsStones instance
     */
    public static CatacombsStones getCemeteryCatacombsStones() {
        return cemeteryCatacombsStones;
    }

    /**
     * Is component can be places here
     *
     * @param boundingBox Component bounding box
     */
    public boolean canBePlacedHere(StructureBoundingBox boundingBox) {
        if (coordBaseMode == 0 || coordBaseMode == 2) {
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
    @Override
    protected void fillCurrentPositionBlocksDownwards(World world, int blockId, int metadata, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        while ((world.isAirBlock(x, y, z) || world.getBlockMaterial(x, y, z).isLiquid() || world.getBlockMaterial(x, y, z).isReplaceable()) && y > 1) {
            world.setBlock(x, y, z, blockId, metadata, 2);
            --y;
        }
    }

    /**
     * Return component direction
     */
    public int getDirection() {
        return coordBaseMode;
    }

    /**
     * Return valuable block
     */
    public static int getValuableBlock(Random random) {
        return GSStructures.VALUEBLE_BLOCKS[random.nextInt(GSStructures.VALUEBLE_BLOCKS.length)];
    }
}
