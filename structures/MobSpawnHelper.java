package gravestone.structures;

import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

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
     * @param world World object
     * @param random
     * @param boundingBox Bounding box
     */
    public static void spawnBats(World world, Random random, StructureBoundingBox boundingBox) {
        EntityBat bat;
        EntityLiving livingEntity;
        int batsCount = 3 + random.nextInt(8);

        for (byte i = 0; i < batsCount; i++) {
            bat = new EntityBat(world);
            //TODO
//            bat.setLocationAndAngles(boundingBox.getCenterX() - 1.5 + random.nextInt(5), boundingBox.getCenterY(),
//                    boundingBox.getCenterZ() - 1.5 + random.nextInt(5), 0.0F, 0.0F);
            livingEntity = (EntityLiving) bat;

            if (livingEntity.getCanSpawnHere()) {
                world.spawnEntityInWorld(bat);
            }
        }
    }
}
