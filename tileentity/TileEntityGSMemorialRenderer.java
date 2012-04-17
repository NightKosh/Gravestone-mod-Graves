
package net.minecraft.GraveStone.tileentity;

import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.GraveStone.models.ModelGraveStone;
import net.minecraft.GraveStone.models.ModelMemorialCross;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityGSMemorialRenderer extends TileEntitySpecialRenderer  {
    
    public void renderAModelAt(TileEntityGSMemorial tile, double d, double d1, double d2, float f) {
        int meta = 0;
        if (tile.worldObj != null) {
            meta = tile.getBlockMetadata();
        } else {
            meta = tile.blockMetadata;
        }
        getMemorialTexture(meta);

        //texture
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        switch (getMemorialDirection(tile.blockMetadata)) {
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
        getMemorialModel(meta).renderAll();
        GL11.glPopMatrix(); //end
    }

    public void renderTileEntityAt(TileEntity tileEntity, double par2, double par4, double par6, float par8) {
        this.renderAModelAt((TileEntityGSMemorial) tileEntity, par2, par4, par6, par8);
    }

    private ModelGraveStone getMemorialModel(int meta) {
        switch (mod_GraveStone.memorial.getMemorialType(meta)) {
            case 0:
                return new ModelMemorialCross();
            default:
                return new ModelMemorialCross();
        }
    }

    private void getMemorialTexture(int meta) {
        switch (mod_GraveStone.memorial.getMemorialType(meta)) {
            case 0: // CROSS
                bindTextureByName("/GraveStone/resources/textures/ModelMemorialCross.png");
                break;
        }
    }

    /*
     * Return grave direction by metadata
     */
    private static int getMemorialDirection(int meta) {
        switch (mod_GraveStone.memorial.getMemorialMeta(meta)) {
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
