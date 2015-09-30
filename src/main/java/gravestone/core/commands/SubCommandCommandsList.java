package gravestone.core.commands;

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
public class SubCommandCommandsList {

    public static final String COMMAND_NAME = "commands_list";
    public static final String COMMAND_USAGE = CommandGS.MAIN_COMMAND_NAME + COMMAND_NAME;

    public static void execute(ICommandSender sender, String[] args) throws CommandException {
        sender.addChatMessage(new ChatComponentText(SubCommandStructuresGenerator.COMMAND_USAGE).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        sender.addChatMessage(new ChatComponentText(SubCommandCustomGraveItems.COMMAND_USAGE).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
    }
}
