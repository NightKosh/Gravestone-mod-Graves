package GraveStone.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;

public class TileEntityGSGraveStone extends TileEntityGSGrave {

    protected GSGraveStoneSpawn gsSpawn;

    public TileEntityGSGraveStone() {
        super();
        gsSpawn = new GSGraveStoneSpawn(this);
        gSItems = new GSGraveStoneItems(this);
    }

    public TileEntityGSGraveStone(World world) {
        this();
        this.worldObj = world;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity() {
        gsSpawn.updateEntity();
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.worldObj.isRemote) {
            gsSpawn.setMinDelay();
        }
        return true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        // grave type
        readType(nbtTag);

        // grave spawn
        gsSpawn.readSpawn(nbtTag);

        // grave loot
        gSItems.readItems(nbtTag);

        // death text
        gSDeathText.readText(nbtTag);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        // grave type
        saveType(nbtTag);

        // grave spawn
        gsSpawn.saveSpawn(nbtTag);

        // grave loot
        gSItems.saveItems(nbtTag);

        // death text
        gSDeathText.saveText(nbtTag);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        nbtTag.removeTag("SpawnPotentials");
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
}
