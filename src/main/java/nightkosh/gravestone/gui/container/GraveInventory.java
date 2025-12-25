package nightkosh.gravestone.gui.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.block_entity.GraveStoneBlockEntity;
import nightkosh.gravestone.core.config.GSConfigs;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveInventory implements Container {

    public static final int DEFAULT_INVENTORY_SIZE = 54;
    private final GraveStoneBlockEntity grave;
    protected List<ItemStack> items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

    public GraveInventory(GraveStoneBlockEntity grave) {
        this.grave = grave;
    }

    public void readItems(CompoundTag tag) {
        var tagList = tag.getList("Items", 10);
        items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

        for (int i = 0; i < tagList.size(); i++) {
            items.add(ItemStack.parse(grave.getLevel().registryAccess(), tagList.getCompound(i)).get());
        }
    }

    public void saveItems(CompoundTag tag) {
        var tags = new ListTag();

        for (var stack : items) {
            if (stack != null && stack != ItemStack.EMPTY) {
                tags.add(stack.save(grave.getLevel().registryAccess()));
            }
        }

        tag.put("Items", tags);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addInventoryContent(ItemStack stack) {
        if (stack != null && stack != ItemStack.EMPTY) {
            items.add(stack);
        }
    }

    public int getSizeInventoryForGui() {
        return Math.max(items.size(), DEFAULT_INVENTORY_SIZE);
    }

    /**
     * Set items as grave loot
     *
     * @param items Saving items
     */
    public void setItems(List<ItemStack> items) {
        if (items != null) {
            int savedItems;
            //TODO filter items count in event, to avoid manual items drop
            if (GSConfigs.GRAVE_ITEMS_COUNT.get() == 100) {
                savedItems = items.size();
            } else {
                savedItems = items.size() * GSConfigs.GRAVE_ITEMS_COUNT.get() / 100;
            }
            Collections.shuffle(items, new Random());

            for (var item : items) {
                if (item != null && item != ItemStack.EMPTY && savedItems > 0) {
                    addInventoryContent(item);
                    savedItems--;
                } else {
                    dropItem(item, grave.getLevel(), grave.getBlockPos());
                }
            }
            setChanged();
        }
    }

    public void setAdditionalItems(ItemStack[] items) {
        if (items != null) {
            for (var item : items) {
                addInventoryContent(item);
            }
        }
    }

    public void setAdditionalItems(List<ItemStack> items) {
        if (items != null) {
            for (var item : items) {
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
            if (GSConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Grave drop: {} x {}, at {}", stack.getHoverName().getString(), stack.getCount(), pos.toShortString());
            }

            var entityItem = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack.copy());
            entityItem.setDeltaMovement(
                    level.getRandom().nextGaussian() * 0.2,
                    level.getRandom().nextGaussian() * 0.2,
                    level.getRandom().nextGaussian() * 0.2);
            level.addFreshEntity(entityItem);
        }
    }

    public void dropItem(ItemStack stack) {
        dropItem(stack, grave.getLevel(), grave.getBlockPos());
    }

    /*
     * Drop all holding items
     */
    public void dropAllItems() {
        items.forEach(this::dropItem);
        items.clear();
        setChanged();
    }

    public List<ItemStack> getGraveContent() {
        return items;
    }

    @Nonnull
    @Override
    public ItemStack getItem(int slot) {
        if (slot < 0 || slot >= items.size()) {
            return ItemStack.EMPTY;
        }
        return items.get(slot);
    }

    @Override
    public void setItem(int slot, @Nonnull ItemStack stack) {
        if (slot < items.size()) {
            this.items.set(slot, stack);
            setChanged();
        }
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int amount) {
        var stack = getItem(slot);
        if (!stack.isEmpty()) {
            if (stack.getCount() <= amount) {
                items.remove(slot);
            } else {
                stack = stack.split(amount);
                if (stack.getCount() == 0) {
                    items.remove(slot);
                }
            }
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        var stack = getItem(slot);
        if (!stack.isEmpty()) {
            items.remove(slot);
        }
        return stack;
    }

    @Override
    public void setChanged() {
        if (grave != null) {
            grave.setChanged();
        }
    }

    @Override
    public int getContainerSize() {
        return getSizeInventoryForGui();
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    public boolean stillValid(@Nonnull Player player) {
        return player.level().getBlockEntity(this.grave.getBlockPos()) == this.grave &&
                player.distanceToSqr(
                        this.grave.getBlockPos().getX() + 0.5,
                        this.grave.getBlockPos().getY() + 0.5,
                        this.grave.getBlockPos().getZ() + 0.5) < 64;
    }

}
