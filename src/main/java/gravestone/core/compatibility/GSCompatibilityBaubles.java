package gravestone.core.compatibility;

import baubles.api.BaublesApi;
import gravestone.ModGraveStone;
import gravestone.config.GSConfig;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBaubles implements ICompatibility {

    public static final String MOD_ID = "Baubles";

    protected GSCompatibilityBaubles() {
        if (isModLoaded(MOD_ID) && GSConfig.storeBaublesItems) {
            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
                IInventory inventory = BaublesApi.getBaubles(player);
                if (inventory != null) {
                    for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                        ItemStack stack = inventory.getStackInSlot(slot);
                        if (stack != null) {
                            items.add(stack.copy());
                            inventory.setInventorySlotContents(slot, null);
                        }

                    }
                }
                return items;
            });
        }
    }
}
