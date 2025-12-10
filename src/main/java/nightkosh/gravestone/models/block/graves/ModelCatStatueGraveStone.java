package nightkosh.gravestone.models.block.graves;

import nightkosh.gravestone.models.block.ModelGraveStone;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//TODO
//@SideOnly(Side.CLIENT)
public class ModelCatStatueGraveStone {//extends ModelGraveStone {
//
//    private ModelRenderer backLeftLeg;
//    private ModelRenderer backRightLeg;
//    private ModelRenderer frontLeftLeg;
//    private ModelRenderer frontRightLeg;
//    private ModelRenderer head;
//    private ModelRenderer body;
//    private ModelRenderer tail;
//    private ModelRenderer tail2;// The second part of tail.
//
//    public ModelCatStatueGraveStone() {
//        this.setTextureOffset("head.main", 0, 0);
//        this.setTextureOffset("head.nose", 0, 24);
//        this.setTextureOffset("head.ear1", 0, 10);
//        this.setTextureOffset("head.ear2", 6, 10);
//        this.head = new ModelRenderer(this, "head");
//        this.head.addBox("main", -2.5F, -2, -3, 5, 4, 5);
//        this.head.addBox("nose", -1.5F, 0, -4, 3, 2, 2);
//        this.head.addBox("ear1", -2, -3, 0, 1, 1, 2);
//        this.head.addBox("ear2", 1, -3, 0, 1, 1, 2);
//        this.head.setRotationPoint(0, 15, -9);
//        this.body = new ModelRenderer(this, 20, 0);
//        this.body.addBox(-2, 3, -8, 4, 16, 6, 0);
//        this.body.setRotationPoint(0, 12, -10);
//        this.tail = new ModelRenderer(this, 0, 15);
//        this.tail.addBox(-0.5F, 0, 0, 1, 8, 1);
//        this.tail.rotateAngleX = 0.9F;
//        this.tail.setRotationPoint(0, 15, 8);
//        this.tail2 = new ModelRenderer(this, 4, 15);
//        this.tail2.addBox(-0.5F, 0, 0, 1, 8, 1);
//        this.tail2.setRotationPoint(0, 20, 14);
//        this.backLeftLeg = new ModelRenderer(this, 8, 13);
//        this.backLeftLeg.addBox(-1, 0, 1, 2, 6, 2);
//        this.backLeftLeg.setRotationPoint(1.1F, 18, 5);
//        this.backRightLeg = new ModelRenderer(this, 8, 13);
//        this.backRightLeg.addBox(-1, 0, 1, 2, 6, 2);
//        this.backRightLeg.setRotationPoint(-1.1F, 18, 5);
//        this.frontLeftLeg = new ModelRenderer(this, 40, 0);
//        this.frontLeftLeg.addBox(-1, 0, 0, 2, 10, 2);
//        this.frontLeftLeg.setRotationPoint(1.2F, 13.8F, -5);
//        this.frontRightLeg = new ModelRenderer(this, 40, 0);
//        this.frontRightLeg.addBox(-1, 0, 0, 2, 10, 2);
//        this.frontRightLeg.setRotationPoint(-1.2F, 13.8F, -5);
//    }
//
//    /**
//     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
//     * used for animating the movement of arms and legs, where par1 represents
//     * the time(so that arms and legs swing back and forth) and par2 represents
//     * how "far" arms and legs can swing at most.
//     */
//    public void setRotationAngles(float par1, float par2, float par4, float par5) {
//        this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
//        this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
//        this.body.rotateAngleX = ((float) Math.PI / 4F);
//        this.body.rotationPointY = 8F;
//        this.body.rotationPointZ = -5F;
//        this.head.rotationPointY = 11.7F;
//        this.head.rotationPointZ = -8F;
//        this.tail.rotationPointY = 23F;
//        this.tail.rotationPointZ = 6F;
//        this.tail2.rotationPointY = 22F;
//        this.tail2.rotationPointZ = 13.2F;
//        this.tail.rotateAngleX = 1.7278761F;
//        this.tail2.rotateAngleX = 2.670354F;
//        this.frontLeftLeg.rotateAngleX = this.frontRightLeg.rotateAngleX = -0.15707964F;
//        this.frontLeftLeg.rotationPointY = this.frontRightLeg.rotationPointY = 15.8F;
//        this.frontLeftLeg.rotationPointZ = this.frontRightLeg.rotationPointZ = -7;
//        this.backLeftLeg.rotateAngleX = this.backRightLeg.rotateAngleX = -((float) Math.PI / 2F);
//        this.backLeftLeg.rotationPointY = this.backRightLeg.rotationPointY = 21;
//        this.backLeftLeg.rotationPointZ = this.backRightLeg.rotationPointZ = 1;
//    }
//
//    @Override
//    public void renderAll() {
//        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F);
//        float par7 = 0.0625F;
//        float f6 = 2;
//        GL11.glPushMatrix();
//        GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
//        GL11.glTranslatef(0, 10 * par7, 4 * par7);
//        this.head.render(par7);
//        GL11.glPopMatrix();
//        GL11.glPushMatrix();
//        GL11.glScalef(1 / f6, 1 / f6, 1 / f6);
//        GL11.glTranslatef(0, 24 * par7, 0);
//        this.body.render(par7);
//        this.backLeftLeg.render(par7);
//        this.backRightLeg.render(par7);
//        this.frontLeftLeg.render(par7);
//        this.frontRightLeg.render(par7);
//        this.tail.render(par7);
//        this.tail2.render(par7);
//        GL11.glPopMatrix();
//    }
}
