package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import mariculture.api.core.MaricultureHandlers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityMariculture {

    protected static boolean isInstalled = false;

    private GSCompatibilityMariculture() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isLoaded() && GraveStoneConfig.storeMaricultureItems) {
            if (MaricultureHandlers.mirror != null) {
                ItemStack[] mirrorItems = MaricultureHandlers.mirror.getMirrorContents(player);
                if (mirrorItems != null) {
                    for (ItemStack item : mirrorItems) {
                        if (item != null) {
                            items.add(item.copy());
                        }
                    }
                    MaricultureHandlers.mirror.setMirrorContents(player, new ItemStack[4]);
                }
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
