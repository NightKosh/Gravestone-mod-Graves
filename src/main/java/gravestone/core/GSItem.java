package gravestone.core;

import gravestone.item.ItemGSChisel;
import gravestone.item.ItemGSCorpse;
import gravestone.item.ItemGSMonsterPlacer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        GameRegistry.registerItem(chisel, "GSChisel");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(chisel, 0, Resources.chiselModel);

        corpse = new ItemGSCorpse();
        GameRegistry.registerItem(corpse, "GSCorpse");

        spawnEgg = new ItemGSMonsterPlacer();
        GameRegistry.registerItem(spawnEgg, "GSSpawnEgg");
        for (ItemGSMonsterPlacer.EnumEggs egg : ItemGSMonsterPlacer.EnumEggs.values()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(spawnEgg, egg.ordinal(), Resources.spawnEggModel);
        }
    }

    public static void registryExternalItems(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }
}
