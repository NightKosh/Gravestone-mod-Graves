package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.item.ItemGSChisel;
import gravestone.item.ItemGSCorpse;
import net.minecraft.item.Item;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSItem {

    // item chisel
    public static Item chisel;
    public static Item corpse;

    private GSItem() {
    }

    public static void registration() {
        // chisel
        chisel = new ItemGSChisel();
        GameRegistry.registerItem(chisel, "Chisel");

        corpse = new ItemGSCorpse();
        GameRegistry.registerItem(corpse, "Corpse");
    }
}
