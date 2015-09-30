package gravestone.core.commands;

import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCommands {
    private static GSCommands instance;

    private GSCommands(FMLServerStartingEvent event) {
        instance = this;

        initCommands(event.getServer());
    }

    public static GSCommands getInstance(FMLServerStartingEvent event) {
        if (instance == null) {
            return new GSCommands(event);
        } else {
            return instance;
        }
    }

    private void initCommands(MinecraftServer server) {
        CommandHandler commandManager = (CommandHandler) server.getCommandManager();
        commandManager.registerCommand(new CommandGS());
        commandManager.registerCommand(new CommandGSLowerCase());
        commandManager.registerCommand(new CommandGravestone());
        commandManager.registerCommand(new CommandGravestoneLowerCase());
    }
}
