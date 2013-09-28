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
public class ModelSkullCandle extends ModelBase {

    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
    public ModelRenderer Shape9;

    public ModelSkullCandle() {
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape1.setRotationPoint(-1F, 11F, 0.5F);
        Shape1.setTextureSize(32, 32);
        setRotation(Shape1, 0F, 0.7853982F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape2.setRotationPoint(-0.25F, 11F, 1.2F);
        Shape2.setTextureSize(32, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 1.570796F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape3.setRotationPoint(0.8F, 11F, 1.2F);
        Shape3.setTextureSize(32, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 2.356194F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape4.setRotationPoint(0F, 11F, 0.5F);
        Shape4.setTextureSize(32, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 3.141593F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape5.setRotationPoint(0.4F, 11F, -0.5F);
        Shape5.setTextureSize(32, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 3.926991F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape6.setRotationPoint(1.5F, 11F, -0.5F);
        Shape6.setTextureSize(32, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 4.712389F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape7.setRotationPoint(0.8F, 11F, -1.2F);
        Shape7.setTextureSize(32, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 5.497787F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 1, 5, 1);
        Shape8.setRotationPoint(-0.2F, 11F, -1.2F);
        Shape8.setTextureSize(32, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape9.setRotationPoint(0F, 11F, -0.5F);
        Shape9.setTextureSize(32, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        Shape1.render(par7);
        Shape2.render(par7);
        Shape3.render(par7);
        Shape4.render(par7);
        Shape5.render(par7);
        Shape6.render(par7);
        Shape7.render(par7);
        Shape8.render(par7);
        Shape9.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
