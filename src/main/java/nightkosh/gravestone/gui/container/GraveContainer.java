package nightkosh.gravestone.gui.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import nightkosh.gravestone.inventory.GraveInventory;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

//TODO
public class GraveContainer {//extends Container {

    public static final int PLAYER_INVENTORY_ROWS_COUNT = 3;
    public static final int ROWS_COUNT = 6;
    public static final int COLUMNS_COUNT = 9;
    public static final int SLOT_WIDTH = 18;
//    private final GraveInventory graveInventory;

//    public GraveContainer(InventoryPlayer inventoryPlayer, GraveStoneBlockEntity te) {
//        int i = 2 * SLOT_WIDTH;
//
//        graveInventory = te.getInventory();
//        for (int row = 0; row < ROWS_COUNT; row++) {
//            for (int column = 0; column < COLUMNS_COUNT; column++) {
//                this.addSlotToContainer(new GraveSlot(graveInventory, column + row * COLUMNS_COUNT, 8 + column * SLOT_WIDTH, SLOT_WIDTH + row * SLOT_WIDTH));
//            }
//        }
//
//        for (int row = 0; row < PLAYER_INVENTORY_ROWS_COUNT; ++row) {
//            for (int column = 0; column < COLUMNS_COUNT; ++column) {
//                this.addSlotToContainer(new Slot(inventoryPlayer, column + row * COLUMNS_COUNT + COLUMNS_COUNT, 8 + column * SLOT_WIDTH, 103 + row * SLOT_WIDTH + i));
//            }
//        }
//
//        for (int column = 0; column < COLUMNS_COUNT; ++column) {
//            this.addSlotToContainer(new Slot(inventoryPlayer, column, 8 + column * SLOT_WIDTH, 161 + i));
//        }
//    }
//
//    @Override
//    public boolean canInteractWith(Player player) {
//        return true;
//    }
//
//
//    @Override
//    public ItemStack transferStackInSlot(Player player, int slot) {
//        ItemStack stack = ItemStack.EMPTY;
//        Slot slotObject = inventorySlots.get(slot);
//
//        if (slotObject != null && slotObject.getHasStack()) {
//            ItemStack stackInSlot = slotObject.getStack();
//            stack = stackInSlot.copy();
//
//            if (slot < graveInventory.getSizeInventoryForGui()) {
//                if (!this.mergeItemStack(stackInSlot, graveInventory.getSizeInventoryForGui(), 36 + graveInventory.getSizeInventoryForGui(), true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else {
//                return ItemStack.EMPTY;
//            }
//
//            if (stackInSlot.getCount() == 0) {
//                slotObject.putStack(ItemStack.EMPTY);
//            } else {
//                slotObject.onSlotChanged();
//            }
//
//            if (stackInSlot.getCount() == stack.getCount()) {
//                return ItemStack.EMPTY;
//            }
//            slotObject.onTake(player, stackInSlot);
//        }
//        return stack;
//    }

}
