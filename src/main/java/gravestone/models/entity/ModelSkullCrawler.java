package gravestone.models.entity;

import gravestone.entity.monster.EntitySkullCrawler;
import gravestone.models.ModelBaseAdapter;
import gravestone.models.ModelRendererSkull;
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
public class ModelSkullCrawler extends ModelBaseAdapter {

    private static final float BASE_Z = 0.3490659F;
    private static final float ADV_BASE_Z = BASE_Z * 0.74F;
    private static final float BASE_Y = 0.1047198F;
    private static final float ADV_BASE_Y = BASE_Y * 2;

    private static final float R_BASE_Y = (float) Math.PI + BASE_Y;
    private static final float R_ADV_BASE_Y = (float) Math.PI + ADV_BASE_Y;

    private ModelRenderer skull;
    private ModelRenderer rightLegs;
    private ModelRenderer leftLegs;

    private ModelRenderer rightLeg1;
    private ModelRenderer rightLeg2;
    private ModelRenderer rightLeg3;
    private ModelRenderer rightLeg4;
    private ModelRenderer leftLeg1;
    private ModelRenderer leftLeg2;
    private ModelRenderer leftLeg3;
    private ModelRenderer leftLeg4;

    ModelRenderer legs;

    public ModelSkullCrawler() {
        textureWidth = 32;
        textureHeight = 32;

        skull = new ModelRendererSkull(this);

        // Right legs
        rightLeg1 = new ModelRenderer(this, 0, 16);
        rightLeg1.addBox(0, 0, 0, 3, 1, 1);
        rightLeg1.setRotationPoint(-3.9F, 22, -2.1F);
        rightLeg2 = new ModelRenderer(this, 0, 16);
        rightLeg2.addBox(0, 0, 0, 3, 1, 1);
        rightLeg2.setRotationPoint(-3.9F, 22, -0.9F);
        rightLeg3 = new ModelRenderer(this, 0, 16);
        rightLeg3.addBox(0, 0, 0, 3, 1, 1);
        rightLeg3.setRotationPoint(-3.9F, 22, 0.4F);
        rightLeg4 = new ModelRenderer(this, 0, 16);
        rightLeg4.addBox(0, 0, 0, 3, 1, 1);
        rightLeg4.setRotationPoint(-3.9F, 22, 1.6F);

        // Left legs
        leftLeg1 = new ModelRenderer(this, 0, 16);
        leftLeg1.addBox(0, 0, 0, 3, 1, 1);
        leftLeg1.setRotationPoint(3.7F, 22, -3.1F);
        leftLeg2 = new ModelRenderer(this, 0, 16);
        leftLeg2.addBox(0, 0, 0, 3, 1, 1);
        leftLeg2.setRotationPoint(3.7F, 22, -1.9F);
        leftLeg3 = new ModelRenderer(this, 0, 16);
        leftLeg3.addBox(0, 0, 0, 3, 1, 1);
        leftLeg3.setRotationPoint(3.7F, 22, -0.6F);
        leftLeg4 = new ModelRenderer(this, 0, 16);
        leftLeg4.addBox(0, 0, 0, 3, 1, 1);
        leftLeg4.setRotationPoint(3.7F, 22, 0.6F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);

        if (((EntitySkullCrawler) entity).isOnLadder()) {
            GL11.glPushMatrix();
            GL11.glRotatef(90, 1, 0, 0);
        }
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        GL11.glPushMatrix();
        GL11.glTranslated(0, -0.05, 0);
        skull.render(0.0625F);
        GL11.glPopMatrix();

        rightLeg1.render(f5);
        rightLeg2.render(f5);
        rightLeg3.render(f5);
        rightLeg4.render(f5);
        leftLeg1.render(f5);
        leftLeg2.render(f5);
        leftLeg3.render(f5);
        leftLeg4.render(f5);

        if (((EntitySkullCrawler) entity).isOnLadder()) {
            GL11.glPopMatrix();
        }
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

        this.rightLeg1.rotateAngleZ = -BASE_Z;
        this.rightLeg2.rotateAngleZ = -ADV_BASE_Z;
        this.rightLeg3.rotateAngleZ = -ADV_BASE_Z;
        this.rightLeg4.rotateAngleZ = -BASE_Z;

        this.rightLeg1.rotateAngleY = -R_ADV_BASE_Y;
        this.rightLeg2.rotateAngleY = -R_BASE_Y;
        this.rightLeg3.rotateAngleY = R_BASE_Y;
        this.rightLeg4.rotateAngleY = R_ADV_BASE_Y;


        this.leftLeg1.rotateAngleZ = BASE_Z;
        this.leftLeg2.rotateAngleZ = ADV_BASE_Z;
        this.leftLeg3.rotateAngleZ = ADV_BASE_Z;
        this.leftLeg4.rotateAngleZ = BASE_Z;

        this.leftLeg1.rotateAngleY = ADV_BASE_Y;
        this.leftLeg2.rotateAngleY = BASE_Y;
        this.leftLeg3.rotateAngleY = -BASE_Y;
        this.leftLeg4.rotateAngleY = -ADV_BASE_Y;

        float firstY = -(MathHelper.cos(f * 0.6662F * 2) * 0.4F) * f1;
        float secondY = -(MathHelper.cos(f * 0.6662F * 2 + (float) Math.PI) * 0.4F) * f1;
        float thirdY = -(MathHelper.cos(f * 0.6662F * 2 + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthY = -(MathHelper.cos(f * 0.6662F * 2 + ((float) Math.PI * 3 / 2F)) * 0.4F) * f1;

        float firstZ = Math.abs(MathHelper.sin(f * 0.6662F) * 0.4F) * f1;
        float secondZ = Math.abs(MathHelper.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
        float thirdZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI * 3 / 2F)) * 0.4F) * f1;


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
