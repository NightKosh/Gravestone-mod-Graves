
package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneBlock extends Block {

    public BlockGSBoneBlock(int id) {
        super(id, Material.rock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("bone_block.normal");
        this.setHardness(2F);
        this.setResistance(2F);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.func_111022_d(Resources.BONE_BLOCK);
    }
}
