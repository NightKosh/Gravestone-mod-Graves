package GraveStone;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GraveStoneMobSpawn {

    public static final String[] MOB_ID = {"Zombie", "Skeleton"};
    public static final String[] DOG_ID = {"GSZombieDog", "GSSkeletonDog"};
    public static final String[] CAT_ID = {"GSZombieCat", "GSSkeletonCat"};
    public static final String[] HELL_MOB_ID = {"PigZombie", "Skeleton"};

    /* 
     * Check can grave spawn hell creature or not
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
    public static Entity getMobEntity(World world, byte graveType, int x, int y, int z) {
        String id;

        switch (graveType) {
            case 3:
                id = getMobID(world.rand, 2);
                break;
            case 4:
                id = getMobID(world.rand, 3);
                break;
            default:
                if (canSpawnHellCreatures(world, x, y, z) && world.rand.nextInt(20) == 0) {
                    id = getMobID(world.rand, 1);
                    if (id.equals("Skeleton")) {
                        EntitySkeleton skeleton = (EntitySkeleton) EntityList.createEntityByName(id, world);
                        skeleton.setSkeletonType(1);
                        if (world.rand.nextInt(2) == 0) {
                            skeleton.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone));
                        }
                        return skeleton;
                    }
                } else {
                    id = getMobID(world.rand, 0);
                }
        }

        Entity entity = EntityList.createEntityByName(id, world);

        return entity;
    }

    /*
     * return random mob id from list
     */
    public static String getMobID(Random random, int creatureType) {
        switch (creatureType) {
            case 0:
                return MOB_ID[random.nextInt(MOB_ID.length)];
            case 1:
                return HELL_MOB_ID[random.nextInt(HELL_MOB_ID.length)];
            case 2:
                return DOG_ID[random.nextInt(DOG_ID.length)];
            case 3:
                return CAT_ID[random.nextInt(CAT_ID.length)];
            default:
                return MOB_ID[random.nextInt(MOB_ID.length)];
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
            if (mob instanceof EntitySkeleton && ((EntitySkeleton) mob).getSkeletonType() == 1) {
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
}
