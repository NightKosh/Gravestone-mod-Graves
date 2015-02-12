package gravestone.renderer.tileentity;

import com.google.common.collect.Maps;
import gravestone.block.enums.EnumGraves;
import gravestone.core.Resources;
import gravestone.models.block.ModelGraveStone;
import gravestone.models.block.graves.*;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
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

    private static final Map<EnumGraves, ResourceLocation> mossyTexturesMap = Maps.newHashMap();
    public static ModelGraveStone verticalPlate = new ModelVerticalPlateGraveStone();
    public static ModelGraveStone cross = new ModelCrossGraveStone();
    public static ModelGraveStone horisontalPlate = new ModelHorisontalPlateGraveStone();
    public static ModelGraveStone dogStatue = new ModelDogStatueGraveStone();
    public static ModelGraveStone catStatue = new ModelCatStatueGraveStone();
    public static ModelGraveStone horseStatue = new ModelHorseGraveStone();
    public static TileEntityGSGraveStoneRenderer instance;

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
            ModelGraveStone model = getModel(graveType.getGraveType());

            bindTextureByName(getTexture(graveType, graveType.getTexture(), tileEntity.isMossy()));
            if (tileEntity.isEnchanted()) {
                model.renderEnchanted();
            } else {
                model.renderAll();
            }

            if (tileEntity.hasFlower()) {
                renderFlower(tileEntity);
            }
        }

        GL11.glPopMatrix();
    }

    private ResourceLocation getTexture(EnumGraves graveType, ResourceLocation texture, boolean isMossy) {
        if (isMossy) {
            ResourceLocation mixedMossyTexture = mossyTexturesMap.get(graveType);
            if (mixedMossyTexture == null) {
                ResourceLocation mossyTexture = getMossyTexture(graveType.getGraveType());
                mixedMossyTexture = new ResourceLocation(texture.getResourceDomain() + ":mossy_" + texture.getResourcePath());
                Minecraft.getMinecraft().getTextureManager().loadTexture(mixedMossyTexture,
                        new LayeredTexture(texture.getResourceDomain() + ":" + texture.getResourcePath(),
                                mossyTexture.getResourceDomain() + ":" + mossyTexture.getResourcePath()));
                mossyTexturesMap.put(graveType, mixedMossyTexture);
                return mixedMossyTexture;
            } else {
                return mixedMossyTexture;
            }
        } else {
            return texture;
        }
    }

    private ResourceLocation getMossyTexture(EnumGraves.EnumGraveType graveType) {
        switch (graveType) {
            case VERTICAL_PLATE:
            default:
                return Resources.GRAVE_MOSSY_VERTICAL_PLATE;
            case CROSS:
                return Resources.GRAVE_MOSSY_CROSS;
            case HORISONTAL_PLATE:
                return Resources.GRAVE_MOSSY_HORISONTAL_PLATE;
            case DOG_STATUE:
                return Resources.GRAVE_MOSSY_DOG_STATUE;
            case CAT_STATUE:
                return Resources.GRAVE_MOSSY_CAT_STATUE;
            case HORSE_STATUE:
                return Resources.GRAVE_MOSSY_HORSE_STATUE;
        }
    }

    private ModelGraveStone getModel(EnumGraves.EnumGraveType graveType) {
        switch (graveType) {
            case VERTICAL_PLATE:
            default:
                return verticalPlate;
            case CROSS:
                return cross;
            case HORISONTAL_PLATE:
                return horisontalPlate;
            case DOG_STATUE:
                return dogStatue;
            case CAT_STATUE:
                return catStatue;
            case HORSE_STATUE:
                return horseStatue;
        }
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
        GL11.glTranslatef(-0.37F, 0.83F, 0);
        GL11.glScalef(1.5F, -1.5F, -1.5F);
        GL11.glRotatef(225, 0, 0, 1);

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
