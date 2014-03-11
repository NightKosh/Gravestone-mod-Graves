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
public class ModelSkullCandle extends ModelBase {

    private ModelRenderer Skull;
    private ModelRenderer Teeth;
    private ModelRenderer Candle;
    private ModelRenderer Thread;

    public ModelSkullCandle() {
        textureWidth = 32;
        textureHeight = 32;

        Skull = new ModelRenderer(this, 0, 0);
        Skull.addBox(0F, 0F, 0F, 8, 6, 8);
        Skull.setRotationPoint(-4F, 16.6F, -4F);
        setRotation(Skull, -0.1745329F, 0F, 0F);
        Teeth = new ModelRenderer(this, 0, 14);
        Teeth.addBox(0F, 0F, 0F, 4, 2, 2);
        Teeth.setRotationPoint(-2F, 22F, -4.8F);
        setRotation(Teeth, -0.1745329F, 0F, 0F);
        Candle = new ModelRenderer(this, 0, 18);
        Candle.addBox(0F, 0F, 0F, 2, 4, 2);
        Candle.setRotationPoint(-1.5F, 13.5F, -1F);
        setRotation(Candle, 0F, 0.7853982F, 0F);
        Thread = new ModelRenderer(this, 16, 14);
        Thread.addBox(0F, 0F, 0F, 1, 2, 1);
        Thread.setRotationPoint(-0.7F, 12F, -2.5F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Skull.render(f5);
        Teeth.render(f5);
        Candle.render(f5);
        Thread.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void renderAll() {
        Skull.render(0.0625F);
        Teeth.render(0.0625F);
        Candle.render(0.0625F);
        
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 1, 0.5F);
        Thread.render(0.0625F);
        GL11.glPopMatrix();
    }
}
