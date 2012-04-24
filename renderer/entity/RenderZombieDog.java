package GraveStone.renderer.entity;

import GraveStone.entity.EntityZombieDog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderZombieDog extends RenderLiving {
    
    public RenderZombieDog(ModelBase model, ModelBase model2) {
        super(model, 1);
        this.setRenderPassModel(model2);
    }

    protected float getTailRotation(EntityZombieDog zombieDog, float par2) {
        return zombieDog.getTailRotation();
    }

    protected int func_82447_a(EntityZombieDog zombieDog, int par2, float par3) {
        float f1;

        if (par2 == 0 && zombieDog.getWolfShaking()) {
            f1 = zombieDog.getBrightness(par3) * zombieDog.getShadingWhileShaking(par3);
            this.loadTexture(zombieDog.getTexture());
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
        return this.func_82447_a((EntityZombieDog) entityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving entityLiving, float par2) {
        return this.getTailRotation((EntityZombieDog) entityLiving, par2);
    }
}
