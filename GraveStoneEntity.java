package GraveStone;

import GraveStone.entity.EntityZombieCat;
import GraveStone.entity.EntityZombieDog;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class GraveStoneEntity {

    private static GraveStoneEntity instance;
    private static int startEntityId = 300;

    private GraveStoneEntity() {
        getEntity();
    }

    public static GraveStoneEntity getInstance() {
        if (instance == null) {
            return new GraveStoneEntity();
        } else {
            return instance;
        }

    }

    public void getEntity() {
        // zombie dog
        EntityRegistry.registerGlobalEntityID(EntityZombieDog.class, "GSZombieDog", EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0x000000);
        EntityRegistry.addSpawn(EntityZombieDog.class, 3, 4, 8, 
                EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills, 
                BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.taigaHills);
        LanguageRegistry.instance().addStringLocalization("entity.GSZombieDog.name", "Zombie Dog");

        // zombie cat
        EntityRegistry.registerGlobalEntityID(EntityZombieCat.class, "GSZombieCat", EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0x000000);
        EntityRegistry.addSpawn(EntityZombieCat.class, 3, 4, 8, 
                EnumCreatureType.monster, BiomeGenBase.jungle, BiomeGenBase.jungleHills);
        LanguageRegistry.instance().addStringLocalization("entity.GSZombieCat.name", "Zombie Cat");
    }
}
