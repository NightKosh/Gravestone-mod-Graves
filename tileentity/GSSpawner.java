package gravestone.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GSSpawner {

    protected TileEntity tileEntity;
    protected int delay;
    protected Entity spawnedMob;

    public GSSpawner(TileEntity tileEntity, int delay) {
        this.tileEntity = tileEntity;
        this.delay = delay;
    }

    /**
     * Update entity state.
     */
    public void update() {
        if (canSpawnMobs(tileEntity.getWorld()) && !tileEntity.getWorld().getDifficulty().equals(EnumDifficulty.PEACEFUL) && anyPlayerInRange()) {
            if (tileEntity.getWorld().isRemote) {
                clientUpdateLogic();
            } else {
                serverUpdateLogic();
            }
        }
    }

    /**
     * Sets the delay before a new spawn.
     */
    protected void updateDelay() {
        delay = getMinDelay() + tileEntity.getWorld().rand.nextInt(getMaxDelay() - getMinDelay());
    }

    protected void setMinDelay() {
        delay = getMinDelay();
    }

    protected int getNearbyMobsCount() {
        return tileEntity.getWorld().getEntitiesWithinAABB(this.spawnedMob.getClass(), AxisAlignedBB.fromBounds(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(),
                tileEntity.getPos().getX() + 1, tileEntity.getPos().getY() + 1, tileEntity.getPos().getZ() + 1).expand(1.0D, 4.0D, getSpawnRange() * 2)).size();
    }

    protected boolean anyPlayerInRange() {
        return tileEntity.getWorld().getClosestPlayer(tileEntity.getPos().getX() + 0.5D, tileEntity.getPos().getY() + 0.5D, tileEntity.getPos().getZ() + 0.5D, getPlayerRange()) != null;
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
