package gravestone.tileentity;

import gravestone.block.enums.EnumGraves;
import gravestone.config.GSConfig;
import gravestone.core.GSMobSpawn;
import gravestone.core.TimeHelper;
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
    private boolean newMobRequired = true;
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
        //TODO do not used. Do not forget about GSConfig.spawnMobsByGraves
    }

    @Override
    protected void serverUpdateLogic() {
        if (GSConfig.spawnMobsByGraves) {
            if (this.delay < 0) {
                this.updateDelay();
            }

            if (this.delay > 0) {
                this.delay--;
            } else if (canSpawnMobs(tileEntity.getWorld()) && anyPlayerInRange()) {
                if (this.newMobRequired) {
                    this.spawnedMob = GSMobSpawn.getMobEntity(this.tileEntity.getWorld(), EnumGraves.getById(((TileEntityGSGraveStone) this.tileEntity).graveType),
                            this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ());

                    if (this.spawnedMob == null) {
                        return;
                    }

                    this.newMobRequired = false;
                }

                int nearbyEntitiesCount = tileEntity.getWorld().getEntitiesWithinAABB(this.spawnedMob.getClass(), AxisAlignedBB.fromBounds(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(),
                        tileEntity.getPos().getX() + 1, tileEntity.getPos().getY() + 1, tileEntity.getPos().getZ() + 1).expand(1.0D, 4.0D, SPAWN_RANGE * 2)).size();

                if (nearbyEntitiesCount >= MAX_NEARBY_ENTITIES) {
                    this.updateDelay();
                    return;
                }

                if (GSMobSpawn.checkChance(this.tileEntity.getWorld().rand) && GSMobSpawn.spawnMob(this.tileEntity.getWorld(), this.spawnedMob,
                        this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ(), true)) {
                    this.newMobRequired = true;
                }
                this.updateDelay();
            }
        }
    }

    /*
     * Check time and weather
     */
    @Override
    protected boolean canSpawnMobs(World world) {
        return TimeHelper.isGraveSpawnTime();
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
        return GSConfig.graveSpawnRate;
    }

    @Override
    protected Entity getMob() {
        return GSMobSpawn.getMobEntity(this.tileEntity.getWorld(), EnumGraves.getById(((TileEntityGSGraveStone) this.tileEntity).graveType),
                this.tileEntity.getPos().getX(), this.tileEntity.getPos().getY(), this.tileEntity.getPos().getZ());
    }
}
