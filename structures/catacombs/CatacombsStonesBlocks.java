package gravestone.structures.catacombs;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsStonesBlocks extends StructureComponent.BlockSelector {

    public CatacombsStonesBlocks() {
    }

    /**
     * Picks Block Ids and Metadata (Silverfish)
     */
    @Override
    public void selectBlocks(Random random, int par2, int par3, int par4, boolean par5) {
        //TODO
//        if (par5) {
//            this.field_151562_a = Blocks.stonebrick;
//            float randFloat = random.nextFloat();
//
//            if (randFloat < 0.2F) {
//                this.selectedBlockMetaData = 2;
//            } else if (randFloat < 0.4F) {
//                this.selectedBlockMetaData = 0;
//            } else if (randFloat < 0.45F) {
//                this.field_151562_a = Blocks.monster_egg;
//                this.selectedBlockMetaData = 2;
//            } else {
//                this.selectedBlockMetaData = 1;
//            }
//        } else {
//            this.field_151562_a = Blocks.air;
//            this.selectedBlockMetaData = 0;
//        }
    }
}
