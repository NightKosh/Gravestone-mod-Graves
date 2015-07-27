package gravestone.structures;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
     * @param x         X coord
     * @param y         Y coord
     * @param z         Z coord
     * @param xLength   Compenent x axis length
     * @param height    Component height
     * @param zLength   Component z axis length
     */
    public static StructureBoundingBox getCorrectBox(EnumFacing direction, int x, int y, int z, int xLength, int height, int zLength, int xShift) {
        int minX = 0;
        int maxX = 0;
        int minY = y;
        int maxY = y + height;
        int minZ = 0;
        int maxZ = 0;

        switch (direction) {
            case SOUTH:
                minX = x - xShift;
                maxX = x + xLength - xShift;
                minZ = z;
                maxZ = z + zLength;
                break;
            case NORTH:
                minX = x - xShift;
                maxX = x + xLength - xShift;
                minZ = z - zLength;
                maxZ = z;
                break;
            case EAST:
                minX = x;
                maxX = x + zLength;
                minZ = z - xShift;
                maxZ = z + xLength - xShift;
                break;
            case WEST:
                minX = x - zLength;
                maxX = x;
                minZ = z - xShift;
                maxZ = z + xLength - xShift;
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
                BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
                height += Math.max(world.getTopSolidOrLiquidBlock(pos).getY(), world.provider.getAverageGroundLevel());
                count++;
            }
        }

        if (count == 0) {
            return -1;
        } else {
            return height / count;
        }
    }
}
