package GraveStone.structures;

import java.util.Random;
import GraveStone.ModGraveStone;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ComponentGSSingleGrave extends ComponentGSCemeteryCatacombs {

    public ComponentGSSingleGrave(int direction, Random random, int x, int z) {
        super(direction);
        boundingBox = new StructureBoundingBox(x + 7, 0, z + 7, x + 7, 240, z + 7);
    }

    public boolean addComponentParts(World world, Random random) {
        int positionX, positionZ, y;

        positionX = getXWithOffset(0, 0);
        positionZ = getZWithOffset(0, 0);
        y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;

        if (!isLiquidUnder(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
            placeGrave(world, random, 0, y, 0, ModGraveStone.graveStone.getMetaDirection(coordBaseMode), this.getGraveType(random, 0));
        }

        return true;
    }

    private static boolean isLiquidUnder(World world, int x, int minY, int z, int maxY) {
        int blockId;
        for (int y = maxY; y >= minY; y--) {
            blockId = world.getBlockId(x, y, z);

            blockId = world.getBlockId(x, y, z);
            if (blockId > 0 && ModGraveStone.graveStone.canPlaceBlockAt(blockId)) {
                return true;
            }
        }
        return false;
    }
}
