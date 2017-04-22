package nightkosh.gravestone.core.compatibility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.grave_items.IPlayerItems;
import nightkosh.gravestone.config.Config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author CrazyPants
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityEnderIO implements ICompatibility {

    public static final String MOD_ID = "EnderIO";

    protected CompatibilityEnderIO() {
        if (isModLoaded(MOD_ID) && Config.enableEnderIOSoulbound) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler(new IPlayerItems() {

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
        for (Object ench : enchantments.keySet()) {
            if (ench != null && ((Enchantment) ench).getName().equals("enchantment.soulBound")) {
                return true;
            }
        }
        return false;
    }
}
