package gravestone.models.block.memorials;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombieVillager;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelHangedZombieVillager extends ModelZombieVillager {

    protected ModelRenderer rightArm;
    protected ModelRenderer leftArm;
    protected ModelRenderer rightArm2;
    protected ModelRenderer leftArm2;


    private boolean isInStocks = false;

    public ModelHangedZombieVillager(boolean isInStocks) {
        this.isInStocks = isInStocks;

        setRotation(this.bipedHead, 0.20F, 0F, 0F);

        // arms (should render only when creature in stocks)
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.addBox(-4, -2, -2, 4, 4, 4);
        rightArm.setRotationPoint(-5, 3, 0);
        setRotation(this.rightArm, 0, 0, 1.57F);
        rightArm.setTextureSize(textureWidth, textureHeight);

        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.addBox(0, -2, -2, 4, 4, 4);
        leftArm.setRotationPoint(5, 3, 0);
        setRotation(this.leftArm, 0, 0, -1.57F);
        leftArm.setTextureSize(textureWidth, textureHeight);

        rightArm2 = new ModelRenderer(this, 40, 16);
        rightArm2.addBox(0, 0, 0, 4, 8, 4);
        rightArm2.setRotationPoint(-7, 3, -2);
        setRotation(this.rightArm2, 0, 0, 3.14F);
        rightArm2.setTextureSize(textureWidth, textureHeight);

        leftArm2 = new ModelRenderer(this, 40, 16);
        leftArm2.addBox(0, 0, 0, 4, 8, 4);
        leftArm2.setRotationPoint(11, 3, -2);
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
        this.bipedHead.render(f5);
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
    }
}
