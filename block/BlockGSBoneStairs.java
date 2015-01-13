package gravestone.block;

import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.core.Resources;
import net.minecraft.block.BlockStairs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneStairs extends BlockStairs {

    public BlockGSBoneStairs() {
        super(GSBlock.boneBlock, 0);
        this.setUnlocalizedName("bone_stairs");
        this.setCreativeTab(GSTabs.otherItemsTab);
//        this.setBlockTextureName(Resources.BONE_BLOCK);
        this.setHarvestLevel("pickaxe", 0);
    }
}
