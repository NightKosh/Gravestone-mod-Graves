package nightkosh.gravestone.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.helper.GraveSpawnerHelper;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;
import nightkosh.gravestone.helper.IFog;
import nightkosh.gravestone.helper.ISpawner;
import nightkosh.gravestone.inventory.GraveInventory;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGraveStone extends TileEntityGrave implements ITickable, ISpawnerEntity {

    public static GraveSpawnerHelper graveSpawnerHelper = new GraveSpawnerHelper();

    protected ItemStack sword = null;
    protected ItemStack flower = null;
    protected String playerId = "";
    protected boolean isPurified = false;
    protected int spawnerHelperId;
    protected GroupOfGravesSpawnerHelper spawnerHelper;
    protected ISpawner spawner;
    public static IFog fogHandler = new IFog() {
    };

    public TileEntityGraveStone() {
        super();
        spawner = graveSpawnerHelper.getSpawner(this);
        inventory = new GraveInventory(this);
    }

    public TileEntityGraveStone(World world) {
        this();
        this.worldObj = world;
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

        spawner.update();

        fogHandler.addFog(this.worldObj, this.pos);
    }

    @Override
    public World getIWorld() {
        return getWorld();
    }

    @Override
    public BlockPos getIPos() {
        return getPos();
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.worldObj.isRemote) {
            spawner.setMinDelay();
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

        isPurified = nbtTag.getBoolean("Purified");

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

        nbtTag.setBoolean("Purified", isPurified);

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

    public boolean isPurified() {
        return isPurified;
    }

    public void setPurified(boolean isPurified) {
        this.isPurified = isPurified;
    }


    public static class VerticalPlate extends TileEntityGraveStone {
    }

    public static class Cross extends TileEntityGraveStone {
    }

    public static class Obelisk extends TileEntityGraveStone {
    }

    public static class CelticCross extends TileEntityGraveStone {
    }

    public static class HorizontalPlate extends TileEntityGraveStone {
    }

    public static class VillagerStatue extends TileEntityGraveStone {
    }

    public static class DogStatue extends TileEntityGraveStone {
    }

    public static class CatStatue extends TileEntityGraveStone {
    }

    public static class HorseStatue extends TileEntityGraveStone {
    }

    public static class CreeperStatue extends TileEntityGraveStone {
    }

    public static class StarvedCorpse extends TileEntityGraveStone {
    }

    public static class WitheredCorpse extends TileEntityGraveStone {
    }

    public static class Sword extends TileEntityGraveStone {
    }
}
