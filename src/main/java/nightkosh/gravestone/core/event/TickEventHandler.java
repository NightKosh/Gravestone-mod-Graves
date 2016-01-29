package nightkosh.gravestone.core.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nightkosh.gravestone.core.TimeHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TickEventHandler {

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


// TODO
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void playerTick(TickEvent.PlayerTickEvent event) {
//        if (event.phase == TickEvent.Phase.END) {
//            if (event.player.equals(Minecraft.getMinecraft().thePlayer)) {
//                fogTicCount++;
//                if (fogTicCount > MAX_FOG_TICK_COUNT) {
//                    fogTicCount = 0;
//                    RenderEventHandler.resetAmountOfFogSources(event.player.worldObj);
//                }
//            }
//        }
//    }
}
