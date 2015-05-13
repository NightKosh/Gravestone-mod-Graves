package gravestone.entity.monster;

import gravestone.entity.ai.EntityAIAttackLivingHorse;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieHorse extends EntityUndeadHorse {
    public EntityZombieHorse(World worldIn) {
        super(worldIn);

        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
        this.tasks.addTask(4, new EntityAIAttackLivingHorse(this, 1, false));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));

        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityHorse.class, false));
    }

    @Override
    public EnumHorseType getUndeadHorseType() {
        return EnumHorseType.ZOMBIE_HORSE_TYPE;
    }

    @Override
    public void onKillEntity(EntityLivingBase entityLiving) {
        super.onKillEntity(entityLiving);

        if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
            spawnZombieMob(entityLiving);
        }
    }

    //TODO
    protected void spawnZombieMob(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityLiving) {
            EntityLiving entity = (EntityLiving) entityLivingBase;
            EntityLiving zombie = null;
            if (entity instanceof EntityVillager) {
                EntityZombie entityZombie = new EntityZombie(this.worldObj);
                entityZombie.copyLocationAndAnglesFrom(entity);
                this.worldObj.removeEntity(entity);
                entityZombie.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(this)), (IEntityLivingData) null);
                entityZombie.setVillager(true);

                if (entity.isChild()) {
                    entityZombie.setChild(true);
                }

                this.worldObj.spawnEntityInWorld(entityZombie);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos(this), 0);
                zombie = entityZombie;
            } else if (entity instanceof EntityWolf) {
                EntityZombieDog zombieDog = new EntityZombieDog(this.worldObj, false);
                zombieDog.copyLocationAndAnglesFrom(entity);

                this.worldObj.removeEntity(entity);
                this.worldObj.spawnEntityInWorld(zombieDog);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos(this), 0);

                zombie = zombieDog;
            } else if (entity instanceof EntityOcelot) {
                EntityZombieCat zombieCat = new EntityZombieCat(this.worldObj, false);
                zombieCat.copyLocationAndAnglesFrom(entity);
                if (((EntityOcelot) entity).isTamed()) {
                    zombieCat.setSkin(((EntityOcelot) entity).getTameSkin());
                } else {
                    zombieCat.setSkin(0);
                }
                this.worldObj.removeEntity(entity);

                zombieCat.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(this)), (IEntityLivingData) null);
                this.worldObj.spawnEntityInWorld(zombieCat);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos(this), 0);

                zombie = zombieCat;
            } else if (entity instanceof EntityHorse) {
                EntityZombieHorse zombieHorse = new EntityZombieHorse(this.worldObj);
                zombieHorse.copyLocationAndAnglesFrom(entity);
                zombieHorse.setGrowingAge(((EntityHorse) entity).getGrowingAge());

                this.worldObj.removeEntity(entity);
                this.worldObj.spawnEntityInWorld(zombieHorse);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos(this), 0);

                zombie = zombieHorse;
            }
            if (zombie != null && entity.hasCustomName()) {
                zombie.setCustomNameTag(entity.getCustomNameTag());
            }
        }
    }
}
