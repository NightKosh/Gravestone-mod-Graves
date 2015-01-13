package gravestone.core.commands;

import gravestone.core.logger.GSLogger;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

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
        GSLogger.logInfo("Custom grave items command recieved");

        if (args.length == 6) {
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
                        ((TileEntityGSGraveStone) graveTE).setAdditionalItems(items.toArray(new ItemStack[items.size()]));
                    } else {
                        sender.addChatMessage(new ChatComponentText("This coordinates occupied by wrong blocks"));
                    }
                } else {
                    sender.addChatMessage(new ChatComponentText("There aren't any blocks at this coordinates"));
                }
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentText("Coordinates error"));
            }
        } else {
            sender.addChatMessage(new ChatComponentText("Wrong number of parameters"));
        }
    }
}
