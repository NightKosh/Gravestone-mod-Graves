package gravestone.renderer.item;

import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

        if (item.hasTagCompound()) {
            te.setGraveType(item.getTagCompound().getInteger("Type"));
            if (item.getTagCompound().hasKey("Sword")) {
                te.setSword(ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("Sword")));
            }
            if (item.getTagCompound().hasKey("Enchanted")) {
                te.setEnchanted(item.getTagCompound().getBoolean("Enchanted"));
            }
        }

        TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
