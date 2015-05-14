package gravestone.core.compatibility;

import gravestone.config.GSConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author CrazyPants
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityEnderIO {
  
  protected static boolean isInstalled = false;

  private GSCompatibilityEnderIO() {
  }

  public static void getSoulboundItemsBack(List<ItemStack> items, EntityPlayer player) {
      if (isInstalled() && GSConfig.enableEnderIOSoulbound) {
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
          Enchantment ench = Enchantment.enchantmentsBookList[((Integer) id).shortValue()];
          if (ench != null && ench.getName().equals("enchantment.enderio.soulBound")) {
              return true;
          }
      }
      return false;
  }

  public static boolean isInstalled() {
      return isInstalled;
  }
  
}
