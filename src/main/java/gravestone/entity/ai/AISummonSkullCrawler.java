package gravestone.entity.ai;

import gravestone.core.GSBlock;
import gravestone.entity.monster.EntitySkullCrawler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AISummonSkullCrawler extends EntityAIBase {
    private EntitySkullCrawler crawler;
    private int summonColdown;

    public AISummonSkullCrawler(EntitySkullCrawler crawler) {
        this.crawler = crawler;
    }

    public void resetSummonColdown() {
        if (this.summonColdown == 0) {
            this.summonColdown = 10;
        }
    }

    @Override
    public boolean shouldExecute() {
        return this.summonColdown > 0;
    }

    @Override
    public void updateTask() {
        --this.summonColdown;

        if (this.summonColdown <= 0) {
            World world = this.crawler.worldObj;
            Random random = this.crawler.getRNG();
            BlockPos blockpos = new BlockPos(this.crawler);

            for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : 0 - i) {
                for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : 0 - j) {
                    for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : 0 - k) {
                        BlockPos blockPos = blockpos.add(j, i, k);
                        IBlockState state = world.getBlockState(blockPos);

                        if ((state.getBlock().equals(GSBlock.boneBlock) && GSBlock.boneBlock.isSkullCrawlerBlock(state)) ||
                                (state.getBlock().equals(GSBlock.pileOfBones) && GSBlock.pileOfBones.isSkullCrawlerBlock(state))) {
                            if (world.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                                world.destroyBlock(blockPos, true);
                            }

                            if (random.nextBoolean()) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
