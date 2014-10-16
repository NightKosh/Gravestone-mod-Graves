package gravestone.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSAltar extends TileEntity {
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
                entityItem = new EntityItem(this.worldObj, this.xCoord + x, this.yCoord + y, this.zCoord + z,
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
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
}
