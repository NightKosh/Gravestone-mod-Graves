package gravestone.renderer.entity;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderUndeadHorse extends RenderHorse {

    private static final Map<String, ResourceLocation> TEXTURES_MAP = Maps.newHashMap();

    public RenderUndeadHorse(RenderManager renderManager, ModelHorse modelHorse, float p_i46170_3_) {
        super(renderManager, modelHorse, p_i46170_3_);
    }

    @Override
    protected ResourceLocation func_180581_a(EntityHorse horse) {
        if (!horse.func_110239_cn()) {
            return super.func_180581_a(horse);
        } else {
            return this.func_110848_b(horse);
        }
    }

    private ResourceLocation func_110848_b(EntityHorse horse) {
        String s = horse.getHorseTexture();

//        if (!horse.func_175507_cI()) {
//            return null;
//        } else {
            ResourceLocation resourcelocation = (ResourceLocation) TEXTURES_MAP.get(s);

            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s);
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(horse.getVariantTexturePaths()));
                TEXTURES_MAP.put(s, resourcelocation);
            }

            return resourcelocation;
//        }
    }
}
