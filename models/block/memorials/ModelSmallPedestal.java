package gravestone.models.block.memorials;

import gravestone.core.Resources;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gravestone.models.block.ModelGraveStone;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelSmallPedestal extends ModelGraveStone {

    protected ModelRenderer Pedestal;
    protected ModelRenderer Pedestal2;

    public ModelSmallPedestal() {
        textureWidth = 64;
        textureHeight = 32;
        Pedestal = new ModelRenderer(this, 0, 0);
        Pedestal.addBox(0F, 0F, 0F, 16, 4, 16);
        Pedestal.setRotationPoint(-8F, 20F, -8F);
        Pedestal.setTextureSize(64, 32);
        Pedestal.mirror = true;
        setRotation(Pedestal, 0F, 0F, 0F);
        Pedestal2 = new ModelRenderer(this, 2, 2);
        Pedestal2.addBox(0F, 0F, 0F, 14, 4, 14);
        Pedestal2.setRotationPoint(-7F, 16F, -7F);
        Pedestal2.setTextureSize(64, 32);
        Pedestal2.mirror = true;
        setRotation(Pedestal2, 0F, 0F, 0F);
    }

    @Override
    public void renderAll() {
        unshiftModel();
        float par7 = 0.0625F;
        TileEntityGSMemorialRenderer.instance.bindTextureByName(Resources.SMALL_PEDESTAL);
        Pedestal.render(par7);
        Pedestal2.render(par7);
    }

    public static void shiftModel() {
        GL11.glTranslated(0, -0.5, 0);
    }

    public static void unshiftModel() {
        GL11.glTranslated(0, 0.5, 0);
    }
}
