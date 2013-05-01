package GraveStone.renderer.entity;

import GraveStone.entity.EntityUndeadDog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderUndeadDog extends RenderLiving {
    
    public RenderUndeadDog(ModelBase model, ModelBase model2) {
        super(model, 0.5F);
        this.setRenderPassModel(model2);
    }

    protected float getTailRotation(EntityUndeadDog undeadDog, float par2) {
        return undeadDog.getTailRotation();
    }

    protected int func_82447_a(EntityUndeadDog undeadDog, int par2, float par3) {
        float f1;

        if (par2 == 0 && undeadDog.getWolfShaking()) {
            f1 = undeadDog.getBrightness(par3) * undeadDog.getShadingWhileShaking(par3);
            this.loadTexture(undeadDog.getTexture());
            GL11.glColor3f(f1, f1, f1);
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving entityLiving, int par2, float par3) {
        return this.func_82447_a((EntityUndeadDog) entityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving entityLiving, float par2) {
        return this.getTailRotation((EntityUndeadDog) entityLiving, par2);
    }
}
