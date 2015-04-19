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
        EntityRegistry.registerGlobalEntityID(EntityZombieDog.class, ZOMBIE_DOG_NAME, EntityRegistry.findGlobalUniqueEntityId(), DOG_BACKGROUND_EGG_COLOR, ZOMBIE_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnZombieDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // zombie cat
        EntityRegistry.registerGlobalEntityID(EntityZombieCat.class, ZOMBIE_CAT_NAME, EntityRegistry.findGlobalUniqueEntityId(), CAT_BACKGROUND_EGG_COLOR, ZOMBIE_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnZombieCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skeleton dog
        EntityRegistry.registerGlobalEntityID(EntitySkeletonDog.class, SKELETON_DOG_NAME, EntityRegistry.findGlobalUniqueEntityId(), DOG_BACKGROUND_EGG_COLOR, SKELETON_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnSkeletonDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // skeleton cat
        EntityRegistry.registerGlobalEntityID(EntitySkeletonCat.class, SKELETON_CAT_NAME, EntityRegistry.findGlobalUniqueEntityId(), CAT_BACKGROUND_EGG_COLOR, SKELETON_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnSkeletonCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skullcrawler
        EntityRegistry.registerGlobalEntityID(EntitySkullCrawler.class, SKULL_CRAWLER_NAME, EntityRegistry.findGlobalUniqueEntityId(), SKELETON_BACKGROUND_EGG_COLOR, SPIDER_FOREGROUND_EGG_COLOR);
        // wither
        EntityRegistry.registerGlobalEntityID(EntityWitherSkullCrawler.class, WITHER_SKULL_CRAWLER_NAME, EntityRegistry.findGlobalUniqueEntityId(), 0, SPIDER_FOREGROUND_EGG_COLOR);
        EntityRegistry.addSpawn(EntityWitherSkullCrawler.class, 3, 1, 4, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
        // zombie
        EntityRegistry.registerGlobalEntityID(EntityZombieSkullCrawler.class, ZOMBIE_SKULL_CRAWLER_NAME, EntityRegistry.findGlobalUniqueEntityId(), ZOMBIE_BACKGROUND_EGG_COLOR, SPIDER_FOREGROUND_EGG_COLOR);


        EntityRegistry.registerGlobalEntityID(EntitySkeletonRaider.class, SKELETON_RAIDER_NAME, EntityRegistry.findGlobalUniqueEntityId(), SKELETON_BACKGROUND_EGG_COLOR, HORSE_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnSkeletonRaiders) {
            EntityRegistry.addSpawn(EntitySkeletonRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }
        EntityRegistry.registerGlobalEntityID(EntityZombieRaider.class, ZOMBIE_RAIDER_NAME, EntityRegistry.findGlobalUniqueEntityId(), ZOMBIE_BACKGROUND_EGG_COLOR, HORSE_FOREGROUND_EGG_COLOR);
        if (GSConfig.spawnZombieRaiders) {
            EntityRegistry.addSpawn(EntityZombieRaider.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.PLAINS));
        }

        EntityRegistry.registerGlobalEntityID(EntityRaven.class, RAVEN_NAME, EntityRegistry.findGlobalUniqueEntityId(), RAVEN_BACKGROUND_EGG_COLOR, SPIDER_FOREGROUND_EGG_COLOR);
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
