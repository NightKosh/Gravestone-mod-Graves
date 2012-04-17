package net.minecraft.GraveStone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockGSGraveStone extends BlockContainer {

    private static final Random rand = new Random();
    public static String[] blockNames = {"Gravestone", "Cross Gravestone", "Gravestone Plate"};
    private static Icon texture;
    public static final int GRAVE_TYPE_COUNT = 3;

    public BlockGSGraveStone(int par1) {
        super(par1, Material.rock);

        this.isBlockContainer = true;

        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("GraveStone");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(mod_GraveStone.creativeTab);
    }

    public void registerIcons(IconRegister iconRegister) {
        texture = iconRegister.registerIcon("stone");
    }

    public Icon getBlockTextureFromSideAndMetadata(int direction, int meta) {
        return texture;
    }

    /**
     * Called when the block is placed in the world
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack itemStack) {
        replaceGround(world, x, y - 1, z);

        int direction = MathHelper.floor_float(player.rotationYaw);
        if (direction < 0) {
            direction = 360 + direction;
        }

        int metadata = getMetadataBasedOnRotation(direction) + itemStack.getItemDamage();
        world.setBlockMetadataWithNotify(x, y, z, metadata, 0);


        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && itemStack.stackTagCompound != null && itemStack.stackTagCompound.hasKey("DeathText")) {
            tileEntity.setDeathText(itemStack.stackTagCompound.getString("DeathText"));
        }
    }

    private int getMetadataBasedOnRotation(int rotation) {
        if (rotation >= 315 || rotation < 45) {
            return 1;
        } else if (rotation >= 45 && rotation < 135) {
            return 2;
        } else if (rotation >= 135 && rotation < 225) {
            return 0;
        } else {
            return 3;
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return metadata;
    }

    /* Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z */
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canPlaceBlockAt(world.getBlockId(x, y - 1, z));
    }

    public boolean canPlaceBlockAt(int blockId) {
        if (blockId == Block.grass.blockID || blockId == Block.dirt.blockID
                || blockId == Block.sand.blockID || blockId == Block.gravel.blockID
                || blockId == Block.slowSand.blockID || blockId == Block.mycelium.blockID
                || blockId == Block.blockSnow.blockID) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        int graveMeta = getGraveMeta(meta);
        switch (getGraveType(meta)) {
            case 0: // STONE_VERTICAL_PLATE
                switch (graveMeta) {
                    case 0:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 0.9375F, 0.1875F);
                        break;
                    case 1:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 0.9375F, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 0.9375F, 0.875F);
                        break;
                    case 3:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 0.9375F, 0.875F);
                        break;
                }
                break;
            case 1: // STONE_CROSS
                switch (graveMeta) {
                    case 0:
                        this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 1, 0.1875F);
                        break;
                    case 1:
                        this.setBlockBounds(0.125F, 0, 0.8125F, 0.875F, 1, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0, 0.125F, 0.1875F, 1, 0.875F);
                        break;
                    case 3:
                        this.setBlockBounds(0.8125F, 0, 0.125F, 0.9375F, 1, 0.875F);
                        break;
                }
                break;
            case 2: // STONE_HORISONTAL_PLATE
                switch (graveMeta) {
                    case 0:
                        this.setBlockBounds(0.09375F, 0.0F, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case 1:
                        this.setBlockBounds(0.09375F, 0.0F, 0.0625F, 0.90625F, 0.0625F, 0.9375F);
                        break;
                    case 2:
                        this.setBlockBounds(0.0625F, 0.0F, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                    case 3:
                        this.setBlockBounds(0.0625F, 0.0F, 0.09375F, 0.9375F, 0.0625F, 0.90625F);
                        break;
                }
                break;
        }
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        player.addExhaustion(0.025F);

        if (GraveStoneConfig.silkTouchForGraves) {
            if (EnchantmentHelper.getSilkTouchModifier(player)) {
                dropBlock(world, x, y, z, metadata);
            }
        } else {
            dropBlock(world, x, y, z, metadata);
        }
    }

    private void dropBlock(World world, int x, int y, int z, int metadata) {
        ItemStack itemStack = getBlockItemStack(world, x, y, z, metadata);
        
        if (itemStack != null) {
            this.dropBlockAsItem_do(world, x, y, z, itemStack);
        }
    }
    
    private ItemStack getBlockItemStack(World world, int x, int y, int z, int metadata) {
        if (metadata % 4 != 0) {
            metadata -= metadata % 4;
        }
        
        ItemStack itemStack = this.createStackedBlock(metadata);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("DeathText", tileEntity.getDeathText());
            itemStack.setTagCompound(nbt);
        }
        
        return itemStack;
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param metadata Current metadata
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (!GraveStoneConfig.silkTouchForGraves) {
            ret.add(getBlockItemStack(world, x, y, z, metadata));
        }
        return ret;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    public boolean canSilkHarvest() {
        return GraveStoneConfig.silkTouchForGraves;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random random, int par3) {
        return GraveStoneConfig.silkTouchForGraves ? 0 : this.blockID;
    }

    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return blockID;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {
        return GraveStoneConfig.graveRenderID;
    }

    public int quantityDropped(int par1) {
        return 1;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityGSGraveStone entity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (entity != null) {
            String deathText = entity.getDeathText();

            if (!deathText.equals("")) {
                entityPlayer.sendChatToPlayer(deathText);
            }
        }

        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityGSGraveStone();
    }

    /*
     * Create grave on death
     */
    public void createOnDeath(World world, int x, int y, int z, String deathText, int direction, ItemStack[] items) {
        if (direction < 0) {
            direction = 360 + direction;
        }

        while ((world.isAirBlock(x, y - 1, z) || world.getBlockMaterial(x, y - 1, z).isLiquid() || world.getBlockMaterial(x, y - 1, z).isReplaceable()) && y > 1) {
            y--;
        }

        world.setBlock(x, y, z, GraveStoneConfig.graveStoneID, rand.nextInt(GRAVE_TYPE_COUNT) * 4 + getMetadataBasedOnRotation(direction), 0x02);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            tileEntity.setDeathText(deathText);
            tileEntity.setItems(items);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        replaceGround(world, x, y - 1, z);
    }

    /*
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    private void replaceGround(World world, int x, int y, int z) {
        int botBlockID = world.getBlockId(x, y, z);
        if (botBlockID == 2 || botBlockID == 110) {
            world.setBlock(x, y, z, Block.dirt.blockID);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        TileEntityGSGraveStone tileEntityGraveStone = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);

        if (tileEntityGraveStone != null) {
            tileEntityGraveStone.dropAllItems();
        }

        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }

    /*
     * Return grave metadata by direction 
     */
    public static int getMetaDirection(int direction) {
        switch (direction) {
            case 0: // S
                return 1;
            case 1: // W
                return 2;
            case 2: // N
                return 0;
            case 3: // E
                return 3;
            default:
                return 0;
        }
    }

    public static int getGraveType(int metadata) {
        return metadata / 4;
    }

    public static int getGraveMeta(int metadata) {
        return metadata % 4;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
        if (!world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.DOWN, true)) {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlock(x, y, z, 0, 0, 2);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata) {
        return metadata - (metadata % 4);
    }

    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list) {
        for (int j = 0; j < GRAVE_TYPE_COUNT; ++j) {
            list.add(new ItemStack(par1, 1, j * 4));
        }
    }
}
