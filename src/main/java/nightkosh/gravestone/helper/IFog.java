package nightkosh.gravestone.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IFog {

    default void addFog(Level level, BlockPos pos) {}

}
