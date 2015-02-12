package gravestone.entity.monster;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkeletonRaider extends EntitySkeleton {

    public EntitySkeletonRaider(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData data) {
        EntityHorse horse = new EntityHorse(this.worldObj);
        horse.copyLocationAndAnglesFrom(this);
        horse.func_180482_a(difficulty, (IEntityLivingData) null);
        horse.setHorseType(4);
        horse.setHorseTamed(true);
        horse.setHorseSaddled(true);

        this.worldObj.spawnEntityInWorld(horse);
        this.mountEntity(horse);

        return super.func_180482_a(difficulty, data);
    }

    @Override
    public void onLivingUpdate() {
        if (this.isRiding() && this.getAttackTarget() != null && this.ridingEntity instanceof EntityHorse) {
            ((EntityLiving) this.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
        }

        super.onLivingUpdate();
    }
}
