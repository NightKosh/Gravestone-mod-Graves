package GraveStone.renderer.tileentity;

import GraveStone.models.block.ModelAngelStatueMemorial;
import GraveStone.models.block.ModelCatStatueMemorial;
import GraveStone.models.block.ModelCreeperStatueMemorial;
import GraveStone.models.block.ModelDogStatueMemorial;
import GraveStone.models.block.ModelGraveStone;
import GraveStone.models.block.ModelMemorialCross;
import GraveStone.models.block.ModelMemorialObelisk;
import GraveStone.models.block.ModelSteveStatueMemorial;
import GraveStone.models.block.ModelVillagerMemorial;
import GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileEntityGSMemorialRenderer extends TileEntityGSRenderer {

    protected static ModelGraveStone cross = new ModelMemorialCross();
    protected static ModelGraveStone obelisk = new ModelMemorialObelisk();
    protected static ModelGraveStone steveStatue = new ModelSteveStatueMemorial();
    protected static ModelGraveStone villagerStatue = new ModelVillagerMemorial();
    protected static ModelGraveStone angelStatue = new ModelAngelStatueMemorial();
    protected static ModelGraveStone dogStatue = new ModelDogStatueMemorial();
    protected static ModelGraveStone catStatue = new ModelCatStatueMemorial();
    protected static ModelCreeperStatueMemorial creeperStatue = new ModelCreeperStatueMemorial();
    public static TileEntityGSMemorialRenderer instance;

    public TileEntityGSMemorialRenderer() {
        instance = this;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) te;
        byte memorialType = tileEntity.getGraveType();
        int meta;
        if (tileEntity.worldObj != null) {
            meta = tileEntity.getBlockMetadata();
        } else {
            meta = 0;
        }
        getMemorialTexture(memorialType);

        //texture
        GL11.glPushMatrix();

        if (tileEntity.worldObj != null) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1F, -1F, -1F);
        } else {
            if (memorialType == 0 || memorialType == 1) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                GL11.glScalef(0.4F, -0.4F, -0.4F);
            } else {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.8F, (float) z + 0.5F);
                GL11.glScalef(0.7F, -0.7F, -0.7F);
            }
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

        if (memorialType == 7 || memorialType == 2) {
            getMemorialModel(memorialType).customRender();
        } else {
            getMemorialModel(memorialType).renderAll();
        }

        GL11.glPopMatrix();
    }

    private ModelGraveStone getMemorialModel(int memorialType) {
        switch (memorialType) {
            case 1:
                return obelisk;
            case 2:
                return steveStatue;
            case 3:
                return villagerStatue;
            case 4:
                return angelStatue;
            case 5:
                return dogStatue;
            case 6:
                return catStatue;
            case 7:
                return creeperStatue;
            case 0:
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
            case 2: // PLAYER_STATUE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelSteveStatueMemorial.png");
                break;
            case 3: // VILLAGER_STATUE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelVillagerStatueMemorial.png");
                break;
            case 4: // ANGEL_STAUTE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelAngelStatueMemorial.png");
                break;
            case 5: // DOG_STATUE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelDogStatueMemorial.png");
                break;
            case 6: // CAT_STAUTE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelCatStatueMemorial.png");
                break;
            case 7: //CREEPER_STATUE
                bindTextureByName("/mods/GraveStone/textures/memorials/ModelCreeperStatueMemorial.png");
                break;
        }
    }

    /**
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
