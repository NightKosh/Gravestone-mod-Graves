package gravestone.renderer.tileentity;

import gravestone.core.Resources;
import gravestone.tileentity.TileEntityGSGhostlyChest;
import java.util.Calendar;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSGhostlyChestRenderer extends TileEntitySpecialRenderer {

    /**
     * The normal small chest model.
     */
    private ModelChest chestModel = new ModelChest();
    /**
     * If true, chests will be rendered with the Christmas present textures.
     */
    private boolean isChristmas;

    public TileEntityGSGhostlyChestRenderer() {
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.isChristmas = true;
        }
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void renderTileEntityChestAt(TileEntityGSGhostlyChest te, double par2, double par4, double par6, float par8) {
        int metadata = 0;

        if (te.hasWorldObj()) {
            metadata = te.getBlockMetadata();
        }


        ModelChest modelchest = this.chestModel;

        if (this.isChristmas) {
            this.func_110628_a(Resources.CHRISTMAS_CHEST);
        } else {
            this.func_110628_a(Resources.DEFAULT_CHEST);
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) par2, (float) par4 + 1.0F, (float) par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        float direction = 0;

        switch (metadata) {
            case 2:
                direction = 180;
                break;
            case 3:
                direction = 0;
                break;
            case 4:
                direction = 90;
                break;
            case 5:
                direction = -90;
                break;
        }

        GL11.glRotatef(direction, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float f1 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * par8;

        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
        modelchest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float par8) {
        this.renderTileEntityChestAt((TileEntityGSGhostlyChest) te, x, y, z, par8);
    }
}
