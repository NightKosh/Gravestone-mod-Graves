package gravestone.renderer.tileentity;

import gravestone.core.Resources;
import gravestone.block.enums.EnumGraves;
import gravestone.block.GraveStoneHelper;
import gravestone.models.block.graves.ModelCatStatueGraveStone;
import gravestone.models.block.graves.ModelCrossGraveStone;
import gravestone.models.block.graves.ModelDogStatueGraveStone;
import gravestone.models.block.ModelGraveStone;
import gravestone.models.block.graves.ModelVerticalPlateGraveStone;
import gravestone.models.block.graves.ModelHorisontalPlateGraveStone;
import gravestone.models.block.graves.ModelSwordGrave;
import gravestone.tileentity.TileEntityGSGraveStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.models.block.graves.ModelHorseGraveStone;
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
    private static ModelGraveStone horseStatue = new ModelHorseGraveStone();
    private static ModelGraveStone swordGrave = new ModelSwordGrave();
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

        getGraveTexture(graveType);
        //texture
        GL11.glPushMatrix();

        if (tileEntity.getWorldObj() == null && GraveStoneHelper.isSwordGrave(tileEntity)) {
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

        if (GraveStoneHelper.isSwordGrave(tileEntity) && tileEntity.isEnchanted()) {
            getGraveModel(graveType).customRender();
        } else {
            getGraveModel(graveType).renderAll();
        }

        GL11.glPopMatrix();
    }

    private ModelGraveStone getGraveModel(EnumGraves graveType) {
        switch (graveType) {
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
                return cross;
            case HORISONTAL_PLATE:
                return horisontalPlate;
            case WOODEN_DOG_STATUE:
            case SANDSTONE_DOG_STATUE:
            case STONE_DOG_STATUE:
            case MOSSY_DOG_STATUE:
            case IRON_DOG_STATUE:
            case GOLDEN_DOG_STATUE:
            case DIAMOND_DOG_STATUE:
            case EMERALD_DOG_STATUE:
            case LAPIS_DOG_STATUE:
            case REDSTONE_DOG_STATUE:
            case OBSIDIAN_DOG_STATUE:
            case QUARTZ_DOG_STATUE:
            case ICE_DOG_STATUE:
                return dogStatue;
            case CAT_STATUE:
                return catStatue;
            case HORSE_STATUE:
                return horseStatue;
            case WOODEN_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case GOLDEN_SWORD:
            case DIAMOND_SWORD:
                return swordGrave;
            case STONE_VERTICAL_PLATE:
            case MOSSY_VERTICAL_PLATE:
            case WOODEN_VERTICAL_PLATE:
            case SANDSTONE_VERTICAL_PLATE:
            case IRON_VERTICAL_PLATE:
            case GOLDEN_VERTICAL_PLATE:
            case DIAMOND_VERTICAL_PLATE:
            case EMERALD_VERTICAL_PLATE:
            case LAPIS_VERTICAL_PLATE:
            case REDSTONE_VERTICAL_PLATE:
            case OBSIDIAN_VERTICAL_PLATE:
            case QUARTZ_VERTICAL_PLATE:
            case ICE_VERTICAL_PLATE:
            default:
                return verticalPlate;
        }
    }

    private void getGraveTexture(EnumGraves graveType) {
        switch (graveType) {
            // VERTICAL PLATES
            case WOODEN_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_WOODEN_VERTICAL_PLATE);
                break;
            case STONE_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_STONE_VERTICAL_PLATE);
                break;
            case MOSSY_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_MOSSY_VERTICAL_PLATE);
                break;
            case SANDSTONE_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_SANDSTONE_VERTICAL_PLATE);
                break;
            case IRON_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_IRON_VERTICAL_PLATE);
                break;
            case GOLDEN_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_GOLDEN_VERTICAL_PLATE);
                break;
            case DIAMOND_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_DIAMOND_VERTICAL_PLATE);
                break;
            case EMERALD_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_EMERALD_VERTICAL_PLATE);
                break;
            case LAPIS_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_LAPIS_VERTICAL_PLATE);
                break;
            case REDSTONE_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_REDSTONE_VERTICAL_PLATE);
                break;
            case OBSIDIAN_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_OBSIDIAN_VERTICAL_PLATE);
                break;
            case QUARTZ_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_QUARTZ_VERTICAL_PLATE);
                break;
            case ICE_VERTICAL_PLATE:
                bindTextureByName(Resources.GRAVE_ICE_VERTICAL_PLATE);
                break;
            // CROSSES
            case WOODEN_CROSS:
                bindTextureByName(Resources.GRAVE_WOODEN_CROSS);
                break;
            case SANDSTONE_CROSS:
                bindTextureByName(Resources.GRAVE_SANDSTONE_CROSS);
                break;
            case STONE_CROSS:
                bindTextureByName(Resources.GRAVE_STONE_CROSS);
                break;
            case MOSSY_CROSS:
                bindTextureByName(Resources.GRAVE_MOSSY_CROSS);
                break;
            case IRON_CROSS:
                bindTextureByName(Resources.GRAVE_IRON_CROSS);
                break;
            case GOLDEN_CROSS:
                bindTextureByName(Resources.GRAVE_GOLDEN_CROSS);
                break;
            case DIAMOND_CROSS:
                bindTextureByName(Resources.GRAVE_DIAMOND_CROSS);
                break;
            case EMERALD_CROSS:
                bindTextureByName(Resources.GRAVE_EMERALD_CROSS);
                break;
            case LAPIS_CROSS:
                bindTextureByName(Resources.GRAVE_LAPIS_CROSS);
                break;
            case REDSTONE_CROSS:
                bindTextureByName(Resources.GRAVE_REDSTONE_CROSS);
                break;
            case OBSIDIAN_CROSS:
                bindTextureByName(Resources.GRAVE_OBSIDIAN_CROSS);
                break;
            case QUARTZ_CROSS:
                bindTextureByName(Resources.GRAVE_QUARTZ_CROSS);
                break;
            case ICE_CROSS:
                bindTextureByName(Resources.GRAVE_ICE_CROSS);
                break;
            //
            case HORISONTAL_PLATE:
                bindTextureByName(Resources.GRAVE_HORISONTAL_PLATE);
                break;
            // DOGS GRAVES
            case WOODEN_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_WOODEN_DOG_STATUE);
                break;
            case SANDSTONE_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_SANDSTONE_DOG_STATUE);
                break;
            case STONE_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_STONE_DOG_STATUE);
                break;
            case MOSSY_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_MOSSY_DOG_STATUE);
                break;
            case IRON_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_IRON_DOG_STATUE);
                break;
            case GOLDEN_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_GOLDEN_DOG_STATUE);
                break;
            case DIAMOND_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_DIAMOND_DOG_STATUE);
                break;
            case EMERALD_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_EMERALD_DOG_STATUE);
                break;
            case LAPIS_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_LAPIS_DOG_STATUE);
                break;
            case REDSTONE_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_REDSTONE_DOG_STATUE);
                break;
            case OBSIDIAN_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_OBSIDIAN_DOG_STATUE);
                break;
            case QUARTZ_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_QUARTZ_DOG_STATUE);
                break;
            case ICE_DOG_STATUE:
                bindTextureByName(Resources.GRAVE_ICE_DOG_STATUE);
                break;
            // CATS GRAVES
            case CAT_STATUE:
                bindTextureByName(Resources.CAT_STATUE_GRAVE);
                break;
            case HORSE_STATUE:
                bindTextureByName(Resources.HORSE_STATUE_GRAVE);
                break;
            case WOODEN_SWORD:
                bindTextureByName(Resources.WOODEN_SWORD_GRAVE);
                break;
            case STONE_SWORD:
                bindTextureByName(Resources.STONE_SWORD_GRAVE);
                break;
            case IRON_SWORD:
                bindTextureByName(Resources.IRON_SWORD_GRAVE);
                break;
            case GOLDEN_SWORD:
                bindTextureByName(Resources.GOLDEN_SWORD_GRAVE);
                break;
            case DIAMOND_SWORD:
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
