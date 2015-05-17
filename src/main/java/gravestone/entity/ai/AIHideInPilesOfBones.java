package gravestone.entity.ai;

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
public class AIHideInPilesOfBones extends EntityAIWander {
    private EntitySkullCrawler crawler;
    private EnumFacing enumFacing;
    private boolean field_179484_c;

    public AIHideInPilesOfBones(EntitySkullCrawler crawler) {
        super(crawler, 1, 10);
        this.setMutexBits(1);
        this.crawler = crawler;
    }

    @Override
    public boolean shouldExecute() {
        if (crawler.canHideInBones() && !crawler.getHideInBonesAI().isExecuting()) {
            if (crawler.getAttackTarget() != null || !crawler.getNavigator().noPath()) {
                return false;
            } else {
                Random random = crawler.getRNG();

                if (random.nextInt(10) == 0) {
                    this.enumFacing = EnumFacing.random(random);

                    BlockPos blockPos = (new BlockPos(crawler.posX, crawler.posY + 0.5, crawler.posZ)).offset(this.enumFacing);
                    IBlockState blockState = crawler.worldObj.getBlockState(blockPos);

                    if (blockState.getBlock().isAir(crawler.worldObj, blockPos)) {
                        this.field_179484_c = true;
                        return true;
                    }
                }

                this.field_179484_c = false;
                return super.shouldExecute();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean continueExecuting() {
        return !this.field_179484_c && super.continueExecuting();
    }

    @Override
    public void startExecuting() {
        if (crawler.canHideInBones()) {
            if (!this.field_179484_c) {
                super.startExecuting();
            } else {
                World world = crawler.worldObj;
                BlockPos blockPos = (new BlockPos(crawler.posX, crawler.posY + 0.5D, crawler.posZ)).offset(this.enumFacing);
                world.setBlockState(blockPos, GSBlock.pileOfBones.getCrawlerBlockState(), 3);
                crawler.spawnExplosionParticle();
                crawler.setDead();
            }
        }
    }
}
