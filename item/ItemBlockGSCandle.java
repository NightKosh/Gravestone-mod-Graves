
package gravestone.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockGSCandle extends ItemBlock {

    public ItemBlockGSCandle(int id) {
        super(id);
        setUnlocalizedName("Candle");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "candle";
    }
}
