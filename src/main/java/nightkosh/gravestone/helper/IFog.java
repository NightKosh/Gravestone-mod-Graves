package nightkosh.gravestone.helper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IFog {

    public default void addFog(World world, BlockPos pos) {
    }
}
