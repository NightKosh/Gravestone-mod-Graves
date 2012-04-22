package GraveStone;

import GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemGSGraveStoneRenderer implements IItemRenderer {

    public ItemGSGraveStoneRenderer() {
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
        TileEntityGSGraveStone entity = new TileEntityGSGraveStone();
        if (item.stackTagCompound != null) {
            entity.setGraveType(item.stackTagCompound.getByte("GraveType"));
        }

        TileEntityRenderer.instance.renderTileEntityAt(entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
