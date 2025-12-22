package nightkosh.gravestone.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.gravestone.core.GSBlockEntities;
import nightkosh.gravestone.inventory.GraveInventory;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GraveBlockEntity extends BlockEntityBase {

    protected GraveInventory inventory;
    protected String deathMessageJson;
    protected boolean isEditable = true;
    protected int age = -1;

    public GraveBlockEntity(BlockPos blockPos, BlockState state) {
        super(GSBlockEntities.getGravestone(), blockPos, state);
    }

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

    public boolean isEditable() {
        return isEditable;
    }

    //TODO
//    /**
//     * Sets the grave's isEditable flag to the specified parameter.
//     */
//    @SideOnly(Side.CLIENT)
//    public void setEditable(boolean isEditable) {
//        this.isEditable = isEditable;
//    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

}
