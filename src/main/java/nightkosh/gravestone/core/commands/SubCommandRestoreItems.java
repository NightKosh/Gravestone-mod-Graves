package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import nightkosh.gravestone.helper.BackupsHandler;
import nightkosh.gravestone.inventory.GraveInventory;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandRestoreItems implements ISubCommand {

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

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String name = (args.length >= 2) ? args[1] : sender.getName();
        if (BackupsHandler.BACKUPS.containsKey(name)) {
            List<ItemStack> items = BackupsHandler.BACKUPS.get(name).getItems();

            if (items != null) {
                for (ItemStack item : items) {
                    if (item != null) {
                        GraveInventory.dropItem(item, sender.getEntityWorld(), sender.getPosition());
                    }
                }
            }
        } else {
            sender.sendMessage(new TextComponentTranslation("There are no items for player " + name)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }
}
