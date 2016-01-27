package nightkosh.gravestone.core.compatibility;

import nightkosh.gravestone.config.Config;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityBaubles implements ICompatibility {

    public static final String MOD_ID = "Baubles";

    protected CompatibilityBaubles() {
        if (isModLoaded(MOD_ID) && Config.storeBaublesItems) {
//            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler((player, source) -> {
//                List<ItemStack> items = new ArrayList<>();
//                IInventory inventory = BaublesApi.getBaubles(player);
//                if (inventory != null) {
//                    for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
//                        ItemStack stack = inventory.getStackInSlot(slot);
//                        if (stack != null) {
//                            items.add(stack.copy());
//                            inventory.setInventorySlotContents(slot, null);
//                        }
//
//                    }
//                }
//                return items;
//            });
        }
    }
}
