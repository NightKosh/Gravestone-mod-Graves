package gravestone.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieCat extends EntityUndeadCat {

    public EntityZombieCat(World world) {
        super(world);
        texture = Resources.ZOMBIE_OZELOT;
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(6, new EntityAIWander(this, 1));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, 0, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChicken.class, 0, false));
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10); // max health
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D); // movespeed
    }

    /**
     * Returns the texture's file path as a String.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture() {
        switch (this.getSkin()) {
            case 1:
                return Resources.ZOMBIE_CAT_BLACK;

            case 2:
                return Resources.ZOMBIE_CAT_RED;

            case 3:
                return Resources.ZOMBIE_CAT_SIAMESE;

            case 0:
            default:
                return Resources.ZOMBIE_OZELOT;
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("ZombieCatType", this.getSkin());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSkin(nbt.getInteger("ZombieCatType"));
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return (this.rand.nextInt(4) == 0) ? "mob.cat.purreow" : "mob.cat.meow";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.cat.hitt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.cat.hitt";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4) {
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

    public int getSkin() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setSkin(int par1) {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte) par1));
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
                //entityZombie.func_82149_j(entityLiving);
                entityZombie.copyLocationAndAnglesFrom(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                //entityZombie.initCreature();
                entityZombie.func_110161_a((EntityLivingData) null);
                entityZombie.setVillager(true);

                if (entityLiving.isChild()) {
                    entityZombie.setChild(true);
                }

                this.worldObj.spawnEntityInWorld(entityZombie);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityWolf) {
                EntityZombieDog entityZombieDog = new EntityZombieDog(this.worldObj);
                //entityZombieDog.func_82149_j(entityLiving);
                entityZombieDog.copyLocationAndAnglesFrom(entityLiving);
                this.worldObj.removeEntity(entityLiving);
                this.worldObj.spawnEntityInWorld(entityZombieDog);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            } else if (entityLiving instanceof EntityOcelot) {
                EntityZombieCat entityZombieCat = new EntityZombieCat(this.worldObj);
                //entityZombieCat.func_82149_j(entityLiving);
                entityZombieCat.copyLocationAndAnglesFrom(entityLiving);
                int catType = ((EntityOcelot) entityLiving).getTameSkin();
                this.worldObj.removeEntity(entityLiving);
                entityZombieCat.setSkin(catType);
                //entityZombieCat.initCreature();
                entityZombieCat.func_110161_a((EntityLivingData) null);
                this.worldObj.spawnEntityInWorld(entityZombieCat);
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
            }
        }
    }
}
