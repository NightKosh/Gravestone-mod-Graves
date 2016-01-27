package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Command extends CommandBase {

    private static final SubCommandCustomGraveItems CUSTOM_GRAVES_ITEMS = new SubCommandCustomGraveItems();
    private static final SubCommandCommandsList COMMANDS_LIST = new SubCommandCommandsList();

    public static final String MAIN_COMMAND_NAME = "/GS ";
    public static final String HELP = "Type \"" + COMMANDS_LIST.getCommandUsage() + "\" for commands list";

    public static final List<ISubCommand> ADDITIONAL_COMMANDS_LIST = new ArrayList<>();

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
                case SubCommandCustomGraveItems.COMMAND_NAME:
                    CUSTOM_GRAVES_ITEMS.execute(sender, args);
                    break;
                case SubCommandCommandsList.COMMAND_NAME:
                    COMMANDS_LIST.execute(sender, args);
                    break;
                default:
                    boolean unknownCommand = true;
                    for (ISubCommand additionalCommand : ADDITIONAL_COMMANDS_LIST) {
                        if (additionalCommand.getCommandName().equals(command)) {
                            additionalCommand.execute(sender, args);
                            unknownCommand = false;
                            break;
                        }
                    }
                    if (unknownCommand) {
                        sender.addChatMessage(new ChatComponentText("Unknown command!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                        sender.addChatMessage(new ChatComponentText(HELP).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
                    }
                    break;
            }
        } else {
            sender.addChatMessage(new ChatComponentText(HELP).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        }
    }

    public static void addCommand(ISubCommand command) {
        if (command != null) {
            ADDITIONAL_COMMANDS_LIST.add(command);
        }
    }
}
