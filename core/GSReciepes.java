
package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSReciepes {

    private GSReciepes() {
        
    }

    public static void registration() {
        // chisel reciep
        GameRegistry.addRecipe(new ItemStack(GSItem.chisel), "   ", "s  ", " i ", 's', Item.stick, 'i', Item.ingotIron);
        // bone blocks
        /*GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock), "xxx", "xxx", "xxx", 'x', Item.bone);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneBlock), "x", "x", 'x', GSBlock.boneSlab);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneSlab, 6), "xxx", 'x', GSBlock.boneBlock);
        GameRegistry.addRecipe(new ItemStack(GSBlock.boneStairs, 4), "x  ", "xx ", "xxx", 'x', GSBlock.boneBlock);*/
    }
}
