package gravestone.entity.monster;

import gravestone.core.GSBlock;
import gravestone.entity.ai.AIHideInBones;
import gravestone.entity.ai.AISummonSkullCrawler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkullCrawler extends EntityMob {

    private AISummonSkullCrawler summonAI;

    public EntitySkullCrawler(World world) {
        super(world);
        this.setSize(0.8F, 0.8F);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonAI = new AISummonSkullCrawler(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
        this.tasks.addTask(5, new AIHideInBones(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public float getEyeHeight() {
        return 0.5F;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.5);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }

    @Override
    protected String getHurtSound() {
        return "mob.skeleton.say";
    }

    @Override
    protected String getDeathSound() {
        return "mob.skeleton.death";
    }

    @Override
    protected void playStepSound(BlockPos pos, Block block) {
        this.playSound("mob.spider.step", 0.15F, 1);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if ((source instanceof EntityDamageSource || source == DamageSource.magic)) {
                summonAI.resetSummonColdown();
            }

            return super.attackEntityFrom(source, par2);
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 5);
            return entityplayer == null;
        } else {
            return false;
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this
     * creature will try to path to the block. Args: x, y, z
     */
    @Override
    public float func_180484_a(BlockPos pos) {
        return this.worldObj.getBlockState(new BlockPos(pos.down())).getBlock().equals(GSBlock.boneBlock) ? 10 : super.func_180484_a(pos);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.bone;
    }

    protected ItemStack getRareDrop() {
        return new ItemStack(Items.skull, 1, 0);
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int lootAttr) {
        super.dropFewItems(p_70628_1_, lootAttr);

        if (p_70628_1_ && (this.rand.nextInt(100) == 0 || this.rand.nextInt(60 - lootAttr * 10) == 0)) {
            this.entityDropItem(getRareDrop(), 0);
        }
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    /**
     * Sets the Entity inside a web block.
     */
    @Override
    public void setInWeb() {
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns
     * false. The WatchableObject is updated using setBesideClimableBlock.
     */
    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to
     * 0x01 if par1 is true or 0x00 if it is false.
     */
    public void setBesideClimbableBlock(boolean par1) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).addPotionEffect(getPotionEffect());
            }
            return true;
        } else {
            return false;
        }
    }

    protected PotionEffect getPotionEffect() {
        return new PotionEffect(Potion.moveSlowdown.id, 200);
    }

    /**
     * Called frequently so the entity can update its state every tick as
     * required. For example, zombies and skeletons use this to react to
     * sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1);

            if (!this.isImmuneToFire && f > 0 && this.rand.nextFloat() * 30 < (f - 0.4F) * 2
                    && this.worldObj.canBlockSeeSky(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    public boolean canHideInBones() {
        return true;
    }

    public enum SkullCrawlerType {
        skeleton,
        wither,
        zombie
    }
}
