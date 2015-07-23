package gravestone.models.block;

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
public class ModelCandle extends ModelBase {
    private ModelRenderer candle;
    private ModelRenderer thread;

    public ModelCandle() {
        textureWidth = 16;
        textureHeight = 16;

        candle = new ModelRenderer(this, 0, 0);
        candle.addBox(0, 0, 0, 2, 5, 2);
        candle.setRotationPoint(-1.5F, 19, 0);
        setRotation(candle, 0, 0.7853982F, 0);
        thread = new ModelRenderer(this, 12, 0);
        thread.addBox(0, 0, 0, 1, 2, 1);
        thread.setRotationPoint(-0.5F, 17.5F, -0.5F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        candle.render(f5);
        thread.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll() {
        candle.render(0.0625F);

        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 1, 0.5F);
        thread.render(0.0625F);
        GL11.glPopMatrix();
    }
}
