package nightkosh.gravestone.core.commands;

import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Commands {
    private static Commands instance;

    private Commands(FMLServerStartingEvent event) {
        instance = this;

        initCommands(event.getServer());
    }

    public static Commands getInstance(FMLServerStartingEvent event) {
        if (instance == null) {
            return new Commands(event);
        } else {
            return instance;
        }
    }

    private void initCommands(MinecraftServer server) {
        CommandHandler commandManager = (CommandHandler) server.getCommandManager();
        commandManager.registerCommand(new Command());
        commandManager.registerCommand(new CommandLowerCase());
        commandManager.registerCommand(new CommandGravestone());
        commandManager.registerCommand(new CommandGravestoneLowerCase());
    }
}
