package gravestone.structures;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MobSpawnHelper {

    private MobSpawnHelper() {
    }

    /**
     * Spawn bats
     *
     * @param world       World object
     * @param random
     * @param boundingBox Bounding box
     */
    public static void spawnBats(World world, Random random, StructureBoundingBox boundingBox) {
        EntityBat bat;
        int batsCount = 3 + random.nextInt(8);

        for (byte i = 0; i < batsCount; i++) {
            bat = new EntityBat(world);
            // getCenterX - func_180717_f
            Vec3i center = boundingBox.func_180717_f();
            bat.setLocationAndAngles(center.getX() - 1.5 + random.nextInt(5), center.getY(),
                    center.getZ() - 1.5 + random.nextInt(5), 0, 0);

            if (bat.getCanSpawnHere()) {
                world.spawnEntityInWorld(bat);
            }
        }
    }
}
