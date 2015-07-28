package gravestone.core;

import gravestone.config.GSConfig;
import gravestone.entity.EntityRaven;
import gravestone.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import gravestone.entity.monster.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEntity {

    private static GSEntity instance;
    private static int mobId = 0;

    private GSEntity() {
        instance = this;
        getEntity();
    }

    public static GSEntity getInstance() {
        if (instance == null) {
            return new GSEntity();
        } else {
            return instance;
        }
    }

    public static final String SKELETON_NAME = "Skeleton";
    public static final String ZOMBIE_DOG_NAME = "GSZombieDog";
    public static final String ZOMBIE_CAT_NAME = "GSZombieCat";
    public static final String SKELETON_DOG_NAME = "GSSkeletonDog";
    public static final String SKELETON_CAT_NAME = "GSSkeletonCat";
    public static final String SKULL_CRAWLER_NAME = "GSSkullCrawler";
    public static final String WITHER_SKULL_CRAWLER_NAME = "GSWitherSkullCrawler";
    public static final String ZOMBIE_SKULL_CRAWLER_NAME = "GSZombieSkullCrawler";
    public static final String ZOMBIE_HORSE_NAME = "zombiehorse";
    public static final String SKELETON_HORSE_NAME = "skeletonhorse";
    public static final String SKELETON_RAIDER_NAME = "GSSkeletonRaider";
    public static final String ZOMBIE_RAIDER_NAME = "GSZombieRaider";
    public static final String RAVEN_NAME = "GSRaven";
    public static final String SPAWNER_HELPER_NAME = "GSSpawnerHelper";


    public static final String SKELETON_ID = ModInfo.ID + "." + SKELETON_NAME;
    public static final String ZOMBIE_DOG_ID = ModInfo.ID + "." + ZOMBIE_DOG_NAME;
    public static final String ZOMBIE_CAT_ID = ModInfo.ID + "." + ZOMBIE_CAT_NAME;
    public static final String SKELETON_DOG_ID = ModInfo.ID + "." + SKELETON_DOG_NAME;
    public static final String SKELETON_CAT_ID = ModInfo.ID + "." + SKELETON_CAT_NAME;
    public static final String SKULL_CRAWLER_ID = ModInfo.ID + "." + SKULL_CRAWLER_NAME;
    public static final String WITHER_SKULL_CRAWLER_ID = ModInfo.ID + "." + WITHER_SKULL_CRAWLER_NAME;
    public static final String ZOMBIE_SKULL_CRAWLER_ID = ModInfo.ID + "." + ZOMBIE_SKULL_CRAWLER_NAME;
    public static final String ZOMBIE_HORSE_ID = ModInfo.ID + "." + ZOMBIE_HORSE_NAME;
    public static final String SKELETON_HORSE_ID = ModInfo.ID + "." + SKELETON_HORSE_NAME;
    public static final String SKELETON_RAIDER_ID = ModInfo.ID + "." + SKELETON_RAIDER_NAME;
    public static final String ZOMBIE_RAIDER_ID = ModInfo.ID + "." + ZOMBIE_RAIDER_NAME;
    public static final String RAVEN_ID = ModInfo.ID + "." + RAVEN_NAME;

    // eggs colors
    public static final int ZOMBIE_BACKGROUND_EGG_COLOR = 44975;
    public static final int SKELETON_BACKGROUND_EGG_COLOR = 12698049;
    public static final int CAT_BACKGROUND_EGG_COLOR = 15720061;
    public static final int DOG_BACKGROUND_EGG_COLOR = 14144467;
    public static final int RAVEN_BACKGROUND_EGG_COLOR = 14144467;
    public static final int HORSE_FOREGROUND_EGG_COLOR = 15656192;
    public static final int SPIDER_FOREGROUND_EGG_COLOR = 11013646;
    public static final int ZOMBIE_FOREGROUND_EGG_COLOR = 7969893;
    public static final int SKELETON_FOREGROUND_EGG_COLOR = 4802889;
    public static final int RAVEN_FOREGROUND_EGG_COLOR = 14144467;

    public void getEntity() {
        // zombie dog
        registerModEntity(EntityZombieDog.class, ZOMBIE_DOG_NAME);
        if (GSConfig.spawnZombieDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // zombie cat
        registerModEntity(EntityZombieCat.class, ZOMBIE_CAT_NAME);
        if (GSConfig.spawnZombieCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skeleton dog
        registerModEntity(EntitySkeletonDog.class, SKELETON_DOG_NAME);
        if (GSConfig.spawnSkeletonDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // skeleton cat
        registerModEntity(EntitySkeletonCat.class, SKELETON_CAT_NAME);
        if (GSConfig.spawnSkeletonCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skull crawlers
        registerModEntity(EntitySkullCrawler.class, SKULL_CRAWLER_NAME);
        // wither
        registerModEntity(EntityWitherSkullCrawler.class, WITHER_SKULL_CRAWLER_NAME);
        EntityRegistry.addSpawn(EntityWitherSkullCrawler.class, 3, 1, 4, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
        // zombie
        registerModEntity(EntityZombieSkullCrawler.class, ZOMBIE_SKULL_CRAWLER_NAME);

        registerModEntity(EntityGSSkeleton.class, SKELETON_NAME);
        EntityRegistry.addSpawn(EntityGSSkeleton.class, 1, 1, 3, EnumCreatureType.MONSTER);
        //TODO addSpawn
        registerModEntity(EntityZombieHorse.class, ZOMBIE_HORSE_NAME);
        registerModEntity(EntitySkeletonHorse.class, SKELETON_HORSE_NAME);

        registerModEntity(EntityZombieRaider.class, ZOMBIE_RAIDER_NAME);
        if (GSConfig.spawnZombieRaiders) {
            EntityRegistry.addSpawn(EntityZombieRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }
        registerModEntity(EntitySkeletonRaider.class, SKELETON_RAIDER_NAME);
        if (GSConfig.spawnSkeletonRaiders) {
            EntityRegistry.addSpawn(EntitySkeletonRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }

        registerModEntity(EntityRaven.class, RAVEN_NAME);
        EntityRegistry.addSpawn(EntityRaven.class, 1, 3, 10, EnumCreatureType.AMBIENT);

        // ghosts
        // LostSoul
        //EntityRegistry.registerGlobalEntityID(EntityLostSoul.class, "GSLostSoul", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityLostSoul.class, 3, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.jungle, BiomeGenBase.jungleHills);

        // VengefulSpirit
        //EntityRegistry.registerGlobalEntityID(EntityVengefulSpirit.class, "GSVengefulSpirit", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityVengefulSpirit.class, 3, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.jungle, BiomeGenBase.jungleHills);


        registerModEntity(EntityGroupOfGravesMobSpawnerHelper.class, SPAWNER_HELPER_NAME);
    }

    private void registerModEntity(Class<? extends Entity> entityClass, String entityName) {
        registerModEntity(entityClass, entityName, mobId, ModInfo.ID, 100, 1, true);
        mobId++;
    }

    private void registerModEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }
}
