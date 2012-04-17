package net.minecraft.GraveStone;

import net.minecraft.GraveStone.models.ModelMemorialCross;
import net.minecraft.GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemGSMemorialRenderer implements IItemRenderer {

    private ModelMemorialCross GSMemorialCross;

    public ItemGSMemorialRenderer() {
        GSMemorialCross = new ModelMemorialCross();
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
        entity.blockMetadata = item.getItemDamage();// - (item.getItemDamage() % 4);
        TileEntityRenderer.instance.renderTileEntityAt(entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
