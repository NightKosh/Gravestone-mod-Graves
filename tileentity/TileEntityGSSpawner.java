package gravestone.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSSpawner extends TileEntity {

    protected GSMobSpawner spawner;

    public TileEntityGSSpawner() {
        spawner = new GSMobSpawner(this);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void updateEntity() {
        spawner.updateEntity();
    }
}
