package gravestone.tileentity;

import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import gravestone.block.enums.EnumGraves;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveStoneSpawn {

    private TileEntityGSGraveStone tileEntity;
    private static final int PLAYER_RANGE = 35;
    /**
     * The stored delay before a new spawn.
     */
    private int delay = 600;
    private static final int MIN_DELAY = 800;
    private Entity spawnedMob;
    private List field_92060_e = null;
    private boolean getNewMob = true;
    private static final int START_TIME = 13500;
    private static final int END_TIME = 22500;
    /**
     * The extra NBT data to add to spawned entities
     */
    private TileEntityGSGraveStoneSpawnData spawnerTags = null;
    /**
     * Maximum number of entities for limiting mob spawning
     */
    private static final int MAX_NEARBY_ENTITIES = 3;
    /**
     * Range for spawning new entities with gravestone
     */
    private static final int SPAWN_RANGE = 1;

    public GSGraveStoneSpawn(TileEntityGSGraveStone tileEntity) {
        this.tileEntity = tileEntity;
    }

    /**
     * Returns true if there is a player in range (using World.getClosestPlayer)
     */
    public boolean anyPlayerInRange() {
        return tileEntity.worldObj.getClosestPlayer(tileEntity.xCoord + 0.5D, tileEntity.yCoord + 0.5D, tileEntity.zCoord + 0.5D, PLAYER_RANGE) != null;
    }

    /*
     * Check time and weather
     */
    private boolean canSpawnMobs(World world) {
        long time = world.getWorldTime();

        if (time > START_TIME && time < END_TIME || world.isThundering()) {
            return true;
        }

        return false;
    }

    /**
     * Update entity s state.
     */
    public void updateEntity() {
        if (canSpawnMobs(tileEntity.worldObj) && anyPlayerInRange()) {
            if (tileEntity.worldObj.isRemote) {
                if (this.delay > 0) {
                    --this.delay;
                }
            } else {
                if (this.delay == -1) {
                    this.updateDelay();
                }

                if (this.delay > 0) {
                    --this.delay;
                    return;
                }

                if (this.getNewMob) {
                    this.spawnedMob = GSMobSpawn.getMobEntity(this.tileEntity.worldObj, EnumGraves.getByID(this.tileEntity.graveType), this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord);

                    if (this.spawnedMob == null) {
                        return;
                    }

                    this.getNewMob = false;
                }

                int nearbyEntitiesCount = tileEntity.worldObj.getEntitiesWithinAABB(this.spawnedMob.getClass(), AxisAlignedBB.getAABBPool().getAABB(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord,
                        tileEntity.xCoord + 1, tileEntity.yCoord + 1, tileEntity.zCoord + 1).expand(1.0D, 4.0D, SPAWN_RANGE * 2)).size();

                if (nearbyEntitiesCount >= MAX_NEARBY_ENTITIES) {
                    this.updateDelay();
                    return;
                }

                if (GSMobSpawn.checkChance(this.tileEntity.worldObj.rand) && GSMobSpawn.spawnMob(this.tileEntity.worldObj, this.spawnedMob, this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord, true)) {
                    this.getNewMob = true;
                }
                this.updateDelay();
            }
        }
    }

    /**
     * Sets the delay before a new spawn.
     */
    private void updateDelay() {
        delay = MIN_DELAY + tileEntity.worldObj.rand.nextInt(GraveStoneConfig.graveSpawnRate - MIN_DELAY);

        if (this.field_92060_e != null && this.field_92060_e.size() > 0) {
            tileEntity.worldObj.markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        }

        int block = tileEntity.getBlockType().blockID;
        tileEntity.worldObj.addBlockEvent(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, block, 1, 0);
    }

    public String getEntityId() {
        return this.spawnerTags == null ? GSMobSpawn.getMobID(this.tileEntity.worldObj.rand, GSMobSpawn.EnumMobType.DEFAULT_MOBS) : this.spawnerTags.field_92033_c;
    }

    public void readSpawn(NBTTagCompound nbtTag) {
        delay = nbtTag.getShort("Delay");

        if (nbtTag.hasKey("SpawnPotentials")) {
            field_92060_e = new ArrayList();
            NBTTagList ntbList = nbtTag.getTagList("SpawnPotentials");

            for (int i = 0; i < ntbList.tagCount(); ++i) {
                field_92060_e.add(new TileEntityGSGraveStoneSpawnData(tileEntity, (NBTTagCompound) ntbList.tagAt(i)));
            }
        } else {
            this.field_92060_e = null;
        }

        if (tileEntity.worldObj != null && tileEntity.worldObj.isRemote) {
            spawnedMob = null;
        }
    }

    public void saveSpawn(NBTTagCompound nbtTag) {
        nbtTag.setShort("Delay", (short) delay);

        if (spawnerTags != null) {
            nbtTag.setCompoundTag("SpawnData", (NBTTagCompound) spawnerTags.field_92032_b.copy());
        }

        if (spawnerTags != null || field_92060_e != null && field_92060_e.size() > 0) {
            NBTTagList nbtList = new NBTTagList();

            if (field_92060_e != null && field_92060_e.size() > 0) {
                Iterator it = field_92060_e.iterator();

                while (it.hasNext()) {
                    TileEntityGSGraveStoneSpawnData tileEntityData = (TileEntityGSGraveStoneSpawnData) it.next();
                    nbtList.appendTag(tileEntityData.func_92030_a());
                }
            } else {
                nbtList.appendTag(spawnerTags.func_92030_a());
            }

            nbtTag.setTag("SpawnPotentials", nbtList);
        }
    }

    public void writeNBTTagsTo(Entity entity) {
            ((EntityLiving) entity).onSpawnWithEgg((EntityLivingData) null);
    }

    public void setMinDelay() {
        delay = MIN_DELAY;
    }
}
