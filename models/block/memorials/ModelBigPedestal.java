/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.models.block.memorials;

import gravestone.core.Resources;
import gravestone.models.block.ModelGraveStone;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBigPedestal extends ModelGraveStone {

    protected ModelRenderer Pedestal1;
    protected ModelRenderer Pedestal2;
    protected ModelRenderer Pedestal3;
    protected ModelRenderer Pedestal4;
    protected ModelRenderer Pedestal5;
    protected ModelRenderer Sign;

    public ModelBigPedestal() {
        textureWidth = 64;
        textureHeight = 64;

        Pedestal1 = new ModelRenderer(this, 0, 24);
        Pedestal1.addBox(0F, 0F, 0F, 16, 1, 16);
        Pedestal1.setRotationPoint(-8F, 23F, -8F);
        Pedestal1.setTextureSize(64, 64);
        Pedestal1.mirror = true;
        setRotation(Pedestal1, 0F, 0F, 0F);
        Pedestal2 = new ModelRenderer(this, 0, 24);
        Pedestal2.addBox(0F, 0F, 0F, 16, 1, 16);
        Pedestal2.setRotationPoint(-8F, 8F, -8F);
        Pedestal2.setTextureSize(64, 64);
        Pedestal2.mirror = true;
        setRotation(Pedestal2, 0F, 0F, 0F);
        Pedestal3 = new ModelRenderer(this, 0, 41);
        Pedestal3.addBox(0F, 0F, 0F, 14, 1, 14);
        Pedestal3.setRotationPoint(-7F, 22F, -7F);
        Pedestal3.setTextureSize(64, 64);
        Pedestal3.mirror = true;
        setRotation(Pedestal3, 0F, 0F, 0F);
        Pedestal4 = new ModelRenderer(this, 0, 41);
        Pedestal4.addBox(0F, 0F, 0F, 14, 1, 14);
        Pedestal4.setRotationPoint(-7F, 9F, -7F);
        Pedestal4.setTextureSize(64, 64);
        Pedestal4.mirror = true;
        setRotation(Pedestal4, 0F, 0F, 0F);
        Pedestal5 = new ModelRenderer(this, 0, 0);
        Pedestal5.addBox(0F, 0F, 0F, 12, 12, 12);
        Pedestal5.setRotationPoint(-6F, 10F, -6F);
        Pedestal5.setTextureSize(64, 64);
        Pedestal5.mirror = true;
        setRotation(Pedestal5, 0F, 0F, 0F);
        Sign = new ModelRenderer(this, 42, 41);
        Sign.addBox(0F, 0F, 0F, 10, 5, 1);
        Sign.setRotationPoint(-5F, 13F, -6.5F);
        Sign.setTextureSize(64, 64);
        Sign.mirror = true;
        setRotation(Sign, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        unshiftModel();
        float f5 = 0.0625F;
        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.BIG_PEDESTAL);
        Pedestal1.render(f5);
        Pedestal2.render(f5);
        Pedestal3.render(f5);
        Pedestal4.render(f5);
        Pedestal5.render(f5);
        Sign.render(f5);
    }

    public static void shiftModel() {
        GL11.glTranslated(0, -1, 0);
    }

    public static void unshiftModel() {
        GL11.glTranslated(0, 1, 0);
    }
}
