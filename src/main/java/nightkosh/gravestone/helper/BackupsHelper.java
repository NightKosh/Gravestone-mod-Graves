package nightkosh.gravestone.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.capability.BackupProvider;
import nightkosh.gravestone.capability.IBackups;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BackupsHelper {

    public static void clonePlayer(EntityPlayer playerOld, EntityPlayer playerNew) {
        try {
            IBackups backupsOld = playerOld.getCapability(BackupProvider.BACKUP_CAP, null);
            IBackups backupsNew = playerNew.getCapability(BackupProvider.BACKUP_CAP, null);

            backupsNew.setBackups(backupsOld.getBackups());
        } catch (Exception e) {
            GSLogger.logError("Can't restore backups at player death!");
        }
    }

    public static void addBackup(Entity entity, World world, BlockPos pos, List<ItemStack> items) {
        if (Config.createBackups && entity instanceof EntityPlayer) {
            try {
                entity.getCapability(BackupProvider.BACKUP_CAP, null)
                        .addBackup(new Backup(world.provider.getDimension(), pos, items));
            } catch (Exception e) {
                GSLogger.logError("Can't create backup!");
            }
        }
    }
}
