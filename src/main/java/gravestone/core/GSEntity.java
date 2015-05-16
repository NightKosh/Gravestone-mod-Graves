package gravestone.core;

import gravestone.config.GSConfig;
import gravestone.entity.EntityRaven;
import gravestone.entity.monster.*;
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

    public static final String ZOMBIE_DOG_NAME = "GSZombieDog";
    public static final String ZOMBIE_CAT_NAME = "GSZombieCat";
    public static final String SKELETON_DOG_NAME = "GSSkeletonDog";
    public static final String SKELETON_CAT_NAME = "GSSkeletonCat";
    public static final String SKULL_CRAWLER_NAME = "GSSkullCrawler";
    public static final String WITHER_SKULL_CRAWLER_NAME = "GSWitherSkullCrawler";
    public static final String ZOMBIE_SKULL_CRAWLER_NAME = "GSZombieSkullCrawler";
    public static final String ZOMBIE_HORSE_NAME = "GSZombieHorse";
    public static final String SKELETON_HORSE_NAME = "GSSkeletonHorse";
    public static final String SKELETON_RAIDER_NAME = "GSSkeletonRaider";
    public static final String ZOMBIE_RAIDER_NAME = "GSZombieRaider";
    public static final String RAVEN_NAME = "GSRaven";

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
        EntityRegistry.registerModEntity(EntityZombieDog.class, ZOMBIE_DOG_NAME, 0, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnZombieDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // zombie cat
        EntityRegistry.registerModEntity(EntityZombieCat.class, ZOMBIE_CAT_NAME, 1, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnZombieCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skeleton dog
        EntityRegistry.registerModEntity(EntitySkeletonDog.class, SKELETON_DOG_NAME, 2, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnSkeletonDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // skeleton cat
        EntityRegistry.registerModEntity(EntitySkeletonCat.class, SKELETON_CAT_NAME, 3, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnSkeletonCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skullcrawlers
        EntityRegistry.registerModEntity(EntitySkullCrawler.class, SKULL_CRAWLER_NAME, 4, ModInfo.ID, 100, 1, true);
        // wither
        EntityRegistry.registerModEntity(EntityWitherSkullCrawler.class, WITHER_SKULL_CRAWLER_NAME, 5, ModInfo.ID, 100, 1, true);
        EntityRegistry.addSpawn(EntityWitherSkullCrawler.class, 3, 1, 4, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
        // zombie
        EntityRegistry.registerModEntity(EntityZombieSkullCrawler.class, ZOMBIE_SKULL_CRAWLER_NAME, 6, ModInfo.ID, 100, 1, true);

        //TODO addSpawn
        EntityRegistry.registerModEntity(EntityZombieHorse.class, ZOMBIE_HORSE_NAME, 7, ModInfo.ID, 100, 1, true);
        EntityRegistry.registerModEntity(EntitySkeletonHorse.class, SKELETON_HORSE_NAME, 8, ModInfo.ID, 100, 1, true);

        EntityRegistry.registerModEntity(EntityZombieRaider.class, ZOMBIE_RAIDER_NAME, 10, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnZombieRaiders) {
            EntityRegistry.addSpawn(EntityZombieRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }
        EntityRegistry.registerModEntity(EntitySkeletonRaider.class, SKELETON_RAIDER_NAME, 9, ModInfo.ID, 100, 1, true);
        if (GSConfig.spawnSkeletonRaiders) {
            EntityRegistry.addSpawn(EntitySkeletonRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }

        EntityRegistry.registerModEntity(EntityRaven.class, RAVEN_NAME, 11, ModInfo.ID, 100, 1, true);
        EntityRegistry.addSpawn(EntityRaven.class, 1, 3, 10, EnumCreatureType.AMBIENT);

        // ghosts
        // LostSoul
        //EntityRegistry.registerGlobalEntityID(EntityLostSoul.class, "GSLostSoul", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityLostSoul.class, 3, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.jungle, BiomeGenBase.jungleHills);

        // VengefulSpirit
        //EntityRegistry.registerGlobalEntityID(EntityVengefulSpirit.class, "GSVengefulSpirit", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityVengefulSpirit.class, 3, 1, 3, EnumCreatureType.MONSTER, BiomeGenBase.jungle, BiomeGenBase.jungleHills);
    }
}
