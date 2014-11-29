package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import gravestone.core.logger.GSLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
            try {
                Class<?> GCPlayerStatsClass = Class.forName("micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats");
                if (GCPlayerStatsClass != null) {
                    Method getStatsMethod = GCPlayerStatsClass.getDeclaredMethod("get", EntityPlayerMP.class);
                    Field getExtendedInventoryField = GCPlayerStatsClass.getDeclaredField("extendedInventory");

                    if (getStatsMethod != null && getExtendedInventoryField != null) {
                        Object gcInventory = getExtendedInventoryField.get(getStatsMethod.invoke(null, (EntityPlayerMP) player));
                        if (gcInventory != null && gcInventory instanceof IInventory) {

                            IInventory inventory = (IInventory) gcInventory;
                            for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                                ItemStack stack = inventory.getStackInSlot(slot);
                                if (stack != null) {
                                    items.add(stack.copy());
                                    inventory.setInventorySlotContents(slot, null);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                GSLogger.logError("Can't save Galacticraft items!!!");
                e.printStackTrace();
            }
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
