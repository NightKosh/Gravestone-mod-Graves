package gravestone.renderer.tileentity;

import com.google.common.collect.ImmutableMap;
import gravestone.block.enums.EnumGraves;
import gravestone.models.block.ModelGraveStone;
import gravestone.models.block.graves.*;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Map;

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

    private final Map<EnumGraves, ModelGraveStone> MODELS_MAP = ImmutableMap.<EnumGraves, ModelGraveStone>builder()
            .put(EnumGraves.STONE_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.STONE_CROSS, cross)
            .put(EnumGraves.STONE_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.STONE_DOG_STATUE, dogStatue)
            .put(EnumGraves.STONE_CAT_STATUE, catStatue)
            .put(EnumGraves.WOODEN_SWORD, swordGrave)
            .put(EnumGraves.STONE_SWORD, swordGrave)
            .put(EnumGraves.IRON_SWORD, swordGrave)
            .put(EnumGraves.GOLDEN_SWORD, swordGrave)
            .put(EnumGraves.DIAMOND_SWORD, swordGrave)
            .put(EnumGraves.STONE_HORSE_STATUE, horseStatue)
                    // VERTICAL PLATES
            .put(EnumGraves.WOODEN_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.SANDSTONE_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.IRON_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.GOLDEN_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.DIAMOND_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.EMERALD_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.LAPIS_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.REDSTONE_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.OBSIDIAN_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.QUARTZ_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.ICE_VERTICAL_PLATE, verticalPlate)
            .put(EnumGraves.MOSSY_VERTICAL_PLATE, verticalPlate)
                    // CROSSES
            .put(EnumGraves.WOODEN_CROSS, cross)
            .put(EnumGraves.SANDSTONE_CROSS, cross)
            .put(EnumGraves.IRON_CROSS, cross)
            .put(EnumGraves.GOLDEN_CROSS, cross)
            .put(EnumGraves.DIAMOND_CROSS, cross)
            .put(EnumGraves.EMERALD_CROSS, cross)
            .put(EnumGraves.LAPIS_CROSS, cross)
            .put(EnumGraves.REDSTONE_CROSS, cross)
            .put(EnumGraves.OBSIDIAN_CROSS, cross)
            .put(EnumGraves.QUARTZ_CROSS, cross)
            .put(EnumGraves.ICE_CROSS, cross)
            .put(EnumGraves.MOSSY_CROSS, cross)
                    // HORISONTAL PLATES
            .put(EnumGraves.WOODEN_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.SANDSTONE_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.IRON_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.GOLDEN_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.DIAMOND_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.EMERALD_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.LAPIS_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.REDSTONE_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.OBSIDIAN_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.QUARTZ_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.ICE_HORISONTAL_PLATE, horisontalPlate)
            .put(EnumGraves.MOSSY_HORISONTAL_PLATE, horisontalPlate)
                    // DOGS GRAVES
            .put(EnumGraves.WOODEN_DOG_STATUE, dogStatue)
            .put(EnumGraves.SANDSTONE_DOG_STATUE, dogStatue)
            .put(EnumGraves.IRON_DOG_STATUE, dogStatue)
            .put(EnumGraves.GOLDEN_DOG_STATUE, dogStatue)
            .put(EnumGraves.DIAMOND_DOG_STATUE, dogStatue)
            .put(EnumGraves.EMERALD_DOG_STATUE, dogStatue)
            .put(EnumGraves.LAPIS_DOG_STATUE, dogStatue)
            .put(EnumGraves.REDSTONE_DOG_STATUE, dogStatue)
            .put(EnumGraves.OBSIDIAN_DOG_STATUE, dogStatue)
            .put(EnumGraves.QUARTZ_DOG_STATUE, dogStatue)
            .put(EnumGraves.ICE_DOG_STATUE, dogStatue)
            .put(EnumGraves.MOSSY_DOG_STATUE, dogStatue)
                    // CATS GRAVES
            .put(EnumGraves.WOODEN_CAT_STATUE, catStatue)
            .put(EnumGraves.SANDSTONE_CAT_STATUE, catStatue)
            .put(EnumGraves.IRON_CAT_STATUE, catStatue)
            .put(EnumGraves.GOLDEN_CAT_STATUE, catStatue)
            .put(EnumGraves.DIAMOND_CAT_STATUE, catStatue)
            .put(EnumGraves.EMERALD_CAT_STATUE, catStatue)
            .put(EnumGraves.LAPIS_CAT_STATUE, catStatue)
            .put(EnumGraves.REDSTONE_CAT_STATUE, catStatue)
            .put(EnumGraves.OBSIDIAN_CAT_STATUE, catStatue)
            .put(EnumGraves.QUARTZ_CAT_STATUE, catStatue)
            .put(EnumGraves.ICE_CAT_STATUE, catStatue)
            .put(EnumGraves.MOSSY_CAT_STATUE, catStatue)
                    // HORSES GRAVES
            .put(EnumGraves.WOODEN_HORSE_STATUE, horseStatue)
            .put(EnumGraves.SANDSTONE_HORSE_STATUE, horseStatue)
            .put(EnumGraves.IRON_HORSE_STATUE, horseStatue)
            .put(EnumGraves.GOLDEN_HORSE_STATUE, horseStatue)
            .put(EnumGraves.DIAMOND_HORSE_STATUE, horseStatue)
            .put(EnumGraves.EMERALD_HORSE_STATUE, horseStatue)
            .put(EnumGraves.LAPIS_HORSE_STATUE, horseStatue)
            .put(EnumGraves.REDSTONE_HORSE_STATUE, horseStatue)
            .put(EnumGraves.OBSIDIAN_HORSE_STATUE, horseStatue)
            .put(EnumGraves.QUARTZ_HORSE_STATUE, horseStatue)
            .put(EnumGraves.ICE_HORSE_STATUE, horseStatue)
            .put(EnumGraves.MOSSY_HORSE_STATUE, horseStatue)
            .put(EnumGraves.SWORD, swordGrave).build();

    public TileEntityGSGraveStoneRenderer() {
        instance = this;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f, int par9) {
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) te;
        EnumGraves graveType = tileEntity.getGraveType();
        int meta = 0;
        if (tileEntity.getWorld() != null) {
            meta = tileEntity.getBlockMetadata();
        }
        EnumFacing facing = EnumFacing.values()[meta];

        if (graveType != EnumGraves.SWORD) {
            bindTextureByName(graveType.getTexture());
        }
        //texture
        GL11.glPushMatrix();

        if (tileEntity.getWorld() == null && tileEntity.isSwordGrave()) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 2, (float) z + 0.5F);
            GL11.glScalef(1.5F, -1.5F, -1.5F);
        } else {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1, -1, -1);
        }

        switch (facing) {
            case SOUTH:
                GL11.glRotatef(0, 0, 1, 0);
                break;
            case WEST:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case NORTH:
                GL11.glRotatef(180, 0, 1, 0);
                break;
            case EAST:
                GL11.glRotatef(270, 0, 1, 0);
                break;
        }

        if (tileEntity.isSwordGrave()) {
            renderSword(tileEntity);
        } else {
            if (tileEntity.isEnchanted()) {
                MODELS_MAP.get(graveType).renderEnchanted();
            } else {
                MODELS_MAP.get(graveType).renderAll();
            }
            if (tileEntity.hasFlower()) {
                renderFlower(tileEntity);
            }
        }

        GL11.glPopMatrix();
    }

    private void renderSword(TileEntityGSGraveStone te) {
        ItemStack sword = te.getSword();
        if (te.isEnchanted()) {
            if (!sword.isItemEnchanted()) {
                if (!sword.hasTagCompound()) {
                    sword.setTagCompound(new NBTTagCompound());
                }
                sword.getTagCompound().setTag("ench", new NBTTagList());
            }
        }
        EntityItem entityitem = new EntityItem(te.getWorld(), 0, 0, 0, sword);
        entityitem.hoverStart = 0;
        GL11.glTranslatef(0.24F, 0.83F, 0);
        GL11.glScalef(1.5F, -1.5F, -1.5F);
        GL11.glRotatef(135, 0, 0, 1);

        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityitem, 0, 0, 0, 0, 0);
    }

    private void renderFlower(TileEntityGSGraveStone te) {
        EntityItem entityitem = new EntityItem(te.getWorld(), 0, 0, 0, te.getFlower());
        entityitem.hoverStart = 0;
        GL11.glTranslatef(0, 1.45F, -0.1F);
        GL11.glScalef(1, -1, -1);
        GL11.glRotatef(45, 0, 1, 0);

        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityitem, 0, 0, 0, 0, 0);

        GL11.glRotatef(-90, 0, 1, 0);
        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityitem, 0, 0, 0, 0, 0);
    }
}
