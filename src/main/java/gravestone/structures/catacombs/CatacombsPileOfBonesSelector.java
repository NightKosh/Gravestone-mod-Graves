package gravestone.structures.catacombs;

import gravestone.block.BlockGSPileOfBones;
import gravestone.block.enums.EnumPileOfBones;
import gravestone.core.GSBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsPileOfBonesSelector extends StructureComponent.BlockSelector {

    protected static final float PILE_OF_BONES_GENERATION_CHANCE = 0.35F;

    public CatacombsPileOfBonesSelector() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean flag) {
        if (flag && random.nextFloat() <= PILE_OF_BONES_GENERATION_CHANCE) {
            if (random.nextInt(30) == 0) {
                if (random.nextBoolean()) {
                    this.field_151562_a = GSBlock.pileOfBones.getDefaultState().withProperty(BlockGSPileOfBones.VARIANT, EnumPileOfBones.PILE_OF_BONES_WITH_SKULL_CRAWLER);
                } else {
                    this.field_151562_a = GSBlock.pileOfBones.getDefaultState().withProperty(BlockGSPileOfBones.VARIANT, EnumPileOfBones.PILE_OF_BONES_WITH_SKULL);
                }
            } else {
                this.field_151562_a = GSBlock.pileOfBones.getDefaultState().withProperty(BlockGSPileOfBones.VARIANT, EnumPileOfBones.PILE_OF_BONES);
            }
        } else {
            this.field_151562_a = Blocks.air.getDefaultState();
        }
    }
}