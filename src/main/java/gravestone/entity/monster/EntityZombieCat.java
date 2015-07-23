package gravestone.entity.monster;

import gravestone.core.Resources;
import gravestone.entity.ai.EntityAIAttackLivingHorse;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityZombieCat extends EntityUndeadCat {

    private static final byte CAT_TYPES = 4;

    protected boolean isGreen = false;

    public EntityZombieCat(World world) {
        this(world, world.rand.nextBoolean());
    }

    public EntityZombieCat(World world, boolean isGreen) {
        super(world);

        this.isGreen = isGreen;
        texture = (isGreen) ? Resources.GREEN_ZOMBIE_OZELOT : Resources.ZOMBIE_OZELOT;

        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1));
        this.tasks.addTask(6, new EntityAIWander(this, 1));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityWolf.class, 1, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityOcelot.class, 1, true));
        this.tasks.addTask(4, new EntityAIAttackLivingHorse(this, 1, false));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOcelot.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChicken.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityHorse.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10); // max health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D); // movespeed
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture() {
        if (this.isGreen) {
            switch (this.getSkin()) {
                case 1:
                    return Resources.GREEN_ZOMBIE_CAT_BLACK;
                case 2:
                    return Resources.GREEN_ZOMBIE_CAT_RED;
                case 3:
                    return Resources.GREEN_ZOMBIE_CAT_SIAMESE;
                case 0:
                default:
                    return Resources.GREEN_ZOMBIE_OZELOT;
            }
        } else {
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
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);

        nbt.setInteger("ZombieCatType", this.getSkin());

        nbt.setBoolean("IsGreen", this.isGreen);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);

        this.setSkin(nbt.getInteger("ZombieCatType"));

        if (nbt.hasKey("IsGreen")) {
            this.isGreen = nbt.getBoolean("IsGreen");
        }
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
    protected void playStepSound(BlockPos pos, Block block) {
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
    protected Item getDropItem() {
        return Items.rotten_flesh;
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

        if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
            spawnZombieMob(entityLiving);
        }
    }

    @Override
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData data) {
        this.setSkin(new Random().nextInt(CAT_TYPES));
        return super.func_180482_a(difficulty, data);
    }
}
