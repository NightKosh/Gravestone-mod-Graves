package gravestone.core.compatibility;

import baubles.api.BaublesApi;
import gravestone.config.GSConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBaubles {

    public static final String MOD_ID = "Baubles";

    protected static boolean isInstalled = false;

    private GSCompatibilityBaubles() {

    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GSConfig.storeBaublesItems) {
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
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
