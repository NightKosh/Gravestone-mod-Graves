package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

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
    public String getUsage(ICommandSender icommandsender) {
        return "/" + getName() + " <command> <command parameters> (" + HELP + ")";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length >= 1) {
            String command = args[0];
            switch (command) {
                case SubCommandCustomGraveItems.COMMAND_NAME:
                    CUSTOM_GRAVES_ITEMS.execute(server, sender, args);
                    break;
                case SubCommandCommandsList.COMMAND_NAME:
                    COMMANDS_LIST.execute(server, sender, args);
                    break;
                default:
                    boolean unknownCommand = true;
                    for (ISubCommand additionalCommand : ADDITIONAL_COMMANDS_LIST) {
                        if (additionalCommand.getCommandName().equals(command)) {
                            additionalCommand.execute(server, sender, args);
                            unknownCommand = false;
                            break;
                        }
                    }
                    if (unknownCommand) {
                        sender.sendMessage(new TextComponentTranslation("Unknown command!").setStyle(new Style().setColor(TextFormatting.RED)));
                        sender.sendMessage(new TextComponentTranslation(HELP).setStyle(new Style().setColor(TextFormatting.GREEN)));
                    }
                    break;
            }
        } else {
            sender.sendMessage(new TextComponentTranslation(HELP).setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
    }

    public static void addCommand(ISubCommand command) {
        if (command != null) {
            ADDITIONAL_COMMANDS_LIST.add(command);
        }
    }
}
