package gravestone.tileentity;

import gravestone.inventory.GraveInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class TileEntityGSGrave extends TileEntity {

    protected GraveInventory inventory;
    protected GSGraveStoneDeathText deathText;
    protected boolean isEditable = true;
    protected boolean isEnchanted = false;
    protected boolean isMossy = false;
    protected int graveType = 0;
    protected int age = -1;

    public TileEntityGSGrave() {
        deathText = new GSGraveStoneDeathText(this);
    }

    public void setGraveType(int graveType) {
        this.graveType = graveType;
    }

    public int getGraveTypeNum() {
        return graveType;
    }

    public void setGraveContent(Random random, boolean isPetGrave, boolean allLoot) {
        deathText.setRandomDeathTextAndName(random, graveType, false, true);
        inventory.setRandomGraveContent(random, isPetGrave, allLoot);
        setRandomAge();
    }

    public GraveInventory getInventory() {
        return inventory;
    }

    public GSGraveStoneDeathText getDeathTextComponent() {
        return deathText;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected void setRandomAge() {
        age = 10 + (new Random()).nextInt(100);
    }

    public boolean isEditable() {
        return isEditable;
    }

    public boolean isEnchanted() {
        return this.isEnchanted;
    }

    public void setEnchanted(boolean isEnchanted) {
        this.isEnchanted = isEnchanted;
    }

    public boolean isMossy() {
        return this.isMossy;
    }

    public void setMossy(boolean isMossy) {
        this.isMossy = isMossy;
    }

    /**
     * Sets the grave's isEditable flag to the specified parameter.
     */
    @SideOnly(Side.CLIENT)
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        graveType = nbtTag.getInteger("Type");
        isEnchanted = nbtTag.getBoolean("Enchanted");
        isMossy = nbtTag.getBoolean("Mossy");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        nbtTag.setInteger("Type", graveType);
        nbtTag.setBoolean("Enchanted", isEnchanted);
        nbtTag.setBoolean("Mossy", isMossy);
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
}
