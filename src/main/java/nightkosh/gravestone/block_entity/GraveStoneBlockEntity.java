package nightkosh.gravestone.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.BlockGraveStone;
import nightkosh.gravestone.core.GSBlockEntities;
import nightkosh.gravestone.core.config.GSConfigs;
import nightkosh.gravestone.gui.container.GraveContainerMenu;
import nightkosh.gravestone.gui.container.GraveInventory;
import nightkosh.gravestone.helper.GraveSpawnerHelper;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;
import nightkosh.gravestone.helper.IFog;
import nightkosh.gravestone.helper.ISpawner;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneBlockEntity extends BlockEntity implements MenuProvider, ISpawnerEntity {

    public static GraveSpawnerHelper graveSpawnerHelper = new GraveSpawnerHelper();

    protected GraveInventory inventory;
    protected String deathMessageJson;
    protected int age = -1;
    protected ItemStack sword = null;
    protected String playerId = "";
    protected boolean isPurified = false;
    protected int spawnerHelperId;
    protected GroupOfGravesSpawnerHelper spawnerHelper;
    protected ISpawner spawner;
    public static IFog fogHandler = new IFog() {
    };

    public GraveStoneBlockEntity(BlockPos blockPos, BlockState state) {
        super(GSBlockEntities.getGravestone(), blockPos, state);
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
    public Level getILevel() {
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


    public GraveInventory getInventory() {
        return inventory;
    }

    public void setInventory(GraveInventory inventory) {
        this.inventory = inventory;
    }

    public String getDeathMessageJson() {
        return deathMessageJson;
    }

    public void setDeathMessageJson(String deathMessageJson) {
        this.deathMessageJson = deathMessageJson;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected void setRandomAge() {
        age = 10 + (new Random()).nextInt(100);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public void handleUpdateTag(@Nonnull ValueInput in) {
        this.loadAdditional(in);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag(@Nonnull HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return Component.empty();
    }

    @Nonnull
    @Override
    public AbstractContainerMenu createMenu(int containerId, @Nonnull Inventory inventory, @Nonnull Player player) {
        return new GraveContainerMenu(containerId, inventory, this);
    }

    @Override
    public void loadAdditional(ValueInput in) {
        super.loadAdditional(in);
        // age
        age = in.getIntOr("Age", 0);
        // grave loot
        inventory.readItems(in);
        // death text
        deathMessageJson = in.getString("deathMessageJson").orElse(null);
        // sword
        readSwordInfo(in);
        // owner
        playerId = in.getString("PlayerId").orElse(null);

        isPurified = in.getBooleanOr("Purified", true);

        //spawnerHelper
        //TODO
//        if (in.contains("SpawnerHelperId")) {
//            spawnerHelperId = in.getInt("SpawnerHelperId");
//        }
    }

    @Override
    public void saveAdditional(@Nonnull ValueOutput out) {
        super.saveAdditional(out);
        // age
        out.putInt("Age", age);
        // grave loot
        inventory.saveItems(out);
        // death text
        if (StringUtils.isNoneBlank(deathMessageJson)) {
            out.putString("deathMessageJson", deathMessageJson);
        }
        // sword
        writeSwordInfo(out);
        // owner
        out.putString("PlayerId", playerId);

        out.putBoolean("Purified", isPurified);

        //spawnerHelper
        if (haveSpawnerHelper()) {
            //TODO
//            tag.putInt("SpawnerHelperId", spawnerHelper.getEntityId());
        }
    }

    private void readSwordInfo(ValueInput in) {
        sword = in.read("Sword", ItemStack.CODEC).orElse(null);
    }

    private void writeSwordInfo(@Nonnull ValueOutput out) {
        if (sword != null) {
            out.store("Sword", ItemStack.CODEC, sword);
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

    public EnumGraveType getGraveType() {
        return ((BlockGraveStone) this.getBlockState().getBlock()).graveType;
    }

    public String getOwner() {
        return this.playerId;
    }

    public void setOwner(String playerId) {
        this.playerId = playerId;
    }

    public boolean canBeLooted(Player player) {
        if (GSConfigs.ONLY_OWNER_CAN_LOOT_GRAVE.get()) {
            if (player != null) {
                return player.isCreative() ||
                        StringUtils.isBlank(this.playerId) ||
                        player.getUUID().toString().equals(this.playerId) ||
                        inventory.getGraveContent().isEmpty();
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
