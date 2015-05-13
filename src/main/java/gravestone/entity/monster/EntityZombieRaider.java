package gravestone.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieRaider extends EntityZombie {

    public EntityZombieRaider(World worldIn) {
        super(worldIn);
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData data) {
        this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
        this.setChild(false);

        EntityUndeadHorse horse = new EntityZombieHorse(this.worldObj);
        ((PathNavigateGround)horse.getNavigator()).func_179690_a(false);
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
