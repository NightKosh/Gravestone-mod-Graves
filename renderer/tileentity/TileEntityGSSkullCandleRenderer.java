package gravestone.renderer.tileentity;

import gravestone.core.Resources;
import gravestone.models.block.ModelSkullCandle;
import gravestone.tileentity.TileEntityGSSkullCandle;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSSkullCandleRenderer extends TileEntitySpecialRenderer {

    private ModelSkeletonHead skeletonSkullModel = new ModelSkeletonHead(0, 0, 64, 32);
    private ModelSkullCandle skullCandleModel = new ModelSkullCandle();

    /**
     * Render a skull tile entity.
     */
    public void renderTileEntitySkullAt(TileEntityGSSkullCandle tileEntitySkullCandle, float x, float y, float z, float par8) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        float rotation = (float) (tileEntitySkullCandle.getRotation() * 360) / 16.0F;
        int meta = tileEntitySkullCandle.getBlockMetadata() & 7;
        switch (meta) {
            case 1:
                GL11.glTranslatef(x + 0.5F, y, z + 0.5F);
                break;
            case 2:
                GL11.glTranslatef(x + 0.5F, y + 0.25F, z + 0.74F);
                break;
            case 3:
                GL11.glTranslatef(x + 0.5F, y + 0.25F, z + 0.26F);
                rotation = 180.0F;
                break;
            case 4:
                GL11.glTranslatef(x + 0.74F, y + 0.25F, z + 0.5F);
                rotation = 270.0F;
                break;
            case 5:
            default:
                GL11.glTranslatef(x + 0.26F, y + 0.25F, z + 0.5F);
                rotation = 90.0F;
        }

        float f4 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        
        this.bindTexture(Resources.SKELETON_SKULL);
        skeletonSkullModel.render((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, f4);
        this.bindTexture(Resources.SKULL_CANDLE);
        skullCandleModel.render((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, f4);
        GL11.glPopMatrix();
    }

    /**
     * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
     */
    @Override
    public void setTileEntityRenderer(TileEntityRenderer tileEntityRenderer) {
        super.setTileEntityRenderer(tileEntityRenderer);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        this.renderTileEntitySkullAt((TileEntityGSSkullCandle) tileEntity, (float) x, (float) y, (float) z, par8);
    }
}
