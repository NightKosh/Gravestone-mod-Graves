package nightkosh.gravestone.core.event;

import nightkosh.gravestone.core.MobHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEventHandlerNetwork {

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MobHandler.setMobSpawnTime(event.player);
        }
    }

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerRespawnEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MobHandler.setMobSpawnTime(event.player);
        }
    }
}
