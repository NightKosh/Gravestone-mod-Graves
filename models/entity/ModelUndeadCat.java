package GraveStone.models.entity;

import GraveStone.entity.EntityUndeadCat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModelUndeadCat extends ModelBase {

    /** The back left leg model for the UndeadCat. */
    ModelRenderer undeadCatBackLeftLeg;
    /** The back right leg model for the UndeadCat. */
    ModelRenderer undeadCatBackRightLeg;
    /** The front left leg model for the UndeadCat. */
    ModelRenderer undeadCatFrontLeftLeg;
    /** The front right leg model for the UndeadCat. */
    ModelRenderer undeadCatFrontRightLeg;
    /** The tail model for the UndeadCat. */
    ModelRenderer undeadCatTail;
    /** The second part of tail model for the UndeadCat. */
    ModelRenderer undeadCatTail2;
    /** The head model for the UndeadCat. */
    ModelRenderer undeadCatHead;
    /** The body model for the UndeadCat. */
    ModelRenderer undeadCatBody;
    int field_78163_i = 1;

    public ModelUndeadCat() {
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.undeadCatHead = new ModelRenderer(this, "head");
        this.undeadCatHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.undeadCatHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.undeadCatHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.undeadCatHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.undeadCatHead.setRotationPoint(0.0F, 15.0F, -9.0F);
        this.undeadCatBody = new ModelRenderer(this, 20, 0);
        this.undeadCatBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.undeadCatBody.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.undeadCatTail = new ModelRenderer(this, 0, 15);
        this.undeadCatTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.undeadCatTail.rotateAngleX = 0.9F;
        this.undeadCatTail.setRotationPoint(0.0F, 15.0F, 8.0F);
        this.undeadCatTail2 = new ModelRenderer(this, 4, 15);
        this.undeadCatTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.undeadCatTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
        this.undeadCatBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.undeadCatBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.undeadCatBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
        this.undeadCatBackRightLeg = new ModelRenderer(this, 8, 13);
        this.undeadCatBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.undeadCatBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
        this.undeadCatFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.undeadCatFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.undeadCatFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
        this.undeadCatFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.undeadCatFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.undeadCatFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

            this.undeadCatHead.render(par7);
            this.undeadCatBody.render(par7);
            this.undeadCatTail.render(par7);
            this.undeadCatTail2.render(par7);
            this.undeadCatBackLeftLeg.render(par7);
            this.undeadCatBackRightLeg.render(par7);
            this.undeadCatFrontLeftLeg.render(par7);
            this.undeadCatFrontRightLeg.render(par7);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.undeadCatHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.undeadCatHead.rotateAngleY = par4 / (180F / (float) Math.PI);

        if (this.field_78163_i != 3) {
            this.undeadCatBody.rotateAngleX = ((float) Math.PI / 2F);

            if (this.field_78163_i == 2) {
                this.undeadCatBackLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
                this.undeadCatBackRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 0.3F) * 1.0F * par2;
                this.undeadCatFrontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI + 0.3F) * 1.0F * par2;
                this.undeadCatFrontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.undeadCatTail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 10F) * MathHelper.cos(par1) * par2;
            } else {
                this.undeadCatBackLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
                this.undeadCatBackRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.undeadCatFrontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.undeadCatFrontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;

                if (this.field_78163_i == 1) {
                    this.undeadCatTail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 4F) * MathHelper.cos(par1) * par2;
                } else {
                    this.undeadCatTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(par1) * par2;
                }
            }
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving entityLiving, float par2, float par3, float par4) {
        EntityUndeadCat undeadCat = (EntityUndeadCat) entityLiving;
        this.undeadCatBody.rotationPointY = 12.0F;
        this.undeadCatBody.rotationPointZ = -10.0F;
        this.undeadCatHead.rotationPointY = 15.0F;
        this.undeadCatHead.rotationPointZ = -9.0F;
        this.undeadCatTail.rotationPointY = 15.0F;
        this.undeadCatTail.rotationPointZ = 8.0F;
        this.undeadCatTail2.rotationPointY = 20.0F;
        this.undeadCatTail2.rotationPointZ = 14.0F;
        this.undeadCatFrontLeftLeg.rotationPointY = this.undeadCatFrontRightLeg.rotationPointY = 13.8F;
        this.undeadCatFrontLeftLeg.rotationPointZ = this.undeadCatFrontRightLeg.rotationPointZ = -5.0F;
        this.undeadCatBackLeftLeg.rotationPointY = this.undeadCatBackRightLeg.rotationPointY = 18.0F;
        this.undeadCatBackLeftLeg.rotationPointZ = this.undeadCatBackRightLeg.rotationPointZ = 5.0F;
        this.undeadCatTail.rotateAngleX = 0.9F;

        if (undeadCat.isSneaking()) {
            this.undeadCatBody.rotationPointY++;
            this.undeadCatHead.rotationPointY += 2.0F;
            this.undeadCatTail.rotationPointY++;
            this.undeadCatTail2.rotationPointY += -4.0F;
            this.undeadCatTail2.rotationPointZ += 2.0F;
            this.undeadCatTail.rotateAngleX = ((float) Math.PI / 2F);
            this.undeadCatTail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 0;
        } else if (undeadCat.isSprinting()) {
            this.undeadCatTail2.rotationPointY = this.undeadCatTail.rotationPointY;
            this.undeadCatTail2.rotationPointZ += 2.0F;
            this.undeadCatTail.rotateAngleX = ((float) Math.PI / 2F);
            this.undeadCatTail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 2;
        } else {
            this.field_78163_i = 1;
        }
    }
}
