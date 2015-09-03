package gravestone.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveRecipeSlot extends Slot {
    public GraveRecipeSlot(IInventory iInventory, int index, int xPosition, int yPosition) {
        super(iInventory, index, xPosition, yPosition);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
