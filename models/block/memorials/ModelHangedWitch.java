package gravestone.models.block.memorials;

import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelHangedWitch extends ModelHangedVillager {

    private ModelRenderer witchHat;

    public ModelHangedWitch() {
        super(64, 128);

        this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        this.head.addChild(this.witchHat);
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelrenderer.rotateAngleX = -0.05235988F;
        modelrenderer.rotateAngleZ = 0.02617994F;
        this.witchHat.addChild(modelrenderer);
        ModelRenderer modelRenderer1 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelRenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelRenderer1.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelRenderer1.rotateAngleX = -0.10471976F;
        modelRenderer1.rotateAngleZ = 0.05235988F;
        modelrenderer.addChild(modelRenderer1);
        ModelRenderer modelRenderer2 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelRenderer2.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelRenderer2.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelRenderer2.rotateAngleX = -0.20943952F;
        modelRenderer2.rotateAngleZ = 0.10471976F;
        modelRenderer1.addChild(modelRenderer2);
    }
}
