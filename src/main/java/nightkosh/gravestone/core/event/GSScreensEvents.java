package nightkosh.gravestone.core.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import nightkosh.gravestone.api.ModInfo;
import nightkosh.gravestone.core.GSMenus;
import nightkosh.gravestone.gui.GraveInventoryScreen;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID, value = Dist.CLIENT)
public class GSScreensEvents {

    @SubscribeEvent
    public static void clientSetup(RegisterMenuScreensEvent event) {
        event.register(GSMenus.GRAVE.get(), GraveInventoryScreen::new);
    }

}
