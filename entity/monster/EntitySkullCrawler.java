package gravestone.entity.monster;

import gravestone.core.GSBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

    protected int allySummonCooldown;
    protected int defaultSummonCooldown = 10;

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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.9D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.5);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this
     * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
     * PigZombies).
     */
    @Override
    protected Entity findPlayerToAttack() {
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, 10);
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
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
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
                this.allySummonCooldown = defaultSummonCooldown;
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
        return this.worldObj.getBlock(x, y - 1, z).equals(Blocks.stone) ? 10 : super.getBlockPathWeight(x, y, z);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.bone;
    }

    @Override
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Items.skull, 1, 0), 0);
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
            float f = this.getBrightness(1.0F);

            if (!this.isImmuneToFire && f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F
                    && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    protected void updateEntityActionState() {
        super.updateEntityActionState();

        silverfishLikeBehaviour();
    }

    protected void silverfishLikeBehaviour() {
        if (!this.worldObj.isRemote) {
            int x = MathHelper.floor_double(this.posX);
            int y = MathHelper.floor_double(this.posY);
            int z = MathHelper.floor_double(this.posZ);
            if (this.allySummonCooldown > 0) {
                --this.allySummonCooldown;

                if (this.allySummonCooldown == 0) {
                    boolean flag = false;

                    for (int shiftY = 0; !flag && shiftY <= 5 && shiftY >= -5; shiftY = shiftY <= 0 ? 1 - shiftY : 0 - shiftY) {
                        for (int shiftX = 0; !flag && shiftX <= 10 && shiftX >= -10; shiftX = shiftX <= 0 ? 1 - shiftX : 0 - shiftX) {
                            for (int ShiftZ = 0; !flag && ShiftZ <= 10 && ShiftZ >= -10; ShiftZ = ShiftZ <= 0 ? 1 - ShiftZ : 0 - ShiftZ) {
                                Block block = this.worldObj.getBlock(x + shiftX, y + shiftY, z + ShiftZ);
                                int blockMeta = this.worldObj.getBlockMetadata(x + shiftX, y + shiftY, z + ShiftZ);

                                if (block.equals(GSBlock.boneBlock) && GSBlock.boneBlock.isSkullCrawlerBlock(blockMeta)) {
                                    this.worldObj.func_147480_a(x + shiftX, y + shiftY, z + ShiftZ, false);
                                    GSBlock.boneBlock.onBlockDestroyedByPlayer(this.worldObj, x + shiftX, y + shiftY, z + ShiftZ, blockMeta);

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
                int offset = this.rand.nextInt(6);
                x += Facing.offsetsXForSide[offset];
                y = MathHelper.floor_double(this.posY + 0.5D) + Facing.offsetsYForSide[offset];
                z += Facing.offsetsZForSide[offset];

                Block block = this.worldObj.getBlock(x, y, z);
                int metadata = this.worldObj.getBlockMetadata(x, y, z);

                if (GSBlock.boneBlock.equals(block) && !GSBlock.boneBlock.isSkullCrawlerBlock(metadata)) {
                    this.worldObj.setBlock(x, y, z, GSBlock.boneBlock, metadata + 2, 3);
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

    public enum SkullCrawlerType {
        skeleton,
        wither,
        zombie
    }
}
