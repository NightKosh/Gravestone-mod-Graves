package gravestone.entity.monster;

import gravestone.core.Resources;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkeletonCat extends EntityUndeadCat {

    public EntitySkeletonCat(World world) {
        super(world);
        texture = Resources.SKELETON_CAT;
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(6, new EntityAIWander(this, 1));
        this.tasks.addTask(8, new EntityAIOcelotAttack(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5); // max health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7); // movespeed
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1); // attack damage
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.skeleton.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.bone;
    }
}
