package gravestone.item;

import gravestone.block.enums.EnumPileOfBones;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSPileOfBones extends ItemBlock {

    public ItemBlockGSPileOfBones(Block block) {
        super(block);
        setUnlocalizedName("Pile Of Bones");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName() + "." + EnumPileOfBones.values()[itemStack.getItemDamage()].getName();
    }
}
