package gravestone.tileentity;

import gravestone.entity.helper.GroupOfGravesSpawnerHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISpawnerEntity {

    public BlockPos getPos();

    public World getWorld();

    public boolean haveSpawnerHelper();

    public GroupOfGravesSpawnerHelper getSpawnerHelper();
}
