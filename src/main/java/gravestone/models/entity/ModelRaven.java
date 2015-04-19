package gravestone.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelRaven extends ModelBase {
    private ModelRenderer body;
    private ModelRenderer head;
    private ModelRenderer neck;
    private ModelRenderer beakTop;
    private ModelRenderer beakBottom;
    private ModelRenderer leftWing;
    private ModelRenderer rightWing;
    private ModelRenderer tail;
    private ModelRenderer leftLeg;
    private ModelRenderer rightLeg;
    private ModelRenderer leftFoot;
    private ModelRenderer rightFoot;

    public ModelRaven() {
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelRenderer(this, 0, 0);
        body.addBox(0, 0, 0, 4, 4, 8);
        body.setRotationPoint(-2F, 16.5F, -1F);
        body.setTextureSize(32, 32);
        body.mirror = true;
        setRotation(body, -0.4014257F, 0F, 0F);
        head = new ModelRenderer(this, 0, 23);
        head.addBox(0F, 0F, 0F, 3, 3, 3);
        head.setRotationPoint(-1.5F, 15.5F, -4.2F);
        head.setTextureSize(32, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        neck = new ModelRenderer(this, 12, 23);
        neck.addBox(0F, 0F, 0F, 3, 2, 2);
        neck.setRotationPoint(-1.5F, 18F, -3.2F);
        neck.setTextureSize(32, 32);
        neck.mirror = true;
        setRotation(neck, 0.3717861F, 0F, 0F);
        beakTop = new ModelRenderer(this, 0, 29);
        beakTop.addBox(0F, 0F, 0F, 1, 1, 2);
        beakTop.setRotationPoint(-0.5F, 16.5F, -6F);
        beakTop.setTextureSize(32, 32);
        beakTop.mirror = true;
        setRotation(beakTop, 0.2617994F, 0F, 0F);
        beakBottom = new ModelRenderer(this, 6, 29);
        beakBottom.addBox(0F, 0F, 0F, 1, 1, 2);
        beakBottom.setRotationPoint(-0.5F, 16.5F, -5.7F);
        beakBottom.setTextureSize(32, 32);
        beakBottom.mirror = true;
        setRotation(beakBottom, -0.2617994F, 0F, 0F);
        leftWing = new ModelRenderer(this, 0, 12);
        leftWing.addBox(0F, 0F, 0F, 1, 3, 8);
        leftWing.setRotationPoint(1.5F, 17F, -1F);
        leftWing.setTextureSize(32, 32);
        leftWing.mirror = true;
        setRotation(leftWing, -0.4014257F, 0F, 0F);
        rightWing = new ModelRenderer(this, 0, 12);
        rightWing.addBox(0F, 0F, 0F, 1, 3, 8);
        rightWing.setRotationPoint(-2.5F, 17F, -1F);
        rightWing.setTextureSize(32, 32);
        rightWing.mirror = true;
        setRotation(rightWing, -0.4014257F, 0F, 0F);
        tail = new ModelRenderer(this, 18, 12);
        tail.addBox(0F, 0F, 0F, 3, 1, 4);
        tail.setRotationPoint(-1.5F, 20F, 6F);
        tail.setTextureSize(32, 32);
        tail.mirror = true;
        setRotation(tail, -0.0872665F, 0F, 0F);
        leftLeg = new ModelRenderer(this, 25, 0);
        leftLeg.addBox(0F, 0F, 0F, 1, 2, 0);
        leftLeg.setRotationPoint(0.5F, 22F, 2F);
        leftLeg.setTextureSize(32, 32);
        leftLeg.mirror = true;
        setRotation(leftLeg, 0F, 0F, 0F);
        rightLeg = new ModelRenderer(this, 25, 0);
        rightLeg.addBox(0F, 0F, 0F, 1, 2, 0);
        rightLeg.setRotationPoint(-1.5F, 22F, 2F);
        rightLeg.setTextureSize(32, 32);
        rightLeg.mirror = true;
        setRotation(rightLeg, 0F, 0F, 0F);
        leftFoot = new ModelRenderer(this, 28, 0);
        leftFoot.addBox(0F, 0F, 0F, 1, 0, 1);
        leftFoot.setRotationPoint(0.5F, 24F, 1F);
        leftFoot.setTextureSize(32, 32);
        leftFoot.mirror = true;
        setRotation(leftFoot, 0F, 0F, 0F);
        rightFoot = new ModelRenderer(this, 28, 0);
        rightFoot.addBox(0F, 0F, 0F, 1, 0, 1);
        rightFoot.setRotationPoint(-1.5F, 24F, 1F);
        rightFoot.setTextureSize(32, 32);
        rightFoot.mirror = true;
        setRotation(rightFoot, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        head.render(f5);
        neck.render(f5);
        beakTop.render(f5);
        beakBottom.render(f5);
        leftWing.render(f5);
        rightWing.render(f5);
        tail.render(f5);
        leftLeg.render(f5);
        rightLeg.render(f5);
        leftFoot.render(f5);
        rightFoot.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
