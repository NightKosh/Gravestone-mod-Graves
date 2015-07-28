package gravestone.core.event;

import gravestone.core.TimeHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSServerTickEventHandler {

    private static short ticCount = 0;

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticCount++;

            if (ticCount >= 500) {
                long time = TimeHelper.getDayTime(event.world);

                TimeHelper.setIsGraveSpawnTime(time > TimeHelper.GRAVE_SPAWN_START_TIME && time < TimeHelper.GRAVE_SPAWN_END_TIME || event.world.isThundering());
                ticCount = 0;
            }
        }
    }
}
