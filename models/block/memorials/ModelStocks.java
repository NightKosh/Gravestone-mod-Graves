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
public class ModelStocks extends ModelMemorial {

    private ModelRenderer horisontalPlank;
    private ModelRenderer verticalPlank1;
    private ModelRenderer verticalPlank2;
    private ModelRenderer headHole;
    private ModelRenderer armHole1;
    private ModelRenderer armHole2;

    private static final ModelHangedBiped bipedModel = new ModelHangedBiped(true);
    private static final ModelHangedBiped zombieModel = new ModelHangedBiped(true, true);
    private static final ModelHangedSkeleton skeletonModel = new ModelHangedSkeleton(true);
    private static final ModelHangedSkeleton witherSkeletonModel = new ModelHangedSkeleton(true, true);
    private static final ModelHangedVillager villagerModel = new ModelHangedVillager(true);
    private static final ModelHangedZombieVillager zombieVillagerModel = new ModelHangedZombieVillager(true);
    private static final ModelHangedWitch witchModel = new ModelHangedWitch(true);

    public ModelStocks() {
        textureWidth = 128;
        textureHeight = 64;

        horisontalPlank = new ModelRenderer(this, 0, 0);
        horisontalPlank.addBox(0, 0, 0, 32, 12, 1);
        horisontalPlank.setRotationPoint(-16, -2, 0);
        horisontalPlank.setTextureSize(128, this.textureHeight);

        verticalPlank1 = new ModelRenderer(this, 0, 13);
        verticalPlank1.addBox(0, 0, 0, 3, 28, 2);
        verticalPlank1.setRotationPoint(-19, -4, -0.5F);
        verticalPlank1.setTextureSize(128, this.textureHeight);

        verticalPlank2 = new ModelRenderer(this, 11, 13);
        verticalPlank2.addBox(0, 0, 0, 3, 28, 2);
        verticalPlank2.setRotationPoint(16, -4, -0.5F);
        verticalPlank2.setTextureSize(128, this.textureHeight);

        headHole = new ModelRenderer(this, 22, 13);
        headHole.addBox(0, 0, 0, 6, 6, 1);
        headHole.setRotationPoint(-3, 1, 0);
        headHole.setTextureSize(128, this.textureHeight);

        armHole1 = new ModelRenderer(this, 37, 13);
        armHole1.addBox(0, 0, 0, 2, 2, 1);
        armHole1.setRotationPoint(-11, 3, 0);
        armHole1.setTextureSize(128, this.textureHeight);

        armHole2 = new ModelRenderer(this, 44, 13);
        armHole2.addBox(0, 0, 0, 2, 2, 1);
        armHole2.setRotationPoint(9, 3, 0);
        armHole2.setTextureSize(128, this.textureHeight);

    }

    @Override
    public void renderAll() {
        horisontalPlank.render(0.0625F);
        verticalPlank1.render(0.0625F);
        verticalPlank2.render(0.0625F);
        headHole.render(0.0625F);
        armHole1.render(0.0625F);
        armHole2.render(0.0625F);
    }

    public void customRender(EnumMemorials memorialType, EnumHangedMobs mob, int villagerProfession) {
        renderAll();
        if (mob != EnumHangedMobs.NONE) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0.35F, 0.125F);
            GL11.glRotatef(45, 1, 0, 0);
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
