package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityRpgInventory {

    protected static boolean isInstalled = false;

    private GSCompatibilityRpgInventory() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isLoaded()) {
            try {
                Class<?> clazz = Class.forName("rpgInventory.gui.rpginv.PlayerRpgInventory");
                Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
                Object result = m.invoke(null, player);

                IInventory inv = (IInventory) result;
                if (inv != null) {
                    for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
                        ItemStack stack = inv.getStackInSlot(slot);
                        if (stack != null) {
                            items.add(inv.getStackInSlot(slot).copy());
                            inv.setInventorySlotContents(slot, null);
                        }
                    }
                }
            }catch (Exception e) {
                GraveStoneLogger.logError("Can't save RpgInventory items!!!");
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
