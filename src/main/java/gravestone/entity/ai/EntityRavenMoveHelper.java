package gravestone.entity.ai;

import gravestone.entity.EntityRaven;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldType;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityRavenMoveHelper extends EntityMoveHelper {

    public EntityRavenMoveHelper(EntityLiving entity) {
        super(entity);
    }

    public void onUpdateMoveHelper() {
        BlockPos groundPos = this.entity.worldObj.getTopSolidOrLiquidBlock(this.entity.getPosition());

        if (((EntityRaven) entity).isFlying()) {
            int height = 20;
            if (groundPos.getY() > 60 || this.entity.worldObj.getWorldInfo().getTerrainType().equals(WorldType.FLAT)) {
                height += groundPos.getY();
            } else {
                height += 60;
            }

            if (this.entity.posY < height - 3) {
                this.entity.motionY += 0.5;
            } else if (this.entity.posY > height + 3) {
                this.entity.motionY -= 0.5;
            }
        }

        super.onUpdateMoveHelper();
    }
}
