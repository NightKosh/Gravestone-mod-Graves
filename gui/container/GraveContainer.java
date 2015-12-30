package gravestone.gui.container;

import gravestone.inventory.GraveInventory;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveContainer extends Container {

    public static final int PLAYER_INVENTORY_ROWS_COUNT = 3;
    public static final int ROWS_COUNT = 6;
    public static final int COLUMNS_COUNT = 9;
    public static final int SLOT_WIDTH = 18;
    private GraveInventory graveInventory;

    public GraveContainer(InventoryPlayer inventoryPlayer, TileEntityGSGraveStone te) {
        int i = 2 * SLOT_WIDTH;

        graveInventory = te.getInventory();
        for (int row = 0; row < ROWS_COUNT; row++) {
            for (int column = 0; column < COLUMNS_COUNT; column++) {
                this.addSlotToContainer(new GraveSlot(graveInventory, column + row * COLUMNS_COUNT, 8 + column * SLOT_WIDTH, SLOT_WIDTH + row * SLOT_WIDTH));
            }
        }

        for (int row = 0; row < PLAYER_INVENTORY_ROWS_COUNT; ++row) {
            for (int column = 0; column < COLUMNS_COUNT; ++column) {
                this.addSlotToContainer(new Slot(inventoryPlayer, column + row * COLUMNS_COUNT + COLUMNS_COUNT, 8 + column * SLOT_WIDTH, 103 + row * SLOT_WIDTH + i));
            }
        }

        for (int column = 0; column < COLUMNS_COUNT; ++column) {
            this.addSlotToContainer(new Slot(inventoryPlayer, column, 8 + column * SLOT_WIDTH, 161 + i));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < graveInventory.getSizeInventoryForGui()) {
                if (!this.mergeItemStack(stackInSlot, graveInventory.getSizeInventoryForGui(), 36 + graveInventory.getSizeInventoryForGui(), true)) {
                    return null;
                }
            } else {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }
}
