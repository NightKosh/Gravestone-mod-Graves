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
    private ModelRenderer neck;
    private ModelRenderer head;
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
        setTextureOffset("head.head", 0, 23);
        setTextureOffset("beakTop.beakTop", 0, 29);
        setTextureOffset("beakBottom.beakBottom", 6, 29);

        body = new ModelRenderer(this, 0, 0);
        body.addBox(0, 0, 0, 4, 4, 8);
        body.setRotationPoint(-2, 16.5F, -1);
        body.setTextureSize(32, 32);
        setRotation(body, -0.4014257F, 0, 0);

        neck = new ModelRenderer(this, 12, 23);
        neck.addBox(0F, 0F, 0F, 3, 2, 2);
        neck.setRotationPoint(-1.5F, 18, -3.2F);
        neck.setTextureSize(32, 32);
        setRotation(neck, 0.3717861F, 0, 0);

        head = new ModelRenderer(this, "head");
        head.setRotationPoint(-1.5F, 15.5F, -4.2F);
        setRotation(head, 0, 0, 0);
        head.addBox("head", 0, 0, 0, 3, 3, 3);

        beakTop = new ModelRenderer(this, "beakTop");
        beakTop.setRotationPoint(1, 1, -1.8F);
        setRotation(beakTop, 0.2617994F, 0, 0);
        beakTop.addBox("beakTop", 0, 0, 0, 1, 1, 2);
        head.addChild(beakTop);

        beakBottom = new ModelRenderer(this, "beakBottom");
        beakBottom.setRotationPoint(1, 1, -1.5F);
        setRotation(beakBottom, -0.2617994F, 0, 0);
        beakBottom.addBox("beakBottom", 0, 0, 0, 1, 1, 2);
        head.addChild(beakBottom);

        leftWing = new ModelRenderer(this, 0, 12);
        leftWing.addBox(0, 0, 0, 1, 3, 8);
        leftWing.setRotationPoint(1.5F, 17, -1);
        leftWing.setTextureSize(32, 32);
        setRotation(leftWing, -0.4014257F, 0, 0);

        rightWing = new ModelRenderer(this, 0, 12);
        rightWing.addBox(0, 0, 0, 1, 3, 8);
        rightWing.setRotationPoint(-2.5F, 17, -1);
        rightWing.setTextureSize(32, 32);
        setRotation(rightWing, -0.4014257F, 0, 0);

        tail = new ModelRenderer(this, 18, 12);
        tail.addBox(0, 0, 0, 3, 1, 4);
        tail.setRotationPoint(-1.5F, 20, 6);
        tail.setTextureSize(32, 32);
        setRotation(tail, -0.0872665F, 0, 0);

        leftLeg = new ModelRenderer(this, 25, 0);
        leftLeg.addBox(0, 0, 0, 1, 2, 0);
        leftLeg.setRotationPoint(0.5F, 22, 2);
        leftLeg.setTextureSize(32, 32);
        setRotation(leftLeg, 0, 0, 0);

        rightLeg = new ModelRenderer(this, 25, 0);
        rightLeg.addBox(0, 0, 0, 1, 2, 0);
        rightLeg.setRotationPoint(-1.5F, 22, 2);
        rightLeg.setTextureSize(32, 32);
        setRotation(rightLeg, 0, 0, 0);

        leftFoot = new ModelRenderer(this, 28, 0);
        leftFoot.addBox(0, 0, 0, 1, 0, 1);
        leftFoot.setRotationPoint(0.5F, 24, 1);
        leftFoot.setTextureSize(32, 32);
        setRotation(leftFoot, 0, 0, 0);

        rightFoot = new ModelRenderer(this, 28, 0);
        rightFoot.addBox(0, 0, 0, 1, 0, 1);
        rightFoot.setRotationPoint(-1.5F, 24, 1);
        rightFoot.setTextureSize(32, 32);
        setRotation(rightFoot, 0, 0, 0);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        head.render(f5);
        neck.render(f5);
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

        this.head.rotateAngleX = f4 / (180 / (float) Math.PI);
        this.head.rotateAngleY = f4 / (180 / (float) Math.PI);
    }
}
