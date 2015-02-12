package gravestone.renderer.item;

import gravestone.block.enums.EnumHangedMobs;
import gravestone.tileentity.TileEntityGSMemorial;
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
        TileEntityGSMemorial te = new TileEntityGSMemorial();

        if (item.hasTagCompound()) {
            te.setGraveType(item.getTagCompound().getByte("GraveType"));
            if (item.getTagCompound().hasKey("Enchanted")) {
                te.setEnchanted(item.getTagCompound().getBoolean("Enchanted"));
            }
            if (item.getTagCompound().hasKey("HangedMob")) {
                te.setHangedMob(EnumHangedMobs.getById(item.getTagCompound().getByte("HangedMob")));
                te.setHangedVillagerProfession(item.getTagCompound().getInteger("HangedVillagerProfession"));
            }
        }

        TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);
    }
}
