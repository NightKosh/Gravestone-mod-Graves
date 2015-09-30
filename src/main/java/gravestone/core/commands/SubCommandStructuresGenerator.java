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

        if (args.length >= 2) {
            String structureName = args[1];
            int x = sender.getPosition().getX();
            int z = sender.getPosition().getZ();
            EnumFacing facing = EnumFacing.NORTH;
            try {
                if (args.length >= 3) {
                    x = Integer.parseInt(args[2]);
                    if (args.length >= 4) {
                        z = Integer.parseInt(args[3]);
                        if (args.length >= 5) {
                            facing = EnumFacing.byName(args[4]);
                            if (facing == null) {
                                sender.addChatMessage(new ChatComponentTranslation("commands.direction_error").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                                return;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentTranslation("commands.coordinate_error").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                return;
            }

            switch (structureName) {
                case "catacombs":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, CatacombsGenerator.getInstance());
                    break;
                case "memorial":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, MemorialGenerator.getInstance());
                    break;
                case "grave":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, SingleGraveGenerator.getInstance());
                    break;
                case "opened_grave":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, OpenedGraveGenerator.getInstance());
                    break;
                case "cemetery":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, VillageCemeteryGenerator.getInstance());
                    break;
                case "undertaker":
                    generateStructure(sender, sender.getEntityWorld(), x, z, facing, VillageUndertakerGenerator.getInstance());
                    break;
                default:
                    sender.addChatMessage(new ChatComponentTranslation("commands.generate.unknown_structure").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    break;
            }
        } else {
            sender.addChatMessage(new ChatComponentTranslation("commands.not_enough_parameters").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }

    }

    private static void generateStructure(ICommandSender sender, World world, int x, int z, EnumFacing facing, GSStructureGenerator structure) {
        structure.generate(world, world.rand, x, z, facing, 0, true);
    }
}
