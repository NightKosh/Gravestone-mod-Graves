package gravestone.core.commands;

import gravestone.core.logger.GSLogger;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;

import java.util.ArrayList;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandCustomGraveItems extends CommandBase {

    @Override
    public String getName() {
        return "fill_grave";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/" + getName() + " <grave x coordinate> <grave y coordinate> <grave z coordinate>  <chest x coordinate> <chest y coordinate> <chest z coordinate>";
    }

    @Override
    public void execute(ICommandSender sender, String[] args) throws CommandException {
        GSLogger.logInfo("Custom grave items command received");

        if (args.length >= 6) {
            try {
                int graveXCoord = Integer.parseInt(args[0]);
                int graveYCoord = Integer.parseInt(args[1]);
                int graveZCoord = Integer.parseInt(args[2]);

                int chestXCoord = Integer.parseInt(args[3]);
                int chestYCoord = Integer.parseInt(args[4]);
                int chestZCoord = Integer.parseInt(args[5]);

                TileEntity graveTE = sender.getEntityWorld().getTileEntity(new BlockPos(graveXCoord, graveYCoord, graveZCoord));
                TileEntity chestTE = sender.getEntityWorld().getTileEntity(new BlockPos(chestXCoord, chestYCoord, chestZCoord));
                if (graveTE != null && chestTE != null) {
                    if (graveTE instanceof TileEntityGSGraveStone && chestTE instanceof TileEntityChest) {
                        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
                        ItemStack item;
                        for (int slot = 0; slot < ((TileEntityChest) chestTE).getSizeInventory(); slot++) {
                            item = ((TileEntityChest) chestTE).getStackInSlot(slot);
                            if (item != null) {
                                items.add(item.copy());
                            }
                        }
                        ((TileEntityGSGraveStone) graveTE).getInventory().setAdditionalItems(items.toArray(new ItemStack[items.size()]));
                    } else {
                        sender.addChatMessage(new ChatComponentTranslation("commands.fill_grave.wrong_block").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                    }
                } else {
                    sender.addChatMessage(new ChatComponentTranslation("commands.fill_grave.empty").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                }
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentTranslation("commands.coordinate_error").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        } else {
            sender.addChatMessage(new ChatComponentTranslation("commands.not_enough_parameters").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }
    }
}
