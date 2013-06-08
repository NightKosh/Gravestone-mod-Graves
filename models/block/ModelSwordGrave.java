package GraveStone.models.block;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModelSwordGrave extends ModelGraveStone {

    ModelRenderer Blade1;
    ModelRenderer Blade2;
    ModelRenderer Blade3;
    ModelRenderer Blade4;
    ModelRenderer Shape3;
    ModelRenderer Hilt;

    public ModelSwordGrave() {
        textureWidth = 32;
        textureHeight = 32;

        Blade1 = new ModelRenderer(this, 0, 0);
        Blade1.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade1.setRotationPoint(-1F, 14F, 0F);
        Blade1.setTextureSize(64, 32);
        Blade1.mirror = true;
        setRotation(Blade1, 0F, 0.2617994F, 0F);
        Blade2 = new ModelRenderer(this, 2, 0);
        Blade2.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade2.setRotationPoint(-1F, 14F, 0F);
        Blade2.setTextureSize(64, 32);
        Blade2.mirror = true;
        setRotation(Blade2, 0F, -0.2617994F, 0F);
        Blade3 = new ModelRenderer(this, 2, 0);
        Blade3.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade3.setRotationPoint(0.9F, 14F, 0F);
        Blade3.setTextureSize(64, 32);
        Blade3.mirror = true;
        setRotation(Blade3, 0F, 2.879793F, 0F);
        Blade4 = new ModelRenderer(this, 0, 0);
        Blade4.addBox(0F, 0F, 0F, 1, 10, 0);
        Blade4.setRotationPoint(0.9F, 14F, 0F);
        Blade4.setTextureSize(64, 32);
        Blade4.mirror = true;
        setRotation(Blade4, 0F, -2.879793F, 0F);
        Shape3 = new ModelRenderer(this, 4, 0);
        Shape3.addBox(0F, 0F, 0F, 4, 1, 1);
        Shape3.setRotationPoint(-2F, 13F, -0.5F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Hilt = new ModelRenderer(this, 4, 2);
        Hilt.addBox(0F, 0F, 0F, 1, 3, 1);
        Hilt.setRotationPoint(-0.5F, 10F, -0.5F);
        Hilt.setTextureSize(64, 32);
        Hilt.mirror = true;
        setRotation(Hilt, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Blade1.render(f5);
        Blade2.render(f5);
        Blade3.render(f5);
        Blade4.render(f5);
        Shape3.render(f5);
        Hilt.render(f5);
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


    @Override
    public void renderAll() {
        Blade1.render(0.0625F);
        Blade2.render(0.0625F);
        Blade3.render(0.0625F);
        Blade4.render(0.0625F);
        Shape3.render(0.0625F);
        Hilt.render(0.0625F);
    }
}
