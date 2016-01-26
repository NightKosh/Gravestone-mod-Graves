package nightkosh.gravestone.core.compatibility;

import nightkosh.gravestone.ModGraveStone;
import nightkosh.gravestone.config.GSConfig;
import nightkosh.gravestone.core.logger.GSLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
public class GSCompatibilityBackpacksMod implements ICompatibility {

    public static final String MOD_ID = "Backpack";

    protected GSCompatibilityBackpacksMod() {
        if (isModLoaded(MOD_ID) && GSConfig.storeBackpacksItems) {
            ModGraveStone.apiGraveGeneration.addPlayerItemsHandler((player, source) -> {
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
                            if (backpackObject != null && backpackObject instanceof ItemStack) {
                                items.add(((ItemStack) backpackObject).copy());
                                setPersonalBackpackMethod.invoke(playerSave, (ItemStack) null);
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
