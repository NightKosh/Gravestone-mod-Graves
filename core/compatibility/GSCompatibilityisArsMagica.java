package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityisArsMagica {

    protected static boolean isInstalled = false;

    private GSCompatibilityisArsMagica() {
    }

    public static void getSoulboundItemsBack(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GraveStoneConfig.enableArsMagicaSoulbound) {
            Iterator<ItemStack> it = items.iterator();
            while (it.hasNext()) {
                ItemStack stack = it.next();
                if (stack != null && hasSoulbound(stack)) {
                    player.inventory.addItemStackToInventory(stack.copy());
                    it.remove();
                }
            }
        }
    }

    private static boolean hasSoulbound(ItemStack stack) {
        Map enchantments = EnchantmentHelper.getEnchantments(stack);
        for (Object id : enchantments.keySet()) {
            Enchantment ench = Enchantment.enchantmentsList[((Integer) id).shortValue()];
            if (ench != null && ench.getClass().getName().equals("am2.enchantments.EnchantmentSoulbound")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
