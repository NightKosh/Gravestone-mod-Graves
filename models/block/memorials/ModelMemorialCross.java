package gravestone.models.block.memorials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.models.block.ModelGraveStone;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelMemorialCross extends ModelGraveStone {

    ModelRenderer BottomPlate;
    ModelRenderer BottomPlate2;
    ModelRenderer VerticalPart;
    ModelRenderer RightHorisontalPart;
    ModelRenderer LeftHorisontalPart;

    public ModelMemorialCross() {
        textureWidth = 256;
        textureHeight = 128;
        BottomPlate = new ModelRenderer(this, 0, 0);
        BottomPlate.addBox(0F, 0F, 0F, 48, 6, 48);
        BottomPlate.setRotationPoint(-24F, 18F, -24F);
        BottomPlate.setTextureSize(64, 32);
        BottomPlate.mirror = true;
        setRotation(BottomPlate, 0F, 0F, 0F);
        BottomPlate2 = new ModelRenderer(this, 0, 54);
        BottomPlate2.addBox(0F, 0F, 0F, 32, 6, 32);
        BottomPlate2.setRotationPoint(-16F, 12F, -16F);
        BottomPlate2.setTextureSize(64, 32);
        BottomPlate2.mirror = true;
        setRotation(BottomPlate2, 0F, 0F, 0F);
        VerticalPart = new ModelRenderer(this, 192, 0);
        VerticalPart.addBox(0F, 0F, 0F, 10, 68, 10);
        VerticalPart.setRotationPoint(-5F, -55F, -5F);
        VerticalPart.setTextureSize(64, 32);
        VerticalPart.mirror = true;
        setRotation(VerticalPart, 0F, 0F, 0F);
        RightHorisontalPart = new ModelRenderer(this, 128, 54);
        RightHorisontalPart.addBox(0F, 0F, 0F, 20, 10, 10);
        RightHorisontalPart.setRotationPoint(5F, -36F, -5F);
        RightHorisontalPart.setTextureSize(64, 32);
        RightHorisontalPart.mirror = true;
        setRotation(RightHorisontalPart, 0F, 0F, 0F);
        LeftHorisontalPart = new ModelRenderer(this, 128, 54);
        LeftHorisontalPart.addBox(0F, 0F, 0F, 20, 10, 10);
        LeftHorisontalPart.setRotationPoint(-25F, -36F, -5F);
        LeftHorisontalPart.setTextureSize(64, 32);
        LeftHorisontalPart.mirror = true;
        setRotation(LeftHorisontalPart, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        BottomPlate.render(0.0625F);
        BottomPlate2.render(0.0625F);
        VerticalPart.render(0.0625F);
        RightHorisontalPart.render(0.0625F);
        LeftHorisontalPart.render(0.0625F);
    }
}
