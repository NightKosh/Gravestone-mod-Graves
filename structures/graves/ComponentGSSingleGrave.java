package GraveStone.structures.graves;

import java.util.Random;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.structures.ComponentGraveStone;
import GraveStone.structures.GraveGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSSingleGrave extends ComponentGraveStone {

    public ComponentGSSingleGrave(int direction, Random random, int x, int z) {
        super(direction);
        boundingBox = new StructureBoundingBox(x + 7, 0, z + 7, x + 7, 240, z + 7);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int positionX, positionZ, y;

        positionX = getXWithOffset(0, 0);
        positionZ = getZWithOffset(0, 0);
        y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;

        if (canPlaceGrave(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
            System.out.println("Grave " + positionX + "x" + positionZ);
            GraveGenerationHelper.placeGrave(this, world, random, 0, y, 0, BlockGSGraveStone.getMetaDirection(coordBaseMode), BlockGSGraveStone.getGraveType(random, 0));
        }

        return true;
    }

    /**
     * Check can be grave placed here
     * @param world World object
     * @param x X coord
     * @param minY Min y coord
     * @param z Z coord
     * @param maxY Max y coord
     */
    private static boolean canPlaceGrave(World world, int x, int minY, int z, int maxY) {
        int blockId;
        for (int y = maxY; y >= minY - 1; y--) {
            blockId = world.getBlockId(x, y, z);
            if (blockId > 0) {
                if (blockId == Block.waterStill.blockID || blockId == Block.lavaStill.blockID) {
                    return false;
                } else if (BlockGSGraveStone.canPlaceBlockAt(blockId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
