package gravestone.models.block.memorials;

import gravestone.core.Resources;
import gravestone.models.block.ModelSkull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
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

    protected ModelRenderer rightArm;
    protected ModelRenderer leftArm;
    protected ModelRenderer rightArm2;
    protected ModelRenderer leftArm2;
    private boolean isWitherSkeleton = false;

    private boolean isInStocks = false;

    public ModelHangedSkeleton(boolean isInStocks) {
        this(isInStocks, false);
    }

    public ModelHangedSkeleton(boolean isInStocks, boolean isWitherSkeleton) {
        //TODO
//        super(0);
        super(0, false);

        this.isInStocks = isInStocks;
        this.isWitherSkeleton = isWitherSkeleton;

        skull = new ModelSkull();

        // arms (should render only when creature in stocks)
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.addBox(-1, -2, -1, 2, 6, 2);
        rightArm.setRotationPoint(-6, 1, 0);
        setRotation(this.rightArm, 0, 0, 1.57F);
        rightArm.setTextureSize(textureWidth, textureHeight);

        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.addBox(-1, -2, -1, 2, 6, 2);
        leftArm.setRotationPoint(6, 1, 0);
        setRotation(this.leftArm, 0, 0, -1.57F);
        leftArm.setTextureSize(textureWidth, textureHeight);

        rightArm2 = new ModelRenderer(this, 40, 16);
        rightArm2.addBox(0, 0, 0, 2, 8, 2);
        rightArm2.setRotationPoint(-9, 2, -1);
        setRotation(this.rightArm2, 0, 0, 3.14F);
        rightArm2.setTextureSize(textureWidth, textureHeight);

        leftArm2 = new ModelRenderer(this, 40, 16);
        leftArm2.addBox(0, 0, 0, 2, 8, 2);
        leftArm2.setRotationPoint(11, 2, -1);
        setRotation(this.leftArm2, 0, 0, 3.14F);
        leftArm2.setTextureSize(textureWidth, textureHeight);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll() {
        float f5 = 0.0625F;
        this.bipedBody.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);

        if (this.isInStocks) {
            this.rightArm.render(f5);
            this.leftArm.render(f5);
            this.rightArm2.render(f5);
            this.leftArm2.render(f5);
        } else {
            this.bipedRightArm.render(f5);
            this.bipedLeftArm.render(f5);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(isWitherSkeleton ? Resources.WITHER_SKULL_CANDLE : Resources.SKELETON_SKULL_CANDLE);
        GL11.glPushMatrix();
        GL11.glRotated(20, 1, 0, 0);
        GL11.glTranslated(0, -1.5, 0);
        skull.renderAll();
        GL11.glPopMatrix();
    }
}
