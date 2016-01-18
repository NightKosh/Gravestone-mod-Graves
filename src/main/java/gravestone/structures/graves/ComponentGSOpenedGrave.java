package gravestone.structures.graves;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.core.GSBlock;
import gravestone.core.logger.GSLogger;
import gravestone.helper.GraveGenerationHelper.EnumGraveTypeByEntity;
import gravestone.helper.GraveInventoryHelper;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSOpenedGrave extends ComponentGraveStone {
    protected ComponentGSOpenedGrave(int componentType, EnumFacing facing, Random random, int x, int z) {
        super(componentType, facing);
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, 0, z, 1, 240, 3, 0);
    }

    @Override
    public boolean addComponentParts(World world, Random random) {
        int gravePositionX, gravePositionZ, y;
        gravePositionX = getXWithOffset(0, 2);
        gravePositionZ = getZWithOffset(0, 2);
        y = world.getTopSolidOrLiquidBlock(new BlockPos(gravePositionX, 0, gravePositionZ)).getY() - boundingBox.minY;

        if (GraveGenerationHelper.canPlaceGrave(world, gravePositionX, boundingBox.minY + y, gravePositionZ, boundingBox.maxY)) {
            GSLogger.logInfo("Generate opened grave at " + gravePositionX + "x" + gravePositionZ);

            GraveGenerationHelper.placeGrave(this, world, random, 0, y, 2,
                    GSBlock.graveStone.getDefaultState().withProperty(BlockGSGraveStone.FACING, this.coordBaseMode.getOpposite()),
                    null, EnumGraveTypeByEntity.HUMAN_GRAVES, GraveInventoryHelper.GraveContentType.RANDOM);
                    //, GraveInventory.GraveCorpseContentType.EMPTY); TODO

            this.fillWithAir(world, this.boundingBox, 0, y - 1, 0, 0, y + 1, 1);
            ObjectsGenerationHelper.generatePileOfBones(this, world, 0, y - 2, 0, this.coordBaseMode, EnumPileOfBones.PILE_OF_BONES);
            ObjectsGenerationHelper.generatePileOfBones(this, world, 0, y - 2, 1, this.coordBaseMode, EnumPileOfBones.PILE_OF_BONES_WITH_SKULL);
        }

        return true;
    }
}
