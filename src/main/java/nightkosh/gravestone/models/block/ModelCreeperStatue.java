package nightkosh.gravestone.models.block;

import net.minecraft.client.Minecraft;
import nightkosh.gravestone.core.GSResources;
import nightkosh.gravestone.renderer.tileentity.TileEntityGraveStoneRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//TODO
//@SideOnly(Side.CLIENT)
public class ModelCreeperStatue {//extends ModelGraveStone {

    //TODO
//    public ModelRenderer head;
//    public ModelRenderer field_78133_b;
//    public ModelRenderer body;
//    public ModelRenderer leg1;
//    public ModelRenderer leg2;
//    public ModelRenderer leg3;
//    public ModelRenderer leg4;

    public ModelCreeperStatue() {
        //TODO
//        textureWidth = 64;
//        textureHeight = 32;
//        float par1 = 0;
//        byte b0 = 4;
//        byte b1 = (byte) (12 + b0);
//
//        this.head = new ModelRenderer(this, 0, 0);
//        this.head.addBox(-4, -8, -4, 8, 8, 8, par1);
//        this.head.setRotationPoint(0, b0, 0);
//        this.field_78133_b = new ModelRenderer(this, 32, 0);
//        this.field_78133_b.addBox(-4, -8, -4, 8, 8, 8, par1 + 0.5F);
//        this.field_78133_b.setRotationPoint(0, b0, 0);
//        this.body = new ModelRenderer(this, 16, 16);
//        this.body.addBox(-4, 0, -2, 8, 12, 4, par1);
//        this.body.setRotationPoint(0, b0, 0);
//        this.leg1 = new ModelRenderer(this, 0, 16);
//        this.leg1.addBox(-2, 0, -2, 4, 6, 4, par1);
//        this.leg1.setRotationPoint(-2, b1, 4);
//        this.leg2 = new ModelRenderer(this, 0, 16);
//        this.leg2.addBox(-2, 0, -2, 4, 6, 4, par1);
//        this.leg2.setRotationPoint(2, b1, 4);
//        this.leg3 = new ModelRenderer(this, 0, 16);
//        this.leg3.addBox(-2, 0, -2, 4, 6, 4, par1);
//        this.leg3.setRotationPoint(-2, b1, -4);
//        this.leg4 = new ModelRenderer(this, 0, 16);
//        this.leg4.addBox(-2, 0, -2, 4, 6, 4, par1);
//        this.leg4.setRotationPoint(2, b1, -4);
    }

    //TODO
//    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
//        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
//        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
//        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
//        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
//        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
//    }
//
//    @Override
//    public void renderAll() {
//        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F);
//        renderCreeper();
//    }
//
//    @Override
//    public void customRender(boolean enchanted) {
//        if (enchanted) {
//            renderEnchanted();
//        } else {
//            renderAll();
//        }
//        renderCreeperCharging();
//    }
//
//    private void renderCreeper() {
//        float par7 = 0.0625F;
//        this.head.render(par7);
//        this.body.render(par7);
//        this.leg1.render(par7);
//        this.leg2.render(par7);
//        this.leg3.render(par7);
//        this.leg4.render(par7);
//    }
//
//    private void renderCreeperCharging() {
//        float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000 * 48;
//        float scale = 1.2F;
//
//        GL11.glTranslated(0, 0.3, 0);
//        GL11.glScalef(scale, scale, scale);
//        TileEntityGraveStoneRenderer.instance.bindTextureByName(GSResources.CREEPER_AURA);
//
//        GlStateManager.depthMask(true);
//        GlStateManager.matrixMode(GL11.GL_TEXTURE);
//        GlStateManager.enableBlend();
//        GlStateManager.disableLighting();
//        GlStateManager.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
//
//        for (int var21 = 0; var21 < 3; var21++) {
//            GlStateManager.loadIdentity();
//            float var23 = tickModifier * (0.001F + (float) var21 * 0.0015F) * 15;
//            GL11.glTranslatef(0, var23, 0);
//            renderCreeper();
//        }
//
//        GlStateManager.matrixMode(GL11.GL_TEXTURE);
//        GlStateManager.loadIdentity();
//        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.depthFunc(GL11.GL_LEQUAL);
//        GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
//    }

}