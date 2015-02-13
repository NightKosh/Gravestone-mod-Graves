
package gravestone.item.itemblock;

import gravestone.block.enums.EnumSpawner;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSSpawner extends ItemBlock {

    public ItemBlockGSSpawner(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {
        return damageValue;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return EnumSpawner.values()[itemstack.getItemDamage()].getUnLocalizedName();
    }
}
