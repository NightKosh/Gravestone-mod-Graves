package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;

public class GSBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES_REGISTER =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ModInfo.ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GraveStoneBlockEntity>> GRAVESTONE =
            BLOCK_ENTITIES_REGISTER.register(
                    "grave_stone_entity",
                    () -> {
                        var blocks = GSBlocks.GRAVE_LIST.stream()
                                .map(DeferredHolder::value)
                                .toArray(Block[]::new);

                        return new BlockEntityType<>(GraveStoneBlockEntity::new, blocks);
                    });

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES_REGISTER.register(eventBus);
    }

    public static BlockEntityType<GraveStoneBlockEntity> getGravestone() {
        return GRAVESTONE.get();
    }

}
