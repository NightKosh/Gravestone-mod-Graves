package gravestone.models.block.memorials;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelMemorialCross extends ModelMemorial {

    private ModelRenderer bottomPlate;
    private ModelRenderer bottomPlate2;
    private ModelRenderer verticalPart;
    private ModelRenderer rightHorizontalPart;
    private ModelRenderer leftHorizontalPart;

    public ModelMemorialCross() {
        textureWidth = 256;
        textureHeight = 128;
        bottomPlate = new ModelRenderer(this, 0, 0);
        bottomPlate.addBox(0, 0, 0, 48, 6, 48);
        bottomPlate.setRotationPoint(-24, 18, -24);
        bottomPlate.setTextureSize(64, 32);
        bottomPlate.mirror = true;
        setRotation(bottomPlate, 0, 0, 0);
        bottomPlate2 = new ModelRenderer(this, 0, 54);
        bottomPlate2.addBox(0, 0, 0, 32, 6, 32);
        bottomPlate2.setRotationPoint(-16, 12, -16);
        bottomPlate2.setTextureSize(64, 32);
        bottomPlate2.mirror = true;
        setRotation(bottomPlate2, 0, 0, 0);
        verticalPart = new ModelRenderer(this, 192, 0);
        verticalPart.addBox(0, 0, 0, 10, 68, 10);
        verticalPart.setRotationPoint(-5, -55, -5);
        verticalPart.setTextureSize(64, 32);
        verticalPart.mirror = true;
        setRotation(verticalPart, 0, 0, 0);
        rightHorizontalPart = new ModelRenderer(this, 128, 54);
        rightHorizontalPart.addBox(0, 0, 0, 20, 10, 10);
        rightHorizontalPart.setRotationPoint(5, -36, -5);
        rightHorizontalPart.setTextureSize(64, 32);
        rightHorizontalPart.mirror = true;
        setRotation(rightHorizontalPart, 0, 0, 0);
        leftHorizontalPart = new ModelRenderer(this, 128, 54);
        leftHorizontalPart.addBox(0, 0, 0, 20, 10, 10);
        leftHorizontalPart.setRotationPoint(-25, -36, -5);
        leftHorizontalPart.setTextureSize(64, 32);
        leftHorizontalPart.mirror = true;
        setRotation(leftHorizontalPart, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        bottomPlate.render(0.0625F);
        bottomPlate2.render(0.0625F);
        verticalPart.render(0.0625F);
        rightHorizontalPart.render(0.0625F);
        leftHorizontalPart.render(0.0625F);
    }
}
