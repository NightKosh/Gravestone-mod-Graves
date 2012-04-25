package GraveStone.models.entity;

import GraveStone.entity.EntityZombieCat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelZombieCat extends ModelBase {

    /** The back left leg model for the ZombieCat. */
    ModelRenderer zombieCatBackLeftLeg;
    /** The back right leg model for the ZombieCat. */
    ModelRenderer zombieCatBackRightLeg;
    /** The front left leg model for the ZombieCat. */
    ModelRenderer zombieCatFrontLeftLeg;
    /** The front right leg model for the ZombieCat. */
    ModelRenderer zombieCatFrontRightLeg;
    /** The tail model for the ZombieCat. */
    ModelRenderer zombieCatTail;
    /** The second part of tail model for the ZombieCat. */
    ModelRenderer zombieCatTail2;
    /** The head model for the ZombieCat. */
    ModelRenderer zombieCatHead;
    /** The body model for the ZombieCat. */
    ModelRenderer zombieCatBody;
    int field_78163_i = 1;

    public ModelZombieCat() {
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.zombieCatHead = new ModelRenderer(this, "head");
        this.zombieCatHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.zombieCatHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.zombieCatHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.zombieCatHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.zombieCatHead.setRotationPoint(0.0F, 15.0F, -9.0F);
        this.zombieCatBody = new ModelRenderer(this, 20, 0);
        this.zombieCatBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.zombieCatBody.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.zombieCatTail = new ModelRenderer(this, 0, 15);
        this.zombieCatTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.zombieCatTail.rotateAngleX = 0.9F;
        this.zombieCatTail.setRotationPoint(0.0F, 15.0F, 8.0F);
        this.zombieCatTail2 = new ModelRenderer(this, 4, 15);
        this.zombieCatTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.zombieCatTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
        this.zombieCatBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.zombieCatBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.zombieCatBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
        this.zombieCatBackRightLeg = new ModelRenderer(this, 8, 13);
        this.zombieCatBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.zombieCatBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
        this.zombieCatFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.zombieCatFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.zombieCatFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
        this.zombieCatFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.zombieCatFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.zombieCatFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        if (this.isChild) {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 10.0F * par7, 4.0F * par7);
            this.zombieCatHead.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.zombieCatBody.render(par7);
            this.zombieCatBackLeftLeg.render(par7);
            this.zombieCatBackRightLeg.render(par7);
            this.zombieCatFrontLeftLeg.render(par7);
            this.zombieCatFrontRightLeg.render(par7);
            this.zombieCatTail.render(par7);
            this.zombieCatTail2.render(par7);
            GL11.glPopMatrix();
        } else {
            this.zombieCatHead.render(par7);
            this.zombieCatBody.render(par7);
            this.zombieCatTail.render(par7);
            this.zombieCatTail2.render(par7);
            this.zombieCatBackLeftLeg.render(par7);
            this.zombieCatBackRightLeg.render(par7);
            this.zombieCatFrontLeftLeg.render(par7);
            this.zombieCatFrontRightLeg.render(par7);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.zombieCatHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.zombieCatHead.rotateAngleY = par4 / (180F / (float) Math.PI);

        if (this.field_78163_i != 3) {
            this.zombieCatBody.rotateAngleX = ((float) Math.PI / 2F);

            if (this.field_78163_i == 2) {
                this.zombieCatBackLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
                this.zombieCatBackRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 0.3F) * 1.0F * par2;
                this.zombieCatFrontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI + 0.3F) * 1.0F * par2;
                this.zombieCatFrontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.zombieCatTail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 10F) * MathHelper.cos(par1) * par2;
            } else {
                this.zombieCatBackLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
                this.zombieCatBackRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.zombieCatFrontLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
                this.zombieCatFrontRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;

                if (this.field_78163_i == 1) {
                    this.zombieCatTail2.rotateAngleX = 1.7278761F + ((float) Math.PI / 4F) * MathHelper.cos(par1) * par2;
                } else {
                    this.zombieCatTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(par1) * par2;
                }
            }
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving entityLiving, float par2, float par3, float par4) {
        EntityZombieCat entityZombieCat = (EntityZombieCat) entityLiving;
        this.zombieCatBody.rotationPointY = 12.0F;
        this.zombieCatBody.rotationPointZ = -10.0F;
        this.zombieCatHead.rotationPointY = 15.0F;
        this.zombieCatHead.rotationPointZ = -9.0F;
        this.zombieCatTail.rotationPointY = 15.0F;
        this.zombieCatTail.rotationPointZ = 8.0F;
        this.zombieCatTail2.rotationPointY = 20.0F;
        this.zombieCatTail2.rotationPointZ = 14.0F;
        this.zombieCatFrontLeftLeg.rotationPointY = this.zombieCatFrontRightLeg.rotationPointY = 13.8F;
        this.zombieCatFrontLeftLeg.rotationPointZ = this.zombieCatFrontRightLeg.rotationPointZ = -5.0F;
        this.zombieCatBackLeftLeg.rotationPointY = this.zombieCatBackRightLeg.rotationPointY = 18.0F;
        this.zombieCatBackLeftLeg.rotationPointZ = this.zombieCatBackRightLeg.rotationPointZ = 5.0F;
        this.zombieCatTail.rotateAngleX = 0.9F;

        if (entityZombieCat.isSneaking()) {
            this.zombieCatBody.rotationPointY++;
            this.zombieCatHead.rotationPointY += 2.0F;
            this.zombieCatTail.rotationPointY++;
            this.zombieCatTail2.rotationPointY += -4.0F;
            this.zombieCatTail2.rotationPointZ += 2.0F;
            this.zombieCatTail.rotateAngleX = ((float) Math.PI / 2F);
            this.zombieCatTail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 0;
        } else if (entityZombieCat.isSprinting()) {
            this.zombieCatTail2.rotationPointY = this.zombieCatTail.rotationPointY;
            this.zombieCatTail2.rotationPointZ += 2.0F;
            this.zombieCatTail.rotateAngleX = ((float) Math.PI / 2F);
            this.zombieCatTail2.rotateAngleX = ((float) Math.PI / 2F);
            this.field_78163_i = 2;
        } else {
            this.field_78163_i = 1;
        }
    }
}
