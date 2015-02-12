
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
    private ModelRenderer Candle;
    private ModelRenderer Thread;

    public ModelCandle() {
        textureWidth = 16;
        textureHeight = 16;
        
        Candle = new ModelRenderer(this, 0, 0);
        Candle.addBox(0F, 0F, 0F, 2, 5, 2);
        Candle.setRotationPoint(-1.5F, 19, 0);
        setRotation(Candle, 0F, 0.7853982F, 0F);
        Thread = new ModelRenderer(this, 12, 0);
        Thread.addBox(0F, 0F, 0F, 1, 2, 1);
        Thread.setRotationPoint(-0.5F, 17.5F, -0.5F);
    }
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Candle.render(f5);
        Thread.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void renderAll() {
        Candle.render(0.0625F);
        
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 1, 0.5F);
        Thread.render(0.0625F);
        GL11.glPopMatrix();
    }
}
