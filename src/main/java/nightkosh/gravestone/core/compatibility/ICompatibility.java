package nightkosh.gravestone.core.compatibility;

import net.minecraftforge.fml.common.Loader;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ICompatibility {

    public default boolean isModLoaded(String modId) {
        return Loader.isModLoaded(modId);
    }
}
