package nightkosh.gravestone.core;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;

public class GSBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModInfo.ID);

    public static final RegistryObject<BlockEntityType<GraveStoneBlockEntity>> GRAVESTONE =
            BLOCK_ENTITIES_REGISTER.register(
                    "gravestone_entity",
                    () -> BlockEntityType.Builder.of(GraveStoneBlockEntity::new, GSBlocks.getGraveStone())
                            .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES_REGISTER.register(eventBus);
    }

    public static BlockEntityType<GraveStoneBlockEntity> getGravestone(IEventBus eventBus) {
        return GRAVESTONE.get();
    }

}
