package gravestone.entity.monster;

import gravestone.core.GSBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntitySkullCrawler extends EntityMob {

    public enum SkullCrawlerType {

        skeleton,
        wither,
        zombie
    }
    protected int allySummonCooldown;

    public EntitySkullCrawler(World world) {
        super(world);
        this.setSize(0.8F, 0.8F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
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
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.9D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.5);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this
     * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
     * PigZombies).
     */
    @Override
    protected Entity findPlayerToAttack() {
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, 2);
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
        return "mob.skeleton.say";
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
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden
     * by each mob to define their attack.
     */
    @Override
    protected void attackEntity(Entity entity, float par2) {
        if (par2 > 2.0F && par2 < 6.0F && this.rand.nextInt(10) == 0) {
            if (this.onGround) {
                double d0 = entity.posX - this.posX;
                double d1 = entity.posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 0.4000000059604645D;
            }
        } else {
            super.attackEntity(entity, par2);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            if (this.allySummonCooldown <= 0 && (source instanceof EntityDamageSource || source == DamageSource.magic)) {
                this.allySummonCooldown = 20;
            }

            return super.attackEntityFrom(source, par2);
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this
     * creature will try to path to the block. Args: x, y, z
     */
    @Override
    public float getBlockPathWeight(int x, int y, int z) {
        return this.worldObj.getBlockId(x, y - 1, z) == Block.stone.blockID ? 10.0F : super.getBlockPathWeight(x, y, z);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected int getDropItemId() {
        return Item.bone.itemID;
    }

    @Override
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 0), 0);
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

    /**
     * Get this Entity's EnumCreatureAttribute
     */
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
        return new PotionEffect(Potion.poison.id, 100);
    }

    /**
     * Called frequently so the entity can update its state every tick as
     * required. For example, zombies and skeletons use this to react to
     * sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1.0F);

            if (!this.isImmuneToFire && f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && 
                    this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        EntityLivingData entityData = super.onSpawnWithEgg(data);

        /*
         if (par1EntityLivingData1 == null) {
         par1EntityLivingData1 = new SpiderEffectsGroupData();

         if (this.worldObj.difficultySetting > 2 && this.worldObj.rand.nextFloat() < 0.1F * this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ)) {
         ((SpiderEffectsGroupData) par1EntityLivingData1).func_111104_a(this.worldObj.rand);
         }
         }

         if (par1EntityLivingData1 instanceof SpiderEffectsGroupData) {
         int i = ((SpiderEffectsGroupData) par1EntityLivingData1).field_111105_a;

         if (i > 0 && Potion.potionTypes[i] != null) {
         this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
         }
         }*/

        return entityData;
    }
    /*
     @Override
     protected void updateEntityActionState() {
     super.updateEntityActionState();

     if (!this.worldObj.isRemote) {
     int x;
     int y;
     int z;
     int l;

     if (this.allySummonCooldown > 0) {
     --this.allySummonCooldown;

     if (this.allySummonCooldown == 0) {
     x = MathHelper.floor_double(this.posX);
     y = MathHelper.floor_double(this.posY);
     z = MathHelper.floor_double(this.posZ);
     boolean flag = false;

     for (l = 0; !flag && l <= 5 && l >= -5; l = l <= 0 ? 1 - l : 0 - l) {
     for (int i1 = 0; !flag && i1 <= 10 && i1 >= -10; i1 = i1 <= 0 ? 1 - i1 : 0 - i1) {
     for (int j1 = 0; !flag && j1 <= 10 && j1 >= -10; j1 = j1 <= 0 ? 1 - j1 : 0 - j1) {
     int blockID = this.worldObj.getBlockId(x + i1, y + l, z + j1);
     int blockMeta = this.worldObj.getBlockMetadata(x + i1, y + l, z + j1);

     if (blockID == GSBlock.boneBlock.blockID && GSBlock.boneBlock.isSkullCrawlerBlock(blockMeta)) {
     this.worldObj.destroyBlock(x + i1, y + l, z + j1, false);

     GSBlock.boneBlock.onBlockDestroyedByPlayer(this.worldObj, x + i1, y + l, z + j1, 0);
     //Block.silverfish.onBlockDestroyedByPlayer(this.worldObj, x + i1, y + l, z + j1, 0);

     if (this.rand.nextBoolean()) {
     flag = true;
     break;
     }
     }
     }
     }
     }
     }
     }

     if (this.entityToAttack == null && !this.hasPath()) {
     x = MathHelper.floor_double(this.posX);
     y = MathHelper.floor_double(this.posY + 0.5D);
     z = MathHelper.floor_double(this.posZ);
     int i2 = this.rand.nextInt(6);
     l = this.worldObj.getBlockId(x + Facing.offsetsXForSide[i2], y + Facing.offsetsYForSide[i2], z + Facing.offsetsZForSide[i2]);

     if (BlockSilverfish.getPosingIdByMetadata(l)) {
     this.worldObj.setBlock(x + Facing.offsetsXForSide[i2], y + Facing.offsetsYForSide[i2], z + Facing.offsetsZForSide[i2], Block.silverfish.blockID, BlockSilverfish.getMetadataForBlockType(l), 3);
     this.spawnExplosionParticle();
     this.setDead();
     } else {
     this.updateWanderPath();
     }
     } else if (this.entityToAttack != null && !this.hasPath()) {
     this.entityToAttack = null;
     }
     }
     }
     */
}
