package nightkosh.gravestone.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class Spawner {

    protected ISpawnerEntity spawnerEntity;
    protected int delay;
    protected Entity spawnedMob;

    public Spawner(ISpawnerEntity tileEntity, int delay) {
        this.spawnerEntity = tileEntity;
        this.delay = delay;
    }

    /**
     * Update entity state.
     */
    public void update() {
        if (!spawnerEntity.getWorld().getDifficulty().equals(EnumDifficulty.PEACEFUL)) {
            if (spawnerEntity.getWorld().isRemote) {
//                clientUpdateLogic(); TODO
            } else {
                serverUpdateLogic();
            }
        }
    }

    /**
     * Sets the delay before a new spawn.
     */
    public void updateDelay() {
        delay = getMinDelay() + spawnerEntity.getWorld().rand.nextInt(getMaxDelay() - getMinDelay());
    }

    protected void setMinDelay() {
        delay = getMinDelay();
    }

    protected int getNearbyMobsCount() {
        return spawnerEntity.getWorld().getEntitiesWithinAABB(this.spawnedMob.getClass(), AxisAlignedBB.fromBounds(spawnerEntity.getPos().getX(), spawnerEntity.getPos().getY(), spawnerEntity.getPos().getZ(),
                spawnerEntity.getPos().getX() + 1, spawnerEntity.getPos().getY() + 1, spawnerEntity.getPos().getZ() + 1).expand(1.0D, 4.0D, getSpawnRange() * 2)).size();
    }

    protected boolean anyPlayerInRange() {
        return spawnerEntity.getWorld().getClosestPlayer(spawnerEntity.getPos().getX() + 0.5D, spawnerEntity.getPos().getY() + 0.5D, spawnerEntity.getPos().getZ() + 0.5D, getPlayerRange()) != null;
    }

    abstract protected boolean canSpawnMobs(World world);

    abstract protected int getPlayerRange();

    abstract protected int getSpawnRange();

    abstract protected int getMinDelay();

    abstract protected int getMaxDelay();

    abstract protected Entity getMob();

    abstract protected void clientUpdateLogic();

    abstract protected void serverUpdateLogic();
}
