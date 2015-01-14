package gravestone.entity.ai;

import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Blocks;
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
                BlockPos blockpos = (new BlockPos(crawler.posX, crawler.posY + 0.5D, crawler.posZ)).offset(this.enumFacing);
                IBlockState iblockstate = crawler.worldObj.getBlockState(blockpos);

                //TODO
                if (BlockSilverfish.canContainSilverfish(iblockstate)) {
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
                BlockPos blockpos = (new BlockPos(crawler.posX, crawler.posY + 0.5D, crawler.posZ)).offset(this.enumFacing);
                IBlockState iblockstate = world.getBlockState(blockpos);

//            int metadata = world.getBlockMetadata(x, y, z);

//            if (GSBlock.boneBlock.equals(iblockstate.getBlock()) && !GSBlock.boneBlock.isSkullCrawlerBlock(metadata)) {
//                world.setBlock(x, y, z, GSBlock.boneBlock, metadata + 2, 3);
                if (BlockSilverfish.canContainSilverfish(iblockstate)) {
                    world.setBlockState(blockpos, Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.forModelBlock(iblockstate)), 3);
                    crawler.spawnExplosionParticle();
                    crawler.setDead();
                }
            }
        }
    }
}
