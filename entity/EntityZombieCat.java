package GraveStone.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
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
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EntityZombieCat extends EntityUndeadCat {

    public EntityZombieCat(World world) {
        super(world);
        this.texture = "/mods/GraveStone/textures/entity/ZombieCat.png";
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
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChicken.class, 16.0F, 0, false));
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture() {
        switch (this.getSkin()) {
            case 0:
                return "/mob/ozelot.png";
            case 1:
                return "/mob/cat_black.png";
            case 2:
                return "/mob/cat_red.png";
            case 3:
                return "/mob/cat_siamese.png";
            default:
                return super.getTexture();
        }
    }

    public int getMaxHealth() {
        return 10;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("ZombieCatType", this.getSkin());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSkin(nbt.getInteger("ZombieCatType"));
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return (this.rand.nextInt(4) == 0) ? "mob.cat.purreow" : "mob.cat.meow";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.cat.hitt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.cat.hitt";
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
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

    public int getSkin() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    public void setSkin(int par1) {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte) par1));
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
