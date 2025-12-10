package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.capability.BackupProvider;
import nightkosh.gravestone.capability.IBackups;
import nightkosh.gravestone.config.Config;

import java.util.List;

import static com.mojang.text2speech.Narrator.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupsHelper {

    public static void clonePlayer(Player playerOld, Player playerNew) {
        try {
            IBackups backupsOld = playerOld.getCapability(BackupProvider.BACKUP_CAP, null);
            IBackups backupsNew = playerNew.getCapability(BackupProvider.BACKUP_CAP, null);

            backupsNew.setBackups(backupsOld.getBackups());
        } catch (Exception e) {
            LOGGER.error("Can't restore backups at player death!");
        }
    }

    public static void addBackup(Entity entity, World world, BlockPos pos, List<ItemStack> items) {
        if (Config.createBackups && entity instanceof Player) {
            try {
                entity.getCapability(BackupProvider.BACKUP_CAP, null)
                        .addBackup(new Backup(world.provider.getDimension(), pos, items));
            } catch (Exception e) {
                LOGGER.error("Can't create backup!");
            }
        }
    }

}
