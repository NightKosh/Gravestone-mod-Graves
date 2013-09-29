package gravestone.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadDog extends EntityUndeadPet {

    protected float field_70926_e;
    protected float field_70924_f;

    public EntityUndeadDog(World world) {
        super(world);
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    @Override
    protected void updateAITick() {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte) 0));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.field_70924_f = this.field_70926_e;

        if (this.func_70922_bv()) {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
        } else {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
        }

        if (this.func_70922_bv()) {
            this.numTicksToChaseTarget = 10;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float par1) {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * (float) Math.PI;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.8F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
        super.handleHealthUpdate(par1);
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return (0.55F - (float) (20 - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI;
    }

    public void func_70918_i(boolean par1) {
        if (par1) {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte) 1));
        } else {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean func_70922_bv() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }
}
