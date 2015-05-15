package gravestone.block;

import gravestone.core.GSTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSBoneSlab extends BlockSlab {

    public BlockGSBoneSlab() {
        super(Material.rock);
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("bone_slab");
        this.setHardness(2);
        this.setResistance(2);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty getVariantProperty() {
        return null;
    }

    @Override
    public Object getVariant(ItemStack stack) {
        return BlockSlab.EnumBlockHalf.values()[stack.getMetadata()];
    }

    public int getMetaFromState(IBlockState state) {
        return ((Enum) state.getValue(HALF)).ordinal();
    }

    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{HALF});
    }
}
