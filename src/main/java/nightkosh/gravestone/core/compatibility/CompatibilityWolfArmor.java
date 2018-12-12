package nightkosh.gravestone.core.compatibility;

import com.attributestudios.wolfarmor.api.IWolfArmorCapability;
import com.attributestudios.wolfarmor.api.util.Capabilities;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityWolfArmor {

    public static List<ItemStack> getWolfItems(EntityWolf wolf) {
        if (wolf.hasCapability(Capabilities.CAPABILITY_WOLF_ARMOR, null)) {
            List<ItemStack> list = new ArrayList<>();
            IWolfArmorCapability wolfCapability = wolf.getCapability(Capabilities.CAPABILITY_WOLF_ARMOR, null);
            if (wolfCapability.getHasChest()) {

                InventoryBasic inventory = wolfCapability.getInventory();
                for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                    list.add(inventory.getStackInSlot(slot).copy());
                    wolfCapability.setInventoryItem(slot, ItemStack.EMPTY);
                }
                wolfCapability.setHasChest(false);
                list.add(new ItemStack(Blocks.CHEST));
            }
            if (wolfCapability.getHasArmor()) {
                list.add(wolfCapability.getArmorItemStack().copy());
                wolfCapability.equipArmor(ItemStack.EMPTY);
            }
            return list;
        }
        return Collections.emptyList();
    }
}
