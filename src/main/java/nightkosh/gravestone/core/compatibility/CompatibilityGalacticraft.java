package nightkosh.gravestone.core.compatibility;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;
import micdoodle8.mods.galacticraft.api.inventory.AccessInventoryGC;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityGalacticraft implements ICompatibility {

    public static final String MOD_ID = "GalacticraftCore";

    protected CompatibilityGalacticraft() {
        if (isModLoaded(MOD_ID) && Config.storeGalacticraftItems) {
            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
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
                return items;
            });
        }
    }
}
