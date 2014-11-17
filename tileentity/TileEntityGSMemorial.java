package gravestone.tileentity;

import gravestone.block.enums.EnumMemorials;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSMemorial extends TileEntityGSGrave {

    private byte hangedMob;
    private int hangedVillagerProfession;

    public TileEntityGSMemorial() {
        super();
    }

    public TileEntityGSMemorial(World world) {
        this();
        this.worldObj = world;
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
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

        hangedMob = nbtTag.getByte("HangedMob");
        hangedVillagerProfession = nbtTag.getInteger("HangedVillagerProfession");
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

        nbtTag.setByte("HangedMob", hangedMob);
        nbtTag.setInteger("HangedVillagerProfession", hangedVillagerProfession);
    }

    public void setMemorialContent(Random random) {
        gSDeathText.setRandomDeathTextAndName(random, graveType, true, true);
    }

    @Override
    public GSGraveStoneDeathText getDeathTextComponent() {
        return gSDeathText;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public EnumMemorials getMemorialType() {
        return EnumMemorials.getByID(graveType);
    }

    public int getHangedVillagerProfession() {
        return hangedVillagerProfession;
    }

    public void setHangedVillagerProfession(int hangedVillagerProfession) {
        this.hangedVillagerProfession = hangedVillagerProfession;
    }

    public byte getHangedMob() {
        return hangedMob;
    }

    public void setHangedMob(byte hangedMob) {
        this.hangedMob = hangedMob;
    }

}
