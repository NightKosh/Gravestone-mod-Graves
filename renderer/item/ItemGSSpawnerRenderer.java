/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.renderer.item;

import gravestone.tileentity.TileEntityGSSpawner;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSSpawnerRenderer implements IItemRenderer {

    public ItemGSSpawnerRenderer() {
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
    public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
        TileEntityGSSpawner te = new TileEntityGSSpawner();
        //TODO
//        te.blockMetadata = itemstack.getItemDamage();

        TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);
    }
}
