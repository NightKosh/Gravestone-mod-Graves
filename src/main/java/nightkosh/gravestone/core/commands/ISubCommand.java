package nightkosh.gravestone.core.commands;

import net.minecraft.server.MinecraftServer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISubCommand {

    String getCommandName();

    default String getCommandUsage() {
        return Command.MAIN_COMMAND_NAME + getCommandName();
    }


    //TODO
//    void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException;

}
