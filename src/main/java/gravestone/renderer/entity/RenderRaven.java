package gravestone.renderer.entity;

import gravestone.core.Resources;
import gravestone.models.entity.ModelRaven;
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
public class RenderRaven extends RenderLiving {

    public RenderRaven(RenderManager renderManager) {
        super(renderManager, new ModelRaven(), 0.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return Resources.RAVEN;
    }
}
