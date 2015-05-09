package gravestone.models.block;

import gravestone.core.Resources;
import gravestone.models.ModelBaseAdapter;
import gravestone.models.ModelRendererSkull;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelSkullCandle extends ModelBaseAdapter {

    private ModelRenderer skull;
    private ModelCandle candle;

    public ModelSkullCandle() {
        textureWidth = 32;
        textureHeight = 32;

        skull = new ModelRendererSkull(this);

        candle = new ModelCandle();
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        skull.render(f5);
        candle.render(entity, f, f1, f2, f3, f4, f5);
    }

    public void renderAll() {
        skull.render(0.0625F);

        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.CANDLE);

        GL11.glPushMatrix();
        GL11.glTranslated(0, -0.34, -0.07);
        candle.renderAll();
        GL11.glPopMatrix();
    }
}
