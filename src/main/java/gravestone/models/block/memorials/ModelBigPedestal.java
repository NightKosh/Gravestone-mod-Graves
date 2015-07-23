/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.models.block.memorials;

import gravestone.models.block.ModelGraveStone;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBigPedestal extends ModelGraveStone {

    protected ModelRenderer pedestal1;
    protected ModelRenderer pedestal2;
    protected ModelRenderer pedestal3;
    protected ModelRenderer pedestal4;
    protected ModelRenderer pedestal5;
    protected ModelRenderer sign;

    protected ResourceLocation texture = null;

    public ModelBigPedestal() {
        textureWidth = 64;
        textureHeight = 64;

        pedestal1 = new ModelRenderer(this, 0, 24);
        pedestal1.addBox(0, 0, 0, 16, 1, 16);
        pedestal1.setRotationPoint(-8, 23, -8);
        pedestal1.setTextureSize(64, 64);
        pedestal1.mirror = true;
        setRotation(pedestal1, 0, 0, 0);
        pedestal2 = new ModelRenderer(this, 0, 24);
        pedestal2.addBox(0, 0, 0, 16, 1, 16);
        pedestal2.setRotationPoint(-8, 8, -8);
        pedestal2.setTextureSize(64, 64);
        pedestal2.mirror = true;
        setRotation(pedestal2, 0, 0, 0);
        pedestal3 = new ModelRenderer(this, 0, 41);
        pedestal3.addBox(0, 0, 0, 14, 1, 14);
        pedestal3.setRotationPoint(-7, 22, -7);
        pedestal3.setTextureSize(64, 64);
        pedestal3.mirror = true;
        setRotation(pedestal3, 0, 0, 0);
        pedestal4 = new ModelRenderer(this, 0, 41);
        pedestal4.addBox(0, 0, 0, 14, 1, 14);
        pedestal4.setRotationPoint(-7, 9, -7);
        pedestal4.setTextureSize(64, 64);
        pedestal4.mirror = true;
        setRotation(pedestal4, 0, 0, 0);
        pedestal5 = new ModelRenderer(this, 0, 0);
        pedestal5.addBox(0, 0, 0, 12, 12, 12);
        pedestal5.setRotationPoint(-6, 10, -6);
        pedestal5.setTextureSize(64, 64);
        pedestal5.mirror = true;
        setRotation(pedestal5, 0, 0, 0);
        sign = new ModelRenderer(this, 42, 41);
        sign.addBox(0, 0, 0, 10, 5, 1);
        sign.setRotationPoint(-5, 13, -6.5F);
        sign.setTextureSize(64, 64);
        sign.mirror = true;
        setRotation(sign, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        unshiftModel();
        float f5 = 0.0625F;
        TileEntityGSMemorialRenderer.instance.bindTextureByName(this.texture);
        pedestal1.render(f5);
        pedestal2.render(f5);
        pedestal3.render(f5);
        pedestal4.render(f5);
        pedestal5.render(f5);
        sign.render(f5);
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public static void shiftModel() {
        GL11.glTranslated(0, -1, 0);
    }

    public static void unshiftModel() {
        GL11.glTranslated(0, 1, 0);
    }
}
