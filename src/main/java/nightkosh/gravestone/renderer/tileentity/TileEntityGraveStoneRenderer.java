package nightkosh.gravestone.renderer.tileentity;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import nightkosh.gravestone.api.grave.EnumGraveType;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.config.GSConfigs;
import nightkosh.gravestone.core.GSResources;
import nightkosh.gravestone.models.block.ModelGraveStone;
import nightkosh.gravestone.models.block.graves.*;
import nightkosh.gravestone.tileentity.GraveStoneBlockEntity;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

//TODO
//@SideOnly(Side.CLIENT)
public class TileEntityGraveStoneRenderer extends TileEntityRenderer {

    protected static final Map<EnumGraves, ResourceLocation> mossyTexturesMap = Maps.newHashMap();
    //TODO
//    public static ModelGraveStone verticalPlate = new ModelVerticalPlateGraveStone();
//    public static ModelGraveStone cross = new ModelCrossGraveStone();
//    public static ModelGraveStone obelisk = new ModelObeliskGravestone();
//    public static ModelGraveStone celticCross = new ModelCelticCrossGravestone();
//    public static ModelGraveStone horizontalPlate = new ModelHorizontalPlateGraveStone();
//    public static ModelGraveStone villagerStatue = new ModelVillagerStatueGravestone();
//    public static ModelGraveStone dogStatue = new ModelDogStatueGraveStone();
//    public static ModelGraveStone catStatue = new ModelCatStatueGraveStone();
//    public static ModelGraveStone horseStatue = new ModelHorseGraveStone();
//    public static ModelGraveStone creeperStatue = new ModelCreeperStatueGravestone();
    public static ModelGraveStone skeletonCorpse = new ModelSkeletonCorpseGravestone(false);
    public static ModelGraveStone witheredSkeletonCorpse = new ModelSkeletonCorpseGravestone(true);

    //TODO
//    public static ModelGraveStone swordModel = new ModelSwordGraveStone();

    public static TileEntityGraveStoneRenderer instance;

    //TODO
//    protected static final GraveStoneBlockEntity GRAVE_TE = new GraveStoneBlockEntity();
    protected static final ItemStack SWORD = new ItemStack(Items.IRON_SWORD);

    public static final Map<Item, ItemEntity> flowersMap = new HashMap<>();
    public static final Map<Item, ItemEntity> swordsMap = new HashMap<>();

    public static final Map<Item, ResourceLocation> swordsTextureMap = new HashMap<>();

    static {
        swordsTextureMap.put(Items.WOODEN_SWORD, GSResources.WOODEN_SWORD);
        swordsTextureMap.put(Items.STONE_SWORD, GSResources.STONE_SWORD);
        swordsTextureMap.put(Items.IRON_SWORD, GSResources.IRON_SWORD);
        swordsTextureMap.put(Items.GOLDEN_SWORD, GSResources.GOLDEN_SWORD);
        swordsTextureMap.put(Items.DIAMOND_SWORD, GSResources.DIAMOND_SWORD);

        //TODO
//        GRAVE_TE.setGraveType(EnumGraves.STONE_VERTICAL_PLATE.ordinal());
    }

    public TileEntityGraveStoneRenderer() {
        instance = this;
    }

    //TODO
//
//    @Override
//    public void render(BlockEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
//        var tileEntity = (GraveStoneBlockEntity) blockEntity;
//        boolean isSwordGrave;
//        boolean isEnchanted;
//        ItemStack sword;
//
//        if (tileEntity == null) {
//            tileEntity = GRAVE_TE;
//            isSwordGrave = isSwordGrave();
//            isEnchanted = false;
//            sword = SWORD;
//        } else {
//            isSwordGrave = tileEntity.isSwordGrave();
//            sword = tileEntity.getSword();
//            isEnchanted = tileEntity.isEnchanted();
//        }
//        EnumGraves graveType = tileEntity.getGraveType();
//
//        int meta = 0;
//        if (tileEntity.getLevel() != null) {
//            meta = tileEntity.getBlockMetadata();
//            if (meta > 5) {
//                meta = 0;
//            }
//        }
//        var facing = EnumFacing.values()[meta];
//
//        renderGrave(x, y, z, tileEntity.getLevel(), graveType, isEnchanted, tileEntity.isMossy(),
//                tileEntity.hasFlower(), tileEntity.getFlower(), isSwordGrave, sword, facing);
//    }
//
//    public void renderGrave(double x, double y, double z, Level level, EnumGraves graveType,
//                            boolean isEnchanted, boolean isMossy, boolean hasFlower, ItemStack flower,
//                            boolean isSwordGrave, ItemStack sword, EnumFacing facing) {
//        GL11.glPushMatrix();
//
//        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
//        GL11.glScalef(1, -1, -1);
//
//        switch (facing) {
//            case SOUTH:
//                GL11.glRotatef(0, 0, 1, 0);
//                break;
//            case WEST:
//                GL11.glRotatef(90, 0, 1, 0);
//                break;
//            case NORTH:
//                GL11.glRotatef(180, 0, 1, 0);
//                break;
//            case EAST:
//                GL11.glRotatef(270, 0, 1, 0);
//                break;
//        }
//
//        renderGrave(level, graveType, isEnchanted, isMossy, hasFlower, flower, isSwordGrave, sword);
//
//        GL11.glPopMatrix();
//    }

    public void renderGraveAsItem(EnumGraves graveType, boolean isEnchanted, boolean isMossy, boolean isSwordGrave, ItemStack sword) {
        GL11.glPushMatrix();

        if (isSwordGrave) {
            GL11.glTranslatef(0.5F, 2, 0.5F);
            GL11.glScalef(1.5F, -1.5F, -1.5F);
        } else {
            GL11.glTranslatef(0.5F, 1.5F, 0.5F);
            GL11.glScalef(1, -1, -1);
            GL11.glRotatef(35, 0, 1, 0);
        }

        this.renderGrave(null, graveType, isEnchanted, isMossy, false, ItemStack.EMPTY, isSwordGrave, sword);

        GL11.glPopMatrix();
    }

    public void renderGrave(Level level, EnumGraves graveType, boolean isEnchanted, boolean isMossy, boolean hasFlower, ItemStack flower, boolean isSwordGrave, ItemStack sword) {
        if (isSwordGrave) {
            ResourceLocation swordTexture = swordsTextureMap.get(sword.getItem());
            if (GSConfigs.VANILLA_RENDERER_FOR_SWORDS_GRAVES.get()) {
                if (level == null) {
                    GL11.glScalef(0.5F, -0.5F, -0.5F);
                    GL11.glTranslatef(-0.37F, -1.7F, 0);
                } else {
                    GL11.glScalef(1, -1, -1);
                    GL11.glTranslatef(-0.27F, -0.83F, 0);
                }
                renderSword(level, sword);
            } else {
                ModelGraveStone model;
                //TODO
//                if (swordTexture != null) {
//                    model = getModel(graveType.getGraveType());
//                    bindTextureByName(swordTexture);
//                } else {
//                    model = getModel(EnumGraveType.VERTICAL_PLATE);
//                    bindTextureByName(graveType.getTexture());
//                }
//                if (isEnchanted) {
//                    model.renderEnchanted();
//                } else {
//                    model.renderAll();
//                }
            }
        } else {
            //TODO
//            ModelGraveStone model = getModel(graveType.getGraveType());
//
//            //TODO
////            bindTextureByName(getTexture(graveType, graveType.getTexture(), isMossy));
//            if (graveType.getGraveType() == EnumGraveType.CREEPER_STATUE) {
//                model.customRender(isEnchanted);
//            } else {
//                if (isEnchanted) {
//                    model.renderEnchanted();
//                } else {
//                    model.renderAll();
//                }
//            }

            if (hasFlower) {
                renderFlower(level, flower);
            }
        }
    }

    protected ResourceLocation getTexture(EnumGraves graveType, ResourceLocation texture, boolean isMossy) {
        if (isMossy) {
            ResourceLocation mixedMossyTexture = mossyTexturesMap.get(graveType);
            if (mixedMossyTexture == null) {
                ResourceLocation mossyTexture = getMossyTexture(graveType.getGraveType());

                //TODO
//                mixedMossyTexture = new ResourceLocation(texture.getResourceDomain() + ":mossy_" + texture.getResourcePath());
//                Minecraft.getMinecraft().getTextureManager().loadTexture(mixedMossyTexture,
//                        new LayeredTexture(texture.getResourceDomain() + ":" + texture.getResourcePath(),
//                                mossyTexture.getResourceDomain() + ":" + mossyTexture.getResourcePath()));
                mossyTexturesMap.put(graveType, mixedMossyTexture);
                return mixedMossyTexture;
            } else {
                return mixedMossyTexture;
            }
        } else {
            return texture;
        }
    }

    protected ResourceLocation getMossyTexture(EnumGraveType graveType) {
        switch (graveType) {
            case VERTICAL_PLATE:
            default:
                return GSResources.GRAVE_MOSSY_VERTICAL_PLATE;
            case CROSS:
                return GSResources.GRAVE_MOSSY_CROSS;
            case OBELISK:
                return GSResources.MOSSY_OBELISK;
            case CELTIC_CROSS:
                return GSResources.MOSSY_CELTIC_CROSS;
            case HORIZONTAL_PLATE:
                return GSResources.GRAVE_MOSSY_HORISONTAL_PLATE;
            case VILLAGER_STATUE:
                return GSResources.MOSSY_VILLAGER_STATUE;
            case DOG_STATUE:
                return GSResources.MOSSY_DOG_STATUE;
            case CAT_STATUE:
                return GSResources.MOSSY_CAT_STATUE;
            case HORSE_STATUE:
                return GSResources.GRAVE_MOSSY_HORSE_STATUE;
            case CREEPER_STATUE:
                return GSResources.MOSSY_CREEPER_STATUE;
        }
    }

    //TODO
//    protected ModelGraveStone getModel(EnumGraveType graveType) {
//        switch (graveType) {
//            case VERTICAL_PLATE:
//            default:
//                return verticalPlate;
//            case CROSS:
//                return cross;
//            case OBELISK:
//                return obelisk;
//            case CELTIC_CROSS:
//                return celticCross;
//            case HORIZONTAL_PLATE:
//                return horizontalPlate;
//            case VILLAGER_STATUE:
//                return villagerStatue;
//            case DOG_STATUE:
//                return dogStatue;
//            case CAT_STATUE:
//                return catStatue;
//            case HORSE_STATUE:
//                return horseStatue;
//            case CREEPER_STATUE:
//                return creeperStatue;
//            case STARVED_CORPSE:
//                return skeletonCorpse;
//            case WITHERED_CORPSE:
//                return witheredSkeletonCorpse;
//            case SWORD:
//                return swordModel;
//        }
//    }

    protected void renderSword(Level level, ItemStack sword) {
        var entityItem = swordsMap.get(sword.getItem());
        if (entityItem == null) {
            entityItem = new ItemEntity(level, 0, 0, 0, sword);
            swordsMap.put(sword.getItem(), entityItem);
        }

        //TODO
//        entityItem.hoverStart = 0;
        GL11.glRotatef(225, 0, 0, 1);

        renderItem(sword, entityItem);
    }

    protected void renderFlower(Level level, ItemStack flower) {
        if (GSConfigs.RENDER_GRAVES_FLOWERS.get()) {
            var entityItem = flowersMap.get(flower.getItem());
            if (entityItem == null) {
                entityItem = new ItemEntity(level, 0, 0, 0, flower);
                flowersMap.put(flower.getItem(), entityItem);
            }

            //TODO
//            entityItem.hoverStart = 0;
            GL11.glTranslatef(0, 1.4F, -0.1F);
            GL11.glScalef(0.6F, -0.6F, -0.6F);
            GL11.glRotatef(45, 0, 1, 0);

            renderItem(flower, entityItem);

            GL11.glRotatef(-90, 0, 1, 0);

            renderItem(flower, entityItem);
        }
    }

    protected void renderItem(ItemStack itemstack, ItemEntity entityItem) {

        //TODO
//        Render<ItemEntity> render = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(entityItem);
//        if (render != null && render instanceof RenderEntityItem) {
//            GlStateManager.pushMatrix();
//
//            RenderEntityItem renderItem = (RenderEntityItem) render;
//            renderItem.bindEntityTexture(entityItem);
//
//            GlStateManager.enableRescaleNormal();
//            GlStateManager.alphaFunc(516, 0.1F);
//            GlStateManager.enableBlend();
//            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//            IBakedModel ibakedmodel = renderItem.itemRenderer.getItemModelMesher().getItemModel(itemstack);
//
//            GlStateManager.translate(0, 0.35F, 0);
//
//            if (ibakedmodel.isGui3d()) {
//                GlStateManager.scale(0.5F, 0.5F, 0.5F);
//            }
//            renderItem.itemRenderer.renderItem(itemstack, ibakedmodel);
//
//            GlStateManager.disableRescaleNormal();
//            GlStateManager.disableBlend();
//            GlStateManager.popMatrix();
//        }
    }

    protected boolean isSwordGrave() {
        return false;
    }
}
