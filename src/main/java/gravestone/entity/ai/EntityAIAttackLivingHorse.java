package gravestone.entity.ai;

import gravestone.entity.monster.EntityUndeadHorse;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.passive.EntityHorse;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityAIAttackLivingHorse extends EntityAIAttackOnCollide {

    protected EntityCreature attacker;

    public EntityAIAttackLivingHorse(EntityCreature creature, double speedTowardsTarget, boolean longMemory) {
        super(creature, EntityHorse.class, speedTowardsTarget, longMemory);
        attacker = creature;
    }

    public boolean shouldExecute() {
        EntityLivingBase entity = this.attacker.getAttackTarget();
        if (entity != null && entity instanceof EntityHorse && !(entity instanceof EntityUndeadHorse)) {
            EntityHorse horse = (EntityHorse) entity;
            if (!horse.isUndead()) {
                return super.shouldExecute();
            }
        }
        return false;
    }
}
