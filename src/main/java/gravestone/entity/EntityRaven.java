package gravestone.entity;

import gravestone.entity.ai.EntityRavenMoveHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityRaven extends EntityAnimal {

    public EntityRaven(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.9F);

        ((PathNavigateGround) this.getNavigator()).func_179690_a(true);
        this.moveHelper = new EntityRavenMoveHelper(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(2, new EntityAITempt(this, 0.9, Items.rotten_flesh, true));
        this.tasks.addTask(5, new EntityAIWander(this, 1));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 20));
        this.tasks.addTask(7, new EntityAILookIdle(this));
//        this.tasks.addTask(8, new AIRavenFly(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityRaven.class}));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8);
//        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null; //TODO
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95F;
    }

    @Override
    protected String getLivingSound() {
        return "";//TODO
    }

    @Override
    protected String getHurtSound() {
        return "";//TODO
    }

    @Override
    protected String getDeathSound() {
        return "";//TODO
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);//TODO
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);//TODO
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if (blockpos.getY() <= 60) {
            return false;
        } else {
            return super.getCanSpawnHere();
        }
    }

    @Override
    public float getEyeHeight() {
        return this.height / 2F;
    }

    public boolean isFlying() {
        return false;
    }
}
