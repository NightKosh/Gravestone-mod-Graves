package nightkosh.gravestone.core.compatibility;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;
import tconstruct.api.IPlayerExtendedInventoryWrapper;
import tconstruct.api.TConstructAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityTinkerConstruct implements ICompatibility {

    public static final String MOD_ID = "tconstruct";

    private static final int ACCESSORIES_SLOTS_COUNT = 4;

    protected CompatibilityTinkerConstruct() {
        if (isModLoaded(MOD_ID) && Config.storeTinkerConstructItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
                IPlayerExtendedInventoryWrapper inventoryWrapper = TConstructAPI.getInventoryWrapper(player);
                if (inventoryWrapper != null) {
                    IInventory knapsackInventory = inventoryWrapper.getKnapsackInventory(player);
                    if (knapsackInventory != null) {
                        for (int slot = 0; slot < knapsackInventory.getSizeInventory(); slot++) {
                            ItemStack stack = knapsackInventory.getStackInSlot(slot);
                            if (stack != null && !stack.isEmpty()) {
                                items.add(stack.copy());
                                knapsackInventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                            }
                        }
                    } else {
                        GSLogger.logError("Can't get Tinkers Construct knapsack items!!!");
                    }

                    IInventory accessoryInventory = inventoryWrapper.getAccessoryInventory(player);
                    if (accessoryInventory != null) {
                        //Heart Canisters should not go in the grave as they are not supposed to be dropped on death, so only first 4 slots required
                        for (int slot = 0; slot < ACCESSORIES_SLOTS_COUNT; slot++) {
                            ItemStack stack = accessoryInventory.getStackInSlot(slot);
                            if (stack != null && !stack.isEmpty()) {
                                items.add(stack.copy());
                                accessoryInventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                            }
                        }
                    } else {
                        GSLogger.logError("Can't get Tinkers Construct accessory items!!!");
                    }
                } else {
                    GSLogger.logError("Can't get access to Tinkers Construct items!!!");
                }
                return items;
            });
        }
    }
}