package nightkosh.gravestone.core.commands;

import net.minecraft.server.MinecraftServer;

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

    //TODO
//    @Override
//    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//        sender.sendMessage(new TextComponentTranslation(getCommandUsage()).setStyle(new Style().setColor(TextFormatting.GREEN)));
//
//        for (ISubCommand additionalCommand : Command.ADDITIONAL_COMMANDS_LIST) {
//            sender.sendMessage(new TextComponentTranslation(additionalCommand.getCommandUsage()).setStyle(new Style().setColor(TextFormatting.GREEN)));
//        }
//    }
}
