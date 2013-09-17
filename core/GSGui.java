
package gravestone.core;

import cpw.mods.fml.common.network.NetworkRegistry;
import gravestone.ModGraveStone;
import gravestone.gui.GuiHandler;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGui {

    private GSGui() {
    }

    public static void registration() {
        NetworkRegistry.instance().registerGuiHandler(ModGraveStone.instance, new GuiHandler());
    }
}
