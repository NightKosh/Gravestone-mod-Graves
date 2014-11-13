package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumHauntedChest;
import gravestone.core.GSTabs;
import gravestone.tileentity.TileEntityGSHauntedChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

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

    public BlockGSHauntedChest() {
        super(Material.wood);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockName("HauntedChest");
        this.setHardness(2.5F);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockTextureName("planks_oak");
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

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 22;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int par2, int par3, int par4) {
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return null;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int metadata) {
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player instanceof FakePlayer) {
            return false;
        } else {
            TileEntityGSHauntedChest te = (TileEntityGSHauntedChest) world.getTileEntity(x, y, z);
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack stack) {
        int direction = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metadata;
        switch (direction) {
            case 0:
                metadata = 2;
                break;
            case 1:
                metadata = 5;
                break;
            case 2:
                metadata = 3;
                break;
            case 3:
            default:
                metadata = 4;
                break;
        }

        world.setBlockMetadataWithNotify(x, y, z, metadata, 3);

        TileEntityGSHauntedChest tileEntity = (TileEntityGSHauntedChest) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (stack.stackTagCompound != null) {
                tileEntity.setChestType(EnumHauntedChest.getById(stack.stackTagCompound.getByte("ChestType")));
            }
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     * (x, y, z) are the coordinates of the block and metadata is the block's subtype/damage.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addExhaustion(0.025F);
        ItemStack itemStack;
        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            itemStack = getBlockItemStack(world, x, y, z);
        } else {
            itemStack = new ItemStack(Blocks.chest, 1, 0);
        }

        if (itemStack != null) {
            this.dropBlockAsItem(world, x, y, z, itemStack);
        }
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world    The current world
     * @param x        X Position
     * @param y        Y Position
     * @param z        Z Position
     * @param metadata Current metadata
     * @param fortune  Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        ret.add(new ItemStack(Blocks.chest, 1, 0));

        return ret;
    }

    /**
     * Get chest block as item block
     */
    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);
        TileEntityGSHauntedChest tileEntity = (TileEntityGSHauntedChest) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("ChestType", (byte) tileEntity.getChestType().ordinal());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return getBlockItemStack(world, x, y, z);
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
            nbt.setByte("ChestType", i);

            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }
}
