package gravestone.entity.ai;

import gravestone.block.BlockGSBoneBlock;
import gravestone.core.GSBlock;
import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AIHideInBones extends EntityAIWander {
    private EntitySkullCrawler crawler;
    private EnumFacing enumFacing;
    private boolean field_179484_c;

    public AIHideInBones(EntitySkullCrawler crawler) {
        super(crawler, 1, 10);
        this.setMutexBits(1);
        this.crawler = crawler;
    }

    @Override
    public boolean shouldExecute() {
        if (crawler.canHideInBones() && crawler.getAttackTarget() != null) {
            return false;
        } else if (!crawler.getNavigator().noPath()) {
            return false;
        } else {
            Random random = crawler.getRNG();

            if (random.nextInt(10) == 0) {
                this.enumFacing = EnumFacing.random(random);
                BlockPos blockPos = (new BlockPos(crawler.posX, crawler.posY + 0.5, crawler.posZ)).offset(this.enumFacing);
                IBlockState blockState = crawler.worldObj.getBlockState(blockPos);

                if (BlockGSBoneBlock.canContainCrawler(blockState)) {
                    this.field_179484_c = true;
                    return true;
                }
            }

            this.field_179484_c = false;
            return super.shouldExecute();
        }
    }

    @Override
    public boolean continueExecuting() {
        return this.field_179484_c ? false : super.continueExecuting();
    }

    @Override
    public void startExecuting() {
        if (crawler.canHideInBones()) {
            if (!this.field_179484_c) {
                super.startExecuting();
            } else {
                World world = crawler.worldObj;
                BlockPos blockPos = (new BlockPos(crawler.posX, crawler.posY + 0.5D, crawler.posZ)).offset(this.enumFacing);
                IBlockState blockState = world.getBlockState(blockPos);
                if (BlockGSBoneBlock.canContainCrawler(blockState)) {
                    world.setBlockState(blockPos, GSBlock.boneBlock.getCrawlerBlockState(blockState), 3);
                    crawler.spawnExplosionParticle();
                    crawler.setDead();
                }
            }
        }
    }
}
