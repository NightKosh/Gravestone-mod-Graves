package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import gravestone.item.ItemGSChisel;
import gravestone.item.ItemGSCorpse;
import gravestone.item.ItemGSMonsterPlacer;
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
    public static Item spawnEgg;

    private GSItem() {
    }

    public static void registration() {
        // chisel
        chisel = new ItemGSChisel();
        GameRegistry.registerItem(chisel, "Chisel");

        corpse = new ItemGSCorpse();
        GameRegistry.registerItem(corpse, "Corpse");

        spawnEgg = new ItemGSMonsterPlacer();
        GameRegistry.registerItem(spawnEgg, "SpawnEgg");
    }

    public static void registryExternalItems(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
