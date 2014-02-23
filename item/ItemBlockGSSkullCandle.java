
package gravestone.item;

import gravestone.block.enums.EnumSkullCandle;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSSkullCandle extends ItemBlock {

    public ItemBlockGSSkullCandle(int id) {
        super(id);
        setHasSubtypes(true);
        setUnlocalizedName("Skull Candle");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName() + "." + EnumSkullCandle.values()[itemStack.getItemDamage()].getName();
    }
}
