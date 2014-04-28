package gravestone.structures.catacombs;

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
public class CatacombsBoneBlocks extends StructureComponent.BlockSelector {

    public CatacombsBoneBlocks() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean flag) {
        if (flag) {
            this.field_151562_a = GSBlock.boneBlock;
            if (random.nextInt(5) == 0) {
                this.selectedBlockMetaData = 1;
            } else {
                this.selectedBlockMetaData = 0;
            }

            if (random.nextInt(100) < 60) {
                this.selectedBlockMetaData += 2;
            }
        } else {
            this.field_151562_a = Blocks.air;
            this.selectedBlockMetaData = 0;
        }
    }
}
