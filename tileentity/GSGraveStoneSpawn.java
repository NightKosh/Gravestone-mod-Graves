package gravestone.tileentity;

import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import gravestone.block.enums.EnumGraves;
import java.util.List;
import net.minecraft.entity.Entity;
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
    private static final int MIN_DELAY = 500;
    private Entity spawnedMob;
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
     * Update entity state.
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

        int block = tileEntity.getBlockType().blockID;
        tileEntity.worldObj.addBlockEvent(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, block, 1, 0);
    }

    public void setMinDelay() {
        delay = MIN_DELAY;
    }
}
