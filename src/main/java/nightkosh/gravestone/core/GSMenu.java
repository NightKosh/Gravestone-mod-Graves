package nightkosh.gravestone.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.gui.container.GraveContainerMenu;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSMenu {

    public static final DeferredRegister<MenuType<?>> MENUS_REGISTER =
            DeferredRegister.create(Registries.MENU, ModInfo.ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GraveContainerMenu>> GRAVE =
            registerMenuType(GraveContainerMenu::new, "grave_inventory");

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
            IContainerFactory<T> factory, String name) {
        return MENUS_REGISTER.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS_REGISTER.register(eventBus);
    }

}
