package gravestone.models.block.memorials;

import net.minecraftforge.fml.common.registry.VillagerRegistry;
import gravestone.block.enums.EnumHangedMobs;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.Resources;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelGibbet extends ModelMemorial {

    private ModelRenderer horisontalPlank;
    private ModelRenderer verticalPlank;
    private ModelRenderer plank1;
    private ModelRenderer plank2;
    private ModelRenderer plank3;
    private ModelRenderer plank4;
    private ModelRenderer rope;
    private ModelRenderer rope2;
    private ModelRenderer knot;
    private ModelRenderer loop1;
    private ModelRenderer loop2;
    private ModelRenderer loop3;
    private ModelRenderer loop4;
    private ModelRenderer loop5;

    private static final ModelHangedBiped bipedModel = new ModelHangedBiped(false);
    private static final ModelHangedBiped zombieModel = new ModelHangedBiped(false, true);
    private static final ModelHangedSkeleton skeletonModel = new ModelHangedSkeleton(false);
    private static final ModelHangedSkeleton witherSkeletonModel = new ModelHangedSkeleton(false, true);
    private static final ModelHangedVillager villagerModel = new ModelHangedVillager(false);
    private static final ModelHangedZombieVillager zombieVillagerModel = new ModelHangedZombieVillager(false);
    private static final ModelHangedWitch witchModel = new ModelHangedWitch(false);

    public ModelGibbet() {
        textureWidth = 64;
        textureHeight = 128;

        horisontalPlank = new ModelRenderer(this, 0, 0);
        horisontalPlank.addBox(0, 0, 0, 4, 56, 4);
        horisontalPlank.setRotationPoint(-2, -32, 3);
        horisontalPlank.setTextureSize(this.textureWidth, this.textureHeight);

        verticalPlank = new ModelRenderer(this, 16, 0);
        verticalPlank.addBox(0, 0, 0, 4, 4, 20);
        verticalPlank.setRotationPoint(-2, -32, -17);
        verticalPlank.setTextureSize(this.textureWidth, this.textureHeight);

        plank1 = new ModelRenderer(this, 31, 24);
        plank1.addBox(0, 0, 0, 4, 15, 3);
        plank1.setRotationPoint(-2, 13.3F, 3.5F);
        plank1.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank1, 0, 0, 0.7853982F);

        plank2 = new ModelRenderer(this, 16, 24);
        plank2.addBox(0, 0, 0, 3, 15, 4);
        plank2.setRotationPoint(-1.5F, 13.3F, 3);
        plank2.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank2, -0.7853982F, 0F, 0F);

        plank3 = new ModelRenderer(this, 31, 42);
        plank3.addBox(0, 0, 0, 4, 15, 3);
        plank3.setRotationPoint(-1, 16, 3.5F);
        plank3.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank3, 0, 0, -0.7853982F);

        plank4 = new ModelRenderer(this, 16, 43);
        plank4.addBox(0, 0, 0, 3, 15, 4);
        plank4.setRotationPoint(-1.5F, -20.3F, 6);
        plank4.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank4, -2.356194F, 0, 0);

        rope = new ModelRenderer(this, 0, 62);
        rope.addBox(0, 0, 0, 5, 5, 3);
        rope.setRotationPoint(-2.5F, -32.5F, -16);
        rope.setTextureSize(this.textureWidth, this.textureHeight);

        rope2 = new ModelRenderer(this, 0, 71);
        rope2.addBox(0, 0, 0, 1, 16, 1);
        rope2.setRotationPoint(-0.5F, -28, -15);
        rope2.setTextureSize(this.textureWidth, this.textureHeight);

        knot = new ModelRenderer(this, 5, 71);
        knot.addBox(0, 0, 0, 2, 6, 2);
        knot.setRotationPoint(-1.4F, -18, -14.5F);
        knot.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(knot, 0, 0.7853982F, 0);

        loop1 = new ModelRenderer(this, 17, 65);
        loop1.addBox(0, 0, 0, 1, 4, 1);
        loop1.setRotationPoint(-0.8F, -12.3F, -15);
        loop1.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(loop1, 0, 0, 0.1745329F);

        loop2 = new ModelRenderer(this, 22, 65);
        loop2.addBox(0, 0, 0, 1, 4, 1);
        loop2.setRotationPoint(-0.2F, -12.1F, -15);
        loop2.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(loop2, 0, 0, -0.1745329F);

        loop3 = new ModelRenderer(this, 27, 65);
        loop3.addBox(0, 0, 0, 2, 1, 1);
        loop3.setRotationPoint(-0.65F, -8.85F, -15);
        loop3.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(loop3, 0, 0, 1.082104F);

        loop4 = new ModelRenderer(this, 27, 68);
        loop4.addBox(0, 0, 0, 2, 1, 1);
        loop4.setRotationPoint(1.5F, -8.4F, -15);
        loop4.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(loop4, 0, 0, 2.094395F);

        loop5 = new ModelRenderer(this, 34, 65);
        loop5.addBox(0, 0, 0, 1, 1, 1);
        loop5.setRotationPoint(-0.5F, -7.6F, -15);
        loop5.setTextureSize(this.textureWidth, this.textureHeight);
    }

    public void renderAllWithoutLoop() {
        horisontalPlank.render(0.0625F);
        verticalPlank.render(0.0625F);
        plank1.render(0.0625F);
        plank2.render(0.0625F);
        plank3.render(0.0625F);
        plank4.render(0.0625F);
        rope.render(0.0625F);
        rope2.render(0.0625F);
        knot.render(0.0625F);
    }

    public void renderLoop() {
        loop1.render(0.0625F);
        loop2.render(0.0625F);
        loop3.render(0.0625F);
        loop4.render(0.0625F);
        loop5.render(0.0625F);
    }

    @Override
    public void renderAll() {
        renderAllWithoutLoop();
        renderLoop();
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
        if (mob == EnumHangedMobs.NONE) {
            renderAll();
        } else {
            renderAllWithoutLoop();

            GL11.glPushMatrix();
            GL11.glTranslatef(0, -0.5F, -1.1F);
            switch (mob) {
                case STEVE:
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.STEVE);
                    bipedModel.renderAll();
                    break;
                case VILLAGER:
                    switch (villagerProfession) {
                        case 0:
                            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_FARMER);
                            break;
                        case 1:
                            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_LIBRARIAN);
                            break;
                        case 2:
                            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_PRIEST);
                            break;
                        case 3:
                            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_SMITH);
                            break;
                        case 4:
                            Minecraft.getMinecraft().renderEngine.bindTexture(Resources.VILLAGER_BUTCHER);
                            break;
                        default:
                            Minecraft.getMinecraft().renderEngine.bindTexture(VillagerRegistry.getVillagerSkin(villagerProfession, Resources.VILLAGER));
                            break;
                    }
                    villagerModel.renderAll();
                    break;
                case ZOMBIE:
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE);
                    zombieModel.renderAll();
                    break;
                case ZOMBIE_VILLAGER:
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_VILLAGER);
                    zombieVillagerModel.renderAll();
                    break;
                case SKELETON:
                    GL11.glTranslatef(0, 0, 0.1F);
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON);
                    skeletonModel.renderAll();
                    break;
                case WITHER_SKELETON:
                    GL11.glTranslatef(0, 0, 0.1F);
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WITHER_SKELETON);
                    witherSkeletonModel.renderAll();
                    break;
                case WITCH:
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WITCH);
                    witchModel.renderAll();
                    break;
                case ZOMBIE_PIGMAN:
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_PIGMAN);
                    zombieModel.renderAll();
            }
            GL11.glPopMatrix();
        }

    }
}
