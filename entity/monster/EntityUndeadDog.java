package gravestone.entity.monster;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadDog extends EntityUndeadPet {

    protected float headRotationCourse;
    protected float headRotationCourseOld;

    public EntityUndeadDog(World world) {
        super(world);
    }

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
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.func_70922_bv()) {
            this.headRotationCourse += (1 - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0 - this.headRotationCourse) * 0.4F;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float par1) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * par1) * 0.15F * (float) Math.PI;
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
        return (0.55F - (20 - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float) Math.PI;
    }

    public boolean func_70922_bv() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }
}
