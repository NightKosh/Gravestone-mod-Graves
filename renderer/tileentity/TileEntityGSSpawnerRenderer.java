package gravestone.renderer.tileentity;

import gravestone.core.Resources;
import gravestone.models.block.ModelSpawnerPentagram;
import gravestone.tileentity.TileEntityGSWitherSpawner;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSSpawnerRenderer extends TileEntitySpecialRenderer {

    private ModelSpawnerPentagram spawnerPentagram = new ModelSpawnerPentagram();

    /**
     * Render a skull tile entity.
     */
    public void renderSpawnerPentagramAt(TileEntityGSWitherSpawner tileEntity, float x, float y, float z, float par8) {

        this.bindTexture(Resources.PENTAGRAM);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glTranslated(0, -0.01, 0);

        spawnerPentagram.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        this.renderSpawnerPentagramAt((TileEntityGSWitherSpawner) tileEntity, (float) x, (float) y, (float) z, par8);
    }
}
