package gravestone.core.compatibility;

import gravestone.ModGraveStone;
import gravestone.api.grave_items.IPlayerItems;
import gravestone.config.GSConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author CrazyPants
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityEnderIO implements ICompatibility {

    public static final String MOD_ID = "EnderIO";

    protected GSCompatibilityEnderIO() {
        if (isModLoaded(MOD_ID) && GSConfig.enableEnderIOSoulbound) {
            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler(new IPlayerItems() {

                @Override
                public List<ItemStack> addItems(EntityPlayer player, DamageSource source) {
                    return null;
                }

                @Override
                public void getItems(EntityPlayer player, DamageSource source, List<ItemStack> items) {
                    Iterator<ItemStack> it = items.iterator();
                    while (it.hasNext()) {
                        ItemStack stack = it.next();
                        if (stack != null && hasSoulbound(stack)) {
                            player.inventory.addItemStackToInventory(stack.copy());
                            it.remove();
                        }
                    }
                }
            });
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
}
