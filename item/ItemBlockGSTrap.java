/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.item;

import gravestone.block.enums.EnumTrap;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSTrap extends ItemBlock {

    public ItemBlockGSTrap(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return EnumTrap.values()[itemstack.getItemDamage()].getLocalizedName();
    }

}
