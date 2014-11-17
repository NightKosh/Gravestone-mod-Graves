package gravestone.models.block.memorials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelHangedVillager extends ModelBase {

    private ModelRenderer head;
    private ModelRenderer body;
    private ModelRenderer rightArm;
    private ModelRenderer leftArm;
    private ModelRenderer rightLeg;
    private ModelRenderer leftLeg;
    private ModelRenderer nose;
    private ModelRenderer rightArm2;
    private ModelRenderer leftArm2;

    public ModelHangedVillager() {
        textureWidth = 64;
        textureHeight = 64;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 10, 8);
        head.setRotationPoint(0F, -2F, 0F);
        head.setTextureSize(64, 64);
        setRotation(head, 0.20F, 0F, 0F);

        body = new ModelRenderer(this, 16, 20);
        body.setTextureOffset(16, 20).addBox(-4F, 0F, -2F, 8, 12, 6);
        body.setTextureOffset(0, 38).addBox(-4F, 0F, -2F, 8, 18, 6, 0.5F);
        body.setRotationPoint(0F, 0F, -1F);
        body.setTextureSize(64, 64);
        setRotation(body, 0F, 0F, 0F);

        rightArm = new ModelRenderer(this, 44, 22);
        rightArm.addBox(-3F, -2F, -2F, 4, 8, 4);
        rightArm.setRotationPoint(-5F, 2F, 0F);
        rightArm.setTextureSize(64, 64);
        setRotation(rightArm, 0F, 0F, 0F);

        leftArm = new ModelRenderer(this, 44, 22);
        leftArm.addBox(-1F, -2F, -2F, 4, 8, 4);
        leftArm.setRotationPoint(5F, 2F, 0F);
        leftArm.setTextureSize(64, 64);
        setRotation(leftArm, 0F, 0F, 0F);

        rightLeg = new ModelRenderer(this, 0, 22);
        rightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightLeg.setRotationPoint(-2F, 12F, 0F);
        rightLeg.setTextureSize(64, 64);
        setRotation(rightLeg, 0F, 0F, 0F);

        leftLeg = new ModelRenderer(this, 0, 22);
        leftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftLeg.setRotationPoint(2F, 12F, 0F);
        leftLeg.setTextureSize(64, 64);
        setRotation(leftLeg, 0F, 0F, 0F);

        nose = new ModelRenderer(this, 24, 0);
        nose.addBox(0F, 0F, 0F, 2, 4, 2);
        nose.setRotationPoint(-1F, -3F, -6F);
        nose.setTextureSize(64, 64);
        setRotation(nose, 0F, 0F, 0F);

        rightArm2 = new ModelRenderer(this, 44, 22);
        rightArm2.addBox(0F, 0F, 0F, 4, 4, 4);
        rightArm2.setRotationPoint(4F, 8F, -2F);
        rightArm2.setTextureSize(64, 64);
        setRotation(rightArm2, 0F, 0F, 0F);

        leftArm2 = new ModelRenderer(this, 44, 22);
        leftArm2.addBox(0F, 0F, 0F, 4, 4, 4);
        leftArm2.setRotationPoint(-8F, 8F, -2F);
        leftArm2.setTextureSize(64, 64);
        setRotation(leftArm2, 0F, 0F, 0F);
    }

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

    private void setRotation(ModelRenderer model, float x, float y, float z) {
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
