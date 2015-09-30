package gravestone.core.commands;

import gravestone.core.logger.GSLogger;
import gravestone.structures.GSStructureGenerator;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.graves.OpenedGraveGenerator;
import gravestone.structures.graves.SingleGraveGenerator;
import gravestone.structures.memorials.MemorialGenerator;
import gravestone.structures.village.undertaker.VillageCemeteryGenerator;
import gravestone.structures.village.undertaker.VillageUndertakerGenerator;
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
public class SubCommandStructuresGenerator {

    public static final String COMMAND_NAME = "generate";
    public static final String COMMAND_USAGE = CommandGS.MAIN_COMMAND_NAME + COMMAND_NAME + " <structure name> <x coordinate> <z coordinate> <direction>";


    public static void execute(ICommandSender sender, String[] args) throws CommandException {
        GSLogger.logInfo("Structure generation command received");

        if (args.length >= 5) {
            String structureName = args[1];
            String xCoord = args[2];
            String zCoord = args[3];
            String direction = args[4];
            switch (structureName) {
                case "catacombs":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, CatacombsGenerator.getInstance());
                    break;
                case "memorial":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, MemorialGenerator.getInstance());
                    break;
                case "grave":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, SingleGraveGenerator.getInstance());
                    break;
                case "opened_grave":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, OpenedGraveGenerator.getInstance());
                    break;
                case "cemetery":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, VillageCemeteryGenerator.getInstance());
                    break;
                case "undertaker":
                    generateStructure(sender, sender.getEntityWorld(), xCoord, zCoord, direction, VillageUndertakerGenerator.getInstance());
                    break;
                default:
                    sender.addChatMessage(new ChatComponentTranslation("commands.generate.unknown_structure").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    break;
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
