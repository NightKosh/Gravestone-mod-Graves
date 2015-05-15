package gravestone.models.block.graves;

import gravestone.models.block.ModelGraveStone;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelHorseGraveStone extends ModelGraveStone {

    private ModelRenderer head;
    private ModelRenderer mouthTop;
    private ModelRenderer mouthBottom;
    private ModelRenderer horseLeftEar;
    private ModelRenderer horseRightEar;
    private ModelRenderer neck;
    private ModelRenderer mane;
    private ModelRenderer pedestal;
    private ModelRenderer pedestal2;

    public ModelHorseGraveStone() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.5F, -10, -1.5F, 5, 5, 7);
        this.head.setRotationPoint(0, 4, -10);
        this.setRotation(this.head, 0.5235988F, 0, 0);
        this.mouthTop = new ModelRenderer(this, 24, 18);
        this.mouthTop.addBox(-2, -10, -7, 4, 3, 6);
        this.mouthTop.setRotationPoint(0, 3.95F, -10);
        this.setRotation(this.mouthTop, 0.5235988F, 0, 0);
        this.mouthBottom = new ModelRenderer(this, 24, 27);
        this.mouthBottom.addBox(-2, -7, -6.5F, 4, 2, 5);
        this.mouthBottom.setRotationPoint(0, 4, -10);
        this.setRotation(this.mouthBottom, 0.5235988F, 0, 0);
        this.head.addChild(this.mouthTop);
        this.head.addChild(this.mouthBottom);

        this.horseLeftEar = new ModelRenderer(this, 0, 0);
        this.horseLeftEar.addBox(0.45F, -12, 4, 2, 3, 1);
        this.horseLeftEar.setRotationPoint(0, 4, -10);
        this.setRotation(this.horseLeftEar, 0.5235988F, 0, 0);
        this.horseRightEar = new ModelRenderer(this, 0, 0);
        this.horseRightEar.addBox(-2.45F, -12, 4, 2, 3, 1);
        this.horseRightEar.setRotationPoint(0, 4, -10);
        this.setRotation(this.horseRightEar, 0.5235988F, 0, 0);

        this.neck = new ModelRenderer(this, 0, 12);
        this.neck.addBox(-2.05F, -9.8F, -2, 4, 14, 8);
        this.neck.setRotationPoint(0, 4, -10);
        this.setRotation(this.neck, 0.5235988F, 0, 0);

        this.mane = new ModelRenderer(this, 58, 0);
        this.mane.addBox(-1, -11.5F, 5, 2, 16, 4);
        this.mane.setRotationPoint(0, 4, -10);
        this.setRotation(this.mane, 0.5235988F, 0, 0);

        pedestal = new ModelRenderer(this, 0, 68);
        pedestal.addBox(0F, 0F, 0F, 12, 1, 9);
        pedestal.setRotationPoint(-6F, 23F, -2F);
        pedestal2 = new ModelRenderer(this, 33, 68);
        pedestal2.addBox(0F, 0F, 0F, 8, 2, 5);
        pedestal2.setRotationPoint(-4F, 21F, 1F);
    }

    @Override
    public void renderAll() {
        this.pedestal.render(0.0625F);
        this.pedestal2.render(0.0625F);

        float horseSize = 0.7F;

        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.75F, 0.5F);
        GL11.glScalef(horseSize, horseSize, horseSize);
        GL11.glTranslatef(0, 1.35F * (1 - horseSize), 0);

        this.neck.render(0.0625F);
        this.mane.render(0.0625F);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0.75F, 0.5F);
        float f8 = 0.5F + horseSize * horseSize * 0.5F;
        GL11.glScalef(f8, f8, f8);
        GL11.glTranslatef(0, 1.35F * (1 - horseSize), 0);

        this.mouthTop.rotationPointY = 0.02F;
        this.mouthBottom.rotationPointY = 0;

        this.mouthTop.rotationPointZ = 0.02F;
        this.mouthBottom.rotationPointZ = 0;

        this.mouthTop.rotateAngleX = 0;
        this.mouthBottom.rotateAngleX = 0;

        this.mouthTop.rotateAngleY = 0;
        this.mouthBottom.rotateAngleY = 0;

        this.horseLeftEar.render(0.0625F);
        this.horseRightEar.render(0.0625F);
        this.head.render(0.0625F);

        GL11.glPopMatrix();
    }
}
