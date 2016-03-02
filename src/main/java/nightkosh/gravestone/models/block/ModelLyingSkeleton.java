package nightkosh.gravestone.models.block;

import nightkosh.gravestone.models.IModelBaseAdapter;
import nightkosh.gravestone.models.entity.ModelGSSkeleton;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelLyingSkeleton extends ModelGSSkeleton implements IModelBaseAdapter {
    private boolean isWitherSkeleton;

    public ModelLyingSkeleton() {
        this(false);
    }

    public ModelLyingSkeleton(boolean isWitherSkeleton) {
        super(0, false);

        setRotation(skull, 0.9F, 0, 0.4F);

        setRotation(bipedRightArm, 0, 0, 0.7F);
        setRotation(bipedLeftArm, 0, 0, -0.8F);

        setRotation(bipedRightLeg, 0, 0, 0.2F);
        setRotation(bipedLeftLeg, 0, 0, -0.15F);

        this.isWitherSkeleton = isWitherSkeleton;
    }

    public void renderAll() {
        float f5 = 0.0625F;
        this.bipedBody.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);

        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);

        skull.renderWithTexture(f5, isWitherSkeleton);
    }

    @Override
    public void setTexturesOffset(String name, int xPos, int zPos) {
        super.setTextureOffset(name, xPos, zPos);
    }
}
