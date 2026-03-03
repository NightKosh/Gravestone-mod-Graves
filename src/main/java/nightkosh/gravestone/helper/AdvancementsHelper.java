package nightkosh.gravestone.helper;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AdvancementsHelper {

    public static void giveAdvancement(Player player, Level level, Identifier advancement) {
        if (player instanceof ServerPlayer serverPlayer) {
            var adv = level.getServer().getAdvancements().get(advancement);
            if (adv != null) {
                var playerAdv = serverPlayer.getAdvancements();
                if (!playerAdv.getOrStartProgress(adv).isDone()) {
                    playerAdv.award(adv, "triggered");
                }
            }
        }
    }

}
