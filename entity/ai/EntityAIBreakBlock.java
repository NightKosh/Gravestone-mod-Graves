/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravestone.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityAIBreakBlock extends EntityAIBlockInteract {

    private boolean isBlockBroken = false;
    
    public EntityAIBreakBlock(EntityLiving entity, Block block) {
        super(entity, block);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return super.shouldExecute() && this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        double d0 = this.theEntity.getDistanceSq((double) this.entityPosX, (double) this.entityPosY, (double) this.entityPosZ);
        return d0 < 4.0D;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.theEntity.worldObj.destroyBlockInWorldPartially(this.theEntity.entityId, this.entityPosX, this.entityPosY, this.entityPosZ, -1);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();

        if (!isBlockBroken && this.theEntity.worldObj.difficultySetting >= 2) {
            isBlockBroken = true;
            this.targetBlock.dropBlockAsItem(this.theEntity.worldObj, this.entityPosX, this.entityPosY, this.entityPosZ, 0, 0);
            this.theEntity.worldObj.setBlockToAir(this.entityPosX, this.entityPosY, this.entityPosZ);
            this.theEntity.worldObj.playAuxSFX(1012, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
            this.theEntity.worldObj.playAuxSFX(2001, this.entityPosX, this.entityPosY, this.entityPosZ, this.targetBlock.blockID);
        }
    }
}
