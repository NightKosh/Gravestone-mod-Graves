package gravestone.renderer.entity;

import gravestone.entity.monster.EntityUndeadDog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
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
@SideOnly(Side.CLIENT)
public class RenderUndeadDog extends RenderLiving {

    public RenderUndeadDog(ModelBase model, ModelBase model2) {
        super(model, 0.5F);
        this.setRenderPassModel(model2);
    }

    protected float getTailRotation(EntityUndeadDog undeadDog, float par2) {
        return undeadDog.getTailRotation();
    }

    protected int func_82447_a(EntityUndeadDog undeadDog, int par2, float par3) {
        return -1;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLivingBase entityLiving, int par2, float par3) {
        return this.func_82447_a((EntityUndeadDog) entityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float handleRotationFloat(EntityLivingBase entityLiving, float par2) {
        return this.getTailRotation((EntityUndeadDog) entityLiving, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return ((EntityUndeadDog) entity).getTexture();
    }
}
