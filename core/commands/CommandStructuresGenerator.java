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
import gravestone.structures.village.cemetery.VillageCemeteryGenerator;
import gravestone.structures.village.undertaker.VillageUndertakerGenerator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandStructuresGenerator extends CommandBase {

    @Override
    public String getName() {
        return "generate";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/" + getName() + " <structure name> <x coordinate> <z coordinate> <direction>";
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        GSLogger.logInfo("Structure generation command received");

        if (args.length >= 4) {
            if (args[0].equals("catacombs")) {
                generateStructure(sender, sender.getEntityWorld(), args[1], args[2], args[3], CatacombsGenerator.getInstance());
            } else if (args[0].equals("memorial")) {
                generateStructure(sender, sender.getEntityWorld(), args[1], args[2], args[3], MemorialGenerator.getInstance());
            } else if (args[0].equals("grave")) {
                generateStructure(sender, sender.getEntityWorld(), args[1], args[2], args[3], SingleGraveGenerator.getInstance());
            } else if (args[0].equals("cemetery")) {
                generateStructure(sender, sender.getEntityWorld(), args[1], args[2], args[3], VillageCemeteryGenerator.getInstance());
            } else if (args[0].equals("undertaker")) {
                generateStructure(sender, sender.getEntityWorld(), args[1], args[2], args[3], VillageUndertakerGenerator.getInstance());
            } else {
                sender.addChatMessage(new ChatComponentTranslation("commands.generate.unknown_structure").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        } else {
            sender.addChatMessage(new ChatComponentTranslation("commands.not_enough_parameters").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }

    }

    private static void generateStructure(ICommandSender sender, World world, String xStr, String zStr, String direction, GSStructureGenerator structure) {
        EnumFacing facing = EnumFacing.byName(direction);
        if (facing == null) {
            sender.addChatMessage(new ChatComponentTranslation("commands.direction_error").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        } else {
            try {
                structure.generate(world, world.rand, Integer.parseInt(xStr), Integer.parseInt(zStr), EnumFacing.byName(direction), 0, true);
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentTranslation("commands.coordinate_error").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }
    }
}
