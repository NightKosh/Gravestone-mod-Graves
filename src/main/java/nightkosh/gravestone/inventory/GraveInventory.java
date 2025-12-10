package nightkosh.gravestone.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

//TODO
public class GraveInventory {//implements IInventory {

    public static final int DEFAULT_INVENTORY_SIZE = 54;
    private GraveStoneBlockEntity tileEntity;
    protected List<ItemStack> items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

    public GraveInventory(GraveStoneBlockEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    public void readItems(CompoundTag tag) {
        var tagList = tag.getList("Items", 10);
        items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

        for (int i = 0; i < tagList.size(); i++) {
            //TODO
//            items.add(new ItemStack(tagList.get(i)));
        }
    }

    public void saveItems(CompoundTag tag) {
        var ntbList = new ListTag();

        for (var stack : items) {
            if (stack != null && stack != ItemStack.EMPTY) {
                var nbt = new CompoundTag();
                //TODO
//                stack.writeToNBT(nbt);
//                ntbList.appendTag(nbt);
            }
        }

        tag.put("Items", ntbList);
    }

    //TODO
//    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addInventoryContent(ItemStack stack) {
        if (stack != null && stack != ItemStack.EMPTY) {
            items.add(stack);
        }
    }

    //TODO
//    @Override
    public int getSizeInventory() {
        return items.size();
    }

    public int getSizeInventoryForGui() {
        return (items.size() > DEFAULT_INVENTORY_SIZE) ? items.size() : DEFAULT_INVENTORY_SIZE;
    }

    //TODO
//    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < items.size()) {
            return items.get(slot);
        }
        return ItemStack.EMPTY;
    }

    //TODO
//    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null && stack != ItemStack.EMPTY) {
            if (stack.getCount() <= amount) {
                setInventorySlotContents(slot, ItemStack.EMPTY);
            } else {
                stack = stack.split(amount);
                if (stack.getCount() == 0) {
                    setInventorySlotContents(slot, ItemStack.EMPTY);
                }
            }
        }
        return stack;
    }

    //TODO
//    @Override
    public ItemStack removeStackFromSlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null && stack != ItemStack.EMPTY) {
            setInventorySlotContents(slot, ItemStack.EMPTY);
        }
        return stack;
    }

    //TODO
//    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot < items.size()) {
            this.items.set(slot, stack);
        }
    }

    //TODO
    /**
     * Returns the maximum stack size for a inventory slot.
     */
//    @Override
//    public int getInventoryStackLimit() {
//        return 64;
//    }
//
//    @Override
//    public void markDirty() {
//
//    }
//
//    @Override
//    public boolean isUsableByPlayer(Player player) {
//        return player.getEntityWorld().getBlockEntity(this.tileEntity.getBlockPos()) == this.tileEntity &&
//                player.getDistanceSq(new BlockPos(
//                        this.tileEntity.getBlockPos().getX() + 0.5,
//                        this.tileEntity.getBlockPos().getY() + 0.5,
//                        this.tileEntity.getBlockPos().getZ() + 0.5)) < 64;
//    }
//
//    @Override
//    public void openInventory(Player player) {
//
//    }

    //TODO
//    @Override
//    public void closeInventory(Player player) {
//
//    }
//
//    @Override
//    public boolean isItemValidForSlot(int index, ItemStack stack) {
//        return false;
//    }
//
//    @Override
//    public int getField(int id) {
//        return 0;
//    }
//
//    @Override
//    public void setField(int id, int value) {
//
//    }
//
//    @Override
//    public int getFieldCount() {
//        return 0;
//    }
//
//    @Override
//    public void clear() {
//        this.items.clear();
//    }
//
//    @Override
//    public String getName() {
//        return "";
//    }
//
//    @Override
//    public boolean hasCustomName() {
//        return false;
//    }
//
//    @Override
//    public ITextComponent getDisplayName() {
//        return null;
//    }

    /**
     * Set items as grave loot
     *
     * @param items Saving items
     */
    public void setItems(List<ItemStack> items) {
        if (items != null) {
            int savedItems;
            if (GSConfigs.GRAVE_ITEMS_COUNT.get() == 100) {
                savedItems = items.size();
            } else {
                savedItems = items.size() * GSConfigs.GRAVE_ITEMS_COUNT.get() / 100;
            }
            Collections.shuffle(Arrays.asList(items.size()), new Random());

            for (ItemStack item : items) {
                if (item != null && item != ItemStack.EMPTY && savedItems > 0) {
                    addInventoryContent(item);
                    savedItems--;
                } else {
                    dropItem(item, tileEntity.getLevel(), tileEntity.getBlockPos());
                }
            }
        }
    }

    public void setAdditionalItems(ItemStack[] items) {
        if (items != null) {
            for (ItemStack item : items) {
                addInventoryContent(item);
            }
        }
    }

    public void setAdditionalItems(List<ItemStack> items) {
        if (items != null) {
            for (ItemStack item : items) {
                addInventoryContent(item);
            }
        }
    }

    /**
     * Drop item
     *
     * @param stack Dropping item
     */
    public static void dropItem(ItemStack stack, Level level, BlockPos pos) {
        if (stack != null && stack != ItemStack.EMPTY) {
            var entityItem = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack.copy());

            //TODO
//            entityItem.motionX = level.getRandom().nextGaussian() * 0.2;
//            entityItem.motionY = level.getRandom().nextGaussian() * 0.2;
//            entityItem.motionZ = level.getRandom().nextGaussian() * 0.2;

            if (stack.hasTag()) {
                entityItem.getItem().setTag(stack.getTag().copy());
            }
            //TODO
//            level.spawnEntity(entityItem);
        }
    }

    /**
     * Drop item by slot number
     *
     * @param slot Item slot number
     */
    public void dropItem(int slot) {
        dropItem(items.get(slot), tileEntity.getLevel(), tileEntity.getBlockPos());
    }

    public void dropItem(ItemStack stack) {
        dropItem(stack, tileEntity.getLevel(), tileEntity.getBlockPos());
    }

    /*
     * Drop all holding items
     */
    public void dropAllItems() {
        items.forEach(this::dropItem);
        items.clear();
    }

    public List<ItemStack> getGraveContent() {
        return items;
    }
}