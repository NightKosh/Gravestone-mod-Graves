package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Commands {

    public static void initCommands(FMLServerStartingEvent event) {
        CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
        commandManager.registerCommand(new Command());
        commandManager.registerCommand(new CommandLowerCase());
        commandManager.registerCommand(new CommandGravestone());
        commandManager.registerCommand(new CommandGravestoneLowerCase());
    }
}
