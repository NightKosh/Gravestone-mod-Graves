package gravestone.block;

import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.BlockState;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneStairs extends BlockStairs {

    public BlockGSBoneStairs() {
        super(new BlockState(GSBlock.boneBlock).getBaseState());
        this.setUnlocalizedName("bone_stairs");
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
    }
}
