
package GraveStone.models.block;

import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ModelCrossGraveStone extends ModelGraveStone {

    ModelRenderer VerticalPart;
    ModelRenderer RightPart;
    ModelRenderer LeftPart;

    public ModelCrossGraveStone() {
        textureWidth = 32;
        textureHeight = 32;

        VerticalPart = new ModelRenderer(this, 0, 0);
        VerticalPart.addBox(0F, 0F, 0F, 2, 16, 2);
        VerticalPart.setRotationPoint(-1F, 8F, 5F);
        VerticalPart.setTextureSize(32, 32);
        VerticalPart.mirror = true;
        setRotation(VerticalPart, 0F, 0F, 0F);
        RightPart = new ModelRenderer(this, 0, 0);
        RightPart.addBox(0F, 0F, 0F, 5, 1, 1);
        RightPart.setRotationPoint(1F, 13F, 5.5F);
        RightPart.setTextureSize(32, 32);
        RightPart.mirror = true;
        setRotation(RightPart, 0F, 0F, 0F);
        LeftPart = new ModelRenderer(this, 0, 0);
        LeftPart.addBox(0F, 0F, 0F, 5, 1, 1);
        LeftPart.setRotationPoint(-6F, 13F, 5.5F);
        LeftPart.setTextureSize(32, 32);
        LeftPart.mirror = true;
        setRotation(LeftPart, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        VerticalPart.render(0.0625F);
        RightPart.render(0.0625F);
        LeftPart.render(0.0625F);
    }
}
