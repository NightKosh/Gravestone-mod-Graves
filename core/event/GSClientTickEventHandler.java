package gravestone.core.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSClientTickEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.player.getCommandSenderName().equals(Minecraft.getMinecraft().thePlayer.getCommandSenderName())) {
                GSRenderEventHandler.fogDensityPerTick = 0;
            }
        }
    }
}
