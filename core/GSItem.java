
package gravestone.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.config.GraveStoneConfig;
import gravestone.item.ItemGSCorpse;
import gravestone.item.ItemGSChisel;
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
    public static Item corpse;
    
    public static void registration() {
        // chisel
        chisel = new ItemGSChisel(GraveStoneConfig.chiselId);
        LanguageRegistry.addName(chisel, "Chisel");
        
        corpse = new ItemGSCorpse(GraveStoneConfig.corpseId);
        LanguageRegistry.addName(corpse, "Corpse");
        
        
        // override vanilla skulls
//        Item.skull = (new ItemSkull(Item.skull.itemID)).setUnlocalizedName(Item.skull.getUnlocalizedName()).setTextureName("skull");   
    }
}
