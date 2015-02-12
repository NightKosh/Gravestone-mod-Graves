package gravestone.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelSkull extends ModelBase {

    private ModelRenderer skull;
    private ModelRenderer teeth;

    public ModelSkull() {
        textureWidth = 32;
        textureHeight = 32;

        skull = new ModelRenderer(this, 0, 0);
        skull.addBox(0F, 0F, 0F, 8, 6, 8);
        skull.setRotationPoint(-4F, 16.6F, -4F);
        setRotation(skull, -0.1745329F, 0F, 0F);
        teeth = new ModelRenderer(this, 0, 14);
        teeth.addBox(0F, 0F, 0F, 4, 2, 2);
        teeth.setRotationPoint(-2F, 22F, -4.8F);
        setRotation(teeth, -0.1745329F, 0F, 0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        skull.render(f5);
        teeth.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll() {
        skull.render(0.0625F);
        teeth.render(0.0625F);
    }
}
