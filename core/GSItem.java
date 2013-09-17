
package gravestone.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.config.GraveStoneConfig;
import gravestone.item.ItemGSChisel;
import gravestone.item.ItemGhostly;
import net.minecraft.item.Item;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSItem {

    private GSItem() {
    }
    
    // item chisel
    public static Item chisel;
    public static Item ghostlyItem;
    
    public static void registration() {
        // chisel
        chisel = new ItemGSChisel(GraveStoneConfig.chiselId);
        LanguageRegistry.addName(chisel, "Chisel");
        
        ghostlyItem = new ItemGhostly(GraveStoneConfig.ghostlyItemId);
        //LanguageRegistry.addName(chisel, "Chisel");
    }
}
