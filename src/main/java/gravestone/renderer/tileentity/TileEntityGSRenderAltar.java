package gravestone.renderer.tileentity;

import gravestone.renderer.item.ItemGSCorpseRenderer;
import gravestone.tileentity.TileEntityGSAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSRenderAltar extends TileEntitySpecialRenderer {

    private static final ItemGSCorpseRenderer corpseRenderer = new ItemGSCorpseRenderer();

    public void renderTileEntityAt(TileEntityGSAltar te, double x, double y, double z, float f) {
        ItemStack corpse = te.getCorpse();
        if (corpse != null) {
            GL11.glPushMatrix();

            float time = Minecraft.getMinecraft().theWorld.getTotalWorldTime() + f;
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.2F, (float) z + 0.5F);
            GL11.glRotatef(time % 360, 0, 1, 0);

//            EntityItem entityItem = new EntityItem(te.getWorld(), 0, 0, 0, corpse);
//            if (corpse.hasTagCompound()) {
//                entityItem.getEntityItem().setTagCompound((NBTTagCompound) corpse.getTagCompound().copy());
//            }
//            entityItem.hoverStart = 0;
//
//            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityItem, 0, 0, 0, 0, 0);

            corpseRenderer.renderItem(corpse);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double z, double y, float f, int p_180535_9_) {
        this.renderTileEntityAt((TileEntityGSAltar) te, x, y, z, f);
    }
}
