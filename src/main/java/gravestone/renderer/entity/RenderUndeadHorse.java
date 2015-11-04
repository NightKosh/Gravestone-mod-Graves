package gravestone.renderer.entity;

import com.google.common.collect.Maps;
import gravestone.entity.monster.EntityUndeadHorse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderUndeadHorse extends RenderHorse {

    protected static final Map<String, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();
    public static final String ZOMBIE_HORSE_TEXTURE = "textures/entity/horse/horse_zombie.png";
    public static final String SKELETON_HORSE_TEXTURE = "textures/entity/horse/horse_skeleton.png";
    protected static final ResourceLocation zombieHorseTexture = new ResourceLocation(ZOMBIE_HORSE_TEXTURE);
    protected static final ResourceLocation skeletonHorseTexture = new ResourceLocation(SKELETON_HORSE_TEXTURE);

    public RenderUndeadHorse(RenderManager renderManager, ModelHorse modelHorse, float p_i46170_3_) {
        super(renderManager, modelHorse, p_i46170_3_);
    }

    protected ResourceLocation func_180581_a(EntityUndeadHorse horse) {
        if (!horse.func_110239_cn()) {
            switch (horse.getHorseType()) {
                case 3:
                default:
                    return zombieHorseTexture;
                case 4:
                    return skeletonHorseTexture;
            }
        } else {
            return this.func_110848_b(horse);
        }
    }

    protected ResourceLocation func_110848_b(EntityUndeadHorse horse) {
        String s = horse.getHorseTexture();

        if (!horse.func_175507_cI()) {
            return null;
        } else {
            ResourceLocation resourcelocation = TEXTURES_MAP.get(s);

            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s);
                String horseTexture = "";
                switch (horse.getHorseType()) {
                    case 3:
                        horseTexture = ZOMBIE_HORSE_TEXTURE;
                        break;
                    case 4:
                        horseTexture = SKELETON_HORSE_TEXTURE;
                        break;
                }
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(horseTexture, horse.getArmorTexturePaths()));
                TEXTURES_MAP.put(s, resourcelocation);
            }

            return resourcelocation;
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.func_180581_a((EntityUndeadHorse) entity);
    }
}
