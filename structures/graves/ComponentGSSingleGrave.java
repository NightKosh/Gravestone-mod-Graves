package gravestone.structures.graves;

import gravestone.GraveStoneLogger;
import java.util.Random;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.GraveGenerationHelper;
import net.minecraft.nbt.NBTTagCompound;
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
        boundingBox = new StructureBoundingBox(x, 0, z, x, 240, z);
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
            GraveStoneLogger.logInfo("Generate grave at " + positionX + "x" + positionZ);
            GraveGenerationHelper.placeGrave(this, world, random, 0, y, 0, GraveStoneHelper.getMetaDirection(coordBaseMode), GraveStoneHelper.getGraveType(random, EnumGraveType.ALL_GRAVES), true);
        }

        return true;
    }

    @Override
    public NBTTagCompound func_143010_b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("id", "GSSingleGrave");
        nbttagcompound.setTag("BB", this.boundingBox.func_151535_h());
        nbttagcompound.setInteger("O", this.coordBaseMode);
        nbttagcompound.setInteger("GD", this.componentType);
        this.func_143012_a(nbttagcompound);
        return nbttagcompound;
    }
}
