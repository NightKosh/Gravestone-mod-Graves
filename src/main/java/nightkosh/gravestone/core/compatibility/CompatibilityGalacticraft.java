package nightkosh.gravestone.core.compatibility;

import micdoodle8.mods.galacticraft.api.inventory.AccessInventoryGC;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityGalacticraft implements ICompatibility {

    public static final String MOD_ID = "galacticraftcore";

    protected CompatibilityGalacticraft() {
        if (isModLoaded(MOD_ID) && Config.storeGalacticraftItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
                IInventory inventory = AccessInventoryGC.getGCInventoryForPlayer((EntityPlayerMP) player);
                if (inventory != null) {
                    for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                        ItemStack stack = inventory.getStackInSlot(slot);
                        if (stack != null && !stack.isEmpty()) {
                            items.add(stack.copy());
                            inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                        }
                    }
                } else {
                    GSLogger.logError("Can't save Galacticraft items!!!");
                }
                return items;
            });
        }
    }
}
