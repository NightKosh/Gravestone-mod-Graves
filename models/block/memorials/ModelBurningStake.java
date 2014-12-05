package gravestone.models.block.memorials;

import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.block.enums.EnumHangedMobs;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.Resources;
import gravestone.models.block.ModelMemorial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBurningStake extends ModelMemorial {

    private ModelRenderer horisontalPlank;
    private ModelRenderer plank1;
    private ModelRenderer plank2;
    private ModelRenderer plank3;
    private ModelRenderer plank4;
    private ModelRenderer plank5;
    private ModelRenderer plank6;
    private ModelRenderer plank7;
    private ModelRenderer plank8;
    private ModelRenderer hay1;
    private ModelRenderer hay2;
    private ModelRenderer hay3;
    private ModelRenderer hay4;
    private ModelRenderer hay5;
    private ModelRenderer hay6;
    private ModelRenderer hay7;
    private ModelRenderer hay8;

    private static final ModelHangedBiped bipedModel = new ModelHangedBiped(false);
    private static final ModelHangedBiped zombieModel = new ModelHangedBiped(false, true);
    private static final ModelHangedSkeleton skeletonModel = new ModelHangedSkeleton(false);
    private static final ModelHangedSkeleton witherSkeletonModel = new ModelHangedSkeleton(false, true);
    private static final ModelHangedVillager villagerModel = new ModelHangedVillager(false);
    private static final ModelHangedZombieVillager zombieVillagerModel = new ModelHangedZombieVillager(false);
    private static final ModelHangedWitch witchModel = new ModelHangedWitch(false);

    public ModelBurningStake() {
        textureWidth = 64;
        textureHeight = 128;

        horisontalPlank = new ModelRenderer(this, 0, 0);
        horisontalPlank.addBox(0, 0, 0, 4, 56, 4);
        horisontalPlank.setRotationPoint(-2, -32, -2);
        horisontalPlank.setTextureSize(this.textureWidth, this.textureHeight);

        plank1 = new ModelRenderer(this, 17, 0);
        plank1.addBox(0, 0, 0, 3, 20, 3);
        plank1.setRotationPoint(-1.5F, 10, -2);
        plank1.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank1, -0.7853982F, 0, 0);

        plank2 = new ModelRenderer(this, 30, 0);
        plank2.addBox(0, 0, 0, 3, 20, 3);
        plank2.setRotationPoint(-2, 10, 0);
        plank2.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank2, -0.7853982F, 0.7853982F, 0);

        plank3 = new ModelRenderer(this, 43, 0);
        plank3.addBox(0, 0, 0, 3, 20, 3);
        plank3.setRotationPoint(-2, 10, 1.5F);
        plank3.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank3, -0.7853982F, 1.570796F, 0);

        plank4 = new ModelRenderer(this, 17, 24);
        plank4.addBox(0, 0, 0, 3, 20, 3);
        plank4.setRotationPoint(0, 10, 2);
        plank4.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank4, -0.7853982F, 2.356194F, 0);

        plank5 = new ModelRenderer(this, 30, 24);
        plank5.addBox(0, 0, 0, 3, 20, 3);
        plank5.setRotationPoint(1.5F, 10, 2);
        plank5.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank5, -0.7853982F, 3.141593F, 0);

        plank6 = new ModelRenderer(this, 43, 24);
        plank6.addBox(0, 0, 0, 3, 20, 3);
        plank6.setRotationPoint(2, 10, 0);
        plank6.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank6, -0.7853982F, -2.356194F, 0);

        plank7 = new ModelRenderer(this, 17, 48);
        plank7.addBox(0, 0, 0, 3, 20, 3);
        plank7.setRotationPoint(2, 10, -1.5F);
        plank7.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank7, -0.7853982F, -1.570796F, 0);

        plank8 = new ModelRenderer(this, 30, 48);
        plank8.addBox(0, 0, 0, 3, 20, 3);
        plank8.setRotationPoint(0, 10, -2);
        plank8.setTextureSize(this.textureWidth, this.textureHeight);
        setRotation(plank8, -0.7853982F, -0.7853982F, 0F);

        hay1 = new ModelRenderer(this, 0, 72);
        hay1.addBox(0, 0, 0, 5, 5, 5);
        hay1.setRotationPoint(-2.3F, 19, -6);
        hay1.setTextureSize(this.textureWidth, this.textureHeight);
        hay1.mirror = true;
        setRotation(hay1, -0.7853982F, -0.2466181F, 0.7853982F);

        hay2 = new ModelRenderer(this, 0, 72);
        hay2.addBox(0, 0, 0, 5, 5, 5);
        hay2.setRotationPoint(-6, 19, -2.6F);
        hay2.setTextureSize(this.textureWidth, this.textureHeight);
        hay2.mirror = true;
        setRotation(hay2, -0.7853982F, 0.5827747F, 0.7853982F);

        hay3 = new ModelRenderer(this, 0, 72);
        hay3.addBox(0, 0, 0, 5, 5, 5);
        hay3.setRotationPoint(-6, 23, -2);
        hay3.setTextureSize(this.textureWidth, this.textureHeight);
        hay3.mirror = true;
        setRotation(hay3, 0.7853982F, -0.5871122F, 0.7853982F);

        hay4 = new ModelRenderer(this, 0, 72);
        hay4.addBox(0, 0, 0, 5, 5, 5);
        hay4.setRotationPoint(-5.6F, 21, 3);
        hay4.setTextureSize(this.textureWidth, this.textureHeight);
        hay4.mirror = true;
        setRotation(hay4, 0.7853982F, 0.1350823F, 0.7853982F);

        hay5 = new ModelRenderer(this, 0, 72);
        hay5.addBox(0, 0, 0, 5, 5, 5);
        hay5.setRotationPoint(1.7F, 23, 3);
        hay5.setTextureSize(this.textureWidth, this.textureHeight);
        hay5.mirror = true;
        setRotation(hay5, 0.5902105F, -0.3160182F, -0.5902105F);

        hay6 = new ModelRenderer(this, 0, 72);
        hay6.addBox(0, 0, 0, 5, 5, 5);
        hay6.setRotationPoint(4, 25, 2F);
        hay5.setTextureSize(this.textureWidth, this.textureHeight);
        hay6.mirror = true;
        setRotation(hay6, 0.924818F, 0.7718693F, -0.6273891F);

        hay7 = new ModelRenderer(this, 0, 72);
        hay7.addBox(0, 0, 0, 5, 5, 5);
        hay7.setRotationPoint(3, 24, -2.6F);
        hay5.setTextureSize(this.textureWidth, this.textureHeight);
        hay7.mirror = true;
        setRotation(hay7, 0.5902105F, 1.219252F, -0.6645677F);

        hay8 = new ModelRenderer(this, 0, 72);
        hay8.addBox(0, 0, 0, 5, 5, 5);
        hay8.setRotationPoint(-2, 21, -6.6F);
        hay8.setTextureSize(this.textureWidth, this.textureHeight);
        hay8.mirror = true;
        setRotation(hay8, -0.924818F, -0.0716721F, -0.4786746F);
    }

    @Override
    public void renderAll() {
        float f5 = 0.0625F;
        horisontalPlank.render(f5);
        plank1.render(f5);
        plank2.render(f5);
        plank3.render(f5);
        plank4.render(f5);
        plank5.render(f5);
        plank6.render(f5);
        plank7.render(f5);
        plank8.render(f5);
        hay1.render(f5);
        hay2.render(f5);
        hay3.render(f5);
        hay4.render(f5);
        hay5.render(f5);
        hay6.render(f5);
        hay7.render(f5);
        hay8.render(f5);
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
        renderAll();
        if (mob != EnumHangedMobs.NONE) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, -0.5F, -0.25F);
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
                    Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON);
                    skeletonModel.renderAll();
                    break;
                case WITHER_SKELETON:
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
