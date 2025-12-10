package nightkosh.gravestone.core.commands.backup;

import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.core.commands.Command;
import nightkosh.gravestone.inventory.GraveInventory;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandRestoreItems extends SubCommandBackup {

    public static final String COMMAND_NAME = "restore_items";
    public static final String COMMAND_USAGE = Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <player name>";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandUsage() {
        return COMMAND_USAGE;
    }

    //TODO
//    @Override
//    protected void execute(Backup backup, ICommandSender sender, String name) {
//        List<ItemStack> items = backup.getItems();
//
//        if (items != null && !items.isEmpty()) {
//            for (ItemStack item : items) {
//                if (item != null) {
//                    GraveInventory.dropItem(item, sender.getEntityWorld(), sender.getPosition());
//                }
//            }
//        } else {
//            sender.sendMessage(new TextComponentTranslation("There are no items for player " + name)
//                    .setStyle(new Style().setColor(TextFormatting.RED)));
//        }
//    }
}
