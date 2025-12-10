package nightkosh.gravestone.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.item.itemblock.ItemBlockGraveStone;

import java.util.function.Supplier;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSBlock {

    @Mod.EventBusSubscriber(modid = ModInfo.ID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();
            registry.registerAll(GRAVE_STONE);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();

            registry.register(GRAVE_STONE_IB);
        }
    }



    public static final DeferredRegister<net.minecraft.world.level.block.Block> BLOCKS_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.ID);

    private static final RegistryObject<BlockGraveStone> GRAVE_STONE = registerBlock("grave_stone", BlockGraveStone::new, ItemBlockGraveStone::new);

    private static <T extends net.minecraft.world.level.block.Block> RegistryObject<T> registerBlock(
            String name, Supplier<T> block, Supplier<net.minecraft.world.item.Item> itemBlock) {
        SWItems.ITEMS_REGISTER.register(name, itemBlock);
        return BLOCKS_REGISTER.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS_REGISTER.register(eventBus);
    }

    public static Block getGraveStone() {
        return GRAVE_STONE.get();
    }

}
