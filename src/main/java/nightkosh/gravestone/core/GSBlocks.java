package nightkosh.gravestone.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
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
public class GSBlocks {

    public static final DeferredRegister<Block> BLOCKS_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.ID);

    public static final DeferredRegister<Item> ITEMS_REGISTER =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.ID);

    private static final RegistryObject<BlockGraveStone> GRAVE_STONE = registerBlock("grave_stone",
            BlockGraveStone::new);

    private static final RegistryObject<BlockGraveStone> GRAVE_PLATE = registerBlock("grave_plate",
            BlockGraveStone::new);

    private static final RegistryObject<BlockGraveStone> CROSS = registerBlock("cross",
            BlockGraveStone::new);

    private static <T extends Block> RegistryObject<T> registerBlock(
            String name, Supplier<T> supplier) {
        var block = BLOCKS_REGISTER.register(name, supplier);
        ITEMS_REGISTER.register(name, () -> new ItemBlockGraveStone(block.get()));
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS_REGISTER.register(eventBus);
        ITEMS_REGISTER.register(eventBus);
    }

    public static Block getGraveStone() {
        return GRAVE_STONE.get();
    }

    public static Block getGravePlate() {
        return GRAVE_PLATE.get();
    }

    public static Block getCross() {
        return CROSS.get();
    }

}
