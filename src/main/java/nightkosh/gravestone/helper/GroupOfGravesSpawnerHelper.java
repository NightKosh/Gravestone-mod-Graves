package nightkosh.gravestone.helper;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone.tileentity.ISpawnerEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GroupOfGravesSpawnerHelper extends Entity implements ISpawnerEntity {

    public GroupOfGravesSpawnerHelper(World worldIn) {
        super(worldIn);
    }

    @Override
    public World getIWorld() {
        return getEntityWorld();
    }

    @Override
    public BlockPos getIPos() {
        return getPosition();
    }
}
