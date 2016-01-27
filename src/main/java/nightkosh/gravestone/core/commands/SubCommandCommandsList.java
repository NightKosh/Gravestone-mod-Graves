package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

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
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        sender.addChatMessage(new ChatComponentText(getCommandUsage()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));

        for (ISubCommand additionalCommand : Command.ADDITIONAL_COMMANDS_LIST) {
            sender.addChatMessage(new ChatComponentText(additionalCommand.getCommandUsage()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        }
    }
}
