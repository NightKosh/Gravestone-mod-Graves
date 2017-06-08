package gravestone.renderer.tileentity;

import com.google.common.collect.ImmutableMap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumGraves;
import gravestone.config.GraveStoneConfig;
import gravestone.core.Resources;
import gravestone.models.block.ModelGraveStone;
import gravestone.models.block.graves.*;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final Map<EnumGraves, ModelGraveStone> MODELS_MAP = ImmutableMap.<EnumGraves, ModelGraveStone>builder()
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
            .put(EnumGraves.SWORD, swordGrave).build();// TODO заменить на null

    public TileEntityGSGraveStoneRenderer() {
        instance = this;
    }

    public static final Map<Item, ResourceLocation> swordsTextureMap = ImmutableMap.<Item, ResourceLocation>builder()
            .put(Items.wooden_sword, Resources.GRAVE_WOODEN_SWORD)
            .put(Items.stone_sword, Resources.GRAVE_STONE_SWORD)
            .put(Items.iron_sword, Resources.GRAVE_IRON_SWORD)
            .put(Items.golden_sword, Resources.GRAVE_GOLDEN_SWORD)
            .put(Items.diamond_sword, Resources.GRAVE_DIAMOND_SWORD).build();
    public static final Map<ItemStack, Object[]> EntityItemMap = new ConcurrentHashMap(500);
    public static int renderFlower_calls = 0;
    public static Random random = new Random();
    public static RenderBlocks field_147909_c = new RenderBlocks();

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

        //int gGD = getGraveDirection(meta))
        if (meta == 0) GL11.glRotatef(0, 0, 1, 0);
        else if (meta == 3) GL11.glRotatef(90, 0, 1, 0);
        else if (meta == 2) GL11.glRotatef(270, 0, 1, 0);
        else GL11.glRotatef(180, 0, 1, 0);

        if (tileEntity.isSwordGrave()) {
            if (GraveStoneConfig.vanillaRendererForSwordsGraves) {
                renderSword(tileEntity);
            } else {
                bindTextureByName(swordsTextureMap.get(tileEntity.getSword().getItem()));
                if (tileEntity.isEnchanted()) {
                    MODELS_MAP.get(graveType).renderEnchanted();
                } else {
                    MODELS_MAP.get(graveType).renderAll();
                }
            }
        } else {
            if (tileEntity.isEnchanted()) {
                MODELS_MAP.get(graveType).renderEnchanted();
            } else {
                MODELS_MAP.get(graveType).renderAll();
            }
            if (tileEntity.hasFlower() && GraveStoneConfig.renderGravesFlowers) {
                renderFlower(tileEntity);
                renderFlower_calls++;
                if (renderFlower_calls == 600000) {
                    renderFlower_calls = 0;
                    EntityItemMap.clear();
                    //EntityItemMapRenderValues.clear();
                }
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
        EntityItem entityitem = new EntityItem(te.getWorldObj(), 0, 0, 0, sword);
        entityitem.hoverStart = 0;
        GL11.glTranslatef(0.24F, 0.83F, 0);
        GL11.glScalef(1.5F, -1.5F, -1.5F);
        GL11.glRotatef(135, 0, 0, 1);

        renderItem(entityitem, 0, 0, 0, 0, 0);
    }

    private void renderFlower(TileEntityGSGraveStone te) {
        Object[] objects = EntityItemMap.get(te.getFlower());
        if (objects == null) {
            EntityItem entityitem = new EntityItem(te.getWorldObj(), 0, 0, 0, te.getFlower());
            RenderItem render = (RenderItem) RenderManager.instance.getEntityClassRenderObject(entityitem.getClass());
            entityitem.hoverStart = 0;
            entityitem.lifespan = Integer.MAX_VALUE;
            Object[] objects0 = new Object[]{entityitem, new boolean[]{render.renderInFrame, render.renderWithColor}};
            EntityItemMap.put(te.getFlower(), objects0);
            objects = objects0;
        }
        //((EntityItem)objects[0]).hoverStart = 0;
        GL11.glTranslatef(0, 1.45F, -0.1F);
        GL11.glScalef(1, -1F, -1F);
        GL11.glRotatef(45, 0, 1, 0);

        //renderItem(entityitem, 0, 0, 0, 0, 0);
        //render.doRender(entityitem, 0, 0, 0, 0, 0);
        doRender(objects, 0, 0, 0, 0, 0);

        GL11.glRotatef(-90, 0, 1, 0);
        //renderItem(entityitem, 0, 0, 0, 0, 0);
        //render.doRender(entityitem, 0, 0, 0, 0, 0);
        doRender(objects, 0, 0, 0, 0, 0);
    }

    public void renderItem(Entity p_147939_1_, double p_147939_2_, double p_147939_4_, double p_147939_6_, float p_147939_8_, float p_147939_9_) {
        Render render = null;

        try {
            render = RenderManager.instance.getEntityClassRenderObject(p_147939_1_.getClass());
            if (render != null && !render.isStaticEntity()) {
                render.doRender(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
                //render.doRenderShadowAndFire(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);//if commited +10fps
            }
        } catch (Throwable e) {
            cpw.mods.fml.common.FMLLog.log(org.apache.logging.log4j.Level.WARN, e, "GraveStone stacktrace: %s", e);
        }
    }

    public void doRender(Object[] objects, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        EntityItem p_76986_1_ = (EntityItem) objects[0];
        boolean[] renderValues = (boolean[]) objects[1];
        ItemStack itemstack = p_76986_1_.getEntityItem();
        if (itemstack.getItem() != null) {
            TextureManager re = RenderManager.instance.renderEngine;
            int gISN = itemstack.getItemSpriteNumber();
            re.bindTexture(re.getResourceLocation(gISN));
            TextureUtil.func_152777_a(false, false, 1.0F);
            random.setSeed(187L);
            GL11.glPushMatrix();
            float f2 = 0F;
            float f3 = 6152086455.86365F;
            byte b0 = 1;
            GL11.glTranslatef((float) p_76986_2_, f2, (float) p_76986_6_);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            float f6;
            float f7;
            int k;
            if (ForgeHooksClient.renderEntityItem(p_76986_1_, itemstack, f2, f3, random, re, field_147909_c, b0)) {
                ;
            } else {
                float f5;

                if (itemstack.getItem().requiresMultipleRenderPasses()) {
                    if (renderValues[0]) {
                        GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                        GL11.glTranslatef(0.0F, -0.05F, 0.0F);
                    } else {
                        GL11.glScalef(0.5F, 0.5F, 0.5F);
                    }

                    for (int j = 0; j < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++j) {
                        random.setSeed(187L);
                        IIcon iicon1 = itemstack.getItem().getIcon(itemstack, j);

                        if (renderValues[1]) {
                            k = itemstack.getItem().getColorFromItemStack(itemstack, j);
                            f5 = (float) (k >> 16 & 255) / 255.0F;
                            f6 = (float) (k >> 8 & 255) / 255.0F;
                            f7 = (float) (k & 255) / 255.0F;
                            GL11.glColor4f(f5, f6, f7, 1.0F);
                            renderDroppedItem(iicon1, f5, f6, f7);
                        } else {
                            renderDroppedItem(iicon1, 1.0F, 1.0F, 1.0F);
                        }
                    }
                } else {
                    if (renderValues[0]) {
                        GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                        GL11.glTranslatef(0.0F, -0.05F, 0.0F);
                    } else {
                        GL11.glScalef(0.5F, 0.5F, 0.5F);
                    }

                    IIcon iicon = itemstack.getIconIndex();

                    if (renderValues[1]) {
                        int i = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                        float f4 = (float) (i >> 16 & 255) / 255.0F;
                        f5 = (float) (i >> 8 & 255) / 255.0F;
                        f6 = (float) (i & 255) / 255.0F;
                        renderDroppedItem(iicon, f4, f5, f6);
                    } else {
                        renderDroppedItem(iicon, 1.0F, 1.0F, 1.0F);
                    }
                }
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            re.bindTexture(re.getResourceLocation(gISN));
            TextureUtil.func_147945_b();
        }
    }

    public void renderDroppedItem(IIcon p_77020_2_, float p_77020_5_, float p_77020_6_, float p_77020_7_) {
        Tessellator tessellator = Tessellator.instance;

        float f14 = ((IIcon) p_77020_2_).getMinU();
        float f15 = ((IIcon) p_77020_2_).getMaxU();
        float f4 = ((IIcon) p_77020_2_).getMinV();
        float f5 = ((IIcon) p_77020_2_).getMaxV();

        GL11.glPushMatrix();
        GL11.glColor4f(p_77020_5_, p_77020_6_, p_77020_7_, 1.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D, -0.25D, 0.0D, (double) f14, (double) f5);
        tessellator.addVertexWithUV(0.5D, -0.25D, 0.0D, (double) f15, (double) f5);
        tessellator.addVertexWithUV(0.5D, 0.75D, 0.0D, (double) f15, (double) f4);
        tessellator.addVertexWithUV(-0.5D, 0.75D, 0.0D, (double) f14, (double) f4);
        tessellator.draw();
        GL11.glPopMatrix();
    }
}
