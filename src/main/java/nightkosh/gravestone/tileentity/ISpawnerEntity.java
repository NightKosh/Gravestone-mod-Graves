package nightkosh.gravestone.tileentity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISpawnerEntity {

    public BlockPos getIPos();

    public World getIWorld();

    public boolean haveSpawnerHelper();

    public GroupOfGravesSpawnerHelper getSpawnerHelper();
}
