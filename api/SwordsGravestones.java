package gravestone.api;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.Item;

import java.lang.reflect.Method;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SwordsGravestones {

    public static void addSwordGravestone(Item sword) {
        try {
            Class<?> aClass = Class.forName("gravestone.block.GraveStoneHelper");
            Method method = aClass.getDeclaredMethod("addSwordToSwordsList", Item.class);
            method.invoke(null, sword);
        } catch (Exception e) {
            FMLLog.warning("[GraveStone] Can't add sword to swords gravestones list!");
            e.printStackTrace();
        }
    }
}
