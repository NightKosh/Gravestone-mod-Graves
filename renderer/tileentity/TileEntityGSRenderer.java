
package GraveStone.renderer.tileentity;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSRenderer extends TileEntitySpecialRenderer {

    /**
     * Binds a texture to the renderEngine given a filename from the JAR.
     */
    @Override
    public void bindTextureByName(String par1Str) {
        RenderEngine renderengine = this.tileEntityRenderer.renderEngine;

        if (renderengine != null) {
            renderengine.bindTexture(par1Str);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
        
    }
}
