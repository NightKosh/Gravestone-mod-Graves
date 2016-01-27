package nightkosh.gravestone.inventory;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveInventory implements IInventory {

    public static final int DEFAULT_INVENTORY_SIZE = 54;
    private TileEntityGraveStone tileEntity;
    protected List<ItemStack> items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

    public GraveInventory(TileEntityGraveStone tileEntity) {
        this.tileEntity = tileEntity;
    }

    public void readItems(NBTTagCompound nbtTag) {
        NBTTagList ntbItemsList = nbtTag.getTagList("Items", 10);
        items = new ArrayList<>(DEFAULT_INVENTORY_SIZE);

        for (int i = 0; i < ntbItemsList.tagCount(); ++i) {
            NBTTagCompound nbt = ntbItemsList.getCompoundTagAt(i);
            ItemStack stack = ItemStack.loadItemStackFromNBT(nbt);
            if (stack != null) {
                items.add(stack);
            }
        }
    }

    public void saveItems(NBTTagCompound nbtTag) {
        NBTTagList ntbList = new NBTTagList();

        for (ItemStack stack : items) {
            if (stack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                stack.writeToNBT(nbt);
                ntbList.appendTag(nbt);
            }
        }

        nbtTag.setTag("Items", ntbList);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addInventoryContent(ItemStack itemStack) {
        if (itemStack != null) {
            items.add(itemStack);
        }
    }

    @Override
    public int getSizeInventory() {
        return items.size();
    }

    public int getSizeInventoryForGui() {
        return (items.size() > DEFAULT_INVENTORY_SIZE) ? items.size() : DEFAULT_INVENTORY_SIZE;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < items.size()) {
            return items.get(slot);
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot < items.size()) {
            this.items.set(slot, stack);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.worldObj.getTileEntity(this.tileEntity.getPos()) == this.tileEntity &&
                player.getDistanceSq(new BlockPos(this.tileEntity.getPos().getX() + 0.5, this.tileEntity.getPos().getY() + 0.5, this.tileEntity.getPos().getZ() + 0.5)) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    /**
     * Set items as grave loot
     *
     * @param items Saving items
     */
    public void setItems(List<ItemStack> items) {
        if (items != null) {
            switch (Config.graveItemsCount) {
                case 0:
                    for (ItemStack item : items) {
                        dropItem(item, tileEntity.getWorld(), tileEntity.getPos());
                    }
                    break;
                case 40:
                    for (ItemStack item : items) {
                        addInventoryContent(item);
                    }
                    break;
                default:
                    int savedItems = Config.graveItemsCount;
                    Collections.shuffle(Arrays.asList(items.size()), new Random());

                    for (ItemStack item : items) {
                        if (item != null && savedItems > 0) {
                            addInventoryContent(item);
                            savedItems--;
                        } else {
                            dropItem(item, tileEntity.getWorld(), tileEntity.getPos());
                        }
                    }
                    break;
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
     * @param items Dropping item
     */
    public static void dropItem(ItemStack items, World world, BlockPos pos) {
        if (items != null) {
            Random random = new Random();
            float var10 = random.nextFloat() * 0.8F + 0.1F;
            float var11 = random.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem;

            for (float var12 = random.nextFloat() * 0.8F + 0.1F; items.stackSize > 0; world.spawnEntityInWorld(entityItem)) {
                int var13 = random.nextInt(21) + 10;

                if (var13 > items.stackSize) {
                    var13 = items.stackSize;
                }

                items.stackSize -= var13;
                entityItem = new EntityItem(world, pos.getX() + var10, pos.getY() + var11, pos.getZ() + var12, new ItemStack(items.getItem(), var13, items.getItemDamage()));
                entityItem.motionX = random.nextGaussian() * 0.05F;
                entityItem.motionY = random.nextGaussian() * 0.15F;
                entityItem.motionZ = random.nextGaussian() * 0.05F;

                if (items.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) items.getTagCompound().copy());
                }
            }
        }
    }

    /**
     * Drop item by slot number
     *
     * @param slot Item slot number
     */
    public void dropItem(int slot) {
        dropItem(items.get(slot), tileEntity.getWorld(), tileEntity.getPos());
    }

    public void dropItem(ItemStack stack) {
        dropItem(stack, tileEntity.getWorld(), tileEntity.getPos());
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