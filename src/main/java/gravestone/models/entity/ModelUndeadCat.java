package gravestone.models.entity;

import gravestone.entity.monster.EntityUndeadCat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
public class ModelUndeadCat extends ModelBase {

    private ModelRenderer backLeftLeg;
    private ModelRenderer backRightLeg;
    private ModelRenderer frontLeftLeg;
    private ModelRenderer frontRightLeg;
    private ModelRenderer tail;
    private ModelRenderer tail2;
    private ModelRenderer head;
    private ModelRenderer body;
    private int field_78163_i = 1;

    public ModelUndeadCat() {
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.head = new ModelRenderer(this, "head");
        this.head.addBox("main", -2.5F, -2, -3, 5, 4, 5);
        this.head.addBox("nose", -1.5F, 0, -4, 3, 2, 2);
        this.head.addBox("ear1", -2.0F, -3, 0, 1, 1, 2);
        this.head.addBox("ear2", 1, -3, 0, 1, 1, 2);
        this.head.setRotationPoint(0, 15, -9);
        this.body = new ModelRenderer(this, 20, 0);
        this.body.addBox(-2, 3, -8, 4, 16, 6, 0);
        this.body.setRotationPoint(0, 12, -10);
        this.tail = new ModelRenderer(this, 0, 15);
        this.tail.addBox(-0.5F, 0, 0, 1, 8, 1);
        this.tail.rotateAngleX = 0.9F;
        this.tail.setRotationPoint(0, 15, 8);
        this.tail2 = new ModelRenderer(this, 4, 15);
        this.tail2.addBox(-0.5F, 0, 0, 1, 8, 1);
        this.tail2.setRotationPoint(0, 20, 14);
        this.backLeftLeg = new ModelRenderer(this, 8, 13);
        this.backLeftLeg.addBox(-1, 0, 1, 2, 6, 2);
        this.backLeftLeg.setRotationPoint(1.1F, 18, 5);
        this.backRightLeg = new ModelRenderer(this, 8, 13);
        this.backRightLeg.addBox(-1, 0, 1, 2, 6, 2);
        this.backRightLeg.setRotationPoint(-1.1F, 18, 5);
        this.frontLeftLeg = new ModelRenderer(this, 40, 0);
        this.frontLeftLeg.addBox(-1, 0, 0, 2, 10, 2);
        this.frontLeftLeg.setRotationPoint(1.2F, 13.8F, -5);
        this.frontRightLeg = new ModelRenderer(this, 40, 0);
        this.frontRightLeg.addBox(-1, 0, 0, 2, 10, 2);
        this.frontRightLeg.setRotationPoint(-1.2F, 13.8F, -5);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.head.render(par7);
        this.body.render(par7);
        this.tail.render(par7);
        this.tail2.render(par7);
        this.backLeftLeg.render(par7);
        this.backRightLeg.render(par7);
        this.frontLeftLeg.render(par7);
        this.frontRightLeg.render(par7);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);

        if (this.field_78163_i != 3) {
            this.body.rotateAngleX = ((float) Math.PI / 2F);

            if (this.field_78163_i == 2) {
                this.backLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * par2;
                this.backRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 0.3F) * par2;
                this.frontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI + 0.3F) * par2;
                this.frontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * par2;
                this.tail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 10F) * MathHelper.cos(par1) * par2;
            } else {
                this.backLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * par2;
                this.backRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * par2;
                this.frontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * par2;
                this.frontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * par2;

                if (this.field_78163_i == 1) {
                    this.tail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 4F) * MathHelper.cos(par1) * par2;
                } else {
                    this.tail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(par1) * par2;
                }
            }
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third
     * float params here are the same second and third as in the
     * setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving entityLiving, float par2, float par3, float par4) {
        EntityUndeadCat undeadCat = (EntityUndeadCat) entityLiving;
        this.body.rotationPointY = 12;
        this.body.rotationPointZ = -10;
        this.head.rotationPointY = 15;
        this.head.rotationPointZ = -9;
        this.tail.rotationPointY = 15;
        this.tail.rotationPointZ = 8;
        this.tail2.rotationPointY = 20;
        this.tail2.rotationPointZ = 14;
        this.frontLeftLeg.rotationPointY = this.frontRightLeg.rotationPointY = 13.8F;
        this.frontLeftLeg.rotationPointZ = this.frontRightLeg.rotationPointZ = -5;
        this.backLeftLeg.rotationPointY = this.backRightLeg.rotationPointY = 18;
        this.backLeftLeg.rotationPointZ = this.backRightLeg.rotationPointZ = 5;
        this.tail.rotateAngleX = 0.9F;

        if (undeadCat.isSneaking()) {
            this.body.rotationPointY++;
            this.head.rotationPointY += 2;
            this.tail.rotationPointY++;
            this.tail2.rotationPointY += -4;
            this.tail2.rotationPointZ += 2;
            this.tail.rotateAngleX = ((float) Math.PI / 2F);
            this.tail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 0;
        } else if (undeadCat.isSprinting()) {
            this.tail2.rotationPointY = this.tail.rotationPointY;
            this.tail2.rotationPointZ += 2;
            this.tail.rotateAngleX = ((float) Math.PI / 2F);
            this.tail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 2;
        } else {
            this.field_78163_i = 1;
        }
    }
}