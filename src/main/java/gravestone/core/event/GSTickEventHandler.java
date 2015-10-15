package gravestone.core.event;

import gravestone.core.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
                long time = TimeHelper.getDayTime(event.world);

                TimeHelper.setIsGraveSpawnTime(time > TimeHelper.GRAVE_SPAWN_START_TIME && time < TimeHelper.GRAVE_SPAWN_END_TIME || event.world.isThundering());
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
                    GSRenderEventHandler.resetAmountOfFogSources();
                }
            }
        }
    }
}
