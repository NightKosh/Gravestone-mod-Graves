package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISubCommand {

    public String getCommandName();

    public default String getCommandUsage() {
        return Command.MAIN_COMMAND_NAME + getCommandName();
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException;
}
