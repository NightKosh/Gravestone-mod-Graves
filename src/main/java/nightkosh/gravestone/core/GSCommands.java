package nightkosh.gravestone.core;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import nightkosh.gravestone.core.commands.CommandsList;
import nightkosh.gravestone.core.commands.CommandGravePosition;
import nightkosh.gravestone.core.commands.CommandRestoreItems;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCommands {

    public static final String NAME = "gravestone";
    public static final String ALIAS = "gs";

    public static LiteralArgumentBuilder<CommandSourceStack> root() {
        return Commands.literal(NAME)
                .then(CommandsList.getCommand())
                .then(CommandGravePosition.getCommand())
                .then(CommandRestoreItems.getCommand());
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getAlias() {
        return Commands.literal(ALIAS);
    }

}
