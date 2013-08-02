package GraveStone.renderer.tileentity;

import GraveStone.Resources;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.models.block.ModelCatStatueGraveStone;
import GraveStone.models.block.ModelCrossGraveStone;
import GraveStone.models.block.ModelDogStatueGraveStone;
import GraveStone.models.block.ModelGraveStone;
import GraveStone.models.block.ModelVerticalPlateGraveStone;
import GraveStone.models.block.ModelHorisontalPlateGraveStone;
import GraveStone.models.block.ModelSwordGrave;
import GraveStone.tileentity.TileEntityGSGraveStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSGraveStoneRenderer extends TileEntityGSRenderer {

    private static ModelGraveStone verticalPlate = new ModelVerticalPlateGraveStone();
    private static ModelGraveStone cross = new ModelCrossGraveStone();
    private static ModelGraveStone horisontalPlate = new ModelHorisontalPlateGraveStone();
    private static ModelGraveStone dogStatue = new ModelDogStatueGraveStone();
    private static ModelGraveStone catStatue = new ModelCatStatueGraveStone();
    private static ModelGraveStone swordGrave = new ModelSwordGrave();

    public static TileEntityGSGraveStoneRenderer instance;
    
    public TileEntityGSGraveStoneRenderer() {
        instance = this;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) te;
        byte graveType = tileEntity.getGraveType();
        int meta;
        if (tileEntity.worldObj != null) {
            meta = tileEntity.getBlockMetadata();
        } else {
            meta = 0;
        }
        getGraveTexture(graveType);

        //texture
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
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
        if (BlockGSGraveStone.isSwordGrave(tileEntity) && tileEntity.isEnchanted()) {
            getGraveModel(graveType).customRender();
        } else {
            getGraveModel(graveType).renderAll();
        }
        
        GL11.glPopMatrix();
    }

    private ModelGraveStone getGraveModel(byte graveType) {
        switch (graveType) {
            case 1:
                return cross;
            case 2:
                return horisontalPlate;
            case 3:
                return dogStatue;
            case 4:
                return catStatue;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return swordGrave;
            case 0:
            default:
                return verticalPlate;
        }
    }

    private void getGraveTexture(byte graveType) {
        switch (graveType) {
            case 0: // STONE_VERTICAL_PLATE
                bindTextureByName(Resources.GRAVE_VERTICAL_PLATE);
                break;
            case 1: // STONE_CROSS
                bindTextureByName(Resources.GRAVE_CROSS);
                break;
            case 2: // STONE_HORISONTAL_PLATE
                bindTextureByName(Resources.GRAVE_HORISONTAL_PLATE);
                break;
            case 3: // DOG_STATUE
                bindTextureByName(Resources.DOG_STATUE_GRAVE);
                break;
            case 4: // CAT_STATUE
                bindTextureByName(Resources.CAT_STATUE_GRAVE);
                break;
            case 5: // WOODEN_SWORD_GRAVE
                bindTextureByName(Resources.WOODEN_SWORD_GRAVE);
                break;
            case 6: // STONE_SWORD_GRAVE
                bindTextureByName(Resources.STONE_SWORD_GRAVE);
                break;
            case 7: // IRON_SWORD_GRAVE
                bindTextureByName(Resources.IRON_SWORD_GRAVE);
                break;
            case 8: // GOLDEN_SWORD_GRAVE
                bindTextureByName(Resources.GOLDEN_SWORD_GRAVE);
                break;
            case 9: // DIAMOND_SWORD_GRAVE
                bindTextureByName(Resources.DIAMOND_SWORD_GRAVE);
                break;
        }
    }

    /**
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
