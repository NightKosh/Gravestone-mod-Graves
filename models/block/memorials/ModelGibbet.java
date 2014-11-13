package gravestone.models.block.memorials;

import gravestone.block.enums.EnumMemorials;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelGibbet extends ModelMemorial {

    private ModelRenderer horisontalPlank;
    private ModelRenderer verticalPlank;
    private ModelRenderer plank1;
    private ModelRenderer plank2;
    private ModelRenderer plank3;
    private ModelRenderer plank4;
    private ModelRenderer rope;
    private ModelRenderer rope2;
    private ModelRenderer knot;
    private ModelRenderer loop1;
    private ModelRenderer loop2;
    private ModelRenderer loop3;
    private ModelRenderer loop4;
    private ModelRenderer loop5;

    private static final ModelVillager villagerModel = new ModelVillager(0);

    public ModelGibbet() {
        textureWidth = 64;
        textureHeight = 128;

        horisontalPlank = new ModelRenderer(this, 0, 0);
        horisontalPlank.addBox(0F, 0F, 0F, 4, 56, 4);
        horisontalPlank.setRotationPoint(-2F, -32F, 3F);
        horisontalPlank.setTextureSize(64, 128);
        horisontalPlank.mirror = true;
        setRotation(horisontalPlank, 0F, 0F, 0F);
        verticalPlank = new ModelRenderer(this, 16, 0);
        verticalPlank.addBox(0F, 0F, 0F, 4, 4, 20);
        verticalPlank.setRotationPoint(-2F, -32F, -17F);
        verticalPlank.setTextureSize(64, 128);
        verticalPlank.mirror = true;
        setRotation(verticalPlank, 0F, 0F, 0F);
        plank1 = new ModelRenderer(this, 31, 24);
        plank1.addBox(0F, 0F, 0F, 4, 15, 3);
        plank1.setRotationPoint(-2F, 13.3F, 3.5F);
        plank1.setTextureSize(64, 128);
        plank1.mirror = true;
        setRotation(plank1, 0F, 0F, 0.7853982F);
        plank2 = new ModelRenderer(this, 16, 24);
        plank2.addBox(0F, 0F, 0F, 3, 15, 4);
        plank2.setRotationPoint(-1.5F, 13.3F, 3F);
        plank2.setTextureSize(64, 128);
        plank2.mirror = true;
        setRotation(plank2, -0.7853982F, 0F, 0F);
        plank3 = new ModelRenderer(this, 31, 42);
        plank3.addBox(0F, 0F, 0F, 4, 15, 3);
        plank3.setRotationPoint(-1F, 16F, 3.5F);
        plank3.setTextureSize(64, 128);
        plank3.mirror = true;
        setRotation(plank3, 0F, 0F, -0.7853982F);
        plank4 = new ModelRenderer(this, 16, 43);
        plank4.addBox(0F, 0F, 0F, 3, 15, 4);
        plank4.setRotationPoint(-1.5F, -20.3F, 6F);
        plank4.setTextureSize(64, 128);
        plank4.mirror = true;
        setRotation(plank4, -2.356194F, 0F, 0F);
        rope = new ModelRenderer(this, 0, 62);
        rope.addBox(0F, 0F, 0F, 5, 5, 3);
        rope.setRotationPoint(-2.5F, -32.5F, -16F);
        rope.setTextureSize(64, 128);
        rope.mirror = true;
        setRotation(rope, 0F, 0F, 0F);
        rope2 = new ModelRenderer(this, 0, 71);
        rope2.addBox(0F, 0F, 0F, 1, 16, 1);
        rope2.setRotationPoint(-0.5F, -28F, -15F);
        rope2.setTextureSize(64, 128);
        rope2.mirror = true;
        setRotation(rope2, 0F, 0F, 0F);
        knot = new ModelRenderer(this, 5, 71);
        knot.addBox(0F, 0F, 0F, 2, 6, 2);
        knot.setRotationPoint(-1.4F, -18F, -14.5F);
        knot.setTextureSize(64, 128);
        knot.mirror = true;
        setRotation(knot, 0F, 0.7853982F, 0F);
        loop1 = new ModelRenderer(this, 17, 65);
        loop1.addBox(0F, 0F, 0F, 1, 4, 1);
        loop1.setRotationPoint(-0.8F, -12.3F, -15F);
        loop1.setTextureSize(64, 128);
        loop1.mirror = true;
        setRotation(loop1, 0F, 0F, 0.1745329F);
        loop2 = new ModelRenderer(this, 22, 65);
        loop2.addBox(0F, 0F, 0F, 1, 4, 1);
        loop2.setRotationPoint(-0.2F, -12.1F, -15F);
        loop2.setTextureSize(64, 128);
        loop2.mirror = true;
        setRotation(loop2, 0F, 0F, -0.1745329F);
        loop3 = new ModelRenderer(this, 27, 65);
        loop3.addBox(0F, 0F, 0F, 2, 1, 1);
        loop3.setRotationPoint(-0.65F, -8.85F, -15F);
        loop3.setTextureSize(64, 128);
        loop3.mirror = true;
        setRotation(loop3, 0F, 0F, 1.082104F);
        loop4 = new ModelRenderer(this, 27, 68);
        loop4.addBox(0F, 0F, 0F, 2, 1, 1);
        loop4.setRotationPoint(1.5F, -8.4F, -15F);
        loop4.setTextureSize(64, 128);
        loop4.mirror = true;
        setRotation(loop4, 0F, 0F, 2.094395F);
        loop5 = new ModelRenderer(this, 34, 65);
        loop5.addBox(0F, 0F, 0F, 1, 1, 1);
        loop5.setRotationPoint(-0.5F, -7.6F, -15F);
        loop5.setTextureSize(64, 128);
        loop5.mirror = true;
        setRotation(loop5, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        horisontalPlank.render(0.0625F);
        verticalPlank.render(0.0625F);
        plank1.render(0.0625F);
        plank2.render(0.0625F);
        plank3.render(0.0625F);
        plank4.render(0.0625F);
        rope.render(0.0625F);
        rope2.render(0.0625F);
        knot.render(0.0625F);
        loop1.render(0.0625F);
        loop2.render(0.0625F);
        loop3.render(0.0625F);
        loop4.render(0.0625F);
        loop5.render(0.0625F);
    }

    public void customRender(EnumMemorials memorialType, int mob) {
        renderAll();
//
//        float xz = 0.0625F;
//
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0, -0.5F, -1);
//        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.VILLAGER_FARMER);
//        villagerModel.render(null, xz, xz, xz, xz, xz, xz);
//        GL11.glPopMatrix();
    }
}
