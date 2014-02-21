
package gravestone.core.compatibility;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBattlegear {

    private static final short FIRST_SLOT = 150;
    private static final short LAST_SLOT = 155;
    protected static boolean isInstalled = false;
    
    private GSCompatibilityBattlegear() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled()) {
            for (int slot = FIRST_SLOT; slot <= LAST_SLOT; slot++) {
                items.add(player.inventory.getStackInSlot(slot));
            }
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
