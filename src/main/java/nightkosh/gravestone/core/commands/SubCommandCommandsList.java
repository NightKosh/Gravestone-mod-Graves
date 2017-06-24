package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandCommandsList implements ISubCommand {

    public static final String COMMAND_NAME = "commands_list";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentTranslation(getCommandUsage()).setStyle(new Style().setColor(TextFormatting.GREEN)));

        for (ISubCommand additionalCommand : Command.ADDITIONAL_COMMANDS_LIST) {
            sender.sendMessage(new TextComponentTranslation(additionalCommand.getCommandUsage()).setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
    }
}
