package GraveStone.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSRenderer extends TileEntitySpecialRenderer {

    /**
     * Binds a texture to the renderEngine given a filename from the JAR.
     */
    /*
     @Override
     public void bindTextureByName(String par1Str) {
     RenderEngine renderengine = this.tileEntityRenderer.renderEngine;

     if (renderengine != null) {
     renderengine.bindTexture(par1Str);
     }
     }
     */
    public void bindTextureByName(ResourceLocation texture) {
        func_110628_a(texture);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
    }
}
