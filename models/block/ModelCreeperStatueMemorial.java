package GraveStone.models.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public class ModelCreeperStatueMemorial extends ModelGraveStone {

    public ModelRenderer head;
    public ModelRenderer field_78133_b;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    ModelRenderer Pedestal;
    ModelRenderer Pedestal2;

    public ModelCreeperStatueMemorial() {
        textureWidth = 64;
        textureHeight = 64;

        float par1 = 0;
        byte b0 = 4;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.head.setRotationPoint(0.0F, (float) b0, 0.0F);
        this.field_78133_b = new ModelRenderer(this, 32, 0);
        this.field_78133_b.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.field_78133_b.setRotationPoint(0.0F, (float) b0, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.body.setRotationPoint(0.0F, (float) b0, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg1.setRotationPoint(-2.0F, (float) (12 + b0), 4.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg2.setRotationPoint(2.0F, (float) (12 + b0), 4.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg3.setRotationPoint(-2.0F, (float) (12 + b0), -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, par1);
        this.leg4.setRotationPoint(2.0F, (float) (12 + b0), -4.0F);


        Pedestal = new ModelRenderer(this, 0, 32);
        Pedestal.addBox(0F, 0F, 0F, 16, 4, 16);
        Pedestal.setRotationPoint(-8F, 20F, -8F);
        Pedestal.setTextureSize(64, 64);
        Pedestal.mirror = true;
        setRotation(Pedestal, 0F, 0F, 0F);
        Pedestal2 = new ModelRenderer(this, 2, 34);
        Pedestal2.addBox(0F, 0F, 0F, 14, 4, 14);
        Pedestal2.setRotationPoint(-7F, 16F, -7F);
        Pedestal2.setTextureSize(64, 64);
        Pedestal2.mirror = true;
        setRotation(Pedestal2, 0F, 0F, 0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7);
        this.head.render(par7);
        this.body.render(par7);
        this.leg1.render(par7);
        this.leg2.render(par7);
        this.leg3.render(par7);
        this.leg4.render(par7);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }

    @Override
    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F);
        float par7 = 0.0625F;

        Pedestal.render(par7);
        Pedestal2.render(par7);

        GL11.glTranslated(0, -0.3, 0);

        renderCreeper();
    }

    @Override
    public void customRender() {
        renderAll();
        renderCreeperCharging();
    }

    private void renderCreeper() {
        float par7 = 0.0625F;

        this.head.render(par7);
        this.body.render(par7);
        this.leg1.render(par7);
        this.leg2.render(par7);
        this.leg3.render(par7);
        this.leg4.render(par7);

    }

    private void renderCreeperCharging() {
        GL11.glTranslated(0, 0.29, 0);
        float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;

        float scale = 1.2F;
        GL11.glScalef(scale, scale, scale);
        
        TileEntityRenderer.instance.renderEngine.bindTexture("/armor/power.png");
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        for (int var21 = 0; var21 < 2; ++var21) {
            float f4 = 0.5F;
            GL11.glColor4f(f4, f4, f4, 1.0F);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glLoadIdentity();
            float var23 = tickModifier * (0.001F + (float) var21 * 0.003F) * 20.0F;
            GL11.glTranslatef(0, var23, 0);

            renderCreeper();
        }

        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
    }
}
