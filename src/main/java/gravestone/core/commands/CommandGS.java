package gravestone.core.commands;

import net.minecraft.command.CommandBase;
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
public class CommandGS extends CommandBase {
    public static final String MAIN_COMMAND_NAME = "/GS ";
    public static final String HELP = "Type \"" + SubCommandCommandsList.COMMAND_USAGE  + "\" for commands list";

    @Override
    public String getName() {
        return "GS";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/" + getName() + " <command> <command parameters> (" + HELP + ")";
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        if (args.length >= 1) {
            String command = args[0];
            switch (command) {
                //TODO
//                case SubCommandStructuresGenerator.COMMAND_NAME:
//                    SubCommandStructuresGenerator.execute(sender, args);
//                    break;
                case SubCommandCustomGraveItems.COMMAND_NAME:
                    SubCommandCustomGraveItems.execute(sender, args);
                    break;
                case SubCommandCommandsList.COMMAND_NAME:
                    SubCommandCommandsList.execute(sender, args);
                    break;
                default:
                    sender.addChatMessage(new ChatComponentText("Unknown command!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    sender.addChatMessage(new ChatComponentText(HELP).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
                    break;
            }
        } else {
            sender.addChatMessage(new ChatComponentText(HELP).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        }
    }
}
