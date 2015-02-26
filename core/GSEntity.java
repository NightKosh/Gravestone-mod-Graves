package gravestone.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import gravestone.config.GraveStoneConfig;
import gravestone.entity.monster.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;

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
    public static final String SKEKETON_DOG_NAME = "GSSkeletonDog";
    public static final String SKEKETON_CAT_NAME = "GSSkeletonCat";
    public static final String SKULL_CRAWLER_NAME = "GSSkullCrawler";
    public static final String WITHER_SKULL_CRAWLER_NAME = "GSWitherSkullCrawler";
    public static final String ZOMBIE_SKULL_CRAWLER_NAME = "GSZombieSkullCrawler";
    
    public void getEntity() {
        // zombie dog
        EntityRegistry.registerModEntity(EntityZombieDog.class, ZOMBIE_DOG_NAME, 0, ModInfo.ID, 100, 1, true);
        if (GraveStoneConfig.spawnZombieDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // zombie cat
        EntityRegistry.registerModEntity(EntityZombieCat.class, ZOMBIE_CAT_NAME, 1, ModInfo.ID, 100, 1, true);
        if (GraveStoneConfig.spawnZombieCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skeleton dog
        EntityRegistry.registerModEntity(EntitySkeletonDog.class, SKEKETON_DOG_NAME, 2, ModInfo.ID, 100, 1, true);
        if (GraveStoneConfig.spawnSkeletonDogs) {
            EntityRegistry.addSpawn(EntityZombieDog.class, 2, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
        }

        // skeleton cat
        EntityRegistry.registerModEntity(EntitySkeletonCat.class, SKEKETON_CAT_NAME, 3, ModInfo.ID, 100, 1, true);
        if (GraveStoneConfig.spawnSkeletonCats) {
            EntityRegistry.addSpawn(EntityZombieCat.class, 2, 1, 1, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
        }

        // skullcrawler
        EntityRegistry.registerModEntity(EntitySkullCrawler.class, SKULL_CRAWLER_NAME, 4, ModInfo.ID, 100, 1, true);
        // wither
        EntityRegistry.registerModEntity(EntityWitherSkullCrawler.class, WITHER_SKULL_CRAWLER_NAME, 5, ModInfo.ID, 100, 1, true);
        EntityRegistry.addSpawn(EntityWitherSkullCrawler.class, 3, 1, 4, EnumCreatureType.monster, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.NETHER));
        // zombie
        EntityRegistry.registerModEntity(EntityZombieSkullCrawler.class, ZOMBIE_SKULL_CRAWLER_NAME, 6, ModInfo.ID, 100, 1, true);

        // ghosts
        // LostSoul
        //EntityRegistry.registerGlobalEntityID(EntityLostSoul.class, "GSLostSoul", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityLostSoul.class, 3, 1, 3, EnumCreatureType.monster, BiomeGenBase.jungle, BiomeGenBase.jungleHills);

        // VengefulSpirit
        //EntityRegistry.registerGlobalEntityID(EntityVengefulSpirit.class, "GSVengefulSpirit", EntityRegistry.findGlobalUniqueEntityId(), 15720061, 4802889);
        //EntityRegistry.addSpawn(EntityVengefulSpirit.class, 3, 1, 3, EnumCreatureType.monster, BiomeGenBase.jungle, BiomeGenBase.jungleHills);
    }
}
