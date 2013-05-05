package GraveStone.renderer.entity;

import GraveStone.entity.EntityUndeadCat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public class RenderUndeadCat extends RenderLiving {

    public RenderUndeadCat(ModelBase model, float par2) {
        super(model, par2);
    }

    public void renderLivingUndeadCat(EntityUndeadCat undeadCat, double par2, double par4, double par6, float par8, float par9) {
        super.doRenderLiving(undeadCat, par2, par4, par6, par8, par9);
    }

    /**
     * Pre-Renders the Ocelot.
     */
    protected void preRenderUndeadCat(EntityUndeadCat undeadCat, float par2) {
        super.preRenderCallback(undeadCat, par2);

        GL11.glScalef(0.8F, 0.8F, 0.8F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving entityLiving, float par2) {
        this.preRenderUndeadCat((EntityUndeadCat) entityLiving, par2);
    }

    public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingUndeadCat((EntityUndeadCat) entityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingUndeadCat((EntityUndeadCat) entity, par2, par4, par6, par8, par9);
    }
}
