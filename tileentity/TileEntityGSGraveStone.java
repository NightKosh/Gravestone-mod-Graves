package gravestone.tileentity;

import gravestone.GraveStoneLogger;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSGraveStone extends TileEntityGSGrave {

    protected GSGraveStoneSpawn gsSpawn;
    protected ItemStack sword = null;
    protected ItemStack flower = null;

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
        // grave loot
        gSItems.readItems(nbtTag);
        // death text
        gSDeathText.readText(nbtTag);

        // age
        if (nbtTag.hasKey("Age")) {
            age = nbtTag.getInteger("Age");
        }

        // sword
        readSwordInfo(nbtTag);

        // read
        // TODO
        readFlowerInfo(nbtTag);
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);
        // grave type
        saveType(nbtTag);
        // grave loot
        gSItems.saveItems(nbtTag);
        // death text
        gSDeathText.saveText(nbtTag);
        // age
        nbtTag.setInteger("Age", age);

        // sword
        writeSwordInfo(nbtTag);

        // flower
        writeFlowerInfo(nbtTag);
    }

    private void readSwordInfo(NBTTagCompound nbtTag) {
        // TODO temporary compatibility with old versions - must be removed in feature
        if (this.graveType > 4 && this.graveType < 10 || nbtTag.hasKey("SwordGrave")) {
            convertSword(nbtTag);
        } else if (nbtTag.hasKey("Sword")) {
            sword = ItemStack.loadItemStackFromNBT(nbtTag.getCompoundTag("Sword"));
        }
    }


    private void writeSwordInfo(NBTTagCompound nbtTag) {
        if (sword != null) {
            NBTTagCompound swordNBT = new NBTTagCompound();
            sword.writeToNBT(swordNBT);
            nbtTag.setTag("Sword", swordNBT);
        }
    }

    private void readFlowerInfo(NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Flower")) {
            flower = ItemStack.loadItemStackFromNBT(nbtTag.getCompoundTag("Flower"));
        }
    }

    private void writeFlowerInfo(NBTTagCompound nbtTag) {
        if (flower != null) {
            NBTTagCompound flowerNBT = new NBTTagCompound();
            flower.writeToNBT(flowerNBT);
            nbtTag.setTag("Flower", flowerNBT);
        }
    }

    public ItemStack getSword() {
        return this.sword;
    }

    public void setSword(ItemStack sword) {
        this.sword = sword;
    }

    public void dropSword() {
        if (this.sword != null) {
            this.gSItems.dropItem(this.sword, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public boolean isSwordGrave() {
        return sword != null;
    }


    public ItemStack getFlower() {
        return this.flower;
    }

    public void setFlower(ItemStack flower) {
        this.flower = flower;
    }

    public void dropFlower() {
        if (this.flower != null) {
            this.gSItems.dropItem(this.flower, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public boolean hasFlower() {
        return flower != null;
    }

    public EnumGraves getGraveType() {
        return EnumGraves.getByID(graveType);
    }

    public boolean isEmpty() {
        return gSItems.graveContents.isEmpty();
    }

    private void convertSword(NBTTagCompound nbtTag) {
        GraveStoneLogger.logInfo("Start converting sword gravestone!");
        try {
            Item sword;
            byte swordType = nbtTag.getByte("SwordType");
            if (swordType == 0) {
                swordType = (byte) (this.graveType - 4);
            }
            switch (swordType) {
                case 5:
                    sword = Items.diamond_sword;
                    break;
                case 3:
                    sword = Items.iron_sword;
                    break;
                case 2:
                    sword = Items.stone_sword;
                    break;
                case 4:
                    sword = Items.golden_sword;
                    break;
                default:
                    sword = Items.wooden_sword;
            }
            GraveStoneLogger.logInfo("Sword type - " + nbtTag.getByte("SwordType") + ". Will be converted to " + sword.getUnlocalizedName());

            int damage = 0;
            if (nbtTag.hasKey("SwordDamage")) {
                damage = nbtTag.getInteger("SwordDamage");
                GraveStoneLogger.logInfo("Sword damage - " + damage);
            }

            ItemStack stack = new ItemStack(sword, 1, damage);

            if (nbtTag.hasKey("SwordName")) {
                stack.setStackDisplayName(nbtTag.getString("SwordName"));
                GraveStoneLogger.logInfo("Sword name - " + nbtTag.getString("SwordName"));
            }

            if (nbtTag.hasKey("SwordNBT")) {
                stack.setTagCompound(nbtTag.getCompoundTag("SwordNBT"));
            }
            this.sword = stack;
        } catch (Exception e) {
            GraveStoneLogger.logError("Something went wrong!!!");
            e.printStackTrace();
        }
        GraveStoneLogger.logInfo("Gravestone converting complete!");
    }

    @Override
    public void setGraveContent(Random random, boolean isPetGrave, boolean allLoot) {
        super.setGraveContent(random, isPetGrave, allLoot);
        setRandomFlower(random);
    }

    public void setRandomFlower(Random random) {
        if (random.nextInt(4) == 0 && GraveStoneHelper.canFlowerBePlaced(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this)) {
            setFlower(new ItemStack(GraveStoneHelper.FLOWERS.get(random.nextInt(GraveStoneHelper.FLOWERS.size())), 1));
        }
    }
}
