/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.core.commands;

import gravestone.core.logger.GSLogger;
import gravestone.structures.GSStructureGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.graves.SingleGraveGenerator;
import gravestone.structures.memorials.MemorialGenerator;
import gravestone.structures.village.VillageCemeteryGenerator;
import gravestone.structures.village.VillageUndertakerGenerator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandStructuresGenerator extends CommandBase {

    @Override
    public String getCommandName() {
        return "generate";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/" + getCommandName() + " <structure name> <x coordinate> <z coordinate>";
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] commandStr) {
        GSLogger.logInfo("Structure generation command recieved");

        if (commandStr[0].equals("catacombs")) {
            generateStructure(icommandsender.getEntityWorld(), commandStr[1], commandStr[2], CatacombsGenerator.getInstance());
        } else if (commandStr[0].equals("memorial")) {
            generateStructure(icommandsender.getEntityWorld(), commandStr[1], commandStr[2], MemorialGenerator.getInstance());
        } else if (commandStr[0].equals("grave")) {
            generateStructure(icommandsender.getEntityWorld(), commandStr[1], commandStr[2], SingleGraveGenerator.getInstance());
        } else if (commandStr[0].equals("cemetery")) {
            generateStructure(icommandsender.getEntityWorld(), commandStr[1], commandStr[2], VillageCemeteryGenerator.getInstance());
        } else if (commandStr[0].equals("undertaker")) {
            generateStructure(icommandsender.getEntityWorld(), commandStr[1], commandStr[2], VillageUndertakerGenerator.getInstance());
        } else {
            GSLogger.logError("Unknown structure type");
        }
    }

    private static void generateStructure(World world, String xStr, String zStr, GSStructureGenerator structure) {
        try {
            structure.generate(world, world.rand, Integer.parseInt(xStr), Integer.parseInt(zStr), 0, true);
        } catch (NumberFormatException e) {
            GSLogger.logError("Coordinate error");
            e.printStackTrace();
        }
    }
}
