package nightkosh.gravestone.gui.container;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveSlot extends Slot {

    public GraveSlot(Container container, int slotNum, int xPos, int yPos) {
        super(container, slotNum, xPos, yPos);
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return false;
    }

}
