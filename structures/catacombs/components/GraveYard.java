package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.structures.GraveGenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveYard extends CatacombsBaseComponent {

    public GraveYard(int direction, Random random, StructureBoundingBox structureBoundingBox) {
        super(direction);
        boundingBox = structureBoundingBox;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int graveMeta = 0; //TODO GraveStoneHelper.getMetaDirection(this.coordBaseMode);
        int positionX, positionZ, y;

        for (int x = 0; x < 11; x += 2) {
            for (int z = 0; z < 11; z += 2) {
                if (random.nextDouble() < 0.05) {
                    positionX = getXWithOffset(x + 1, z + 1);
                    positionZ = getZWithOffset(x + 1, z + 1);
                    //TODO
//                    y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;
//                    this.placeBlockAtCurrentPosition(world, Blocks.deadbush, 0, x + 1, y, z + 1, boundingBox);
                }

                if (random.nextInt(5) < 2) {
                    positionX = getXWithOffset(x, z);
                    positionZ = getZWithOffset(x, z);
                    //TODO
//                    y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;
//
//                    if (GraveGenerationHelper.canPlaceGrave(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
//                        byte graveType = GraveStoneHelper.getGraveType(world, this.getXWithOffset(0, 0), this.getZWithOffset(0, 0), random, BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES);
//                        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
//                        GraveGenerationHelper.placeGrave(this, world, random, x, y, z, graveMeta, graveType, sword, false);
//                    }
                }
            }
        }

        return true;
    }
}
