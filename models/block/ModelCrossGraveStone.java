
package GraveStone.models.block;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModelCrossGraveStone extends ModelGraveStone {

    ModelRenderer VerticalPart;
    ModelRenderer RightPart;
    ModelRenderer LeftPart;

    public ModelCrossGraveStone() {
        textureWidth = 32;
        textureHeight = 32;

        VerticalPart = new ModelRenderer(this, 0, 0);
        VerticalPart.addBox(0F, 0F, 0F, 2, 16, 2);
        VerticalPart.setRotationPoint(-1F, 8F, 5F);
        VerticalPart.setTextureSize(32, 32);
        VerticalPart.mirror = true;
        setRotation(VerticalPart, 0F, 0F, 0F);
        RightPart = new ModelRenderer(this, 0, 0);
        RightPart.addBox(0F, 0F, 0F, 5, 1, 1);
        RightPart.setRotationPoint(1F, 13F, 5.5F);
        RightPart.setTextureSize(32, 32);
        RightPart.mirror = true;
        setRotation(RightPart, 0F, 0F, 0F);
        LeftPart = new ModelRenderer(this, 0, 0);
        LeftPart.addBox(0F, 0F, 0F, 5, 1, 1);
        LeftPart.setRotationPoint(-6F, 13F, 5.5F);
        LeftPart.setTextureSize(32, 32);
        LeftPart.mirror = true;
        setRotation(LeftPart, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        VerticalPart.render(f5);
        RightPart.render(f5);
        LeftPart.render(f5);
    }


    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void renderAll() {
        VerticalPart.render(0.0625F);
        RightPart.render(0.0625F);
        LeftPart.render(0.0625F);
    }
}
