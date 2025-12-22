package nightkosh.gravestone.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.helper.GraveSpawnerHelper;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;
import nightkosh.gravestone.helper.IFog;
import nightkosh.gravestone.helper.ISpawner;
import nightkosh.gravestone.inventory.GraveInventory;

import javax.annotation.Nonnull;

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

    public GraveStoneBlockEntity(BlockPos blockPos, BlockState state) {
        super(blockPos, state);
        spawner = graveSpawnerHelper.getSpawner(this);
        inventory = new GraveInventory(this);
    }

    //TODO
//    @Override
//    public void update() {
//        if (spawnerHelperId != 0 && spawnerHelper == null) {
//            var entity = this.getLevel().getEntity(spawnerHelperId);
//
//            if (entity instanceof GroupOfGravesSpawnerHelper) {
//                spawnerHelper = (GroupOfGravesSpawnerHelper) entity;
//            }
//        }
//
//        spawner.update();
//
//        fogHandler.addFog(this.getLevel(), this.getBlockPos());
//    }

    @Override
    public Level getIWorld() {
        return getLevel();
    }

    @Override
    public BlockPos getIPos() {
        return getBlockPos();
    }

    //TODO
//    @Override
//    public boolean receiveClientEvent(int par1, int par2) {
//        if (par1 == 1 && this.getLevel().isClientSide()) {
//            spawner.setMinDelay();
//        }
//
//        return true;
//    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        // age
        age = tag.getInt("Age");
        // grave loot
        inventory.readItems(tag);
        // death text
        deathMessageJson = tag.getString("deathMessageJson");
        // sword
        readSwordInfo(tag);
        // flower
        readFlowerInfo(tag);
        // owner
        playerId = tag.getString("PlayerId");

        isPurified = tag.getBoolean("Purified");

        //spawnerHelper
        if (tag.contains("SpawnerHelperId")) {
            spawnerHelperId = tag.getInt("SpawnerHelperId");
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
        super.saveAdditional(tag);
        // age
        tag.putInt("Age", age);
        // grave loot
        inventory.saveItems(tag);
        // death text
        tag.putString("deathMessageJson", deathMessageJson);
        // sword
        writeSwordInfo(tag);
        // flower
        writeFlowerInfo(tag);
        // owner
        tag.putString("PlayerId", playerId);

        tag.putBoolean("Purified", isPurified);

        //spawnerHelper
        if (haveSpawnerHelper()) {
            //TODO
//            tag.putInt("SpawnerHelperId", spawnerHelper.getEntityId());
        }
    }

    private void readSwordInfo(CompoundTag tag) {
        if (tag.contains("Sword")) {
            //TODO
//            sword = new ItemStack(tag.get("Sword"));
        }
    }

    private void writeSwordInfo(CompoundTag tag) {
        if (sword != null) {
            var swordTag = new CompoundTag();
            sword.save(swordTag);
            tag.put("Sword", swordTag);
        }
    }

    private void readFlowerInfo(CompoundTag tag) {
        if (tag.contains("Flower")) {
            //TODO
//            flower = new ItemStack(tag.get("Flower"));
        }
    }

    private void writeFlowerInfo(CompoundTag nbtTag) {
        if (flower != null) {
            var flowerTag = new CompoundTag();
            flower.save(flowerTag);
            nbtTag.put("Flower", flowerTag);
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
        return this.getGraveType() == EnumGraveType.SWORD;
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

    public EnumGraveType getGraveType() {
        return ((BlockGraveStone) this.getBlockState().getBlock()).graveType;
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
                return false;//TODO
//                String playerId = player.getUniqueID().toString();
//                return player.isCreative() || StringUtils.isBlank(this.playerId) || playerId.equals(this.playerId) || inventory.getGraveContent().isEmpty();
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
            //TODO
//            this.spawnerHelperId = spawnerHelper.getEntityId();
        }
    }

    public boolean isPurified() {
        return isPurified;
    }

    public void setPurified(boolean isPurified) {
        this.isPurified = isPurified;
    }

}
