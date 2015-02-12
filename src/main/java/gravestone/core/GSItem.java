package gravestone.core;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
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
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(chisel, 0, Resources.chiselModel);

        corpse = new ItemGSCorpse();
        GameRegistry.registerItem(corpse, "Corpse");
    }

    public static void registryExternalItems(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
