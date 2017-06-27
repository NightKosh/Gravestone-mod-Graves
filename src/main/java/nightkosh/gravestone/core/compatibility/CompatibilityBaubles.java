package nightkosh.gravestone.core.compatibility;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityBaubles implements ICompatibility {

    public static final String MOD_ID = "baubles";

    protected CompatibilityBaubles() {
        if (isModLoaded(MOD_ID) && Config.storeBaublesItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
                IInventory inventory = baubles.api.BaublesApi.getBaubles(player);
                if (inventory != null) {
                    for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                        ItemStack stack = inventory.getStackInSlot(slot);
                        if (stack != null && !stack.isEmpty()) {
                            items.add(stack.copy());
                            inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                        }

                    }
                }
                return items;
            });
        }
    }
}
