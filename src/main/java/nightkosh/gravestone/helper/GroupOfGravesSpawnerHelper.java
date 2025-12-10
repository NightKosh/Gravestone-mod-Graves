package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.tileentity.ISpawnerEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GroupOfGravesSpawnerHelper extends Entity implements ISpawnerEntity {

    public GroupOfGravesSpawnerHelper(Level level) {
        super(level);
    }

    @Override
    public Level getIWorld() {
        return getEntityWorld();
    }

    @Override
    public BlockPos getIPos() {
        return getPosition();
    }

}
