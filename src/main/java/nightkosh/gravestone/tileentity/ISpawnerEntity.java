package nightkosh.gravestone.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ISpawnerEntity {

    BlockPos getIPos();

    Level getIWorld();

    boolean haveSpawnerHelper();

    GroupOfGravesSpawnerHelper getSpawnerHelper();

}
