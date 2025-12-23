package nightkosh.gravestone.core.commands.backup;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandGravePosition extends SubCommandBackup {

    public static final String NAME = "grave_pos";
    private static final String COMMAND_USAGE = "";//TODO Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <player name>";

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        var src = ctx.getSource();
        src.sendSuccess(Component.literal("Grave positon: ")
                        //TODO .append(Component.literal(backup.getPos().toString()))
                        .withStyle(ChatFormatting.GREEN),
                false);

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(CommandGravePosition.NAME)
                .requires(src -> src.hasPermission(1))
                .executes(CommandGravePosition::execute);
    }

}
