package nightkosh.gravestone.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
public class GraveStoneBlockEntity extends GraveBlockEntity implements ISpawnerEntity {

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

    public GraveStoneBlockEntity() {
        super();
        spawner = graveSpawnerHelper.getSpawner(this);
        inventory = new GraveInventory(this);
    }

    public GraveStoneBlockEntity(Level level) {
        this();
        this.setLevel(level);
    }

    @Override
    public void update() {
        if (spawnerHelperId != 0 && spawnerHelper == null) {
            Entity entity = this.getLevel().getEntityByID(spawnerHelperId);

            if (entity instanceof GroupOfGravesSpawnerHelper) {
                spawnerHelper = (GroupOfGravesSpawnerHelper) entity;
            }
        }

        spawner.update();

        fogHandler.addFog(this.getLevel(), this.getBlockPos());
    }

    @Override
    public Level getIWorld() {
        return getLevel();
    }

    @Override
    public BlockPos getIPos() {
        return getBlockPos();
    }

    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1 && this.getLevel().isClientSide()) {
            spawner.setMinDelay();
        }

        return true;
    }

    @Override
    public void readFromNBT(CompoundTag nbtTag) {
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
    public CompoundTag writeToNBT(CompoundTag tag) {
        tag = super.writeToNBT(tag);
        // age
        tag.setInteger("Age", age);
        // grave loot
        inventory.saveItems(tag);
        // death text
        deathText.saveText(tag);
        // sword
        writeSwordInfo(tag);
        // flower
        writeFlowerInfo(tag);
        // owner
        tag.putString("PlayerId", playerId);

        tag.putBoolean("Purified", isPurified);

        //spawnerHelper
        if (haveSpawnerHelper()) {
            tag.setInteger("SpawnerHelperId", spawnerHelper.getEntityId());
        }
        return tag;
    }

    private void readSwordInfo(CompoundTag nbtTag) {
        if (nbtTag.hasKey("Sword")) {
            sword = new ItemStack(nbtTag.getCompoundTag("Sword"));
        }
    }

    private void writeSwordInfo(CompoundTag nbtTag) {
        if (sword != null) {
            var swordNBT = new CompoundTag();
            sword.writeToNBT(swordNBT);
            nbtTag.setTag("Sword", swordNBT);
        }
    }

    private void readFlowerInfo(CompoundTag tag) {
        if (tag.hasKey("Flower")) {
            flower = new ItemStack(tag.getCompoundTag("Flower"));
        }
    }

    private void writeFlowerInfo(CompoundTag nbtTag) {
        if (flower != null) {
            var flowerNBT = new CompoundTag();
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
            GraveInventory.dropItem(this.sword, this.getLevel(), this.getBlockPos());
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
            GraveInventory.dropItem(this.flower, this.getLevel(), this.getBlockPos());
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
