package gravestone.tileentity;

import gravestone.block.enums.EnumGraves;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSMobSpawn;
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
            this.spawnedMob = GSMobSpawn.getMobEntity(this.tileEntity.getWorld(), EnumGraves.getByID(((TileEntityGSGraveStone) this.tileEntity).graveType),
                    this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ());

            if (this.spawnedMob == null) {
                return;
            }

            this.getNewMob = false;
        }

        int nearbyEntitiesCount = tileEntity.getWorld().getEntitiesWithinAABB(this.spawnedMob.getClass(), AxisAlignedBB.fromBounds(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(),
                tileEntity.getPos().getX() + 1, tileEntity.getPos().getY() + 1, tileEntity.getPos().getZ() + 1).expand(1.0D, 4.0D, SPAWN_RANGE * 2)).size();

        if (nearbyEntitiesCount >= MAX_NEARBY_ENTITIES) {
            this.updateDelay();
            return;
        }

        if (GSMobSpawn.checkChance(this.tileEntity.getWorld().rand) && GSMobSpawn.spawnMob(this.tileEntity.getWorld(), this.spawnedMob,
                this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ(), true)) {
            this.getNewMob = true;
        }
        this.updateDelay();
    }

    /*
     * Check time and weather
     */
    @Override
    protected boolean canSpawnMobs(World world) {
        long time = world.getWorldTime() % 24000;

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
        return GSMobSpawn.getMobEntity(this.tileEntity.getWorld(), EnumGraves.getByID(((TileEntityGSGraveStone) this.tileEntity).graveType),
                this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ());
    }
}
