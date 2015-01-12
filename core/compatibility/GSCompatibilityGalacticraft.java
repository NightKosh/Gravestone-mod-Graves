package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import gravestone.core.logger.GSLogger;
import micdoodle8.mods.galacticraft.api.inventory.AccessInventoryGC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityGalacticraft {

    protected static boolean isInstalled = false;

    private GSCompatibilityGalacticraft() {

    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GraveStoneConfig.storeGalacticraftItems) {
            IInventory inventory = AccessInventoryGC.getGCInventoryForPlayer((EntityPlayerMP) player);
            if (inventory != null) {
                for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                    ItemStack stack = inventory.getStackInSlot(slot);
                    if (stack != null) {
                        items.add(stack.copy());
                        inventory.setInventorySlotContents(slot, null);
                    }
                }
            } else {
                GSLogger.logError("Can't save Galacticraft items!!!");
            }
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
