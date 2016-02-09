package nightkosh.gravestone.core.compatibility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityRpgInventory implements ICompatibility {

    public static final String MOD_ID = "rpginventorymod";

    protected CompatibilityRpgInventory() {
        if (isModLoaded(MOD_ID) && Config.storeRpgInventoryItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<ItemStack>();
                try {
                    Class<?> clazz = Class.forName("rpgInventory.gui.rpginv.PlayerRpgInventory");
                    Method m = clazz.getDeclaredMethod("get", EntityPlayer.class);
                    Object result = m.invoke(null, player);

                    IInventory inventory = (IInventory) result;
                    if (inventory != null) {
                        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                            ItemStack stack = inventory.getStackInSlot(slot);
                            if (stack != null) {
                                items.add(stack.copy());
                                inventory.setInventorySlotContents(slot, null);
                            }
                        }
                    }
                } catch (Exception e) {
                    GSLogger.logError("Can't save RpgInventory items!!!");
                }
                return items;
            });
        }
    }
}
