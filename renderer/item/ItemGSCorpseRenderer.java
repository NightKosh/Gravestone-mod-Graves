package gravestone.renderer.item;

import cpw.mods.fml.common.registry.VillagerRegistry;
import gravestone.core.Resources;
import gravestone.item.corpse.CatCorpseHelper;
import gravestone.item.corpse.HorseCorpseHelper;
import gravestone.item.corpse.VillagerCorpseHelper;
import gravestone.item.enums.EnumCorpse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemGSCorpseRenderer implements IItemRenderer {

    private static final ModelVillager villagerModel = new ModelVillager(0);
    private static final ModelWolf dogModel = new ModelWolf();
    private static final ModelOcelot catModel = new ModelOcelot();
    private static final ModelHorse horseModel = new ModelHorse();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glRotatef(180, 1, 0, 0);

        byte corpseType = (byte) item.getItemDamage();
        float xz = 0.0625F;
        switch (EnumCorpse.getById(corpseType)) {
            case VILLAGER:
                GL11.glTranslatef(0, -0.5F, 0);
                int profession = VillagerCorpseHelper.getVillagerType(item.getTagCompound());
                switch (profession) {
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
                        Minecraft.getMinecraft().renderEngine.bindTexture(VillagerRegistry.getVillagerSkin(profession, Resources.VILLAGER));
                        break;
                }
                villagerModel.render(null, xz, xz, xz, xz, xz, xz);
                break;
            case DOG:
                GL11.glTranslatef(0, -1, 0);
                Minecraft.getMinecraft().renderEngine.bindTexture(Resources.WOLF);
//                EntityWolf dog = new EntityWolf(Minecraft.getMinecraft().theWorld);
//                dog.setGrowingAge(0);
//                dog.setSitting(true);
//                dog.setTamed(true);
                dogModel.render(null, xz, xz, xz, xz, xz, xz);
                break;
            case CAT:
                GL11.glTranslatef(0, -1, 0);
                int catType = CatCorpseHelper.getCatType(item.getTagCompound());
                switch (catType) {
                    case 0:
                    default:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.OCELOT);
                        break;
                    case 1:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.BLACK_CAT);
                        break;
                    case 2:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.RED_CAT);
                        break;
                    case 3:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SIAMESE_CAT);
                        break;
                }
                catModel.render(null, xz, xz, xz, xz, xz, xz);
                break;
            case HORSE:
                GL11.glTranslatef(0, -0.6F, 0);
                EntityHorse horse = new EntityHorse(Minecraft.getMinecraft().theWorld);
                horse.setHorseType(HorseCorpseHelper.getHorseType(item.getTagCompound()));
                horse.setHorseVariant(HorseCorpseHelper.getHorseVariant(item.getTagCompound()));
//                horse..setEating(false);

                switch (HorseCorpseHelper.getHorseType(item.getTagCompound())) {
                    case 0:
                        Minecraft.getMinecraft().getTextureManager().loadTexture(new ResourceLocation(horse.getHorseTexture()), new LayeredTexture(horse.getVariantTexturePaths()));
                        break;
                    case 1:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.DONKEY);
                        break;
                    case 2:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.MULE);
                        break;
                    case 3:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.ZOMBIE_HORSE);
                        break;
                    case 4:
                        Minecraft.getMinecraft().renderEngine.bindTexture(Resources.SKELETON_HORSE);
                        break;
                }

                horseModel.render(horse, xz, xz, xz, xz, xz, xz);
                break;
        }
        GL11.glPopMatrix();
    }
}
