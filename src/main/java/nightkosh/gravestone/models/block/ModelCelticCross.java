package nightkosh.gravestone.models.block;

import net.minecraft.client.model.ModelRenderer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelCelticCross extends ModelGraveStone {

    private ModelRenderer shape11;
    private ModelRenderer shape21;
    private ModelRenderer shape3;
    private ModelRenderer shape41;
    private ModelRenderer shape42;
    private ModelRenderer shape22;
    private ModelRenderer shape12;
    private ModelRenderer shape51;
    private ModelRenderer shape52;
    private ModelRenderer shape61;
    private ModelRenderer shape62;
    private ModelRenderer shape63;
    private ModelRenderer shape64;
    private ModelRenderer shape71;
    private ModelRenderer shape72;
    private ModelRenderer shape73;
    private ModelRenderer shape74;
    private ModelRenderer shape8;
    private ModelRenderer shape9;

    public ModelCelticCross() {
        textureWidth = 64;
        textureHeight = 64;

        shape11 = new ModelRenderer(this, 0, 0);
        shape11.addBox(0, 0, 0, 5, 17, 5);
        shape11.setRotationPoint(-2.5F, 3, -2.5F);
        shape11.setTextureSize(textureWidth, textureHeight);

        shape21 = new ModelRenderer(this, 21, 14);
        shape21.addBox(0, 0, 0, 3, 4, 4);
        shape21.setRotationPoint(-1.5F, -1, -2);
        shape21.setTextureSize(textureWidth, textureHeight);

        shape3 = new ModelRenderer(this, 42, 0);
        shape3.addBox(0, 0, 0, 6, 6, 5);
        shape3.setRotationPoint(0, -7, -2.5F);
        shape3.setTextureSize(textureWidth, textureHeight);
        setRotation(shape3, 0, 0, 0.7853982F);

        shape41 = new ModelRenderer(this, 0, 34);
        shape41.addBox(0, 0, 0, 4, 3, 4);
        shape41.setRotationPoint(-6, -4.5F, -2);
        shape41.setTextureSize(textureWidth, textureHeight);

        shape42 = new ModelRenderer(this, 17, 34);
        shape42.addBox(0, 0, 0, 4, 3, 4);
        shape42.setRotationPoint(2, -4.5F, -2);
        shape42.setTextureSize(textureWidth, textureHeight);

        shape22 = new ModelRenderer(this, 36, 14);
        shape22.addBox(0, 0, 0, 3, 4, 4);
        shape22.setRotationPoint(-1.5F, -9, -2);
        shape22.setTextureSize(textureWidth, textureHeight);

        shape12 = new ModelRenderer(this, 21, 0);
        shape12.addBox(0, 0, 0, 5, 8, 5);
        shape12.setRotationPoint(-2.5F, -17, -2.5F);
        shape12.setTextureSize(textureWidth, textureHeight);
        shape12.mirror = true;
        setRotation(shape12, 0, 0, 0);
        shape51 = new ModelRenderer(this, 0, 23);
        shape51.addBox(0, 0, 0, 8, 5, 5);
        shape51.setRotationPoint(-14, -5.5F, -2.5F);
        shape51.setTextureSize(textureWidth, textureHeight);

        shape52 = new ModelRenderer(this, 27, 23);
        shape52.addBox(0, 0, 0, 8, 5, 5);
        shape52.setRotationPoint(6, -5.5F, -2.5F);
        shape52.setTextureSize(textureWidth, textureHeight);

        shape61 = new ModelRenderer(this, 33, 59);
        shape61.addBox(0, 0, 0, 4, 2, 3);
        shape61.setRotationPoint(2, 3, -1.5F);
        shape61.setTextureSize(textureWidth, textureHeight);

        shape62 = new ModelRenderer(this, 33, 42);
        shape62.addBox(0, 0, 0, 4, 2, 3);
        shape62.setRotationPoint(2, -11F, -1.5F);
        shape62.setTextureSize(textureWidth, textureHeight);

        shape63 = new ModelRenderer(this, 11, 59);
        shape63.addBox(0, 0, 0, 4, 2, 3);
        shape63.setRotationPoint(-6, 3, -1.5F);
        shape63.setTextureSize(textureWidth, textureHeight);

        shape64 = new ModelRenderer(this, 11, 42);
        shape64.addBox(0, 0, 0, 4, 2, 3);
        shape64.setRotationPoint(-6, -11, -1.5F);
        shape64.setTextureSize(textureWidth, textureHeight);

        shape71 = new ModelRenderer(this, 48, 55);
        shape71.addBox(0, 0, 0, 2, 6, 3);
        shape71.setRotationPoint(6, -1, -1.5F);
        shape71.setTextureSize(textureWidth, textureHeight);

        shape72 = new ModelRenderer(this, 0, 55);
        shape72.addBox(0, 0, 0, 2, 6, 3);
        shape72.setRotationPoint(-8, -1, -1.5F);
        shape72.setTextureSize(textureWidth, textureHeight);

        shape73 = new ModelRenderer(this, 48, 42);
        shape73.addBox(0, 0, 0, 2, 6, 3);
        shape73.setRotationPoint(6, -11, -1.5F);
        shape73.setTextureSize(textureWidth, textureHeight);

        shape74 = new ModelRenderer(this, 0, 42);
        shape74.addBox(0, 0, 0, 2, 6, 3);
        shape74.setRotationPoint(-8, -11, -1.5F);
        shape74.setTextureSize(textureWidth, textureHeight);

        shape8 = new ModelRenderer(this, 36, 33);
        shape8.addBox(0, 0, 0, 7, 2, 7);
        shape8.setRotationPoint(-3.5F, 20, -3.5F);
        shape8.setTextureSize(textureWidth, textureHeight);

        shape9 = new ModelRenderer(this, 11, 47);
        shape9.addBox(0, 0, 0, 9, 2, 9);
        shape9.setRotationPoint(-4.5F, 22, -4.5F);
        shape9.setTextureSize(textureWidth, textureHeight);
    }


    @Override
    public void renderAll() {
        shape11.render(0.0625F);
        shape21.render(0.0625F);
        shape3.render(0.0625F);
        shape41.render(0.0625F);
        shape42.render(0.0625F);
        shape22.render(0.0625F);
        shape12.render(0.0625F);
        shape51.render(0.0625F);
        shape52.render(0.0625F);
        shape61.render(0.0625F);
        shape62.render(0.0625F);
        shape63.render(0.0625F);
        shape64.render(0.0625F);
        shape71.render(0.0625F);
        shape72.render(0.0625F);
        shape73.render(0.0625F);
        shape74.render(0.0625F);
        shape8.render(0.0625F);
        shape9.render(0.0625F);
    }
}
