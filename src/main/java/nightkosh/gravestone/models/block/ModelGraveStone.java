package nightkosh.gravestone.models.block;

import nightkosh.gravestone.core.Resources;
import nightkosh.gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
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
        float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000F * 48;
        TileEntityGSGraveStoneRenderer.instance.bindTextureByName(Resources.SWORD_AURA);

        GlStateManager.enableBlend();
        GlStateManager.depthFunc(GL11.GL_EQUAL);
        GlStateManager.depthMask(false);
        float color = 0.5F;
        GlStateManager.color(color, color, color, 1);

        for (int i = 0; i < 3; i++) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float color2 = 0.76F;
            GlStateManager.color(0.5F * color2, 0.25F * color2, 0.8F * color2, 1);
            GlStateManager.matrixMode(GL11.GL_TEXTURE);
            GlStateManager.loadIdentity();

            float var23 = tickModifier * (0.001F + i * 0.0015F) * 15;
            float scale = 0.33F;
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.rotate(30 - i * 60, 0, 0, 1);
            GlStateManager.translate(0, var23, 0);
            GlStateManager.matrixMode(GL11.GL_MODELVIEW);

            renderAll();
        }

        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(GL11.GL_LEQUAL);
        GlStateManager.disableBlend();
    }
}
