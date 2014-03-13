package gravestone.tileentity;

import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
import gravestone.block.enums.EnumGraves;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGraveStoneSpawn extends GSSpawner {

    private static final int BASE_DELAY = 600;
    private static final int PLAYER_RANGE = 35;
    private static final int MIN_DELAY = 500;
    private static final int START_TIME = 13500;
    private static final int END_TIME = 22500;
    private boolean getNewMob = true;
    /**
     * Maximum number of entities for limiting mob spawning
     */
    private static final int MAX_NEARBY_ENTITIES = 3;
    /**
     * Range for spawning new entities with gravestone
     */
    private static final int SPAWN_RANGE = 1;

    public GSGraveStoneSpawn(TileEntity tileEntity) {
        super(tileEntity, BASE_DELAY);
    }

    @Override
    protected void clientUpdateLogic() {
        if (this.delay > 0) {
            --this.delay;
        }
    }

    @Override
    protected void serverUpdateLogic() {
        if (this.delay == -1) {
            this.updateDelay();
        }

        if (this.delay > 0) {
            --this.delay;
            return;
        }

        if (this.getNewMob) {
            this.spawnedMob = GSMobSpawn.getMobEntity(this.tileEntity.worldObj, EnumGraves.getByID(((TileEntityGSGraveStone) this.tileEntity).graveType), this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord);

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

    /*
     * Check time and weather
     */
    @Override
    protected boolean canSpawnMobs(World world) {
        long time = world.getWorldTime();

        if (time > START_TIME && time < END_TIME || world.isThundering()) {
            return true;
        }

        return false;
    }

    @Override
    protected int getPlayerRange() {
        return PLAYER_RANGE;
    }

    @Override
    protected int getSpawnRange() {
        return SPAWN_RANGE;
    }

    @Override
    protected int getMinDelay() {
        return MIN_DELAY;
    }

    @Override
    protected int getMaxDelay() {
        return GraveStoneConfig.graveSpawnRate;
    }

    @Override
    protected Entity getMob() {
        return GSMobSpawn.getMobEntity(this.tileEntity.worldObj, EnumGraves.getByID(((TileEntityGSGraveStone) this.tileEntity).graveType),
                this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord);
    }
}
