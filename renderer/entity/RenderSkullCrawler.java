package gravestone.renderer.entity;

import gravestone.core.Resources;
import gravestone.entity.monster.EntitySkullCrawler;
import gravestone.models.entity.ModelSkullCrawler;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderSkullCrawler extends RenderLiving {

    public RenderSkullCrawler() {
        super(new ModelSkullCrawler(), 0.2F);
        this.setRenderPassModel(new ModelSkullCrawler());
    }

    protected float setSpiderDeathMaxRotation(EntitySkullCrawler entity) {
        return 180.0F;
    }

    @Override
    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return this.setSpiderDeathMaxRotation((EntitySkullCrawler) entity);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return Resources.SKULL_CRAWLER;
    }
}
