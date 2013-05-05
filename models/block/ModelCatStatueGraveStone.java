package GraveStone.models.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public class ModelCatStatueGraveStone extends ModelGraveStone {

    /** The back left leg model for the Ocelot. */
    ModelRenderer ocelotBackLeftLeg;
    /** The back right leg model for the Ocelot. */
    ModelRenderer ocelotBackRightLeg;
    /** The front left leg model for the Ocelot. */
    ModelRenderer ocelotFrontLeftLeg;
    /** The front right leg model for the Ocelot. */
    ModelRenderer ocelotFrontRightLeg;
    /** The head model for the Ocelot. */
    ModelRenderer ocelotHead;
    /** The body model for the Ocelot. */
    ModelRenderer ocelotBody;
    /** The tail model for the Ocelot. */
    ModelRenderer ocelotTail;
    /** The second part of tail model for the Ocelot. */
    ModelRenderer ocelotTail2;

    public ModelCatStatueGraveStone() {
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.ocelotHead = new ModelRenderer(this, "head");
        this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);
        this.ocelotBody = new ModelRenderer(this, 20, 0);
        this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.ocelotTail = new ModelRenderer(this, 0, 15);
        this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);
        this.ocelotTail2 = new ModelRenderer(this, 4, 15);
        this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
        this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
        this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
        this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
        this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        float f6 = 2.0F;
        GL11.glPushMatrix();
        GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
        GL11.glTranslatef(0.0F, 10.0F * par7, 4.0F * par7);
        this.ocelotHead.render(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
        GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
        this.ocelotBody.render(par7);
        this.ocelotBackLeftLeg.render(par7);
        this.ocelotBackRightLeg.render(par7);
        this.ocelotFrontLeftLeg.render(par7);
        this.ocelotFrontRightLeg.render(par7);
        this.ocelotTail.render(par7);
        this.ocelotTail2.render(par7);
        GL11.glPopMatrix();
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par4, float par5) {
        this.ocelotHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.ocelotHead.rotateAngleY = par4 / (180F / (float) Math.PI);

        this.ocelotBody.rotateAngleX = ((float) Math.PI / 4F);
        this.ocelotBody.rotationPointY = 8F;
        this.ocelotBody.rotationPointZ = -5F;
        this.ocelotHead.rotationPointY = 11.7F;
        this.ocelotHead.rotationPointZ = -8F;
        
        this.ocelotTail.rotationPointY = 23F;
        this.ocelotTail.rotationPointZ = 6F;
        this.ocelotTail2.rotationPointY = 22F;
        this.ocelotTail2.rotationPointZ = 13.2F;
        this.ocelotTail.rotateAngleX = 1.7278761F;
        this.ocelotTail2.rotateAngleX = 2.670354F;
        
        this.ocelotFrontLeftLeg.rotateAngleX = this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
        this.ocelotFrontLeftLeg.rotationPointY = this.ocelotFrontRightLeg.rotationPointY = 15.8F;
        this.ocelotFrontLeftLeg.rotationPointZ = this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
        this.ocelotBackLeftLeg.rotateAngleX = this.ocelotBackRightLeg.rotateAngleX = -((float) Math.PI / 2F);
        this.ocelotBackLeftLeg.rotationPointY = this.ocelotBackRightLeg.rotationPointY = 21.0F;
        this.ocelotBackLeftLeg.rotationPointZ = this.ocelotBackRightLeg.rotationPointZ = 1.0F;
    }

    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F);
        float par7 = 0.0625F;

        float f6 = 2.0F;
        GL11.glPushMatrix();
        GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
        GL11.glTranslatef(0.0F, 10.0F * par7, 4.0F * par7);
        this.ocelotHead.render(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
        GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
        this.ocelotBody.render(par7);
        this.ocelotBackLeftLeg.render(par7);
        this.ocelotBackRightLeg.render(par7);
        this.ocelotFrontLeftLeg.render(par7);
        this.ocelotFrontRightLeg.render(par7);
        this.ocelotTail.render(par7);
        this.ocelotTail2.render(par7);
        GL11.glPopMatrix();

    }
}
