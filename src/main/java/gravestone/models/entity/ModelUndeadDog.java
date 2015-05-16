package gravestone.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelUndeadDog extends ModelBase {

    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer tail;
    public ModelRenderer mane;

    public ModelUndeadDog() {
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3, -3, -2, 6, 6, 4, 0);
        this.head.setRotationPoint(-1, 13.5F, -7);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-4, -2, -3, 6, 9, 6, 0);
        this.body.setRotationPoint(0, 14, 2);
        this.mane = new ModelRenderer(this, 21, 0);
        this.mane.addBox(-4, -3, -3, 8, 6, 7, 0);
        this.mane.setRotationPoint(-1, 14, 2);
        this.leg1 = new ModelRenderer(this, 0, 18);
        this.leg1.addBox(-1, 0, -1, 2, 8, 2, 0);
        this.leg1.setRotationPoint(-2.5F, 16, 7);
        this.leg2 = new ModelRenderer(this, 0, 18);
        this.leg2.addBox(-1, 0, -1, 2, 8, 2, 0);
        this.leg2.setRotationPoint(0.5F, 16, 7);
        this.leg3 = new ModelRenderer(this, 0, 18);
        this.leg3.addBox(-1, 0, -1, 2, 8, 2, 0);
        this.leg3.setRotationPoint(-2.5F, 16, -4);
        this.leg4 = new ModelRenderer(this, 0, 18);
        this.leg4.addBox(-1, 0, -1, 2, 8, 2, 0);
        this.leg4.setRotationPoint(0.5F, 16, -4);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.addBox(-1, 0, -1, 2, 8, 2, 0);
        this.tail.setRotationPoint(-1, 12, 8);
        this.head.setTextureOffset(16, 14).addBox(-3, -5, 0, 2, 2, 1, 0);
        this.head.setTextureOffset(16, 14).addBox(1, -5, 0, 2, 2, 1, 0);
        this.head.setTextureOffset(0, 10).addBox(-1.5F, 0, -5, 3, 3, 4, 0);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
        this.head.renderWithRotation(par7);
        this.body.render(par7);
        this.leg1.render(par7);
        this.leg2.render(par7);
        this.leg3.render(par7);
        this.leg4.render(par7);
        this.tail.renderWithRotation(par7);
        this.mane.render(par7);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third
     * float params here are the same second and third as in the
     * setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase entityLiving, float par2, float par3, float par4) {
        this.tail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        this.body.setRotationPoint(0, 14, 2);
        this.body.rotateAngleX = ((float) Math.PI / 2F);
        this.mane.setRotationPoint(-1, 14, -3);
        this.mane.rotateAngleX = this.body.rotateAngleX;
        this.tail.setRotationPoint(-1, 12, 8);
        this.leg1.setRotationPoint(-2.5F, 16, 7);
        this.leg2.setRotationPoint(0.5F, 16, 7);
        this.leg3.setRotationPoint(-2.5F, 16, -4);
        this.leg4.setRotationPoint(0.5F, 16, -4);
        this.leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        this.leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
        this.leg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
        this.leg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.tail.rotateAngleX = par3;
    }
}