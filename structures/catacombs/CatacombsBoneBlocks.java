
package gravestone.structures.catacombs;

import gravestone.core.GSBlock;
import java.util.Random;
import net.minecraft.world.gen.structure.StructurePieceBlockSelector;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsBoneBlocks extends StructurePieceBlockSelector {

    public CatacombsBoneBlocks() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean flag) {
        if (flag) {
            this.selectedBlockId = GSBlock.boneBlock.blockID;
            if (random.nextInt(5) == 0) {
                this.selectedBlockMetaData = 1;
            } else {
                this.selectedBlockMetaData = 0;
            }
            
            if (random.nextInt(100) < 60) {
                this.selectedBlockMetaData += 2;
            }
        } else {
            this.selectedBlockId = 0;
            this.selectedBlockMetaData = 0;
        }
    }
}
