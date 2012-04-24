package GraveStone.renderer.item;

import GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

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
        
        TileEntityRenderer.instance.renderTileEntityAt(entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
