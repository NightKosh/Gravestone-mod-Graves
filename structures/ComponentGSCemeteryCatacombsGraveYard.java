package GraveStone.structures;

import java.util.Random;
import GraveStone.ModGraveStone;
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
    public boolean addComponentParts(World world, Random random) {
        int graveMeta = ModGraveStone.graveStone.getMetaDirection(this.coordBaseMode);
        byte graveType;
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
                        graveType = ModGraveStone.graveStone.GENERATED_GRAVES[random.nextInt(ModGraveStone.graveStone.GENERATED_GRAVES.length)];
                        placeGrave(world, random, x, y, z, graveMeta, graveType);
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

            blockId = world.getBlockId(x, y, z);
            if (blockId > 0 && ModGraveStone.graveStone.canPlaceBlockAt(blockId)) {
                return true;
            }
        }
        return false;
    }
}
