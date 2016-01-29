package nightkosh.gravestone.core;

import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TimeHelper {
//TODO
//    public static final int PRE_NIGHT = 12000;
//    public static final int NIGHT = 14000;
//    public static final int PRE_MORNING = 22500;
//
//    public static final int FOG_START_TIME = 12000;
//    public static final int FOG_END_TIME = 24000;
//
//    public static final int SUN_SET = 13000;
//    public static final int SUN_RISING = 23000;

    public static final int GRAVE_SPAWN_START_TIME = 13500;
    public static final int GRAVE_SPAWN_END_TIME = 22500;

    private static boolean isGraveSpawnTime;


    public static long getDayTime(World world) {
        return world.getWorldTime() % 24000;
    }
//TODO
//    public static long getDayTime(long time) {
//        return time % 24000;
//    }
//
//    public static boolean isGraveSpawnTime() {
//        return isGraveSpawnTime;
//    }

    public static void setIsGraveSpawnTime(boolean isValidTime) {
        isGraveSpawnTime = isValidTime;
    }

    public static void updateIsGraveSpawnTime(World world) {
        long time = TimeHelper.getDayTime(world);

        setIsGraveSpawnTime(time > GRAVE_SPAWN_START_TIME && time < GRAVE_SPAWN_END_TIME || world.isThundering());
    }
//TODO
//    public static boolean isFogTime(World world) {
//        if (world.isRaining()) {
//            return false;
//        } else {
//            long dayTime = getDayTime(world);
//            return dayTime > FOG_START_TIME && dayTime < FOG_END_TIME;
//        }
//    }
}
