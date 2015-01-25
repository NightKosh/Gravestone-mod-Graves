package gravestone.tileentity;

import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GSConfig;
import gravestone.core.TimeHelper;
import gravestone.core.event.GSRenderEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSGraveStone extends TileEntityGSGrave implements IUpdatePlayerListBox {

    protected GSGraveStoneSpawn gsSpawn;
    protected ItemStack sword = null;
    protected ItemStack flower = null;
    public static final int FOG_RANGE = 30;

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
    public void update() {
        gsSpawn.update();

        if (this.worldObj.isRemote && GSConfig.isFogEnabled) {
            EntityPlayer player = this.worldObj.getClosestPlayer(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D, FOG_RANGE);
            if (player != null && player.getCommandSenderEntity().equals(Minecraft.getMinecraft().thePlayer) && isFogTime(this.worldObj)) {
                GSRenderEventHandler.addFog();
            }
        }
    }

    public static boolean isFogTime(World world) {
        if (world.isRaining()) {
            return false;
        } else {
            long dayTime = TimeHelper.getDayTime(world);
            return dayTime > TimeHelper.FOG_START_TIME && dayTime < TimeHelper.FOG_END_TIME;
        }
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
        if (nbtTag.hasKey("Sword")) {
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
            this.gSItems.dropItem(this.sword, this.worldObj, this.pos);
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
            this.gSItems.dropItem(this.flower, this.worldObj, this.pos);
        }
    }

    public boolean hasFlower() {
        return flower != null;
    }

    public EnumGraves getGraveType() {
        return EnumGraves.getById(graveType);
    }

    public boolean isEmpty() {
        return gSItems.graveContents.isEmpty();
    }

    @Override
    public void setGraveContent(Random random, boolean isPetGrave, boolean allLoot) {
        super.setGraveContent(random, isPetGrave, allLoot);
        setRandomFlower(random);
    }

    public void setRandomFlower(Random random) {
        if (random.nextInt(4) == 0) {
            ItemStack flower = new ItemStack(GraveStoneHelper.FLOWERS.get(random.nextInt(GraveStoneHelper.FLOWERS.size())), 1);
            if (GraveStoneHelper.canFlowerBePlaced(this.worldObj, this.pos, flower, this)) {
                setFlower(flower);
            }
        }
    }
}
