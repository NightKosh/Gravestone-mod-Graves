package nightkosh.gravestone.core.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import nightkosh.gravestone.core.GSBackups;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandGravePosition {

    public static final String NAME = "graves_pos";

    private static int execute(CommandContext<CommandSourceStack> ctx, ServerPlayer player) {
        var src = ctx.getSource();

        if (player == null) {
            try {
                player = src.getPlayerOrException();
            } catch (Exception e) {
                src.sendFailure(Component.literal("Only player can run this command. Try to run it with player name."));
                return 0;
            }
        }

        final String playerName = player.getScoreboardName();
        var backups = player.getData(GSBackups.BACKUPS.get());
        if (backups.getBackups().isEmpty()) {
            src.sendSuccess(() -> Component.literal("Backups not found"), false);
            return 1;
        }

        src.sendSuccess(() -> Component.literal(playerName + " player's graves positons: ")
                        .withStyle(ChatFormatting.GREEN),
                false);
        for (var backup : backups.getBackups()) {
            src.sendSuccess(() -> Component.literal(backup.getDimension().location().getPath())
                    .append(" : ")
                    .append(Component.literal(backup.getPos().toShortString())), false);
        }

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(NAME)
                .requires(src -> src.hasPermission(1))
                .executes(ctx -> CommandGravePosition.execute(ctx, null))
                .then(Commands.argument("player", EntityArgument.player())
                        .executes(ctx ->
                                execute(ctx, EntityArgument.getPlayer(ctx, "player")))
                );
    }

}
