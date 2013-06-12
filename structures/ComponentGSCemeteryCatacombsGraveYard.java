package GraveStone.structures;

import java.util.Random;
import GraveStone.block.BlockGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ComponentGSCemeteryCatacombsGraveYard extends ComponentGSCemeteryCatacombs {

    public ComponentGSCemeteryCatacombsGraveYard(int direction, Random random, StructureBoundingBox structureBoundingBox) {
        super(direction);
        boundingBox = structureBoundingBox;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int graveMeta = BlockGSGraveStone.getMetaDirection(this.coordBaseMode);
        int positionX, positionZ, y;

        for (int x = 0; x < 11; x += 2) {
            for (int z = 0; z < 11; z += 2) {
                if (random.nextDouble() < 0.05) {
                    positionX = getXWithOffset(x + 1, z + 1);
                    positionZ = getZWithOffset(x + 1, z + 1);
                    y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;
                    this.placeBlockAtCurrentPosition(world, Block.deadBush.blockID, 0, x + 1, y, z + 1, boundingBox);
                }

                if (random.nextInt(5) < 2) {
                    positionX = getXWithOffset(x, z);
                    positionZ = getZWithOffset(x, z);
                    y = world.getTopSolidOrLiquidBlock(positionX, positionZ) - boundingBox.minY;
                    if (!isLiquidUnder(world, positionX, boundingBox.minY + y, positionZ, boundingBox.maxY)) {
                        placeGrave(world, random, x, y, z, graveMeta, BlockGSGraveStone.getGraveType(random, 1));
                    }
                }
            }
        }

        return true;
    }

    private static boolean isLiquidUnder(World world, int x, int minY, int z, int maxY) {
        int blockId;
        for (int y = maxY; y >= minY; y--) {
            blockId = world.getBlockId(x, y, z);
            if (blockId > 0 && BlockGSGraveStone.canPlaceBlockAt(blockId)) {
                return true;
            }
        }
        return false;
    }
}
