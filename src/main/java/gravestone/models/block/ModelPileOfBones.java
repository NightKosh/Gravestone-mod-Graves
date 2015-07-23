package gravestone.models.block;

import gravestone.core.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelPileOfBones extends ModelBase {

    private ModelRenderer bone1;
    private ModelRenderer bone2;
    private ModelRenderer bone3;
    private ModelRenderer bone4;
    private ModelRenderer bone5;
    private ModelRenderer bone6;
    private ModelRenderer bone7;
    private ModelRenderer bone8;

    private ModelSkull skull;

    public ModelPileOfBones() {
        textureWidth = 32;
        textureHeight = 32;

        bone1 = new ModelRenderer(this, 0, 0);
        bone1.addBox(0, 0, 0, 4, 1, 1);
        bone1.setRotationPoint(7, 23, 4);
        bone1.setTextureSize(32, 32);
        bone1.mirror = true;
        setRotation(bone1, 0, -2.303835F, 0);
        bone2 = new ModelRenderer(this, 0, 3);
        bone2.addBox(0, 0, 0, 4, 1, 1);
        bone2.setRotationPoint(0, 23, 1);
        bone2.setTextureSize(32, 32);
        bone2.mirror = true;
        setRotation(bone2, 0, 0.2792527F, 0);
        bone3 = new ModelRenderer(this, 0, 6);
        bone3.addBox(0, 0, 0, 4, 1, 1);
        bone3.setRotationPoint(-6, 23, -3);
        bone3.setTextureSize(32, 32);
        bone3.mirror = true;
        setRotation(bone3, 0, 0.7853982F, 0);
        bone4 = new ModelRenderer(this, 0, 9);
        bone4.addBox(0, 0, 0, 4, 1, 1);
        bone4.setRotationPoint(-6, 23, 3);
        bone4.setTextureSize(32, 32);
        bone4.mirror = true;
        setRotation(bone4, 0, -0.418879F, 0);
        bone5 = new ModelRenderer(this, 11, 0);
        bone5.addBox(0, 0, 0, 4, 1, 1);
        bone5.setRotationPoint(2, 23, -4);
        bone5.setTextureSize(32, 32);
        bone5.mirror = true;
        setRotation(bone5, 0, 0.1919862F, 0);
        bone6 = new ModelRenderer(this, 11, 3);
        bone6.addBox(0, 0, 0, 4, 1, 1);
        bone6.setRotationPoint(1, 23, -2);
        bone6.setTextureSize(32, 32);
        bone6.mirror = true;
        setRotation(bone6, 0, -0.9250245F, -0.2617994F);
        bone7 = new ModelRenderer(this, 11, 6);
        bone7.addBox(0, 0, 0, 4, 1, 1);
        bone7.setRotationPoint(-6, 23, 7);
        bone7.setTextureSize(32, 32);
        bone7.mirror = true;
        setRotation(bone7, 0, 1.117011F, -0.3141593F);
        bone8 = new ModelRenderer(this, 11, 9);
        bone8.addBox(0, 0, 0, 4, 1, 1);
        bone8.setRotationPoint(-5, 22, -5);
        bone8.setTextureSize(32, 32);
        bone8.mirror = true;
        setRotation(bone8, 0, -0.5759587F, 0.3490659F);

        skull = new ModelSkull();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bone1.render(f5);
        bone2.render(f5);
        bone3.render(f5);
        bone4.render(f5);
        bone5.render(f5);
        bone6.render(f5);
        bone7.render(f5);
        bone8.render(f5);
    }

    public void renderAll(boolean haveSkull) {
        float f5 = 0.0625F;
        bone1.render(f5);
        bone2.render(f5);
        bone3.render(f5);
        bone4.render(f5);
        bone5.render(f5);
        bone6.render(f5);
        bone7.render(f5);
        bone8.render(f5);

        if (haveSkull) {
            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON_SKULL_CANDLE);
            GL11.glPushMatrix();
            GL11.glTranslated(0.4, 0, 0.5);
            GL11.glRotated(45, 0, 1, 0);
            skull.renderAll();
            GL11.glPopMatrix();
        }
    }

}
