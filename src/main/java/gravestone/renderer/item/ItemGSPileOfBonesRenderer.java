package gravestone.renderer.item;

import gravestone.tileentity.TileEntityGSPileOfBones;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSPileOfBonesRenderer implements IItemRenderer {

    public ItemGSPileOfBonesRenderer() {
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
        TileEntityGSPileOfBones te = new TileEntityGSPileOfBones();
        //TODO
//        te.blockMetadata = item.getItemDamage();
        TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);
    }
}
