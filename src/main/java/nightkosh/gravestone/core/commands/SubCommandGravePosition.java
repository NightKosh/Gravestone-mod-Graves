package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import nightkosh.gravestone.helper.BackupsHandler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandGravePosition implements ISubCommand {

    public static final String COMMAND_NAME = "grave_pos";
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
            sender.sendMessage(new TextComponentTranslation(BackupsHandler.BACKUPS.get(name).getPos().toString())
                    .setStyle(new Style().setColor(TextFormatting.GREEN)));
        } else {
            sender.sendMessage(new TextComponentTranslation("There are no coordinates of grave for player " + name)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }
}
