package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.item.Chisel;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

public class GSItems {

    public static final DeferredRegister<Item> ITEMS_REGISTER =
            DeferredRegister.create(Registries.ITEM, ModInfo.ID);

    public static final DeferredHolder<Item, Chisel> CHISEL =
            ITEMS_REGISTER.register("chisel", () -> new Chisel(
                    new Item.Properties()
                            .durability(50)
                            .repairable(Items.IRON_INGOT)
                            .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "chisel")))));

    public static void register(IEventBus eventBus) {
        ITEMS_REGISTER.register(eventBus);
    }

}
