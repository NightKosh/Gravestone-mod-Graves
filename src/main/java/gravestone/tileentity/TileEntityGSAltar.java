package gravestone.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSAltar extends TileEntity implements IInventory {
    private ItemStack corpse = null;

    public boolean hasCorpse() {
        return corpse != null;
    }

    public ItemStack getCorpse() {
        return this.corpse;
    }

    public void setCorpse(ItemStack corpse) {
        this.corpse = corpse;
    }

    public void dropCorpse() {
        if (corpse != null) {
            Random random = new Random();
            float x = random.nextFloat() * 0.8F + 0.1F;
            float y = random.nextFloat() * 0.8F + 1.1F;
            EntityItem entityItem;

            for (float z = random.nextFloat() * 0.8F + 0.1F; corpse.stackSize > 0; this.worldObj.spawnEntityInWorld(entityItem)) {
                int stackSize = random.nextInt(21) + 10;

                if (stackSize > corpse.stackSize) {
                    stackSize = corpse.stackSize;
                }

                corpse.stackSize -= stackSize;
                entityItem = new EntityItem(this.worldObj, this.pos.getX() + x, this.pos.getY() + y, this.pos.getZ() + z,
                        new ItemStack(corpse.getItem(), stackSize, corpse.getItemDamage()));
                entityItem.motionX = random.nextGaussian() * 0.05;
                entityItem.motionY = random.nextGaussian() * 0.15;
                entityItem.motionZ = random.nextGaussian() * 0.05;

                if (corpse.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) corpse.getTagCompound().copy());
                }
            }
            corpse = null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        if (nbtTag.hasKey("Corpse")) {
            corpse = ItemStack.loadItemStackFromNBT(nbtTag.getCompoundTag("Corpse"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        if (corpse != null) {
            NBTTagCompound swordNBT = new NBTTagCompound();
            corpse.writeToNBT(swordNBT);
            nbtTag.setTag("Corpse", swordNBT);
        }
    }

    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net    The NetworkManager the packet originated from
     * @param packet The data packet
     */
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.pos, 1, nbtTag);
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
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

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return corpse;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        corpse = stack;
//        inv[slot] = stack;
//        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
//            stack.stackSize = getInventoryStackLimit();
//        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(this.pos) == this &&
                player.getDistanceSq(new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5)) < 64;
    }
}
