package gravestone.models.entity;

import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

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
    ModelRenderer Skull;
    ModelRenderer RightLeg1;
    ModelRenderer RightLeg2;
    ModelRenderer RightLeg3;
    ModelRenderer RightLeg4;
    ModelRenderer LeftLeg1;
    ModelRenderer LeftLeg2;
    ModelRenderer LeftLeg3;
    ModelRenderer LeftLeg4;

    public ModelSkullCrawler() {
        textureWidth = 32;
        textureHeight = 32;

        Skull = new ModelRenderer(this, 0, 0);
        Skull.addBox(0F, 0F, 0F, 8, 8, 8);
        Skull.setRotationPoint(-4F, 15F, -4F);
        
        // Right legs
        RightLeg1 = new ModelRenderer(this, 0, 16);
        RightLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg1.setRotationPoint(-3.9F, 22F, -1.1F);
        RightLeg2 = new ModelRenderer(this, 0, 16);
        RightLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg2.setRotationPoint(-3.9F, 22F, 0.1F);
        RightLeg3 = new ModelRenderer(this, 0, 16);
        RightLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg3.setRotationPoint(-3.9F, 22F, 1.4F);
        RightLeg4 = new ModelRenderer(this, 0, 16);
        RightLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg4.setRotationPoint(-3.9F, 22F, 2.6F);

        // Left legs
        LeftLeg1 = new ModelRenderer(this, 0, 16);
        LeftLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg1.setRotationPoint(3.7F, 22, -2.1F);
        LeftLeg2 = new ModelRenderer(this, 0, 16);
        LeftLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg2.setRotationPoint(3.7F, 22, -0.9F);
        LeftLeg3 = new ModelRenderer(this, 0, 16);
        LeftLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg3.setRotationPoint(3.7F, 22, 0.4F);
        LeftLeg4 = new ModelRenderer(this, 0, 16);
        LeftLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg4.setRotationPoint(3.7F, 22, 1.6F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Skull.render(f5);
        RightLeg1.render(f5);
        RightLeg2.render(f5);
        RightLeg3.render(f5);
        RightLeg4.render(f5);
        LeftLeg1.render(f5);
        LeftLeg2.render(f5);
        LeftLeg3.render(f5);
        LeftLeg4.render(f5);
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

        this.RightLeg1.rotateAngleZ = -baseZ;
        this.RightLeg2.rotateAngleZ = -advBaseZ;
        this.RightLeg3.rotateAngleZ = -advBaseZ;
        this.RightLeg4.rotateAngleZ = -baseZ;

        this.RightLeg1.rotateAngleY = -rAdvBaseY;
        this.RightLeg2.rotateAngleY = -rBaseY;
        this.RightLeg3.rotateAngleY = rBaseY;
        this.RightLeg4.rotateAngleY = rAdvBaseY;


        this.LeftLeg1.rotateAngleZ = baseZ;
        this.LeftLeg2.rotateAngleZ = advBaseZ;
        this.LeftLeg3.rotateAngleZ = advBaseZ;
        this.LeftLeg4.rotateAngleZ = baseZ;

        this.LeftLeg1.rotateAngleY = advBaseY;
        this.LeftLeg2.rotateAngleY = baseY;
        this.LeftLeg3.rotateAngleY = -baseY;
        this.LeftLeg4.rotateAngleY = -advBaseY;

        float firstY = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
        float secondY = -(MathHelper.cos(f * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * f1;
        float thirdY = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthY = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;

        float firstZ = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
        float secondZ = Math.abs(MathHelper.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
        float thirdZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float fourthZ = Math.abs(MathHelper.sin(f * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;


        this.RightLeg1.rotateAngleY += firstY;
        this.RightLeg2.rotateAngleY += secondY;
        this.RightLeg3.rotateAngleY += thirdY;
        this.RightLeg4.rotateAngleY += fourthY;

        this.RightLeg1.rotateAngleZ += firstZ;
        this.RightLeg2.rotateAngleZ += secondZ;
        this.RightLeg3.rotateAngleZ += thirdZ;
        this.RightLeg4.rotateAngleZ += fourthZ;


        this.LeftLeg1.rotateAngleY -= firstY;
        this.LeftLeg2.rotateAngleY -= secondY;
        this.LeftLeg3.rotateAngleY -= thirdY;
        this.LeftLeg4.rotateAngleY -= fourthY;

        this.LeftLeg1.rotateAngleZ -= firstZ;
        this.LeftLeg2.rotateAngleZ -= secondZ;
        this.LeftLeg3.rotateAngleZ -= thirdZ;
        this.LeftLeg4.rotateAngleZ -= fourthZ;
    }
}
