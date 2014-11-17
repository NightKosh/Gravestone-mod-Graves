package gravestone.models.block.memorials;

import gravestone.core.Resources;
import gravestone.models.block.ModelSkull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSkeleton;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelHangedSkeleton extends ModelSkeleton {

    private ModelSkull skull;
    private boolean isWitherSkeleton = false;

    public ModelHangedSkeleton() {
        this(false);
    }

    public ModelHangedSkeleton(boolean isWitherSkeleton) {
        super(isWitherSkeleton ? 0.5F : 0);
        this.isWitherSkeleton = isWitherSkeleton;

        skull = new ModelSkull();
    }

    public void renderAll() {
        float f5 = 0.0625F;
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);

        Minecraft.getMinecraft().renderEngine.bindTexture(isWitherSkeleton ? Resources.WITHER_SKULL_CANDLE : Resources.SKELETON_SKULL_CANDLE);
        GL11.glPushMatrix();
        GL11.glRotated(20, 1, 0, 0);
        GL11.glTranslated(0, -1.5, 0);
        skull.renderAll();
        GL11.glPopMatrix();
    }
}
