package nightkosh.gravestone.core;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.gui.GraveInventoryScreen;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod.EventBusSubscriber(modid = ModInfo.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GSScreen {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(GSMenu.GRAVE.get(), GraveInventoryScreen::new);
    }

}
