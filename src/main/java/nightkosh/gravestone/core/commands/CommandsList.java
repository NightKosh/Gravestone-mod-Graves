package nightkosh.gravestone.core.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import nightkosh.gravestone.core.commands.backup.CommandGravePosition;
import nightkosh.gravestone.core.commands.backup.CommandRestoreItems;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandsList {

    public static final String NAME = "gs";//TODO gravestone

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        var src = ctx.getSource();
        src.sendSuccess(Component.literal("Gravestone mod commands:").withStyle(ChatFormatting.GREEN), false);
        src.sendSuccess(Component.literal("/gs ")
                .append(CommandGravePosition.NAME),
                false);
        src.sendSuccess(Component.literal("/gs ")
                .append(CommandRestoreItems.NAME),
                false);

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(CommandsList.NAME)
                .requires(src -> src.hasPermission(0))
                .executes(CommandsList::execute);
    }

}
