package gravestone.core.commands;

import gravestone.GraveStoneLogger;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

import java.util.ArrayList;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandCustomGraveItems extends CommandBase {

    @Override
    public String getCommandName() {
        return "fill_grave";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/" + getCommandName() + " <grave x coordinate> <grave y coordinate> <grave z coordinate>  <chest x coordinate> <chest y coordinate> <chest z coordinate>";
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] commandStr) {
        GraveStoneLogger.logInfo("Custom grave items command recieved");

        if (commandStr.length == 6) {
            try {
                int graveXCoord = Integer.parseInt(commandStr[0]);
                int graveYCoord = Integer.parseInt(commandStr[1]);
                int graveZCoord = Integer.parseInt(commandStr[2]);

                int chestXCoord = Integer.parseInt(commandStr[3]);
                int chestYCoord = Integer.parseInt(commandStr[4]);
                int chestZCoord = Integer.parseInt(commandStr[5]);

                TileEntity graveTE = icommandsender.getEntityWorld().getTileEntity(graveXCoord, graveYCoord, graveZCoord);
                TileEntity chestTE = icommandsender.getEntityWorld().getTileEntity(chestXCoord, chestYCoord, chestZCoord);
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
                        GraveStoneLogger.logError("This coordinates occupied by wrong blocks");
                    }
                } else {
                    GraveStoneLogger.logError("There aren't any blocks at this coordinates");
                }
            } catch (NumberFormatException e) {
                GraveStoneLogger.logError("Coordinates error");
                e.printStackTrace();
            }
        } else {
            GraveStoneLogger.logError("Wrong number of parameters");
        }
    }
}
