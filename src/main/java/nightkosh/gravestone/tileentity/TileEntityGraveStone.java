package nightkosh.gravestone.tileentity;

import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.TimeHelper;
import nightkosh.gravestone.core.event.RenderEventHandler;
import nightkosh.gravestone.core.event.TickEventHandler;
import nightkosh.gravestone.entity.helper.GroupOfGravesSpawnerHelper;
import nightkosh.gravestone.inventory.GraveInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGraveStone extends TileEntityGrave implements IUpdatePlayerListBox, ISpawnerEntity {

    protected Spawner gsSpawn;
    protected ItemStack sword = null;
    protected ItemStack flower = null;
    protected String playerId = "";
    protected int spawnerHelperId;
    protected GroupOfGravesSpawnerHelper spawnerHelper;
    public static final int FOG_RANGE = 30;

    public TileEntityGraveStone() {
        super();
        gsSpawn = getSpawner();
        inventory = new GraveInventory(this);
    }

    public TileEntityGraveStone(World world) {
        super();
        this.worldObj = world;
        gsSpawn = getSpawner();
        inventory = new GraveInventory(this);
    }

    private Spawner getSpawner() {
        return null;//new GSGraveStoneSpawn(this)TODO !!!!!!
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void update() {
        if (spawnerHelperId != 0 && spawnerHelper == null) {
            spawnerHelper = (GroupOfGravesSpawnerHelper) this.getWorld().getEntityByID(spawnerHelperId);
        }

        if (gsSpawn != null) {
            gsSpawn.update();
        }

        if (this.worldObj.isRemote && Config.isFogEnabled && TickEventHandler.getFogTicCount() == 0) {
            EntityPlayer player = this.worldObj.getClosestPlayer(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D, FOG_RANGE);
            if (player != null && player.getCommandSenderEntity().equals(Minecraft.getMinecraft().thePlayer) && TimeHelper.isFogTime(this.worldObj)) {
                RenderEventHandler.addFog();
            }
        }
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.worldObj.isRemote && gsSpawn != null) {
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
        // age
        age = nbtTag.getInteger("Age");
        // grave loot
        inventory.readItems(nbtTag);
        // death text
        deathText.readText(nbtTag);
        // sword
        readSwordInfo(nbtTag);
        // flower
        readFlowerInfo(nbtTag);
        // owner
        playerId = nbtTag.getString("PlayerId");
        //spawnerHelper
        if (nbtTag.hasKey("SpawnerHelperId")) {
            spawnerHelperId = nbtTag.getInteger("SpawnerHelperId");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);
        // age
        nbtTag.setInteger("Age", age);
        // grave loot
        inventory.saveItems(nbtTag);
        // death text
        deathText.saveText(nbtTag);
        // sword
        writeSwordInfo(nbtTag);
        // flower
        writeFlowerInfo(nbtTag);
        // owner
        nbtTag.setString("PlayerId", playerId);
        //spawnerHelper
        if (haveSpawnerHelper()) {
            nbtTag.setInteger("SpawnerHelperId", spawnerHelper.getEntityId());
        }

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
            this.inventory.dropItem(this.sword, this.worldObj, this.pos);
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
            this.inventory.dropItem(this.flower, this.worldObj, this.pos);
        }
    }

    public boolean hasFlower() {
        return flower != null;
    }

    public EnumGraves getGraveType() {
        return EnumGraves.getById(graveType);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    //    @Override
    //    public void setGraveContent(Random random, boolean isPetGrave, GraveInventory.GraveContentType contentType, GraveInventory.GraveCorpseContentType corpseType) {
    ////        deathText.setRandomDeathTextAndName(random, graveType, false, true);
    ////        inventory.setRandomGraveContent(random, isPetGrave, contentType, corpseType);
    ////        setRandomAge();
    ////        setRandomFlower(random);
    //    }

    //TODO
//    public void setGraveInfo(GraveWorldGenerationHelper.GraveGenerationInfo graveInfo) {
//        //super.setGraveContent(random, isPetGrave, contentType, corpseType);
//
//        if (graveInfo.getSword() != null) {
//            this.setSword(graveInfo.getSword());
//        }
//        this.setGraveType(graveInfo.getGrave().ordinal());
//
//        deathText = graveInfo.getDeathText();
//        inventory.setAdditionalItems(graveInfo.getItems());
//        setRandomAge();//TODO
//
//        if (graveInfo.getFlower() != null) {
//            setFlower(graveInfo.getFlower());
//        }
//    }

    public void setOwner(String playerId) {
        this.playerId = playerId;
    }

    public boolean canBeLooted(String playerId) {
        return !Config.onlyOwnerCanLootGrave || StringUtils.isBlank(this.playerId) || playerId.equals(this.playerId) || inventory.getGraveContent().isEmpty();
    }

    @Override
    public boolean haveSpawnerHelper() {
        return spawnerHelperId != 0;//spawnerHelper != null;
    }

    @Override
    public GroupOfGravesSpawnerHelper getSpawnerHelper() {
        return spawnerHelper;
    }

    public void setSpawnerHelper(GroupOfGravesSpawnerHelper spawnerHelper) {
        this.spawnerHelper = spawnerHelper;
    }
}
