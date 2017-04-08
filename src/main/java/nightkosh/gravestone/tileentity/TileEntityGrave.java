package nightkosh.gravestone.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone.inventory.GraveInventory;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class TileEntityGrave extends TileEntityBase {

    protected GraveInventory inventory;
    protected GraveStoneDeathText deathText = new GraveStoneDeathText();//TODO is required ????
    protected boolean isEditable = true;
    protected boolean isEnchanted = false;
    protected boolean isMossy = false;
    protected int graveType = 0;
    protected int age = -1;

    public TileEntityGrave() {
    }

    public void setGraveType(int graveType) {
        this.graveType = graveType;
    }

    public int getGraveTypeNum() {
        return graveType;
    }

    public GraveInventory getInventory() {
        return inventory;
    }

    public GraveStoneDeathText getDeathTextComponent() {
        return deathText;
    }

    public void setDeathTextComponent(GraveStoneDeathText deathText) {
        this.deathText = deathText;
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
        nbtTag = super.writeToNBT(nbtTag);

        nbtTag.setInteger("Type", graveType);
        nbtTag.setBoolean("Enchanted", isEnchanted);
        nbtTag.setBoolean("Mossy", isMossy);

        return nbtTag;
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
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
}
