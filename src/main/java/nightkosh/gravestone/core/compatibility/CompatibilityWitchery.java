package nightkosh.gravestone.core.compatibility;

import nightkosh.gravestone.ModGraveStone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityWitchery implements ICompatibility {

    public static final String MOD_ID = "witchery";

    protected CompatibilityWitchery() {
        if (isModLoaded(MOD_ID)) {
            ModGraveStone.apiGraveGeneration.addPlayerDeathHandler((player, source) -> {
                        try {
                            Class playerClass = Class.forName("com.emoniph.witchery.common.ExtendedPlayer");
                            Method getPlayerMethod = playerClass.getDeclaredMethod("get", EntityPlayer.class);
                            Object extendedPlayer = getPlayerMethod.invoke(null, player);

                            Method isVampireMethod = playerClass.getDeclaredMethod("isVampire");

                            if ((Boolean) isVampireMethod.invoke(extendedPlayer)) {
                                Class utilClass = Class.forName("com.emoniph.witchery.util.CreatureUtil");
                                Method checkForDeathMethod = utilClass.getDeclaredMethod("checkForVampireDeath", EntityLivingBase.class, DamageSource.class);
                                return !(Boolean) checkForDeathMethod.invoke(null, player, source);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
            );
        }
    }
}
