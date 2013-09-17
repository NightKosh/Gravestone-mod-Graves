package gravestone.models.block;

import gravestone.core.Resources;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelSwordGrave extends ModelGraveStone {

    ModelRenderer Blade1;
    ModelRenderer Blade2;
    ModelRenderer Blade3;
    ModelRenderer Blade4;
    ModelRenderer Shape3;
    ModelRenderer Hilt;

    public ModelSwordGrave() {
        textureWidth = 32;
        textureHeight = 32;
        Blade1 = new ModelRenderer(this, 0, 0);
        Blade1.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade1.setRotationPoint(-1F, 14F, 0F);
        Blade1.setTextureSize(64, 32);
        Blade1.mirror = true;
        setRotation(Blade1, 0F, 0.2617994F, 0F);
        Blade2 = new ModelRenderer(this, 2, 0);
        Blade2.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade2.setRotationPoint(-1F, 14F, 0F);
        Blade2.setTextureSize(64, 32);
        Blade2.mirror = true;
        setRotation(Blade2, 0F, -0.2617994F, 0F);
        Blade3 = new ModelRenderer(this, 2, 0);
        Blade3.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade3.setRotationPoint(0.9F, 14F, 0F);
        Blade3.setTextureSize(64, 32);
        Blade3.mirror = true;
        setRotation(Blade3, 0F, 2.879793F, 0F);
        Blade4 = new ModelRenderer(this, 0, 0);
        Blade4.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade4.setRotationPoint(0.9F, 14F, 0F);
        Blade4.setTextureSize(64, 32);
        Blade4.mirror = true;
        setRotation(Blade4, 0F, -2.879793F, 0F);
        Shape3 = new ModelRenderer(this, 4, 0);
        Shape3.addBox(0F, 0F, 0F, 4, 1, 1);
        Shape3.setRotationPoint(-2F, 13F, -0.5F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Hilt = new ModelRenderer(this, 4, 2);
        Hilt.addBox(0F, 0F, 0F, 1, 3, 1);
        Hilt.setRotationPoint(-0.5F, 10F, -0.5F);
        Hilt.setTextureSize(64, 32);
        Hilt.mirror = true;
        setRotation(Hilt, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        Blade1.render(0.0625F);
        Blade2.render(0.0625F);
        Blade3.render(0.0625F);
        Blade4.render(0.0625F);
        Shape3.render(0.0625F);
        Hilt.render(0.0625F);
    }

    @Override
    public void customRender() {
        renderAll();
        renderEnchantment();
    }

    private void renderEnchantment() {
        float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
        TileEntityGSGraveStoneRenderer.instance.bindTextureByName(Resources.SWORD_AURA);
        GL11.glEnable(GL11.GL_BLEND);
        float var20 = 0.5F;
        GL11.glColor4f(var20, var20, var20, 1);
        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDepthMask(false);

        for (int var21 = 0; var21 < 2; var21++) {
            GL11.glDisable(GL11.GL_LIGHTING);
            float var22 = 0.76F;
            GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float var23 = tickModifier * (0.001F + (float) var21 * 0.003F) * 20;
            float var24 = 0.33333334F;
            GL11.glScalef(var24, var24, var24);
            GL11.glRotatef(30 - (float) var21 * 60, 0, 0, 1);
            GL11.glTranslatef(0, var23, 0);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            renderAll();
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glDepthMask(true);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }
}
