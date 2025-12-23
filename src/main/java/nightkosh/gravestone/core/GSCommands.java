package nightkosh.gravestone.core;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import nightkosh.gravestone.core.commands.CommandsList;
import nightkosh.gravestone.core.commands.backup.CommandGravePosition;
import nightkosh.gravestone.core.commands.backup.CommandRestoreItems;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCommands {

    public static final LiteralArgumentBuilder<CommandSourceStack> COMMANDS_LIST = CommandsList.getCommand();

    public static final LiteralArgumentBuilder<CommandSourceStack> GRAVE_POSITION = CommandGravePosition.getCommand();

    public static final LiteralArgumentBuilder<CommandSourceStack> RESTORE_ITEMS = CommandRestoreItems.getCommand();

}
