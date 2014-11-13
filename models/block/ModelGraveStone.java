package gravestone.models.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.core.Resources;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public abstract class ModelGraveStone extends ModelBase {

    public abstract void renderAll();

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * Custom render
     */
    public void customRender(boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void renderEnchanted() {
        renderAll();
        renderEnchantment();
    }

    protected void renderEnchantment() {
        float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
        TileEntityGSGraveStoneRenderer.instance.bindTextureByName(Resources.SWORD_AURA);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
        GL11.glMatrixMode(GL11.GL_TEXTURE);

        for (int var21 = 0; var21 < 3; var21++) {
            GL11.glDisable(GL11.GL_LIGHTING);
            float var22 = 0.76F;
            GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float var23 = tickModifier * (0.001F + (float) var21 * 0.0015F) * 15;
            float var24 = 0.33333334F;
            GL11.glScalef(var24, var24, var24);
            GL11.glRotatef(30 - (float) var21 * 60, 0, 0, 1);
            GL11.glTranslatef(0, var23, 0);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            renderAll();
        }

        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glDepthMask(true);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
}
