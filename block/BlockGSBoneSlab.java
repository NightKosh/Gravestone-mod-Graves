package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneSlab extends BlockHalfSlab {

    public BlockGSBoneSlab(int id) {
        super(id, false, Material.rock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("bone_slab");
        this.setHardness(2F);
        this.setResistance(2F);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setTextureName(Resources.BONE_BLOCK);
    }

    /**
     * Returns the slab block name with step type.
     */
    @Override
    public String getFullSlabName(int par1) {
        return getUnlocalizedName();
    }
}
