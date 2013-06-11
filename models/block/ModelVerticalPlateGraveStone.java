
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
public class ModelVerticalPlateGraveStone extends ModelGraveStone {

    ModelRenderer Plate;

    public ModelVerticalPlateGraveStone() {
        textureWidth = 32;
        textureHeight = 32;

        Plate = new ModelRenderer(this, 0, 0);
        Plate.addBox(0F, 0F, 0F, 12, 15, 2);
        Plate.setRotationPoint(-6F, 9F, 5F);
        Plate.setTextureSize(32, 32);
        Plate.mirror = true;
        setRotation(Plate, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Plate.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void renderAll() {
        Plate.render(0.0625F);
    }
}
