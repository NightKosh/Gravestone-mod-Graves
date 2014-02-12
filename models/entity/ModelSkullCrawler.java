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
public class ModelSkullCrawler extends ModelBase {

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
        Skull.setTextureSize(64, 32);
        Skull.mirror = true;
        setRotation(Skull, 0F, 0F, 0F);
        RightLeg1 = new ModelRenderer(this, 0, 16);
        RightLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg1.setRotationPoint(-6.2F, 23F, -2.5F);
        RightLeg1.setTextureSize(64, 32);
        RightLeg1.mirror = true;
        setRotation(RightLeg1, 0F, -0.3490659F, -0.3490659F);
        RightLeg2 = new ModelRenderer(this, 0, 16);
        RightLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg2.setRotationPoint(-6.5F, 23F, -1F);
        RightLeg2.setTextureSize(64, 32);
        RightLeg2.mirror = true;
        setRotation(RightLeg2, 0F, -0.1745329F, -0.3490659F);
        RightLeg3 = new ModelRenderer(this, 0, 16);
        RightLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg3.setRotationPoint(-6.65F, 23F, 0.8F);
        RightLeg3.setTextureSize(64, 32);
        RightLeg3.mirror = true;
        setRotation(RightLeg3, 0F, 0.1745329F, -0.3490659F);
        RightLeg4 = new ModelRenderer(this, 0, 16);
        RightLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        RightLeg4.setRotationPoint(-6.4F, 23F, 2.3F);
        RightLeg4.setTextureSize(64, 32);
        RightLeg4.mirror = true;
        setRotation(RightLeg4, 0F, 0.3490659F, -0.3490659F);
        LeftLeg1 = new ModelRenderer(this, 0, 16);
        LeftLeg1.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg1.setRotationPoint(3.4F, 22, -2F);
        LeftLeg1.setTextureSize(64, 32);
        LeftLeg1.mirror = true;
        setRotation(LeftLeg1, 0F, 0.2617994F, 0.3490659F);
        LeftLeg2 = new ModelRenderer(this, 0, 16);
        LeftLeg2.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg2.setRotationPoint(3.6F, 22, -0.9F);
        LeftLeg2.setTextureSize(64, 32);
        LeftLeg2.mirror = true;
        setRotation(LeftLeg2, 0F, 0.0872665F, 0.3490659F);
        LeftLeg3 = new ModelRenderer(this, 0, 16);
        LeftLeg3.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg3.setRotationPoint(3.7F, 22, 0.4F);
        LeftLeg3.setTextureSize(64, 32);
        LeftLeg3.mirror = true;
        setRotation(LeftLeg3, 0F, -0.1047198F, 0.3490659F);
        LeftLeg4 = new ModelRenderer(this, 0, 16);
        LeftLeg4.addBox(0F, 0F, 0F, 3, 1, 1);
        LeftLeg4.setRotationPoint(3.7F, 22, 1.5F);
        LeftLeg4.setTextureSize(64, 32);
        LeftLeg4.mirror = true;
        setRotation(LeftLeg4, 0F, -0.2268928F, 0.3490659F);
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

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
