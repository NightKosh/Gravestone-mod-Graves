package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityTinkerConstruct {

    protected static boolean isInstalled = false;

    private GSCompatibilityTinkerConstruct() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isLoaded()) {
            try {
                Class<?> clazz = Class.forName("tconstruct.util.player.TPlayerStats");
                Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
                Object result = m.invoke(null, player);
                Field f = clazz.getDeclaredField("knapsack");
                IInventory inventory = (IInventory) f.get(result);

                if (inventory != null) {
                    for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack itemStack = inventory.getStackInSlot(i);
                        if (itemStack != null) {
                            items.add(itemStack.copy());
                            inventory.setInventorySlotContents(i, null);
                        }
                    }
                }
            } catch (Exception e) {
                GraveStoneLogger.logError("Can't get access to Tinkers Construct items!!!");
            }

            try {
                Class<?> clazz = Class.forName("tconstruct.util.player.TPlayerStats");
                Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
                Object result = m.invoke(null, player);
                Field f = clazz.getDeclaredField("armor");
                IInventory inventory = (IInventory) f.get(result);

                if (inventory != null) {
                    for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack itemStack = inventory.getStackInSlot(i);
                        if (itemStack != null) {
                            items.add(itemStack.copy());
                            inventory.setInventorySlotContents(i, null);
                        }
                    }
                }
            } catch (Exception e) {
                GraveStoneLogger.logError("Can't get access to Tinkers Construct items!!!");
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
