package gravestone.tileentity;

import gravestone.block.enums.EnumHauntedChest;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSHauntedChest extends TileEntity {

    /**
     * The number of players currently using this chest
     */
    private int openTicks = 0;
    private boolean isOpen = false;
    /**
     * The current angle of the lid (between 0 and 1)
     */
    public float lidAngle;
    /**
     * The angle of the lid last tick
     */
    public float prevLidAngle;
    private EnumHauntedChest chestType;

    public TileEntityGSHauntedChest() {
        chestType = EnumHauntedChest.BATS_CHEST;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void updateEntity() {
        super.updateEntity();
        float f;

        if (openTicks > 0) {
            openTicks--;
        }

        if (this.worldObj.isRemote) {
            this.prevLidAngle = this.lidAngle;
            f = 0.1F;
            double d0;

            if (this.openTicks > 0 && this.lidAngle == 0.0F) {
                double d1 = (double) this.xCoord + 0.5D;
                d0 = (double) this.zCoord + 0.5D;

                this.worldObj.playSoundEffect(d1, (double) this.yCoord + 0.5D, d0, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.openTicks == 0 && this.lidAngle > 0.0F || this.openTicks > 0 && this.lidAngle < 1.0F) {
                float f1 = this.lidAngle;

                if (this.openTicks > 0) {
                    this.lidAngle += f;
                } else {
                    this.lidAngle -= f;
                }

                if (this.lidAngle > 1.0F) {
                    this.lidAngle = 1.0F;
                }

                float f2 = 0.5F;
                if (this.lidAngle < f2 && f1 >= f2) {
                    d0 = (double) this.xCoord + 0.5D;
                    double d2 = (double) this.zCoord + 0.5D;

                    this.worldObj.playSoundEffect(d0, (double) this.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                }

                if (this.lidAngle < 0.0F) {
                    this.lidAngle = 0.0F;
                }
            }
        } else {
            if (openTicks == 45) {
                spawnMobs(this.worldObj);
            }
        }

        if (openTicks == 0) {
            if (this.isOpen && GraveStoneConfig.replaceHauntedChest) {
                int meta = worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
                this.getWorldObj().removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.getWorldObj().setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.chest);
                this.getWorldObj().setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, meta, 2);
            }
            this.isOpen = false;
        }
    }

    public void openChest() {
        if (openTicks == 0) {
            this.openTicks = 50;
            this.isOpen = true;
        }
    }

    public EnumHauntedChest getChestType() {
        return chestType;
    }

    public void setChestType(EnumHauntedChest chestType) {
        this.chestType = chestType;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        chestType = EnumHauntedChest.getById(nbt.getByte("ChestType"));
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setByte("ChestType", (byte) chestType.ordinal());
    }

    public void spawnMobs(World world) {
        switch (getChestType()) {
            case SKELETON_CHEST:
                EntitySkeleton skeleton = GSMobSpawn.getSkeleton(world, (byte) 1);
                skeleton.setLocationAndAngles(this.xCoord + 0.5, this.yCoord, this.zCoord + 0.5, 0.0F, 0.0F);
                world.spawnEntityInWorld(skeleton);
                break;
            case BATS_CHEST:
            default:
                EntityBat bat;
                int batsCount = 15;

                for (byte i = 0; i < batsCount; i++) {
                    bat = new EntityBat(world);
                    bat.setLocationAndAngles(this.xCoord + 0.5, this.yCoord + 0.7, this.zCoord + 0.5, 0.0F, 0.0F);

                    world.spawnEntityInWorld(bat);
                }
                break;
        }
    }
}
