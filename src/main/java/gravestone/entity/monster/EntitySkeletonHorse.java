package gravestone.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkeletonHorse extends EntityUndeadHorse {

    public EntitySkeletonHorse(World worldIn) {
        super(worldIn);

        this.tasks.addTask(3, new EntityAIAvoidEntity(this, new Predicate() {
            public boolean func_179945_a(Entity entity) {
                return entity instanceof EntityWolf;
            }

            @Override
            public boolean apply(Object obj) {
                return this.func_179945_a((Entity) obj);
            }
        }, 6, 1, 1.2));
    }

    @Override
    public EnumHorseType getUndeadHorseType() {
        return EnumHorseType.SKELETON_HORSE_TYPE;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3);
    }
}
