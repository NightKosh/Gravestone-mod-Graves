
package net.minecraft.GraveStone.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGSMemorial extends TileEntityGSGrave {
    
    private GSGraveStoneDeathText gSDeathText = new GSGraveStoneDeathText();
    
    public TileEntityGSMemorial() {
        gSDeathText = new GSGraveStoneDeathText();
    }
    
    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int par1, int par2) {
        return true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        // death text
        gSDeathText.readText(nbtTag);
    }
    

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        // death text
        gSDeathText.saveText(nbtTag);
    }
    
    public void setMemorialContent() {
        gSDeathText.setRandomDeathText();
    }
    
    public String getDeathText() {
        return gSDeathText.getDeathText();
    }
    
    public void setDeathText(String text) {
        gSDeathText.setDeathText(text);
    }
}
