package nightkosh.gravestone.helper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

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
        return hasEnchantment(item, Enchantment.getEnchantmentID(enchantment), lvl);
    }

    public static boolean hasEnchantment(ItemStack item, int id, int lvl) {
        NBTTagList nbtList = item.getEnchantmentTagList();
        for (NBTBase nbt : nbtList) {
            if (((NBTTagCompound) nbt).getInteger("id") == id) {
                return lvl == 0 || lvl == ((NBTTagCompound) nbt).getShort("lvl");
            }
        }
        return false;
    }
}
