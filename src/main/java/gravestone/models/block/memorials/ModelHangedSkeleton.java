package gravestone.models.block.memorials;

import gravestone.models.IModelBaseAdapter;
import gravestone.models.entity.ModelGSSkeleton;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelHangedSkeleton extends ModelGSSkeleton implements IModelBaseAdapter {

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
        super(0, false);

        setRotation(skull, 0.1745329F, 0, 0);

        this.isInStocks = isInStocks;
        this.isWitherSkeleton = isWitherSkeleton;

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

        skull.renderWithTexture(f5, isWitherSkeleton);
    }

    @Override
    public void setTexturesOffset(String name, int xPos, int zPos) {
        super.setTextureOffset(name, xPos, zPos);
    }
}
