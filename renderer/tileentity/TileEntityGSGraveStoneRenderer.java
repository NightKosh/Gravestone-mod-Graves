package gravestone.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumGraves;
import gravestone.models.block.ModelGraveStone;
import gravestone.models.block.graves.*;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
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

    public static ModelGraveStone verticalPlate = new ModelVerticalPlateGraveStone();
    public static ModelGraveStone cross = new ModelCrossGraveStone();
    public static ModelGraveStone horisontalPlate = new ModelHorisontalPlateGraveStone();
    public static ModelGraveStone dogStatue = new ModelDogStatueGraveStone();
    public static ModelGraveStone catStatue = new ModelCatStatueGraveStone();
    public static ModelGraveStone horseStatue = new ModelHorseGraveStone();
    public static ModelGraveStone swordGrave = new ModelSwordGrave();
    public static TileEntityGSGraveStoneRenderer instance;

    public TileEntityGSGraveStoneRenderer() {
        instance = this;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) te;
        EnumGraves graveType = tileEntity.getGraveType();
        int meta = 0;

        if (tileEntity.getWorldObj() != null) {
            meta = tileEntity.getBlockMetadata();
        }

        if (graveType != EnumGraves.SWORD) {
            bindTextureByName(graveType.getTexture());
        }
        //texture
        GL11.glPushMatrix();

        if (tileEntity.getWorldObj() == null && tileEntity.isSwordGrave()) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 2, (float) z + 0.5F);
            GL11.glScalef(1.5F, -1.5F, -1.5F);
        } else {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1.0F, -1F, -1F);
        }

        switch (getGraveDirection(meta)) {
            case 0:
                GL11.glRotatef(0, 0, 1, 0);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case 2:
                GL11.glRotatef(180, 0, 1, 0);
                break;
            case 3:
                GL11.glRotatef(270, 0, 1, 0);
                break;
        }

        if (tileEntity.isSwordGrave()) {
            renderSword(tileEntity);
        } else {
            graveType.getModel().renderAll();
        }

        GL11.glPopMatrix();
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

    private void renderSword(TileEntityGSGraveStone te) {
        EntityItem entityitem = new EntityItem(te.getWorldObj(), 0, 0, 0, te.getSword());
        entityitem.hoverStart = 0;
        GL11.glTranslatef(0.24F, 0.83F, 0);
        GL11.glScalef(1.5F, -1.5F, -1.5F);
        GL11.glRotatef(135, 0, 0, 1);

        RenderManager.instance.renderEntityWithPosYaw(entityitem, 0, 0, 0, 0, 0);
    }
}
