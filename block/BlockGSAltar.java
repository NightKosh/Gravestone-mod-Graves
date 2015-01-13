package gravestone.block;

import gravestone.ModGraveStone;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSAltar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSAltar extends BlockContainer {
//
//    @SideOnly(Side.CLIENT)
//    private IIcon topTexture;
//    @SideOnly(Side.CLIENT)
//    private IIcon bottomTexture;

    public BlockGSAltar() {
        super(Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.setLightOpacity(0);
        this.setUnlocalizedName("Altar");
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getCurrentEquippedItem();

        TileEntityGSAltar tileEntity = (TileEntityGSAltar) world.getTileEntity(pos);
        if (tileEntity != null && !player.isSneaking()) {
//            if (tileEntity.hasCorpse()) {
//                if (!world.isRemote) {
//                    tileEntity.dropCorpse();
//                }
//            } else {
//                if (stack != null && stack.getItem() instanceof ItemGSCorpse) {
//                    ItemStack corpse = stack.copy();
//                    corpse.stackSize = 1;
//                    tileEntity.setCorpse(corpse);
//                    if (!player.capabilities.isCreativeMode) {
//                        stack.stackSize--;
//                    }
//                }
//            }
            player.openGui(ModGraveStone.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }
//
//    /**
//     * If this block doesn't render as an ordinary block it will return False
//     * (examples: signs, buttons, stairs, etc)
//     */
//    @Override
//    public boolean renderAsNormalBlock() {
//        return false;
//    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

//    /**
//     * From the specified side and block metadata retrieves the blocks texture.
//     */
//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getIcon(int side, int metadata) {
//        return side == 0 ? this.bottomTexture : (side == 1 ? this.topTexture : this.blockIcon);
//    }
//
//    /**
//     * When this method is called, your block should register all the icons it
//     * needs with the given IconRegister. This is the only chance you get to
//     * register icons.
//     */
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister register) {
//        this.blockIcon = register.registerIcon(Resources.ALTAR_SIDE);
//        this.topTexture = register.registerIcon(Resources.ALTAR_TOP);
//        this.bottomTexture = register.registerIcon(Resources.BONE_BLOCK);
//    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGSAltar();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityGSAltar tileEntity = (TileEntityGSAltar) world.getTileEntity(pos);

        if (tileEntity != null) {
            tileEntity.dropCorpse();
        }

        super.breakBlock(world, pos, state);
    }
}
