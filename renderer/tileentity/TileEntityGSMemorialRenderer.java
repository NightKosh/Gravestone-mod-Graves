package gravestone.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumMemorials;
import gravestone.models.block.ModelMemorial;
import gravestone.models.block.memorials.*;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSMemorialRenderer extends TileEntityGSRenderer {

    public static ModelMemorial cross = new ModelMemorialCross();
    public static ModelMemorial obelisk = new ModelMemorialObelisk();
    public static ModelMemorial steveStatue = new ModelSteveStatueMemorial();
    public static ModelMemorial villagerStatue = new ModelVillagerMemorial();
    public static ModelMemorial angelStatue = new ModelAngelStatueMemorial();
    public static ModelMemorial dogStatue = new ModelDogStatueMemorial();
    public static ModelMemorial catStatue = new ModelCatStatueMemorial();
    public static ModelMemorial creeperStatue = new ModelCreeperStatueMemorial();

    //private static IModelCustom celticCross = AdvancedModelLoader.loadModel("/assets/gravestone/obj_models/CelticCross.obj");
    //private ResourceLocation casinoTexture = new ResourceLocation("modid", "textures/casinoTexture.png");

    public static TileEntityGSMemorialRenderer instance;

    public TileEntityGSMemorialRenderer() {
        instance = this;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) te;
        EnumMemorials memorialType = tileEntity.getMemorialType();
        int meta;

        if (tileEntity.getWorldObj() != null) {
            meta = tileEntity.getBlockMetadata();
        } else {
            meta = 0;
        }

        bindTextureByName(memorialType.getTexture());

        //texture
        GL11.glPushMatrix();

        if (tileEntity.getWorldObj() != null) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1F, -1F, -1F);
        } else {
            switch (memorialType) {
                case WOODEN_CROSS:
                case SANDSTONE_CROSS:
                case STONE_CROSS:
                case MOSSY_CROSS:
                case IRON_CROSS:
                case GOLDEN_CROSS:
                case DIAMOND_CROSS:
                case EMERALD_CROSS:
                case LAPIS_CROSS:
                case REDSTONE_CROSS:
                case OBSIDIAN_CROSS:
                case QUARTZ_CROSS:
                case ICE_CROSS:
                case WOODEN_OBELISK:
                case SANDSTONE_OBELISK:
                case STONE_OBELISK:
                case MOSSY_OBELISK:
                case IRON_OBELISK:
                case GOLDEN_OBELISK:
                case DIAMOND_OBELISK:
                case EMERALD_OBELISK:
                case LAPIS_OBELISK:
                case REDSTONE_OBELISK:
                case OBSIDIAN_OBELISK:
                case QUARTZ_OBELISK:
                case ICE_OBELISK:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                    GL11.glScalef(0.4F, -0.4F, -0.4F);
                    break;
                default:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.8F, (float) z + 0.5F);
                    GL11.glScalef(0.7F, -0.7F, -0.7F);
                    break;
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
//
//        if (memorialType == 9) {
//            celticCross.renderAll();
//        } else

        memorialType.getModel().setPedestalTexture(memorialType.getPedestalTexture());
        switch (memorialType) {
            case WOODEN_CREEPER_STATUE:
            case SANDSTONE_CREEPER_STATUE:
            case STONE_CREEPER_STATUE:
            case MOSSY_CREEPER_STATUE:
            case IRON_CREEPER_STATUE:
            case GOLDEN_CREEPER_STATUE:
            case DIAMOND_CREEPER_STATUE:
            case EMERALD_CREEPER_STATUE:
            case LAPIS_CREEPER_STATUE:
            case REDSTONE_CREEPER_STATUE:
            case OBSIDIAN_CREEPER_STATUE:
            case QUARTZ_CREEPER_STATUE:
            case ICE_CREEPER_STATUE:
                memorialType.getModel().customRender();
                break;
            case WOODEN_STEVE_STATUE:
            case SANDSTONE_STEVE_STATUE:
            case STONE_STEVE_STATUE:
            case MOSSY_STEVE_STATUE:
            case IRON_STEVE_STATUE:
            case GOLDEN_STEVE_STATUE:
            case DIAMOND_STEVE_STATUE:
            case EMERALD_STEVE_STATUE:
            case LAPIS_STEVE_STATUE:
            case REDSTONE_STEVE_STATUE:
            case OBSIDIAN_STEVE_STATUE:
            case QUARTZ_STEVE_STATUE:
            case ICE_STEVE_STATUE:
                memorialType.getModel().customRender(memorialType);
                break;
            default:
                memorialType.getModel().renderAll();
        }

        GL11.glPopMatrix();
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
