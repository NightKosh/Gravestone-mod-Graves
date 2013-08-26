package GraveStone;

import GraveStone.block.EnumGraves;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GraveStoneMobSpawn {

    /**
     * Provides a mapping between entity classes and a string
     */
    private static Map<String, Constructor<EntityLiving>> mobNameToClassMapping = new HashMap();
    private static ArrayList<String> MOB_ID = new ArrayList(Arrays.asList("Zombie", "Skeleton"));
    private static ArrayList<String> DOG_ID = new ArrayList(Arrays.asList("GSZombieDog", "GSSkeletonDog"));
    private static ArrayList<String> CAT_ID = new ArrayList(Arrays.asList("GSZombieCat", "GSSkeletonCat"));
    private static ArrayList<String> HELL_MOB_ID = new ArrayList(Arrays.asList("PigZombie", "Skeleton"));

    public enum EnumMobType {

        DEFAULT_MOBS,
        HELL_MOBS,
        UNDEAD_DOGS,
        UNDEAD_CATS
    }
    // catacombs spawner mobs
    public static ArrayList<String> catacombsSpawnerMobs = new ArrayList(Arrays.asList(
            "Skeleton", "Spider", "Zombie",
            "Skeleton", "Spider", "Zombie",
            "Skeleton", "Spider", "Zombie",
            "GSZombieDog", "GSZombieCat",
            "GSSkeletonDog", "GSSkeletonCat"));
    // catacombs statues mobs
    public static ArrayList<String> catacombsStatuesMobs = new ArrayList(Arrays.asList(
            "Skeleton", "Zombie"));
    // mo_creatures mobs classes
    private static final String MO_CREATURES_S_SKELETON = "drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton";
    private static final String MO_CREATURES_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityWraith";
    private static final String MO_CREATURES_F_WRAITH = "drzhark.mocreatures.entity.monster.MoCEntityFlameWraith";
    private static final String MO_CREATURES_SCORPIONS = "drzhark.mocreatures.entity.monster.MoCEntityScorpion";
    // twilight forest mobs classes
    private static final String TWILIGHT_WRAITH = "twilightforest.entity.EntityTFWraith";

    /**
     * Check can grave spawn hell creature or not
     *
     * @param world
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    private static boolean canSpawnHellCreatures(World world, int x, int y, int z) {
        if (world != null) {
            return y < 51 && world.getBlockId(x, y - 1, z) == Block.netherBrick.blockID;
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
            case DOG_STATUE:
                id = getMobID(world.rand, EnumMobType.UNDEAD_DOGS);
                break;

            case CAT_STATUE:
                id = getMobID(world.rand, EnumMobType.UNDEAD_CATS);
                break;

            default:
                if (canSpawnHellCreatures(world, x, y, z) && world.rand.nextInt(20) == 0) {
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
            entity.func_110161_a((EntityLivingData) null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * Return Skeleton with bow/sword
     *
     * @param world
     * @return
     */
    private static EntitySkeleton getSkeleton(World world) {
        EntitySkeleton skeleton = (EntitySkeleton) EntityList.createEntityByName("Skeleton", world);

        if (world.rand.nextInt(2) == 0) {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone.itemID, 1, 0));
        } else {
            skeleton.setCurrentItemOrArmor(0, new ItemStack(Item.bow.itemID, 1, 0));
        }

        return skeleton;
    }

    /**
     * Return constructor for forein mobs classes based on class path
     *
     * @param path Class Path with name
     */
    private static Constructor getForeinMobConstructor(String path) {
        Constructor<EntityLiving> constructor = null;

        try {
            Class mobClass = Class.forName(path);
            constructor = mobClass.getConstructor(World.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return constructor;
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
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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

    /**
     * Spawn mob in world
     *
     * @param world World object
     * @param entity Spawned mob
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public static boolean spawnMob(World world, Entity mob, int x, int y, int z) {
        EntityLiving livingEntity = (EntityLiving) mob;
        float rotation = world.rand.nextFloat() * 360.0F;
        boolean canSpawn = false;
        double xPosition = x + 0.5;
        double yPosition = y;
        double zPosition = z + 0.5;
        mob.setLocationAndAngles(xPosition, yPosition, zPosition, rotation, 0.0F);

        if (livingEntity.getCanSpawnHere()) {
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
            world.playAuxSFX(2004, x, y, z, 0);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check spawn mob or
     */
    public static boolean checkChance(Random random) {
        return random.nextInt(40) == 13;
    }

    /**
     * Add Mo'creatures mobs to mob list
     */
    public static void addMoCreaturesMobs() {
        Constructor<EntityLiving> constructor = getForeinMobConstructor(MO_CREATURES_S_SKELETON);

        if (constructor != null) {
            MOB_ID.add("SilverSkeleton");
            mobNameToClassMapping.put("SilverSkeleton", constructor);
            catacombsSpawnerMobs.add("SilverSkeleton");
            catacombsStatuesMobs.add("SilverSkeleton");
        }

        constructor = getForeinMobConstructor(MO_CREATURES_WRAITH);

        if (constructor != null) {
            MOB_ID.add("Wraith");
            mobNameToClassMapping.put("Wraith", constructor);
            catacombsSpawnerMobs.add("Wraith");
            catacombsStatuesMobs.add("Wraith");
        }

        constructor = getForeinMobConstructor(MO_CREATURES_F_WRAITH);

        if (constructor != null) {
            HELL_MOB_ID.add("FlameWraith");
            mobNameToClassMapping.put("FlameWraith", constructor);
            catacombsStatuesMobs.add("FlameWraith");
        }

        if (getForeinMobConstructor(MO_CREATURES_SCORPIONS) != null) {
            catacombsSpawnerMobs.add("Scorpion");
        }
    }

    /**
     * Add Mo'creatures mobs to mob list
     */
    public static void addTwilightForestMobs() {
        Constructor<EntityLiving> constructor = getForeinMobConstructor(TWILIGHT_WRAITH);

        if (constructor != null) {
            MOB_ID.add("Twilight Wraith");
            mobNameToClassMapping.put("Twilight Wraith", constructor);
            catacombsSpawnerMobs.add("Twilight Wraith");
            catacombsStatuesMobs.add("Twilight Wraith");
        }
    }

    /**
     * Return random mob for spawner
     */
    public static String getMobForSpawner(Random random) {
        return catacombsSpawnerMobs.get(random.nextInt(catacombsSpawnerMobs.size()));
    }

    /**
     * Return random mob for spawner
     */
    public static String getMobForStatueSpawner(Random random) {
        return catacombsStatuesMobs.get(random.nextInt(catacombsStatuesMobs.size()));
    }
}
