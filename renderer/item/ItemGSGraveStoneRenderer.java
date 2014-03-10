package gravestone.renderer.item;

import gravestone.tileentity.TileEntityGSGraveStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.GraveStoneHelper;
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
@SideOnly(Side.CLIENT)
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
        TileEntityGSGraveStone te = new TileEntityGSGraveStone();

        if (item.stackTagCompound != null) {
            te.setGraveType(item.stackTagCompound.getByte("GraveType"));
            if (GraveStoneHelper.isSwordGrave(item.stackTagCompound.getByte("GraveType"))) {
                te.setSword(item.stackTagCompound.getByte("SwordType"));
                te.setEnchantment(item.stackTagCompound.getCompoundTag("SwordNBT"));
            }
        }

        TileEntityRenderer.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
