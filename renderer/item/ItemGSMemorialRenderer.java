package gravestone.renderer.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ItemGSMemorialRenderer implements IItemRenderer {

    public ItemGSMemorialRenderer() {
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
        TileEntityGSMemorial entity = new TileEntityGSMemorial();

        if (item.stackTagCompound != null) {
            entity.setGraveType(item.stackTagCompound.getByte("GraveType"));
        }

        TileEntityRendererDispatcher.instance.renderTileEntityAt(entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
