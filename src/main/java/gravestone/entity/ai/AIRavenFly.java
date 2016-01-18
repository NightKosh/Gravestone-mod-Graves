package gravestone.entity.ai;

import gravestone.entity.EntityRaven;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AIRavenFly extends EntityAIBase {

    private EntityRaven raven;

    public AIRavenFly(EntityRaven raven) {
        this.raven = raven;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        EntityMoveHelper moveHelper = this.raven.getMoveHelper();

        if (!moveHelper.isUpdating()) {
            return true;
        } else {
            double d0 = moveHelper.func_179917_d() - this.raven.posX;
            double d1 = moveHelper.func_179919_e() - this.raven.posY;
            double d2 = moveHelper.func_179918_f() - this.raven.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1 || d3 > 3600;
        }
    }

    public boolean continueExecuting() {
        return false;
    }

    public void startExecuting() {
        Random random = this.raven.getRNG();
        double d0 = this.raven.posX + (double) ((random.nextFloat() * 2 - 1) * 16);
        double d1 = this.raven.posY + (double) ((random.nextFloat() * 2 - 1) * 16);
        double d2 = this.raven.posZ + (double) ((random.nextFloat() * 2 - 1) * 16);
        this.raven.getMoveHelper().setMoveTo(d0, d1, d2, 1);
    }
}
