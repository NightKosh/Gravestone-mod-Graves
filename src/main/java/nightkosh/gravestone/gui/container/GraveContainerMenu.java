package nightkosh.gravestone.gui.container;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.core.GSMenu;

import javax.annotation.Nonnull;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveContainerMenu extends AbstractContainerMenu {

    public static final int PLAYER_INVENTORY_ROWS_COUNT = 3;
    public static final int ROWS_COUNT = 6;
    public static final int COLUMNS_COUNT = 9;
    public static final int SLOT_WIDTH = 18;
    private final GraveInventory graveInventory;

    public GraveContainerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public GraveContainerMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity) {
        super(GSMenu.GRAVE.get(), containerId);
        this.graveInventory = ((GraveStoneBlockEntity) blockEntity).getInventory();

        graveInventory.startOpen(playerInventory.player);

        int i = 2 * SLOT_WIDTH;

        for (int row = 0; row < ROWS_COUNT; row++) {
            for (int column = 0; column < COLUMNS_COUNT; column++) {
                this.addSlot(new GraveSlot(graveInventory, column + row * COLUMNS_COUNT,
                        8 + column * SLOT_WIDTH, SLOT_WIDTH + row * SLOT_WIDTH));
            }
        }

        for (int row = 0; row < PLAYER_INVENTORY_ROWS_COUNT; ++row) {
            for (int column = 0; column < COLUMNS_COUNT; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * COLUMNS_COUNT + COLUMNS_COUNT,
                        8 + column * SLOT_WIDTH, 103 + row * SLOT_WIDTH + i));
            }
        }

        for (int column = 0; column < COLUMNS_COUNT; ++column) {
            this.addSlot(new Slot(playerInventory, column,
                    8 + column * SLOT_WIDTH, 161 + i));
        }
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return graveInventory.stillValid(player);
    }

    @Override
    public void removed(@Nonnull Player player) {
        super.removed(player);
        graveInventory.stopOpen(player);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull Player player, int slot) {
        var stack = ItemStack.EMPTY;

        var stackInSlot = graveInventory.getItem(slot);
        stack = stackInSlot.copy();

        if (slot < graveInventory.getSizeInventoryForGui()) {
            if (!this.moveItemStackTo(stackInSlot,
                    graveInventory.getSizeInventoryForGui(),
                    36 + graveInventory.getSizeInventoryForGui(),
                    true)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (stackInSlot.getCount() == 0) {
            graveInventory.removeItemNoUpdate(slot);
        }

        if (stackInSlot.getCount() == stack.getCount()) {
            return ItemStack.EMPTY;
        }

        return stack;
    }

}
