package nightkosh.gravestone.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnchantmentHelper {

    public static boolean hasEnchantment(ItemStack item, Enchantment enchantment) {
        return hasEnchantment(item, enchantment, 0);
    }

    public static boolean hasEnchantment(ItemStack item, Enchantment enchantment, int lvl) {
        return false;//TODO hasEnchantment(item, Enchantment.getEnchantmentID(enchantment), lvl);
    }

    public static boolean hasEnchantment(ItemStack item, int id, int lvl) {
        var tags = item.getEnchantmentTags();
        for (var tag : tags) {
            if (((CompoundTag) tag).getInt("id") == id) {
                return lvl == 0 || lvl == ((CompoundTag) tag).getShort("lvl");
            }
        }
        return false;
    }

}
