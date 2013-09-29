package gravestone.entity;

import gravestone.Resources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieDog extends EntityUndeadDog {

    public EntityZombieDog(World world) {
        super(world);
        texture = Resources.ZOMBIE_DOG;
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(6, new EntityAIWander(this, 1));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityHorse.class, 1, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntitySheep.class, 1, false));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityHorse.class, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, 0, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20); // max health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3); // movespeed
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3); // attack damage
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return (this.rand.nextInt(3) == 0) ? "mob.wolf.bark" : "mob.wolf.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected int getDropItemId() {
        return Item.rottenFlesh.itemID;
    }

    /**
     * This method gets called when the entity kills another one.
     */
    @Override
    public void onKillEntity(EntityLivingBase entityLiving) {
        super.onKillEntity(entityLiving);

        if (this.worldObj.difficultySetting >= 2) {
            if (this.worldObj.difficultySetting == 2 && this.rand.nextBoolean()) {
                return;
            }

            if (entityLiving instanceof EntityVillager) {
                EntityZombie entityZombie = new EntityZombie(this.worldObj);
                entityZombie.copyLocationAndAnglesFrom(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                entityZombie.onSpawnWithEgg((EntityLivingData) null);
                entityZombie.setVillager(true);

                if (entityLiving.isChild()) {
                    entityZombie.setChild(true);
                }

                this.worldObj.spawnEntityInWorld(entityZombie);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityWolf) {
                EntityZombieDog entityZombieDog = new EntityZombieDog(this.worldObj);
                entityZombieDog.copyLocationAndAnglesFrom(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                this.worldObj.spawnEntityInWorld(entityZombieDog);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityOcelot) {
                EntityZombieCat entityZombieCat = new EntityZombieCat(this.worldObj);
                entityZombieCat.copyLocationAndAnglesFrom(entityLiving);
                int catType = ((EntityOcelot) entityLiving).getTameSkin();
                this.worldObj.removeEntity(entityLiving);
                entityZombieCat.setSkin(catType);
                entityZombieCat.onSpawnWithEgg((EntityLivingData) null);
                this.worldObj.spawnEntityInWorld(entityZombieCat);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityHorse) {
                EntityHorse horse = new EntityHorse(this.worldObj);
                horse.copyLocationAndAnglesFrom(entityLiving);
                horse.setHorseType(3);
                this.worldObj.removeEntity(entityLiving);
                this.worldObj.spawnEntityInWorld(horse);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            }
        }
    }
}
