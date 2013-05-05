package GraveStone.renderer.tileentity;

import GraveStone.models.block.ModelCatStatueGraveStone;
import GraveStone.models.block.ModelCrossGraveStone;
import GraveStone.models.block.ModelDogStatueGraveStone;
import GraveStone.models.block.ModelGraveStone;
import GraveStone.models.block.ModelVerticalPlateGraveStone;
import GraveStone.models.block.ModelHorisontalPlateGraveStone;
import GraveStone.tileentity.TileEntityGSGraveStone;
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
public class TileEntityGSGraveStoneRenderer extends TileEntitySpecialRenderer {

    private static ModelGraveStone verticalPlate = new ModelVerticalPlateGraveStone();
    private static ModelGraveStone cross = new ModelCrossGraveStone();
    private static ModelGraveStone horisontalPlate = new ModelHorisontalPlateGraveStone();
    private static ModelGraveStone dogStatue = new ModelDogStatueGraveStone();
    private static ModelGraveStone catStatue = new ModelCatStatueGraveStone();

    public void renderAModelAt(TileEntityGSGraveStone tile, double d, double d1, double d2, float f) {
        byte graveType = tile.getGraveType();
        int meta;
        if (tile.worldObj != null) {
            meta = tile.getBlockMetadata();
        } else {
            meta = 0;
        }
        getGraveTexture(graveType);

        //texture
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        switch (getGraveDirection(meta)) {
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
        getGraveModel(graveType).renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileEntity, double d1, double d2, double d3, float par8) {
        this.renderAModelAt((TileEntityGSGraveStone) tileEntity, d1, d2, d3, par8);
    }

    private ModelGraveStone getGraveModel(byte graveType) {
        switch (graveType) {
            case 0:
                return verticalPlate;
            case 1:
                return cross;
            case 2:
                return horisontalPlate;
            case 3:
                return dogStatue;
            case 4:
                return catStatue;
            default:
                return verticalPlate;
        }
    }

    private void getGraveTexture(byte graveType) {
        switch (graveType) {
            case 0: // STONE_VERTICAL_PLATE
                bindTextureByName("/GraveStone/resources/textures/ModelVerticalPlateGraveStone.png");
                break;
            case 1: // STONE_CROSS
                bindTextureByName("/GraveStone/resources/textures/ModelCrossGraveStone.png");
                break;
            case 2: // STONE_HORISONTAL_PLATE
                bindTextureByName("/GraveStone/resources/textures/ModelHorisontalPlateGraveStone.png");
                break;
            case 3: // DOG_STATUE
                bindTextureByName("/GraveStone/resources/textures/ModelDogStatueGraveStone.png");
                break;
            case 4: // CAT_STATUE
                bindTextureByName("/GraveStone/resources/textures/ModelCatStatueGraveStone.png");
                break;
        }
    }

    /*
     * Return grave direction by metadata
     */
    private static int getGraveDirection(int meta) {
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
