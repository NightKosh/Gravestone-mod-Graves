package nightkosh.gravestone.core.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import nightkosh.gravestone.capability.BackupProvider;
import nightkosh.gravestone.gui.container.GraveInventory;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandRestoreItems {

    public static final String NAME = "restore_items";

    private static int execute(CommandContext<CommandSourceStack> ctx, ServerPlayer player) {
        var src = ctx.getSource();
        var backups = player.getData(BackupProvider.BACKUPS.get());

        if (backups.getBackups().isEmpty()) {
            src.sendSuccess(() -> Component.literal("Backups not found"), false);
            return 1;
        }

        String playerName = player.getScoreboardName();
        src.sendSuccess(() -> Component.literal("Going to restore " + playerName + " Items!")
                        .withStyle(ChatFormatting.GREEN),
                false);
        if (backups.getBackups().isEmpty()) {
            src.sendFailure(Component.literal(playerName + " has no backups"));
        } else {
            src.sendSuccess(() -> Component.literal(playerName + " has " + backups.getBackups().size() + " backups"),
                    false);

            for (var backup : backups.getBackups()) {
                var items = backup.getItems();

                if (items != null && !items.isEmpty()) {
                    for (var item : items) {
                        if (item != null) {
                            GraveInventory.dropItem(item, player.level(), player.blockPosition());
                        }
                    }
                }
            }
            src.sendSuccess(() -> Component.literal(playerName + "'s items restored")
                            .withStyle(ChatFormatting.GREEN),
                    false);
        }

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(NAME)
                .requires(src -> src.hasPermission(3))
                .executes(ctx -> {
                    ctx.getSource().sendFailure(Component.literal("<player> required"));
                    return 0;
                })
                .then(Commands.argument("player", EntityArgument.player())
                        .executes(ctx ->
                                execute(ctx, EntityArgument.getPlayer(ctx, "player")))
                );
    }

}
