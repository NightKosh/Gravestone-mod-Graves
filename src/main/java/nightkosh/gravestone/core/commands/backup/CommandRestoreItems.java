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
public class CommandRestoreItems extends SubCommandBackup {

    public static final String NAME = "restore_items";
    private static final String COMMAND_USAGE = "";//TODO Command.MAIN_COMMAND_NAME + COMMAND_NAME + " <player name>";

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        var src = ctx.getSource();
        src.sendSuccess(Component.literal("Restore Items!").withStyle(ChatFormatting.GREEN), false);
        //TODO
//        List<ItemStack> items = backup.getItems();
//
//        if (items != null && !items.isEmpty()) {
//            for (var item : items) {
//                if (item != null) {
//                    GraveInventory.dropItem(item, sender.getEntityWorld(), sender.getPosition());
//                }
//            }
//        } else {
//          src.sendFailure(Component.literal("There are no items for player " + name).withStyle(ChatFormatting.RED));
//        }

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(CommandRestoreItems.NAME)
                .requires(src -> src.hasPermission(3))
                .executes(CommandRestoreItems::execute);
    }

}
