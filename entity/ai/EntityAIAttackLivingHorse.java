package gravestone.entity.ai;

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
        if (entity != null && entity instanceof EntityHorse) {
            EntityHorse horse = (EntityHorse) entity;
            if (horse.getHorseType() != 3 && horse.getHorseType() != 4) {
                return super.shouldExecute();
            }
        }
        return false;
    }
}
