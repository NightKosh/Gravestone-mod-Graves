package gravestone.structures.graves;

import gravestone.block.BlockGSGraveStone;
import gravestone.core.GSBlock;
import gravestone.core.logger.GSLogger;

import java.util.Random;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.GraveGenerationHelper;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
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
        y = 100;//TODO world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;

        if (GraveGenerationHelper.canPlaceGrave(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
            GSLogger.logInfo("Generate grave at " + positionX + "x" + positionZ);

            byte graveType = GraveStoneHelper.getGraveType(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(y), this.getZWithOffset(0, 0)), random, EnumGraveType.ALL_GRAVES);
            Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
            GraveGenerationHelper.placeGrave(this, world, random, 0, y, 0,
                    GSBlock.graveStone.getDefaultState().withProperty(BlockGSGraveStone.FACING, this.coordBaseMode), graveType, sword, true);
        }

        return true;
    }

    @Override
    public NBTTagCompound func_143010_b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("id", "GSSingleGrave");
        nbttagcompound.setTag("BB", this.boundingBox.func_151535_h());
        nbttagcompound.setInteger("O", this.coordBaseMode == null ? -1 : this.coordBaseMode.getHorizontalIndex());
        nbttagcompound.setInteger("GD", this.componentType);
        this.writeStructureToNBT(nbttagcompound);
        return nbttagcompound;
    }
}
