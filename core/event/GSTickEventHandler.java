package gravestone.core.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.core.TimeHelper;
import net.minecraft.client.Minecraft;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTickEventHandler {

    private static short ticCount = 0;

    private static short fogTicCount = 0;
    public static final short MAX_FOG_TICK_COUNT = 100;

    public static short getFogTicCount() {
        return fogTicCount;
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticCount++;

            if (ticCount >= 500) {
                TimeHelper.updateIsGraveSpawnTime(event.world);
                ticCount = 0;
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.player.equals(Minecraft.getMinecraft().thePlayer)) {
                fogTicCount++;
                if (fogTicCount > MAX_FOG_TICK_COUNT) {
                    fogTicCount = 0;
                    GSRenderEventHandler.resetAmountOfFogSources(event.player.worldObj);
                }
            }
        }
    }
}
