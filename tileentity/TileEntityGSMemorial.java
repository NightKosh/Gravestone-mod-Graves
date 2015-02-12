package gravestone.tileentity;

import gravestone.block.enums.EnumHangedMobs;
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

    private EnumHangedMobs hangedMob = EnumHangedMobs.NONE;
    private int hangedVillagerProfession = 0;

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
        // death text
        gSDeathText.readText(nbtTag);

        hangedMob = EnumHangedMobs.getById(nbtTag.getByte("HangedMob"));
        hangedVillagerProfession = nbtTag.getInteger("HangedVillagerProfession");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);
        // death text
        gSDeathText.saveText(nbtTag);

        nbtTag.setByte("HangedMob", (byte) hangedMob.ordinal());
        nbtTag.setInteger("HangedVillagerProfession", hangedVillagerProfession);
    }

    public void setMemorialContent(Random random) {
        gSDeathText.setRandomDeathTextAndName(random, graveType, true, true);
    }

    public void setRandomMob(Random random) {
        hangedMob = EnumHangedMobs.values()[random.nextInt(EnumHangedMobs.values().length)];
    }

    @Override
    public GSGraveStoneDeathText getDeathTextComponent() {
        return gSDeathText;
    }

    public EnumMemorials getMemorialType() {
        return EnumMemorials.getById(graveType);
    }

    public int getHangedVillagerProfession() {
        return hangedVillagerProfession;
    }

    public void setHangedVillagerProfession(int hangedVillagerProfession) {
        this.hangedVillagerProfession = hangedVillagerProfession;
    }

    public EnumHangedMobs getHangedMob() {
        return hangedMob;
    }

    public void setHangedMob(EnumHangedMobs hangedMob) {
        this.hangedMob = hangedMob;
    }

}
