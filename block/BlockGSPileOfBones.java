package gravestone.block;

import gravestone.block.enums.EnumPileOfBones;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSPileOfBones;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSPileOfBones extends BlockContainer {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumPileOfBones.class);

    public BlockGSPileOfBones() {
        super(Material.circuits);
        this.setStepSound(Block.soundTypeStone);
        this.setUnlocalizedName("pile of bones");
        this.setHardness(0.1F);
        this.setResistance(0);
//        this.setBlockTextureName("snow");
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockBounds(0.1F, 0, 0.1F, 0.9F, 0.2F, 0.9F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityGSPileOfBones();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

//    @Override
//    public boolean renderAsNormalBlock() {
//        return false;
//    }

    //TODO
//    @Override
//    public int getRenderType() {
//        return GraveStoneConfig.pileOfBonesRenderID;
//    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Items.bone;
    }

    @Override
    public int quantityDropped(Random random) {
        return 4;
    }

    @Override
    public int damageDropped(IBlockState state) {
        int metadata = ((Enum) state.getValue(VARIANT)).ordinal();
        return metadata;
    }

    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(GSBlock.pileOfBones, 1, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        // TODO setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);
        world.setBlockState(pos, state, 2);

        TileEntityGSPileOfBones te = (TileEntityGSPileOfBones) world.getTileEntity(pos);
        if (te != null) {
            te.setDirection((byte) (MathHelper.floor_double((double) (entity.rotationYaw * 4F / 360F) + 0.5D) & 3));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte i = 0; i < EnumPileOfBones.values().length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return world.doesBlockHaveSolidTopSurface(world, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
        if (!canPlaceBlockAt(world, pos)) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }
}
