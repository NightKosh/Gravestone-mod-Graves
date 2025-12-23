package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.capability.BackupProvider;
import nightkosh.gravestone.config.GSConfigs;

import java.util.ArrayDeque;
import java.util.List;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupsHelper {

    public static void clonePlayer(Player playerOld, Player playerNew) {
        playerOld.reviveCaps();

        playerOld.getCapability(BackupProvider.BACKUP_CAP).ifPresent(oldCap -> {
            playerNew.getCapability(BackupProvider.BACKUP_CAP).ifPresent(newCap -> {
                try {
                    if (GSConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Creating player {} backup ", playerOld.getScoreboardName());
                    }
                    newCap.setBackups(new ArrayDeque<>(oldCap.getBackups()));
                } catch (Exception e) {
                    LOGGER.error("Can't restore backups at player death!", e);
                }
            });
        });

        playerOld.invalidateCaps();
    }

    public static void addBackup(Entity entity, Level level, BlockPos pos, List<ItemStack> items) {
        if (GSConfigs.CREATE_BACKUPS.get() && !level.isClientSide && entity instanceof Player player) {
            player.getCapability(BackupProvider.BACKUP_CAP, null)
                    .ifPresent(backups -> {
                        try {
                            if (GSConfigs.DEBUG_MODE.get()) {
                                LOGGER.info("Add player {} backup ", player.getScoreboardName());
                            }
                            var dim = level.dimension();
                            backups.addBackup(new Backup(dim, pos, items));
                        } catch (Exception e) {
                            LOGGER.error("Can't create backup!", e);
                        }
                    });
        }
    }

}
