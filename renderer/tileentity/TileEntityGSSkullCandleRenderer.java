package gravestone.renderer.tileentity;

import gravestone.block.enums.EnumSkullCandle;
import gravestone.core.Resources;
import gravestone.models.block.ModelSkullCandle;
import gravestone.tileentity.TileEntityGSSkullCandle;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSSkullCandleRenderer extends TileEntitySpecialRenderer {

    private ModelSkullCandle skullCandleModel = new ModelSkullCandle();

    public void renderTileEntitySkullAt(TileEntityGSSkullCandle tileEntity, float x, float y, float z, float par8) {
        float rotation = 0;
        byte meta;
        if (tileEntity.getWorld() != null) {
            rotation = (float) (tileEntity.getRotation() * 360) / 8F;
            meta = (byte) tileEntity.getBlockMetadata();
        } else {
            // TODO
            meta = 0;//(byte) tileEntity.blockMetadata;
        }

        bindSkullCandleTexture(EnumSkullCandle.getByID(meta));

        GL11.glPushMatrix();
        if (tileEntity.getWorld() == null) {
            GL11.glTranslatef(x + 0.5F, y + 2.2F, z + 0.5F);
            GL11.glScalef(1.5F, -1.5F, -1.5F);
        } else {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1.0F, -1F, -1F);
        }
        GL11.glRotatef(rotation, 0, 1, 0);

        skullCandleModel.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8, int par9) {
        this.renderTileEntitySkullAt((TileEntityGSSkullCandle) tileEntity, (float) x, (float) y, (float) z, par8);
    }

    private void bindSkullCandleTexture(EnumSkullCandle skullType) {
        switch (skullType) {
            case SKELETON_SKULL:
                this.bindTexture(Resources.SKELETON_SKULL_CANDLE);
                break;
            case WITHER_SKULL:
                this.bindTexture(Resources.WITHER_SKULL_CANDLE);
                break;
            case ZOMBIE_SKULL:
                this.bindTexture(Resources.ZOMBIE_SKULL_CANDLE);
                break;
        }
    }
}
