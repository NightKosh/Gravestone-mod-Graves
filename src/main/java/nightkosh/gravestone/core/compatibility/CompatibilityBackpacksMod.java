package nightkosh.gravestone.core.compatibility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import nightkosh.gravestone.api.GraveStoneAPI;
import nightkosh.gravestone.config.Config;
import nightkosh.gravestone.core.logger.GSLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityBackpacksMod implements ICompatibility {

    public static final String MOD_ID = "backpack";

    protected CompatibilityBackpacksMod() {
        if (isModLoaded(MOD_ID) && Config.storeBackpacksItems) {
            GraveStoneAPI.graveGenerationAtDeath.addPlayerItemsHandler((player, source) -> {
                try {
                    List<ItemStack> items = new ArrayList<>();
                    Class<?> PlayerSaveClass = Class.forName("de.eydamos.backpack.saves.PlayerSave");
                    if (PlayerSaveClass != null) {
                        Constructor constructor = PlayerSaveClass.getConstructor(EntityPlayer.class);
                        Object playerSave = constructor.newInstance(player);

                        Method getPersonalBackpackMethod = playerSave.getClass().getDeclaredMethod("getPersonalBackpack");
                        Method setPersonalBackpackMethod = playerSave.getClass().getDeclaredMethod("setPersonalBackpack", ItemStack.class);

                        if (getPersonalBackpackMethod != null && setPersonalBackpackMethod != null) {
                            Object backpackObject = getPersonalBackpackMethod.invoke(playerSave);
                            if (backpackObject != null && backpackObject instanceof ItemStack && !((ItemStack) backpackObject).isEmpty()) {
                                items.add(((ItemStack) backpackObject).copy());
                                setPersonalBackpackMethod.invoke(playerSave, ItemStack.EMPTY);
                            }
                        }
                    }
                    return items;
                } catch (Exception e) {
                    GSLogger.logError("Can't save Backpacks items!!!");
                    e.printStackTrace();
                    return null;
                }
            });
        }
    }
}
