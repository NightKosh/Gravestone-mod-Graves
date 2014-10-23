package gravestone.models.block;

import gravestone.core.Resources;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
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

    private ModelSkull skull;
    private ModelCandle candle;

    public ModelSkullCandle() {
        textureWidth = 32;
        textureHeight = 32;

        skull = new ModelSkull();

        candle = new ModelCandle();
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        skull.render(entity, f, f1, f2, f3, f4, f5);
        candle.render(entity, f, f1, f2, f3, f4, f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll() {
        skull.renderAll();

        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.CANDLE);

        GL11.glPushMatrix();
        GL11.glTranslated(0, -0.34, -0.07);
        candle.renderAll();
        GL11.glPopMatrix();
    }
}
