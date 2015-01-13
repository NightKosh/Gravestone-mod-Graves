
package gravestone.entity;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityVengefulSpirit extends EntityGhost {
    
    public EntityVengefulSpirit(World world) {
        super(world);
        
        //this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
    }
    
    

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (entity instanceof EntityLivingBase) {
            //TODO
//            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) entity);
//            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) entity);
            
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(getPotionId(entity.worldObj.rand), 7));
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(getAdditionalPotionId(entity.worldObj.rand), 3));
        }

        return true;
    }
    
    /**
     * Get random potion id
     */
    private int getAdditionalPotionId(Random random) {
        switch (random.nextInt(3)) {
            case 1:
                return Potion.blindness.getId();
            case 2:
                return Potion.poison.getId();
            case 0:
            default:
                return Potion.wither.getId();
        }
    }
}
