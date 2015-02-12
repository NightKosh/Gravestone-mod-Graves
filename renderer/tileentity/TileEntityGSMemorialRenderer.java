package gravestone.renderer.tileentity;

import com.google.common.collect.Maps;
import gravestone.block.enums.EnumGraveMaterial;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.Resources;
import gravestone.models.block.ModelMemorial;
import gravestone.models.block.memorials.*;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.LayeredTexture;
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
public class TileEntityGSMemorialRenderer extends TileEntityGSRenderer {

    private static final Map<EnumMemorials, ResourceLocation> mossyTexturesMap = Maps.newHashMap();
    private static final Map<EnumGraveMaterial, ResourceLocation> mossyPedestalTexturesMap = Maps.newHashMap();
    private static final Map<EnumGraveMaterial, ResourceLocation> mossyArmorTexturesMap = Maps.newHashMap();
    public static ModelMemorial cross = new ModelMemorialCross();
    public static ModelMemorial obelisk = new ModelMemorialObelisk();
    public static ModelMemorial steveStatue = new ModelSteveStatueMemorial();
    public static ModelMemorial villagerStatue = new ModelVillagerMemorial();
    public static ModelMemorial angelStatue = new ModelAngelStatueMemorial();
    public static ModelMemorial dogStatue = new ModelDogStatueMemorial();
    public static ModelMemorial catStatue = new ModelCatStatueMemorial();
    public static ModelMemorial creeperStatue = new ModelCreeperStatueMemorial();
    public static ModelMemorial gibbet = new ModelGibbet();
    public static ModelMemorial stocks = new ModelStocks();
    public static ModelMemorial burningStake = new ModelBurningStake();

    //private static IModelCustom celticCross = AdvancedModelLoader.loadModel("/assets/gravestone/obj_models/CelticCross.obj");

    public static TileEntityGSMemorialRenderer instance;

    public TileEntityGSMemorialRenderer() {
        instance = this;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f, int par9) {
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) te;
        EnumMemorials memorialType = tileEntity.getMemorialType();
        int meta = 0;

        if (tileEntity.getWorld() != null) {
            meta = tileEntity.getBlockMetadata();
        }
        EnumFacing facing = EnumFacing.values()[meta];

        GL11.glPushMatrix();

        if (tileEntity.getWorld() != null) {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1, -1, -1);
        } else {
            switch (memorialType.getMemorialType()) {
                case CROSS:
                case OBELISK:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                    GL11.glScalef(0.4F, -0.4F, -0.4F);
                    break;
                default:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.8F, (float) z + 0.5F);
                    GL11.glScalef(0.7F, -0.7F, -0.7F);
                    break;
            }
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
//
//        if (memorialType == 9) {
//            celticCross.renderAll();
//        } else
        boolean isMossy = tileEntity.isMossy();
        ModelMemorial model = getModel(memorialType.getMemorialType());
        bindTextureByName(getTexture(memorialType, memorialType.getTexture(), isMossy));
        model.setPedestalTexture(getPedestalTexture(memorialType, isMossy));
        switch (memorialType.getMemorialType()) {
            case CREEPER_STATUE:
                model.customRender(tileEntity.isEnchanted());
                break;
            case STEVE_STATUE:
                model.customRender(getArmorTexture(memorialType, isMossy), tileEntity.isEnchanted());
                break;
            case GIBBET:
            case STOCKS:
            case BURNING_STAKE:
                model.customRender(memorialType, tileEntity.getHangedMob(), tileEntity.getHangedVillagerProfession());
                break;
            default:
                if (tileEntity.isEnchanted()) {
                    model.renderEnchanted();
                } else {
                    model.renderAll();
                }
        }

        GL11.glPopMatrix();
    }

    private ResourceLocation getTexture(EnumMemorials memorialType, ResourceLocation texture, boolean isMossy) {
        if (isMossy) {
            ResourceLocation mixedMossyTexture = mossyTexturesMap.get(memorialType);
            if (mixedMossyTexture == null) {
                ResourceLocation mossyTexture = getMossyTexture(memorialType.getMemorialType());
                mixedMossyTexture = new ResourceLocation(texture.getResourceDomain() + ":mossy_" + texture.getResourcePath());
                Minecraft.getMinecraft().getTextureManager().loadTexture(mixedMossyTexture,
                        new LayeredTexture(texture.getResourceDomain() + ":" + texture.getResourcePath(),
                                mossyTexture.getResourceDomain() + ":" + mossyTexture.getResourcePath()));
                mossyTexturesMap.put(memorialType, mixedMossyTexture);
                return mixedMossyTexture;
            } else {
                return mixedMossyTexture;
            }
        } else {
            return texture;
        }
    }

    private ResourceLocation getPedestalTexture(EnumMemorials memorialType, boolean isMossy) {
        ResourceLocation texture = memorialType.getPedestalTexture();
        if (isMossy && texture != null) {
            ResourceLocation mixedMossyTexture = mossyPedestalTexturesMap.get(memorialType.getMaterial());
            if (mixedMossyTexture == null) {
                ResourceLocation mossyTexture = getMossyPedestalTexture(memorialType.getMemorialType());
                mixedMossyTexture = new ResourceLocation(texture.getResourceDomain() + ":mossy_" + texture.getResourcePath());
                Minecraft.getMinecraft().getTextureManager().loadTexture(mixedMossyTexture,
                        new LayeredTexture(texture.getResourceDomain() + ":" + texture.getResourcePath(),
                                mossyTexture.getResourceDomain() + ":" + mossyTexture.getResourcePath()));
                mossyPedestalTexturesMap.put(memorialType.getMaterial(), mixedMossyTexture);
                return mixedMossyTexture;
            } else {
                return mixedMossyTexture;
            }
        } else {
            return texture;
        }
    }

    public static ResourceLocation getArmorTexture(EnumMemorials memorialType, boolean isMossy) {
        ResourceLocation texture = getArmorTexture(memorialType);
        if (isMossy) {
            ResourceLocation mixedMossyTexture = mossyArmorTexturesMap.get(memorialType.getMaterial());
            if (mixedMossyTexture == null) {
                ResourceLocation mossyTexture = Resources.MOSSY_ARMOR;
                mixedMossyTexture = new ResourceLocation(texture.getResourceDomain() + ":mossy_" + texture.getResourcePath());
                Minecraft.getMinecraft().getTextureManager().loadTexture(mixedMossyTexture,
                        new LayeredTexture(texture.getResourceDomain() + ":" + texture.getResourcePath(),
                                mossyTexture.getResourceDomain() + ":" + mossyTexture.getResourcePath()));
                mossyArmorTexturesMap.put(memorialType.getMaterial(), mixedMossyTexture);
                return mixedMossyTexture;
            } else {
                return mixedMossyTexture;
            }
        } else {
            return texture;
        }
    }

    private static ResourceLocation getMossyTexture(EnumMemorials.EnumMemorialType memorialType) {
        switch (memorialType) {
            case CROSS:
            default:
                return Resources.MEMORIAL_MOSSY_CROSS;
            case OBELISK:
                return Resources.MEMORIAL_MOSSY_OBELISK;
            case STEVE_STATUE:
                return Resources.MEMORIAL_MOSSY_STEVE_STATUE;
            case VILLAGER_STATUE:
                return Resources.MEMORIAL_MOSSY_VILLAGER_STATUE;
            case ANGEL_STATUE:
                return Resources.MEMORIAL_MOSSY_ANGEL_STATUE;
            case DOG_STATUE:
                return Resources.MEMORIAL_MOSSY_DOG_STATUE;
            case CAT_STATUE:
                return Resources.MEMORIAL_MOSSY_CAT_STATUE;
            case CREEPER_STATUE:
                return Resources.MEMORIAL_MOSSY_CREEPER_STATUE;
        }
    }

    private static ResourceLocation getMossyPedestalTexture(EnumMemorials.EnumMemorialType memorialType) {
        switch (memorialType) {
            case CROSS:
            default:
                return Resources.MEMORIAL_MOSSY_BIG_PEDESTAL;
        }
    }

    private static ResourceLocation getArmorTexture(EnumMemorials memorialType) {
        switch (memorialType) {
            case WOODEN_STEVE_STATUE:
            default:
                return Resources.WOODEN_ARMOR;
            case SANDSTONE_STEVE_STATUE:
                return Resources.SANDSTONE_ARMOR;
            case STONE_STEVE_STATUE:
                return Resources.STONE_ARMOR;
            case IRON_STEVE_STATUE:
                return Resources.IRON_ARMOR;
            case GOLDEN_STEVE_STATUE:
                return Resources.GOLDEN_ARMOR;
            case DIAMOND_STEVE_STATUE:
                return Resources.DIAMOND_ARMOR;
            case EMERALD_STEVE_STATUE:
                return Resources.EMERALD_ARMOR;
            case LAPIS_STEVE_STATUE:
                return Resources.LAPIS_ARMOR;
            case REDSTONE_STEVE_STATUE:
                return Resources.REDSTONE_ARMOR;
            case OBSIDIAN_STEVE_STATUE:
                return Resources.OBSIDIAN_ARMOR;
            case QUARTZ_STEVE_STATUE:
                return Resources.QUARTZ_ARMOR;
            case ICE_STEVE_STATUE:
                return Resources.ICE_ARMOR;
        }
    }

    private static ModelMemorial getModel(EnumMemorials.EnumMemorialType memorialType) {
        switch (memorialType) {
            case CROSS:
            default:
                return cross;
            case OBELISK:
                return obelisk;
            case STEVE_STATUE:
                return steveStatue;
            case VILLAGER_STATUE:
                return villagerStatue;
            case ANGEL_STATUE:
                return angelStatue;
            case DOG_STATUE:
                return dogStatue;
            case CAT_STATUE:
                return catStatue;
            case CREEPER_STATUE:
                return creeperStatue;
            case GIBBET:
                return gibbet;
            case STOCKS:
                return stocks;
            case BURNING_STAKE:
                return burningStake;
        }
    }
}
