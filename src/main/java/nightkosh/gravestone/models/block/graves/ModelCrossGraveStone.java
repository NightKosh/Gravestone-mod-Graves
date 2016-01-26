package nightkosh.gravestone.models.block.graves;

import nightkosh.gravestone.models.block.ModelGraveStone;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelCrossGraveStone extends ModelGraveStone {

    ModelRenderer VerticalPart;
    ModelRenderer RightPart;
    ModelRenderer LeftPart;

    public ModelCrossGraveStone() {
        textureWidth = 32;
        textureHeight = 32;
        VerticalPart = new ModelRenderer(this, 0, 0);
        VerticalPart.addBox(0, 0, 0, 2, 16, 2);
        VerticalPart.setRotationPoint(-1, 8, 5);
        VerticalPart.setTextureSize(32, 32);
        VerticalPart.mirror = true;
        setRotation(VerticalPart, 0, 0, 0);
        RightPart = new ModelRenderer(this, 0, 0);
        RightPart.addBox(0, 0, 0, 5, 1, 1);
        RightPart.setRotationPoint(1, 13, 5.5F);
        RightPart.setTextureSize(32, 32);
        RightPart.mirror = true;
        setRotation(RightPart, 0, 0, 0);
        LeftPart = new ModelRenderer(this, 0, 0);
        LeftPart.addBox(0, 0, 0, 5, 1, 1);
        LeftPart.setRotationPoint(-6, 13, 5.5F);
        LeftPart.setTextureSize(32, 32);
        LeftPart.mirror = true;
        setRotation(LeftPart, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        VerticalPart.render(0.0625F);
        RightPart.render(0.0625F);
        LeftPart.render(0.0625F);
    }
}
