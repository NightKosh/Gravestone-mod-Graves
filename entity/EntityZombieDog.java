package GraveStone.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EntityZombieDog extends EntityUndeadDog {

    public EntityZombieDog(World world) {
        super(world);
        this.texture = "/mods/GraveStone/textures/entity/ZombieDog.png";
        this.moveSpeed = 0.5F;
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
        this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
        
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, this.moveSpeed, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, this.moveSpeed, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, this.moveSpeed, true));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, this.moveSpeed, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, 16.0F, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCow.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPig.class, 16.0F, 0, false));
    }

    public int getMaxHealth() {
        return 20;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return (this.rand.nextInt(3) == 0) ? "mob.wolf.bark" : "mob.wolf.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId() {
        return Item.rottenFlesh.itemID;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();
        if (this.isWet()) {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else if ((this.isShaking || this.field_70928_h) && this.field_70928_h) {
            if (this.timeWolfIsShaking == 0.0F) {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F) {
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F) {
                float f = (float) this.boundingBox.minY;
                int i = (int) (MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.worldObj.spawnParticle("splash", this.posX + (double) f1, (double) (f + 0.8F), this.posZ + (double) f2, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving entityLiving) {
        super.onKillEntity(entityLiving);

        if (this.worldObj.difficultySetting >= 2) {
            if (this.worldObj.difficultySetting == 2 && this.rand.nextBoolean()) {
                return;
            }
            if (entityLiving instanceof EntityVillager) {
                EntityZombie entityzombie = new EntityZombie(this.worldObj);
                entityzombie.func_82149_j(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                entityzombie.initCreature();
                entityzombie.setVillager(true);

                if (entityLiving.isChild()) {
                    entityzombie.setChild(true);
                }

                this.worldObj.spawnEntityInWorld(entityzombie);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityWolf) {
                EntityZombieDog entityZombieDog = new EntityZombieDog(this.worldObj);
                entityZombieDog.func_82149_j(entityLiving);
                this.worldObj.removeEntity(entityLiving);

                this.worldObj.spawnEntityInWorld(entityZombieDog);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityOcelot) {
                EntityZombieCat entityZombieCat = new EntityZombieCat(this.worldObj);
                entityZombieCat.func_82149_j(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                entityZombieCat.initCreature();

                this.worldObj.spawnEntityInWorld(entityZombieCat);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            }
        }
    }
}
