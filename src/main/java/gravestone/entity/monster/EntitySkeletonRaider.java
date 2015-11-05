package gravestone.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkeletonRaider extends EntityGSSkeleton {

    public EntitySkeletonRaider(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData data) {
        EntitySkeletonHorse horse = new EntitySkeletonHorse(this.worldObj);
        horse.copyLocationAndAnglesFrom(this);
        horse.func_180482_a(difficulty, (IEntityLivingData) null);

        this.worldObj.spawnEntityInWorld(horse);
        this.mountEntity(horse);

        return super.func_180482_a(difficulty, data);
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public void onLivingUpdate() {
//        if (this.isRiding() && this.getAttackTarget() != null && this.ridingEntity instanceof EntityUndeadHorse) {
//            ((EntityLiving) this.ridingEntity).getNavigator().setPath(this.getNavigator().getPath(), 1.5D);
//        }

        super.onLivingUpdate();
    }
}
