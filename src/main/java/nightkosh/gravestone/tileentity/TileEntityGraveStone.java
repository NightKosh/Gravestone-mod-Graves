package nightkosh.gravestone.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.GSConfigs;
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
        this.setWorld(world);
    }

    @Override
    public void update() {
        if (spawnerHelperId != 0 && spawnerHelper == null) {
            Entity entity = this.getWorld().getEntityByID(spawnerHelperId);

            if (entity instanceof GroupOfGravesSpawnerHelper) {
                spawnerHelper = (GroupOfGravesSpawnerHelper) entity;
            }
        }

        spawner.update();

        fogHandler.addFog(this.getWorld(), this.pos);
    }

    @Override
    public World getIWorld() {
        return getWorld();
    }

    @Override
    public BlockPos getIPos() {
        return getPos();
    }

    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.getWorld().isRemote) {
            spawner.setMinDelay();
        }

        return true;
    }

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

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
        nbtTag = super.writeToNBT(nbtTag);
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
        return nbtTag;
    }

    private void readSwordInfo(NBTTagCompound nbtTag) {
        if (nbtTag.hasKey("Sword")) {
            sword = new ItemStack(nbtTag.getCompoundTag("Sword"));
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
            flower = new ItemStack(nbtTag.getCompoundTag("Flower"));
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
            GraveInventory.dropItem(this.sword, this.getWorld(), this.pos);
        }
    }

    public boolean isSwordGrave() {
        return sword != null;
    }

    public boolean canBeMossy() {
        return !isSwordGrave() && this.getGraveType() != EnumGraves.STARVED_CORPSE && this.getGraveType() != EnumGraves.WITHERED_CORPSE;
    }


    public ItemStack getFlower() {
        return this.flower;
    }

    public void setFlower(ItemStack flower) {
        this.flower = flower;
    }

    public void dropFlower() {
        if (this.flower != null) {
            GraveInventory.dropItem(this.flower, this.getWorld(), this.pos);
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

    /// /        setRandomAge();//TODO
    /// /        setRandomFlower(random);//TODO
    //    }
    public String getOwner() {
        return this.playerId;
    }

    public void setOwner(String playerId) {
        this.playerId = playerId;
    }

    public boolean canBeLooted(Player player) {
        if (GSConfigs.ONLY_OWNER_CAN_LOOT_GRAVE.get()) {
            if (player != null) {
                String playerId = player.getUniqueID().toString();
                return player.isCreative() || StringUtils.isBlank(this.playerId) || playerId.equals(this.playerId) || inventory.getGraveContent().isEmpty();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean haveSpawnerHelper() {
        return spawnerHelper != null;
    }

    @Override
    public GroupOfGravesSpawnerHelper getSpawnerHelper() {
        return spawnerHelper;
    }

    public void setSpawnerHelper(GroupOfGravesSpawnerHelper spawnerHelper) {
        this.spawnerHelper = spawnerHelper;
        if (spawnerHelper != null) {
            this.spawnerHelperId = spawnerHelper.getEntityId();
        }
    }

    public boolean isPurified() {
        return isPurified;
    }

    public void setPurified(boolean isPurified) {
        this.isPurified = isPurified;
    }

}
