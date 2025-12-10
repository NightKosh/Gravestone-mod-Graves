package nightkosh.gravestone.core.compatibility;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.GSConfigs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.mojang.text2speech.Narrator.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityRpgInventory implements ICompatibility {

    public static final String MOD_ID = "rpginventorymod";

    protected CompatibilityRpgInventory() {
        if (isModLoaded(MOD_ID) && GSConfigs.storeRpgInventoryItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                List<ItemStack> items = new ArrayList<>();
                try {
                    Class<?> clazz = Class.forName("rpgInventory.gui.rpginv.PlayerRpgInventory");
                    Method m = clazz.getDeclaredMethod("get", Player.class);
                    Object result = m.invoke(null, player);

                    IInventory inventory = (IInventory) result;
                    if (inventory != null) {
                        for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                            ItemStack stack = inventory.getStackInSlot(slot);
                            if (stack != null && !stack.isEmpty()) {
                                items.add(stack.copy());
                                inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
                            }
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("Can't save RpgInventory items!!!");
                }
                return items;
            });
        }
    }

}
