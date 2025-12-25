package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.core.GSBackups;
import nightkosh.gravestone.capability.Backups;
import nightkosh.gravestone.core.config.GSConfigs;

import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupsHelper {

    public static Backups getBackups(Player player) {
        return player.getData(GSBackups.BACKUPS.get());
    }

    public static Deque<Backup> getBackupsCopy(Player player) {
        return new ArrayDeque<>(getBackups(player).getBackups());
    }

    @Nullable
    public static Backup getBackup(Player player, int num) {
        return getBackups(player).getBackup(num);
    }

    public static void clonePlayer(Player oldPlayer, Player newPlayer) {
        var oldBackups = oldPlayer.getData(GSBackups.BACKUPS.get());

        var newBackups = newPlayer.getData(GSBackups.BACKUPS.get());

        if (GSConfigs.DEBUG_MODE.get()) {
            LOGGER.info("Creating player {} backup ", oldPlayer.getScoreboardName());
        }
        newBackups.setBackups(deepCopy(oldBackups.getBackups()));
    }

    private static Deque<Backup> deepCopy(Deque<Backup> src) {
        Deque<Backup> out = new ArrayDeque<>(src.size());

        for (var b : src) {
            if (b != null) {
                var itemsCopy = b.getItems().stream()
                        .filter(s -> s != null && !s.isEmpty())
                        .map(ItemStack::copy)
                        .collect(Collectors.toList());

                out.addLast(new Backup(b.getDimension(), b.getPos(), itemsCopy));
            }
        }

        return out;
    }

    public static void addBackup(Entity entity, Level level, BlockPos pos, List<ItemStack> items) {
        if (GSConfigs.CREATE_BACKUPS.get() && !level.isClientSide && entity instanceof Player player) {
            try {
                if (GSConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Add player {} backup", player.getScoreboardName());
                }

                getBackups(player).addBackup(new Backup(level.dimension(), pos, items));
            } catch (Exception e) {
                LOGGER.error("Can't create backup!", e);
            }
        }
    }

}
