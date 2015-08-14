package gravestone.renderer.entity;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderSpawnerHelper extends RenderLiving {

    public RenderSpawnerHelper(RenderManager renderManager) {
        super(renderManager, null, 0);
    }

    @Override
    public boolean shouldRender(Entity entity, ICamera camera, double p_shouldRender_3_, double p_shouldRender_5_, double p_shouldRender_7_) {
        return false;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
