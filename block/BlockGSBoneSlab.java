package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneSlab extends BlockSlab {

    public BlockGSBoneSlab() {
        super(false, Material.rock);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName("bone_slab");
        this.setHardness(2F);
        this.setResistance(2F);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setBlockTextureName(Resources.BONE_BLOCK);
        this.setHarvestLevel("pickaxe", 0);
    }

    /**
     * Returns the slab block name with step type.
     */
    @Override
    public String func_150002_b(int par1) {
        return getUnlocalizedName();
    }
}
