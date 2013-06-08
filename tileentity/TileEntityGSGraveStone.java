package GraveStone.tileentity;

import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
public class TileEntityGSGraveStone extends TileEntityGSGrave {

    protected GSGraveStoneSpawn gsSpawn;
    protected byte swordType = 0;
    protected int swordDamage = 0;
    protected String swordName = "";
    protected ArrayList<NBTBase> swordEnchantment = new ArrayList();
    protected NBTTagCompound swordNBT = new NBTTagCompound();

    public TileEntityGSGraveStone() {
        super();
        gsSpawn = new GSGraveStoneSpawn(this);
        gSItems = new GSGraveStoneItems(this);
    }

    public TileEntityGSGraveStone(World world) {
        super();
        this.worldObj = world;
        gsSpawn = new GSGraveStoneSpawn(this);
        gSItems = new GSGraveStoneItems(this);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void updateEntity() {
        gsSpawn.updateEntity();
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.worldObj.isRemote) {
            gsSpawn.setMinDelay();
        }
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

        // grave spawn
        gsSpawn.readSpawn(nbtTag);

        // grave loot
        gSItems.readItems(nbtTag);

        // death text
        gSDeathText.readText(nbtTag);

        // age
        if (nbtTag.hasKey("Age")) {
            age = nbtTag.getInteger("Age");
        }

        // sword
        if (this.graveType > 4 && this.graveType < 10) {
            readSwordInfo(nbtTag);
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
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

        // age
        nbtTag.setInteger("Age", age);

        // sword
        if (this.swordType != 0) {
            writeSwordInfo(nbtTag);
        }
    }

    private void readSwordInfo(NBTTagCompound nbtTag) {
        swordType = nbtTag.getByte("SwordType");
        swordDamage = nbtTag.getInteger("SwordDamage");
        swordName = nbtTag.getString("SwordName");
        swordNBT = nbtTag.getCompoundTag("SwordNBT");
    }

    private void writeSwordInfo(NBTTagCompound nbtTag) {
        nbtTag.setByte("SwordType", swordType);
        nbtTag.setInteger("SwordDamage", swordDamage);
        nbtTag.setString("SwordName", swordName);
        nbtTag.setCompoundTag("SwordNBT", swordNBT);
    }

    /*
     * Get sword type
     */
    public byte getSword() {
        return this.swordType;
    }

    /*
     * Set sword damage
     */
    public int getDamage() {
        return this.swordDamage;
    }

    /*
     * Set sword name
     */
    public String getSwordName() {
        return this.swordName;
    }

    /*
     * Set sword enchantment
     */
    
    public NBTTagCompound getEnchantment() {
        return this.swordNBT;
    }
    
    /*
     * Set sword type
     */
    public void setSword(byte swordType) {
        this.swordType = swordType;
    }

    /*
     * Set sword damage
     */
    public void setDamage(int swordDamage) {
        this.swordDamage = swordDamage;
    }

    /*
     * Set sword name
     */
    public void setSwordName(String swordName) {
        this.swordName = swordName;
    }

    /*
     * Set sword enchantment
     */
    
    public void setEnchantment(NBTTagCompound swordNBT) {
        this.swordNBT = swordNBT;
    }
    
    /*
     * Drop sword as item
     */
    public void dropSword() {
        int id;
        switch (swordType) {
            case 5:
                id = Item.swordDiamond.itemID;
                break;
            case 3:
                id = Item.swordIron.itemID;
                break;
            case 2:
                id = Item.swordStone.itemID;
                break;
            case 4:
                id = Item.swordGold.itemID;
                break;
            default:
                id = Item.swordWood.itemID;
        }
        ItemStack sword = new ItemStack(id, 1, swordDamage);
        sword.setItemName(swordName);
        sword.setTagCompound(swordNBT);
        this.gSItems.dropItem(sword);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        nbtTag.removeTag("SpawnPotentials");
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }
}
