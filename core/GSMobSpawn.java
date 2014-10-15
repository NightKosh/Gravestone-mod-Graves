package gravestone.core;

import gravestone.core.logger.GSLogger;
import gravestone.block.enums.EnumGraves;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GraveStoneConfig;
import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSMobSpawn {

    public static final String WITHER_ID = "WitherBoss";
    //
    private static final int HELL_HEIGHT = 51;
    /**
     * Provides a mapping between entity classes and a string
     */
    public static Map<String, Constructor<EntityLiving>> mobNameToClassMapping = new HashMap();
    public static List<String> MOB_ID = new ArrayList(Arrays.asList("Zombie", "Skeleton"));
    public static List<String> DOG_ID = new ArrayList(Arrays.asList("GSZombieDog", "GSSkeletonDog"));
    public static List<String> CAT_ID = new ArrayList(Arrays.asList("GSZombieCat", "GSSkeletonCat"));
    public static List<String> HELL_MOB_ID = new ArrayList(Arrays.asList("PigZombie", "Skeleton"));
    // spawner mobs
    public static List<String> skeletonSpawnerMobs = new ArrayList(Arrays.asList(
            "Skeleton", "Skeleton", "Skeleton", "Skeleton",
            "GSSkeletonDog",
            "GSSkeletonCat"));
    public static List<String> zombieSpawnerMobs = new ArrayList(Arrays.asList(
            "Zombie", "Zombie", "Zombie", "Zombie",
            "GSZombieDog",
            "GSZombieCat"));
    // catacombs statues mobs
    public static List<String> catacombsStatuesMobs = new ArrayList(Arrays.asList(
            "Skeleton", "Zombie"));

    private GSMobSpawn() {
    }

    /**
     * Check can grave spawn hell creature or not
     *
     * @param world
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param z     Z coordinate
     */
    private static boolean canSpawnHellCreatures(World world, int x, int y, int z) {
        if (world != null) {
            return y < HELL_HEIGHT && world.getBlock(x, y - 1, z).equals(Blocks.nether_brick);
        } else {
            return false;
        }
    }

    /**
     * will create the entity from the internalID the first time it is accessed
     */
    public static Entity getMobEntity(World world, EnumGraves graveType, int x, int y, int z) {
        String id;

        switch (graveType) {
            case WOODEN_DOG_STATUE:
            case SANDSTONE_DOG_STATUE:
            case STONE_DOG_STATUE:
            case MOSSY_DOG_STATUE:
            case IRON_DOG_STATUE:
            case GOLDEN_DOG_STATUE:
            case DIAMOND_DOG_STATUE:
            case EMERALD_DOG_STATUE:
            case LAPIS_DOG_STATUE:
            case REDSTONE_DOG_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case QUARTZ_DOG_STATUE:
            case ICE_DOG_STATUE:
                id = getMobID(world.rand, EnumMobType.UNDEAD_DOGS);
                break;
            case WOODEN_CAT_STATUE:
            case SANDSTONE_CAT_STATUE:
            case STONE_CAT_STATUE:
            case MOSSY_CAT_STATUE:
            case IRON_CAT_STATUE:
            case GOLDEN_CAT_STATUE:
            case DIAMOND_CAT_STATUE:
            case EMERALD_CAT_STATUE:
            case LAPIS_CAT_STATUE:
            case REDSTONE_CAT_STATUE:
            case OBSIDIAN_CAT_STATUE:
            case QUARTZ_CAT_STATUE:
            case ICE_CAT_STATUE:
                id = getMobID(world.rand, EnumMobType.UNDEAD_CATS);
                break;
            case WOODEN_HORSE_STATUE:
            case SANDSTONE_HORSE_STATUE:
            case STONE_HORSE_STATUE:
            case MOSSY_HORSE_STATUE:
            case IRON_HORSE_STATUE:
            case GOLDEN_HORSE_STATUE:
            case DIAMOND_HORSE_STATUE:
            case EMERALD_HORSE_STATUE:
            case LAPIS_HORSE_STATUE:
            case REDSTONE_HORSE_STATUE:
            case OBSIDIAN_HORSE_STATUE:
            case QUARTZ_HORSE_STATUE:
            case ICE_HORSE_STATUE:
                return null;
            default:
                if (canSpawnHellCreatures(world, x, y, z) && world.rand.nextInt(10) == 0) {
                    id = getMobID(world.rand, EnumMobType.HELL_MOBS);

                    if (id.equals("Skeleton")) {
                        EntitySkeleton skeleton = getSkeleton(world);
                        skeleton.setSkeletonType(1);
                        return skeleton;
                    }
                } else {
                    id = getMobID(world.rand, EnumMobType.DEFAULT_MOBS);

                    if (id.equals("Skeleton")) {
                        return getSkeleton(world);
                    }
                }
        }

        EntityLiving entity = (EntityLiving) EntityList.createEntityByName(id, world);

        if (entity == null) {
            entity = getForeinMob(world, id);
        }

        try {
            entity.onSpawnWithEgg((IEntityLivingData) null);
        } catch (Exception e) {
            GSLogger.logError("getMobEntity exception with onSpawnWithEgg");
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * will create the entity from the internalID the first time it is accessed
     */
    public static Entity getMobEntityForSpawner(World world, EnumSpawner spawnerType, int x, int y, int z) {
        String mobId;

        switch (spawnerType) {
            case WITHER_SPAWNER:
                mobId = GSMobSpawn.WITHER_ID;
                break;
            case SKELETON_SPAWNER:
                mobId = skeletonSpawnerMobs.get(world.rand.nextInt(skeletonSpawnerMobs.size()));

                if (mobId.equals("Skeleton") && world.rand.nextInt(10) == 0) {
                    EntitySkeleton skeleton = getSkeleton(world);
                    skeleton.setSkeletonType(1);
                    return skeleton;
                }
                break;
            case ZOMBIE_SPAWNER:
            default:
                mobId = zombieSpawnerMobs.get(world.rand.nextInt(zombieSpawnerMobs.size()));
                break;
        }

        EntityLiving entity = (EntityLiving) EntityList.createEntityByName(mobId, world);

        if (entity == null) {
            entity = getForeinMob(world, mobId);
        }

        try {
            entity.onSpawnWithEgg((IEntityLivingData) null);
        } catch (Exception e) {
            GSLogger.logError("getMobEntity exception with onSpawnWithEgg");
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * Return Skeleton with bow/sword
     */
    private static EntitySkeleton getSkeleton(World world) {
        EntitySkeleton skeleton = (EntitySkeleton) EntityList.createEntityByName("Skeleton", world);

        if (world.rand.nextInt(2) == 0) {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword, 1, 0));
        } else {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.bow, 1, 0));
        }

        return skeleton;
    }

    /**
     * Return Skeleton with bow/sword
     */
    public static EntitySkeleton getSkeleton(World world, byte skeletonType) {
        EntitySkeleton skeleton = (EntitySkeleton) EntityList.createEntityByName("Skeleton", world);

        if (skeletonType == 0) {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.bow, 1, 0));
        } else {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword, 1, 0));
        }

        return skeleton;
    }

    public static boolean isWitherSkeleton(EntitySkeleton skeleton) {
        return skeleton.getSkeletonType() == 1;
    }

    /**
     * Create and return instance for forein mobs
     *
     * @param world
     * @param mobName
     */
    private static EntityLiving getForeinMob(World world, String mobName) {
        EntityLiving mob = null;

        try {
            mob = mobNameToClassMapping.get(mobName).newInstance(new Object[]{world});
        } catch (InstantiationException e) {
            GSLogger.logError("getForeinMob InstantiationException. mob name " + mobName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            GSLogger.logError("getForeinMob IllegalAccessException. mob name " + mobName);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            GSLogger.logError("getForeinMob InvocationTargetException. mob name " + mobName);
            e.getCause().printStackTrace();
        }

        return mob;
    }

    /**
     * Return random mob id from list
     *
     * @param random
     * @param mobType
     */
    public static String getMobID(Random random, EnumMobType mobType) {
        switch (mobType) {
            case HELL_MOBS:
                return HELL_MOB_ID.get(random.nextInt(HELL_MOB_ID.size()));
            case UNDEAD_DOGS:
                return DOG_ID.get(random.nextInt(DOG_ID.size()));
            case UNDEAD_CATS:
                return CAT_ID.get(random.nextInt(CAT_ID.size()));
            case DEFAULT_MOBS:
            default:
                return MOB_ID.get(random.nextInt(MOB_ID.size()));
        }
    }

    public static boolean spawnMob(World world, Entity mob, double x, double y, double z, boolean checkSpawn) {
        float rotation = world.rand.nextFloat() * 360.0F;
        return spawnMob(world, mob, x, y, z, rotation, checkSpawn);
    }

    /**
     * Spawn mob in world
     *
     * @param world World object
     * @param mob   Spawned mob
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param z     Z coordinate
     */
    public static boolean spawnMob(World world, Entity mob, double x, double y, double z, float rotation, boolean checkSpawn) {
        EntityLiving livingEntity = (EntityLiving) mob;
        boolean canSpawn = false;
        double xPosition = x + 0.5;
        double yPosition = y;
        double zPosition = z + 0.5;
        mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

        if (!checkSpawn || livingEntity.getCanSpawnHere()) {
            canSpawn = true;
        } else {
            if (!(mob instanceof EntityZombie)) {
                xPosition += 1;
                mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

                if (livingEntity.getCanSpawnHere()) {
                    canSpawn = true;
                } else {
                    xPosition -= 1;
                    zPosition += 1;
                    mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

                    if (livingEntity.getCanSpawnHere()) {
                        canSpawn = true;
                    } else {
                        zPosition -= 2;
                        mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

                        if (livingEntity.getCanSpawnHere()) {
                            canSpawn = true;
                        } else {
                            xPosition -= 1;
                            zPosition += 1;
                            mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

                            if (livingEntity.getCanSpawnHere()) {
                                canSpawn = true;
                            }
                        }
                    }
                }
            }
        }

        if (canSpawn) {
            xPosition = x + world.rand.nextFloat();
            yPosition = y + world.rand.nextFloat();
            zPosition = z + world.rand.nextFloat();
            world.spawnParticle("largesmoke", xPosition, yPosition + 2, zPosition, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", xPosition, yPosition + 1, zPosition, 0.0D, 0.0D, 0.0D);
            world.spawnEntityInWorld(mob);
            world.playAuxSFX(2004, (int) x, (int) y, (int) z, 0);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check spawn mob or
     */
    public static boolean checkChance(Random random) {
        return random.nextInt(100) < GraveStoneConfig.spawnChance;
    }

    /**
     * Return random mob for spawner
     */
    public static String getMobForSkeletonSpawner(Random random) {
        return skeletonSpawnerMobs.get(random.nextInt(skeletonSpawnerMobs.size()));
    }

    public static String getMobForZombieSpawner(Random random) {
        return zombieSpawnerMobs.get(random.nextInt(zombieSpawnerMobs.size()));
    }

    /**
     * Return random mob for spawner
     */
    public static String getMobForStatueSpawner(Random random) {
        return catacombsStatuesMobs.get(random.nextInt(catacombsStatuesMobs.size()));
    }

    public static void spawnCrawler(Entity entity, EntitySkullCrawler crawler) {
        if (entity.worldObj.rand.nextInt(10) == 0) {
            GSMobSpawn.spawnMob(entity.worldObj, crawler,
                    (int) Math.floor(entity.posX), entity.posY + 1.5, (int) Math.floor(entity.posZ),
                    entity.rotationYaw, false);
        }
    }

    public enum EnumMobType {

        DEFAULT_MOBS,
        HELL_MOBS,
        UNDEAD_DOGS,
        UNDEAD_CATS
    }
}
