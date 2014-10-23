package gravestone.models.entity;

import gravestone.models.block.ModelSkull;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelSkullCrawler extends ModelBase {

    private static final float baseZ = 0.3490659F;
    private static final float advBaseZ = baseZ * 0.74F;
    private static final float baseY = 0.1047198F;
    private static final float advBaseY = baseY * 2;
    
    private static final float rBaseY = (float) Math.PI + baseY;
    private static final float rAdvBaseY = (float) Math.PI + advBaseY;

    private ModelSkull skull;
    ModelRenderer rightLeg1;
    ModelRenderer rightLeg2;
    ModelRenderer rightLeg3;
    ModelRenderer rightLeg4;
    ModelRenderer leftLeg1;
    ModelRenderer leftLeg2;
    ModelRenderer leftLeg3;
    ModelRenderer leftLeg4;

    public ModelSkullCrawler() {
        textureWidth = 32;
        textureHeight = 32;

        skull = new ModelSkull();
        
        // Right legs
        rightLeg1 = new ModelRenderer(this, 0, 16);
        rightLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        rightLeg1.setRotationPoint(-3.9F, 22F, -2.1F);
        rightLeg2 = new ModelRenderer(this, 0, 16);
        rightLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        rightLeg2.setRotationPoint(-3.9F, 22F, -0.9F);
        rightLeg3 = new ModelRenderer(this, 0, 16);
        rightLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        rightLeg3.setRotationPoint(-3.9F, 22F, 0.4F);
        rightLeg4 = new ModelRenderer(this, 0, 16);
        rightLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        rightLeg4.setRotationPoint(-3.9F, 22F, 1.6F);

        // Left legs
        leftLeg1 = new ModelRenderer(this, 0, 16);
        leftLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        leftLeg1.setRotationPoint(3.7F, 22, -3.1F);
        leftLeg2 = new ModelRenderer(this, 0, 16);
        leftLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        leftLeg2.setRotationPoint(3.7F, 22, -1.9F);
        leftLeg3 = new ModelRenderer(this, 0, 16);
        leftLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        leftLeg3.setRotationPoint(3.7F, 22, -0.6F);
        leftLeg4 = new ModelRenderer(this, 0, 16);
        leftLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        leftLeg4.setRotationPoint(3.7F, 22, 0.6F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        GL11.glPushMatrix();
        GL11.glTranslated(0, -0.05, 0);
        skull.render(entity, f, f1, f2, f3, f4, f5);
        GL11.glPopMatrix();

        rightLeg1.render(f5);
        rightLeg2.render(f5);
        rightLeg3.render(f5);
        rightLeg4.render(f5);
        leftLeg1.render(f5);
        leftLeg2.render(f5);
        leftLeg3.render(f5);
        leftLeg4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.rightLeg1.rotateAngleZ = -baseZ;
        this.rightLeg2.rotateAngleZ = -advBaseZ;
        this.rightLeg3.rotateAngleZ = -advBaseZ;
        this.rightLeg4.rotateAngleZ = -baseZ;

        this.rightLeg1.rotateAngleY = -rAdvBaseY;
        this.rightLeg2.rotateAngleY = -rBaseY;
        this.rightLeg3.rotateAngleY = rBaseY;
        this.rightLeg4.rotateAngleY = rAdvBaseY;


        this.leftLeg1.rotateAngleZ = baseZ;
        this.leftLeg2.rotateAngleZ = advBaseZ;
        this.leftLeg3.rotateAngleZ = advBaseZ;
        this.leftLeg4.rotateAngleZ = baseZ;

        this.leftLeg1.rotateAngleY = advBaseY;
        this.leftLeg2.rotateAngleY = baseY;
        this.leftLeg3.rotateAngleY = -baseY;
        this.leftLeg4.rotateAngleY = -advBaseY;

        float firstY = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
        float secondY = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * f1;
        float thirdY = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthY = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;

        float firstZ = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
        float secondZ = Math.abs(MathHelper.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
        float thirdZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;


        this.rightLeg1.rotateAngleY += firstY;
        this.rightLeg2.rotateAngleY += secondY;
        this.rightLeg3.rotateAngleY += thirdY;
        this.rightLeg4.rotateAngleY += fourthY;

        this.rightLeg1.rotateAngleZ += firstZ;
        this.rightLeg2.rotateAngleZ += secondZ;
        this.rightLeg3.rotateAngleZ += thirdZ;
        this.rightLeg4.rotateAngleZ += fourthZ;


        this.leftLeg1.rotateAngleY -= firstY;
        this.leftLeg2.rotateAngleY -= secondY;
        this.leftLeg3.rotateAngleY -= thirdY;
        this.leftLeg4.rotateAngleY -= fourthY;

        this.leftLeg1.rotateAngleZ -= firstZ;
        this.leftLeg2.rotateAngleZ -= secondZ;
        this.leftLeg3.rotateAngleZ -= thirdZ;
        this.leftLeg4.rotateAngleZ -= fourthZ;
    }
}
