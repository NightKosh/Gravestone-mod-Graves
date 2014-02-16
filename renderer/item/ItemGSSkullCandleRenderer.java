
package gravestone.renderer.item;

import gravestone.tileentity.TileEntityGSSkullCandle;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSSkullCandleRenderer implements IItemRenderer {

    public ItemGSSkullCandleRenderer() {
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        TileEntityGSSkullCandle tileEntity = new TileEntityGSSkullCandle();

        if (item.stackTagCompound != null) {
            tileEntity.setSkullType(item.stackTagCompound.getByte("SkullType"));
        }

        TileEntityRenderer.instance.renderTileEntityAt(tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
    }

}
