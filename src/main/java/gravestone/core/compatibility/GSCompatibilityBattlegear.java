package gravestone.core.compatibility;

import gravestone.config.GSConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBattlegear {

    public static final String MOD_ID = "battlegear2";

    private static final short FIRST_SLOT = 150;
    private static final short LAST_SLOT = 155;
    protected static boolean isInstalled = false;

    private GSCompatibilityBattlegear() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GSConfig.storeBattlegearItems) {
            for (int slot = FIRST_SLOT; slot <= LAST_SLOT; slot++) {
                items.add(player.inventory.getStackInSlot(slot));
                player.inventory.setInventorySlotContents(slot, null);
            }
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
