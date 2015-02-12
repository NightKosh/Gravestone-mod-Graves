package gravestone.block;

import gravestone.block.enums.EnumHauntedChest;
import gravestone.core.GSTabs;
import gravestone.tileentity.GSGraveStoneItems;
import gravestone.tileentity.TileEntityGSHauntedChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSHauntedChest extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockGSHauntedChest() {
        super(Material.wood);
        this.setStepSound(Block.soundTypeWood);
        this.setUnlocalizedName("HauntedChest");
        this.setHardness(2.5F);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setHarvestLevel("axe", 0);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 2;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
        this.setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return null;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player instanceof FakePlayer) {
            return false;
        } else {
            TileEntityGSHauntedChest te = (TileEntityGSHauntedChest) world.getTileEntity(pos);
            if (te != null) {
                te.openChest();
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSHauntedChest();
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double) (placer.rotationYaw * 4 / 360F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);

        world.setBlockState(pos, state, 3);

        TileEntityGSHauntedChest tileEntity = (TileEntityGSHauntedChest) world.getTileEntity(pos);

        if (tileEntity != null) {
            if (stack.getTagCompound() != null) {
                tileEntity.setChestType(EnumHauntedChest.getById(stack.getTagCompound().getInteger("ChestType")));
            }
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        player.addExhaustion(0.025F);
        ItemStack itemStack;
        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            itemStack = getBlockItemStack(world, pos, state);
        } else {
            itemStack = new ItemStack(Blocks.chest, 1, 0);
        }

        if (itemStack != null) {
            GSGraveStoneItems.dropItem(itemStack, world, pos);
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess access, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(Blocks.chest, 1, 0));

        return ret;
    }

    /**
     * Get chest block as item block
     */
    private ItemStack getBlockItemStack(World world, BlockPos pos, IBlockState state) {
        ItemStack itemStack = this.createStackedBlock(this.getDefaultState());
        TileEntityGSHauntedChest tileEntity = (TileEntityGSHauntedChest) world.getTileEntity(pos);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("ChestType", tileEntity.getChestType().ordinal());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return getBlockItemStack(world, pos, world.getBlockState(pos));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        for (byte i = 0; i < EnumHauntedChest.values().length; i++) {
            ItemStack stack = new ItemStack(item, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("ChestType", i);

            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING});
    }
}
