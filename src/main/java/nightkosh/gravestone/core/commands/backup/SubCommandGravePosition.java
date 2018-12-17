package nightkosh.gravestone.core.commands.backup;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import nightkosh.gravestone.capability.Backup;
import nightkosh.gravestone.core.commands.Command;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SubCommandGravePosition extends SubCommandBackup {

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
    protected void execute(Backup backup, ICommandSender sender, String name) {
        sender.sendMessage(new TextComponentTranslation(backup.getPos().toString())
                .setStyle(new Style().setColor(TextFormatting.GREEN)));
    }
}
