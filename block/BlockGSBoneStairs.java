
package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.GSBlock;
import gravestone.core.Resources;
import net.minecraft.block.BlockStairs;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneStairs extends BlockStairs {

    public BlockGSBoneStairs(int id) {
        super(id, GSBlock.boneBlock, 0);
        this.setUnlocalizedName("Bone stairs");
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName(Resources.BONE_BLOCK);
    }
}
