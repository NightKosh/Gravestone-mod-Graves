package nightkosh.gravestone.models.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import nightkosh.gravestone.models.IModelBaseAdapter;
import nightkosh.gravestone.models.ModelRendererSkull;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelGSSkeleton extends ModelSkeleton implements IModelBaseAdapter {

    protected ModelRendererSkull skull;

    public ModelGSSkeleton() {
        this(0, false);
    }

    public ModelGSSkeleton(float p_i46303_1_, boolean p_i46303_2_) {
        super(p_i46303_1_, p_i46303_2_);

        this.bipedHead = new ModelRenderer(this, 0, 0);
        skull = new ModelRendererSkull(this, -4, -8, -4, 0, 0, 0);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        skull.rotateAngleX = bipedHead.rotateAngleX + 0.1745329F;
        skull.rotateAngleY = this.bipedHead.rotateAngleY;
        skull.rotateAngleZ = this.bipedHead.rotationPointZ;
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        super.render(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);

        skull.renderWithTexture(p_78088_7_, ModelRendererSkull.EnumSkullType.getTypeByClass(entity));
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setTexturesOffset(String name, int xPos, int zPos) {
        super.setTextureOffset(name, xPos, zPos);
    }
}
