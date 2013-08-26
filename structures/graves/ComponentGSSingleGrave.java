package GraveStone.structures.graves;

import java.util.Random;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.block.BlockGSGraveStone.EnumGraveType;
import GraveStone.structures.ComponentGraveStone;
import GraveStone.structures.GraveGenerationHelper;
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

        if (GraveGenerationHelper.canPlaceGrave(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
            System.out.println("Grave " + positionX + "x" + positionZ);
            GraveGenerationHelper.placeGrave(this, world, random, 0, y, 0, BlockGSGraveStone.getMetaDirection(coordBaseMode), BlockGSGraveStone.getGraveType(random, EnumGraveType.ALL_GRAVES), true);
        }

        return true;
    }
}
