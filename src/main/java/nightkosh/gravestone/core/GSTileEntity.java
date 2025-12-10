package nightkosh.gravestone.core;

import nightkosh.gravestone.ModGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTileEntity {

    public static void registration() {
        ModGraveStone.proxy.registerTERenderers();
    }

}
