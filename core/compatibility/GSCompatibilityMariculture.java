package gravestone.core.compatibility;

import gravestone.GraveStoneLogger;
import mariculture.api.core.MaricultureHandlers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;
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
        if (isLoaded()) {
            ItemStack[] mirrorItems = MaricultureHandlers.mirror.getMirrorContents(player);
            for (ItemStack item : mirrorItems) {
                items.add(item.copy());
            }

            try {
                Class<?> mirrorHelperClass = Class.forName("mariculture.magic.MirrorHelper");
                Method saveMethod = mirrorHelperClass.getDeclaredMethod("save", EntityPlayer.class, ItemStack[].class);
                saveMethod.invoke(null, player, new ItemStack[3]);
            } catch (Exception e) {
                GraveStoneLogger.logError("Can't clear mariculture mirror items!!!");
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
