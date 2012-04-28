package GraveStone.renderer.entity;

import GraveStone.entity.EntityZombieCat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderZombieCat extends RenderLiving {

    public RenderZombieCat(ModelBase model, float par2) {
        super(model, par2);
    }

    public void renderLivingZombieCat(EntityZombieCat zombieCat, double par2, double par4, double par6, float par8, float par9) {
        super.doRenderLiving(zombieCat, par2, par4, par6, par8, par9);
    }

    /**
     * Pre-Renders the Ocelot.
     */
    protected void preRenderZombieCat(EntityZombieCat zombieCat, float par2) {
        super.preRenderCallback(zombieCat, par2);

        GL11.glScalef(0.8F, 0.8F, 0.8F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving entityLiving, float par2) {
        this.preRenderZombieCat((EntityZombieCat) entityLiving, par2);
    }

    public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingZombieCat((EntityZombieCat) entityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingZombieCat((EntityZombieCat) entity, par2, par4, par6, par8, par9);
    }
}
