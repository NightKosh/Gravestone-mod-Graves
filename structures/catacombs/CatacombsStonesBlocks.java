package gravestone.structures.catacombs;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.gen.structure.StructurePieceBlockSelector;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsStonesBlocks extends StructurePieceBlockSelector {

    public CatacombsStonesBlocks() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean par5) {
        if (par5) {
            this.selectedBlockId = Block.stoneBrick.blockID;
            float randFloat = random.nextFloat();

            if (randFloat < 0.2F) {
                this.selectedBlockMetaData = 2;
            } else if (randFloat < 0.4F) {
                this.selectedBlockMetaData = 0;
            } else if (randFloat < 0.45F) {
                this.selectedBlockId = Block.silverfish.blockID;
                this.selectedBlockMetaData = 2;
            } else {
                this.selectedBlockMetaData = 1;
            }
        } else {
            this.selectedBlockId = 0;
            this.selectedBlockMetaData = 0;
        }
    }
}
