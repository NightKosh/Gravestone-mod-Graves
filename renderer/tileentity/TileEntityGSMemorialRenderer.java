package GraveStone.renderer.tileentity;

import GraveStone.models.block.ModelGraveStone;
import GraveStone.models.block.ModelMemorialCross;
import GraveStone.models.block.ModelMemorialObelisk;
import GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileEntityGSMemorialRenderer extends TileEntitySpecialRenderer {

    protected static ModelGraveStone cross = new ModelMemorialCross();
    protected static ModelGraveStone obelisk = new ModelMemorialObelisk();
    
    public void renderAModelAt(TileEntityGSMemorial tile, double d, double d1, double d2, float f) {
        byte memorialType = tile.getGraveType();
        int meta;
        if (tile.worldObj != null) {
            meta = tile.getBlockMetadata();
        } else {
            meta = 0;
        }
        getMemorialTexture(memorialType);

        //texture
        GL11.glPushMatrix();

        if (tile.worldObj != null) {
            GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
            GL11.glScalef(1F, -1F, -1F);
        } else {
            GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
            GL11.glScalef(0.4F, -0.4F, -0.4F);
        }

        switch (getMemorialDirection(meta)) {
            case 0:
                GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
                break;
            case 1:
                GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
                break;
            case 2:
                GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
                break;
            case 3:
                GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
                break;
        }
        getMemorialModel(memorialType).renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileEntity, double par2, double par4, double par6, float par8) {
        this.renderAModelAt((TileEntityGSMemorial) tileEntity, par2, par4, par6, par8);
    }

    private ModelGraveStone getMemorialModel(int memorialType) {
        switch (memorialType) {
            case 0:
                return cross;
            case 1:
                return obelisk;
            default:
                return cross;
        }
    }

    private void getMemorialTexture(int memorialType) {
        switch (memorialType) {
            case 0: // CROSS
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelMemorialCross.png");
                break;
            case 1: // OBELISK
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelMemorialObelisk.png");
                break;
        }
    }

    /*
     * Return grave direction by metadata
     */
    private static int getMemorialDirection(int meta) {
        switch (meta) {
            case 0: // S
                return 0;
            case 1: // N
                return 2;
            case 2: // E
                return 3;
            case 3: // W
                return 1;
            default:
                return 2;
        }
    }
}
