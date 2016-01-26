package gravestone.core.compatibility;

import gravestone.config.GSConfig;
import gravestone.core.logger.GSLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityBackpacksMod {

    public static final String MOD_ID = "Backpack";

    protected static boolean isInstalled = false;

    private GSCompatibilityBackpacksMod() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isInstalled() && GSConfig.storeBackpacksItems) {
            try {
                Class<?> PlayerSaveClass = Class.forName("de.eydamos.backpack.saves.PlayerSave");
                if (PlayerSaveClass != null) {
                    Constructor constructor = PlayerSaveClass.getConstructor(EntityPlayer.class);
                    Object playerSave = constructor.newInstance(player);

                    Method getPersonalBackpackMethod = playerSave.getClass().getDeclaredMethod("getPersonalBackpack");
                    Method setPersonalBackpackMethod = playerSave.getClass().getDeclaredMethod("setPersonalBackpack", ItemStack.class);

                    if (getPersonalBackpackMethod != null && setPersonalBackpackMethod != null) {
                        Object backpackObject = getPersonalBackpackMethod.invoke(playerSave);
                        if (backpackObject != null && backpackObject instanceof ItemStack) {
                            items.add(((ItemStack) backpackObject).copy());
                            setPersonalBackpackMethod.invoke(playerSave, (ItemStack) null);
                        }
                    }
                }
            } catch (Exception e) {
                GSLogger.logError("Can't save Backpacks items!!!");
                e.printStackTrace();
            }
        }
    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
