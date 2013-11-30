package gravestone.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGhostlyChest extends Entity {

    public EntityGhostlyChest(World world) {
        super(world);
    }

    public EntityGhostlyChest(World world, double x, double y, double z) {
        super(world);
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        //spawnBats();
    }

    /**
     * Spawn bats
     *
     * @param world World object
     * @param random
     * @param boundingBox Bounding box
     */
    public void spawnBats() {
        EntityBat bat;
        EntityLiving livingEntity;
        int batsCount = 5;

        for (byte i = 0; i < batsCount; i++) {
            bat = new EntityBat(this.worldObj);
            bat.setLocationAndAngles(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, 0.0F, 0.0F);
            livingEntity = (EntityLiving) bat;

            if (livingEntity.getCanSpawnHere()) {
                this.worldObj.spawnEntityInWorld(bat);
            }
        }
    }

    @Override
    public boolean interactFirst(EntityPlayer layer) {
        spawnBats();
        return true;
    }
}
