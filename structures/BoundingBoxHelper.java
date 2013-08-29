package gravestone.structures;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BoundingBoxHelper {

    private BoundingBoxHelper() {
    }

    /**
     * Return bounding Box for structure component
     *
     * @param direction Component direction
     * @param x X coord
     * @param y Y coord
     * @param z Z coord
     * @param xLength Compenent x axis length
     * @param height Component height
     * @param zLength Component z axis length
     */
    public static StructureBoundingBox getCorrectBox(int direction, int x, int y, int z, int xLength, int height, int zLength, int xShift) {
        int minX = 0;
        int maxX = 0;
        int minY = y;
        int maxY = y + height;
        int minZ = 0;
        int maxZ = 0;

        switch (direction) {
            case 0:
                minX = x - xShift;
                maxX = x - xShift + xLength;
                minZ = z;
                maxZ = z + zLength;
                break;

            case 1:
                minX = x - zLength;
                maxX = x;
                minZ = z - xShift;
                maxZ = z - xShift + xLength;
                break;

            case 2:
                minX = x - xShift;
                maxX = x - xShift + xLength;
                minZ = z - zLength;
                maxZ = z;
                break;

            case 3:
                minX = x;
                maxX = x + zLength;
                minZ = z - xShift;
                maxZ = z - xShift + xLength;
                break;
        }

        return new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the
     * supplied BoundingBox. (A median of all the levels in the BB's horizontal
     * rectangle).
     */
    public static int getAverageGroundLevel(World world, StructureBoundingBox boundingBox) {
        int height = 0;
        int count = 0;

        for (int z = boundingBox.minZ; z <= boundingBox.maxZ; ++z) {
            for (int x = boundingBox.minX; x <= boundingBox.maxX; ++x) {
                if (boundingBox.isVecInside(x, 64, z)) {
                    height += Math.max(world.getTopSolidOrLiquidBlock(x, z), world.provider.getAverageGroundLevel());
                    count++;
                }
            }
        }

        if (count == 0) {
            return -1;
        } else {
            return height / count;
        }
    }
}
