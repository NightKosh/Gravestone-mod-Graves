
package GraveStone.tileentity;

import GraveStone.block.EnumMemorialsType;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileEntityGSMemorial extends TileEntityGSGrave {
    
    public TileEntityGSMemorial() {
        super();
    }

    public TileEntityGSMemorial(World world) {
        this();
        this.worldObj = world;
    }
    
    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        return true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        // grave type
        readType(nbtTag);
        
        // death text
        gSDeathText.readText(nbtTag);
    }
    

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        // grave type
        saveType(nbtTag);
        
        // death text
        gSDeathText.saveText(nbtTag);
    }
    
    public void setMemorialContent(Random random) {
        gSDeathText.setRandomDeathText(random, graveType, true);
    }
    
    @Override
    public String getDeathText() {
        return gSDeathText.getDeathText();
    }
    
    @Override
    public void setDeathText(String text) {
        gSDeathText.setDeathText(text);
    }

    public EnumMemorialsType getMemorialType() {
        return EnumMemorialsType.getByID(graveType);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
}
