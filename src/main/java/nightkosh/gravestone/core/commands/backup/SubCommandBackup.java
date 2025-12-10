package nightkosh.gravestone.core.commands.backup;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.capability.BackupProvider;
import nightkosh.gravestone.capability.IBackups;
import nightkosh.gravestone.core.commands.ISubCommand;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class SubCommandBackup implements ISubCommand {

    //TODO
//    @Override
//    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//        String name = (args.length >= 2) ? args[1] : sender.getName();
//        try {
//            int num = (args.length >= 3) ? Integer.parseInt(args[2]) - 1 : 0;
//
//            IBackups backups = ((Player) sender).getCapability(BackupProvider.BACKUP_CAP, null);
//            Backup backup = backups.getBackup(num);
//
//            if (backup != null) {
//                execute(backup, sender, name);
//            } else {
//                sender.sendMessage(new TextComponentTranslation("There is no backup for player " + name)
//                        .setStyle(new Style().setColor(TextFormatting.RED)));
//            }
//        } catch (NumberFormatException e) {
//            sender.sendMessage(new TextComponentTranslation("There is no backup with entered number")
//                    .setStyle(new Style().setColor(TextFormatting.RED)));
//        }
//    }
//
//    protected abstract void execute(Backup backup, ICommandSender sender, String name);

}