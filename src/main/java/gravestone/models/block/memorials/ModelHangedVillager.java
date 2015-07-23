package gravestone.models.block.memorials;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelHangedVillager extends ModelBase {

    protected ModelRenderer head;
    protected ModelRenderer body;
    protected ModelRenderer rightArm;
    protected ModelRenderer leftArm;
    protected ModelRenderer rightLeg;
    protected ModelRenderer leftLeg;
    protected ModelRenderer nose;
    protected ModelRenderer rightArm2;
    protected ModelRenderer leftArm2;

    public ModelHangedVillager(boolean isInStocks) {
        this(isInStocks, 64, 64);
    }

    public ModelHangedVillager(boolean isInStocks, int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4, -8, -4, 8, 10, 8);
        head.setRotationPoint(0, -2, 0);
        head.setTextureSize(textureWidth, textureHeight);
        setRotation(head, 0.20F, 0, 0);

        body = new ModelRenderer(this, 16, 20);
        body.setTextureOffset(16, 20).addBox(-4, 0, -2, 8, 12, 6);
        body.setTextureOffset(0, 38).addBox(-4, 0, -2, 8, 18, 6, 0.5F);
        body.setRotationPoint(0, 0, -1);
        body.setTextureSize(textureWidth, textureHeight);

        rightArm = new ModelRenderer(this, 44, 22);
        rightArm.addBox(-3, -2, -2, 4, 8, 4);
        rightArm.setRotationPoint(-5, 2, 0);
        rightArm.setTextureSize(textureWidth, textureHeight);

        leftArm = new ModelRenderer(this, 44, 22);
        leftArm.addBox(-1, -2, -2, 4, 8, 4);
        leftArm.setRotationPoint(5, 2, 0);
        leftArm.setTextureSize(textureWidth, textureHeight);

        rightLeg = new ModelRenderer(this, 0, 22);
        rightLeg.addBox(-2, 0, -2, 4, 12, 4);
        rightLeg.setRotationPoint(-2, 12, 0);
        rightLeg.setTextureSize(textureWidth, textureHeight);

        leftLeg = new ModelRenderer(this, 0, 22);
        leftLeg.addBox(-2, 0, -2, 4, 12, 4);
        leftLeg.setRotationPoint(2, 12, 0);
        leftLeg.setTextureSize(textureWidth, textureHeight);

        nose = new ModelRenderer(this, 24, 0);
        nose.addBox(0, 0, 0, 2, 4, 2);
        nose.setRotationPoint(-1, -3, -6);
        nose.setTextureSize(textureWidth, textureHeight);

        rightArm2 = new ModelRenderer(this, 44, 22);
        rightArm2.addBox(0, 0, 0, 4, 4, 4);
        rightArm2.setRotationPoint(-8, 8, -2);
        rightArm2.setTextureSize(textureWidth, textureHeight);

        leftArm2 = new ModelRenderer(this, 44, 22);
        leftArm2.addBox(0, 0, 0, 4, 4, 4);
        leftArm2.setRotationPoint(4, 8, -2);
        leftArm2.setTextureSize(textureWidth, textureHeight);

        if (isInStocks) {
            setRotation(this.leftArm, 0, 0, -1.57F);
            setRotation(this.rightArm, 0, 0, 1.57F);

            leftArm2.setRotationPoint(11, -1, -2);
            setRotation(this.leftArm2, 0, 0, 3.14F);

            rightArm2.setRotationPoint(-7, -1, -2);
            setRotation(this.rightArm2, 0, 0, 3.14F);
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        nose.render(f5);
        rightArm2.render(f5);
        leftArm2.render(f5);
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll() {
        float f5 = 0.0625F;
        head.render(f5);
        body.render(f5);
        rightArm.render(f5);
        leftArm.render(f5);
        rightLeg.render(f5);
        leftLeg.render(f5);
        nose.render(f5);
        rightArm2.render(f5);
        leftArm2.render(f5);
    }
}
