package nightkosh.gravestone.core.compatibility;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.api.grave_items.IPlayerItems;
import nightkosh.gravestone.config.GSConfigs;

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

    public static final String MOD_ID = "enderio";

    protected CompatibilityEnderIO() {
        //TODO
//        if (isModLoaded(MOD_ID) && GSConfigs.enableEnderIOSoulbound) {
//            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler(new IPlayerItems() {
//
//                @Override
//                public List<ItemStack> addItems(Player player, DamageSource source) {
//                    return null;
//                }
//
//                @Override
//                public void getItems(Player player, DamageSource source, List<ItemStack> items) {
//                    Iterator<ItemStack> it = items.iterator();
//                    while (it.hasNext()) {
//                        ItemStack stack = it.next();
//                        if (stack != null && hasSoulbound(stack)) {
//                            player.inventory.addItemStackToInventory(stack.copy());
//                            it.remove();
//                        }
//                    }
//                }
//            });
//        }
    }

    //TODO
//    private static boolean hasSoulbound(ItemStack stack) {
//        Map enchantments = EnchantmentHelper.getEnchantments(stack);
//        for (Object ench : enchantments.keySet()) {
//            if (ench != null && ((Enchantment) ench).getName().equals("enchantment.enderio.soulbound")) {
//                return true;
//            }
//        }
//        return false;
//    }

}
