package gravestone.models.block.memorials;

import gravestone.core.Resources;
import gravestone.models.block.ModelGraveStone;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelSmallPedestal extends ModelGraveStone {

    protected ModelRenderer pedestal;
    protected ModelRenderer pedestal2;

    public ModelSmallPedestal() {
        textureWidth = 64;
        textureHeight = 32;
        pedestal = new ModelRenderer(this, 0, 0);
        pedestal.addBox(0, 0, 0, 16, 4, 16);
        pedestal.setRotationPoint(-8, 20, -8);
        pedestal.setTextureSize(64, 32);
        pedestal.mirror = true;
        setRotation(pedestal, 0, 0, 0);
        pedestal2 = new ModelRenderer(this, 2, 2);
        pedestal2.addBox(0, 0, 0, 14, 4, 14);
        pedestal2.setRotationPoint(-7, 16, -7);
        pedestal2.setTextureSize(64, 32);
        pedestal2.mirror = true;
        setRotation(pedestal2, 0, 0, 0);
    }

    @Override
    public void renderAll() {
        unshiftModel();
        float par7 = 0.0625F;
        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.SMALL_PEDESTAL);
        pedestal.render(par7);
        pedestal2.render(par7);
    }

    public static void shiftModel() {
        GL11.glTranslated(0, -0.5, 0);
    }

    public static void unshiftModel() {
        GL11.glTranslated(0, 0.5, 0);
    }
}