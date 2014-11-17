package gravestone.models.block.memorials;

import gravestone.block.enums.EnumHangedMobs;
import gravestone.block.enums.EnumMemorials;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelStocks extends ModelMemorial {

    private ModelRenderer horisontalPlank;
    private ModelRenderer verticalPlank1;
    private ModelRenderer verticalPlank2;
    private ModelRenderer headHole;
    private ModelRenderer armHole1;
    private ModelRenderer armHole2;

    public ModelStocks() {
        textureWidth = 128;
        textureHeight = 64;

        horisontalPlank = new ModelRenderer(this, 0, 0);
        horisontalPlank.addBox(0F, 0F, 0F, 32, 12, 1);
        horisontalPlank.setRotationPoint(-16F, -2F, 0F);
        horisontalPlank.setTextureSize(128, 64);
        horisontalPlank.mirror = true;
        setRotation(horisontalPlank, 0F, 0F, 0F);
        verticalPlank1 = new ModelRenderer(this, 0, 13);
        verticalPlank1.addBox(0F, 0F, 0F, 3, 28, 2);
        verticalPlank1.setRotationPoint(-19F, -4F, -0.5F);
        verticalPlank1.setTextureSize(128, 64);
        verticalPlank1.mirror = true;
        setRotation(verticalPlank1, 0F, 0F, 0F);
        verticalPlank2 = new ModelRenderer(this, 11, 13);
        verticalPlank2.addBox(0F, 0F, 0F, 3, 28, 2);
        verticalPlank2.setRotationPoint(16F, -4F, -0.5F);
        verticalPlank2.setTextureSize(128, 64);
        verticalPlank2.mirror = true;
        setRotation(verticalPlank2, 0F, 0F, 0F);
        headHole = new ModelRenderer(this, 22, 13);
        headHole.addBox(0F, 0F, 0F, 6, 6, 1);
        headHole.setRotationPoint(-3F, 1F, 0F);
        headHole.setTextureSize(128, 64);
        headHole.mirror = true;
        setRotation(headHole, 0F, 0F, 0F);
        armHole1 = new ModelRenderer(this, 37, 13);
        armHole1.addBox(0F, 0F, 0F, 2, 2, 1);
        armHole1.setRotationPoint(-11F, 3F, 0F);
        armHole1.setTextureSize(128, 64);
        armHole1.mirror = true;
        setRotation(armHole1, 0F, 0F, 0F);
        armHole2 = new ModelRenderer(this, 44, 13);
        armHole2.addBox(0F, 0F, 0F, 2, 2, 1);
        armHole2.setRotationPoint(9F, 3F, 0F);
        armHole2.setTextureSize(128, 64);
        armHole2.mirror = true;
        setRotation(armHole2, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        horisontalPlank.render(0.0625F);
        verticalPlank1.render(0.0625F);
        verticalPlank2.render(0.0625F);
        headHole.render(0.0625F);
        armHole1.render(0.0625F);
        armHole2.render(0.0625F);
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
        renderAll();
    }
}
