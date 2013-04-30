package GraveStone.block;

import GraveStone.GraveStoneConfig;
import GraveStone.mod_GraveStone;
import GraveStone.tileentity.TileEntityGSGraveStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public static final String[] NAMES = {"Gravestone", "Cross", "Grave Plate", "Dog statue", "Cat statue"};
    private static Icon texture;
    public static final int GRAVE_TYPE_COUNT = 5;
    public static final byte[] GENERATED_GRAVES = {0, 1, 2};
    public static final byte[] PETS_GRAVES = {3, 4};
    public static final byte[] DOG_GRAVES = {3};
    public static final byte[] CAT_GRAVES = {4};

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

    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int direction, int meta) {
        return texture;
    }

    /**
     * Called when the block is placed in the world
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack itemStack) {
        replaceGround(world, x, y - 1, z);

        //System.out.println("onBlockPlacedBy");
        int direction = MathHelper.floor_float(player.rotationYaw);
        if (direction < 0) {
            direction = 360 + direction;
        }

        int metadata = getMetadataBasedOnRotation(direction);
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            if (itemStack.stackTagCompound != null) {
                if (itemStack.stackTagCompound.hasKey("GraveType")) {
                    tileEntity.setGraveType(itemStack.stackTagCompound.getByte("GraveType"));
                } else {
                    tileEntity.setGraveType((byte) 0);
                }

                if (itemStack.stackTagCompound.hasKey("DeathText")) {
                    tileEntity.setDeathText(itemStack.stackTagCompound.getString("DeathText"));
                }
                if (itemStack.stackTagCompound.hasKey("Age")) {
                    tileEntity.setAge(itemStack.stackTagCompound.getInteger("Age"));
                }
            }
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
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
        int meta = access.getBlockMetadata(x, y, z);
        byte graveType = 0;
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) access.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            graveType = tileEntity.getGraveType();
        }
        //System.out.println("$$$$$$ " + graveType);
        switch (graveType) {
            case 0: // STONE_VERTICAL_PLATE
                switch (meta) {
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
                switch (meta) {
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
                switch (meta) {
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
                dropBlock(world, x, y, z);
            }
        } else {
            dropBlock(world, x, y, z);
        }
    }

    private void dropBlock(World world, int x, int y, int z) {
        ItemStack itemStack = getBlockItemStack(world, x, y, z);

        if (itemStack != null) {
            this.dropBlockAsItem_do(world, x, y, z, itemStack);
        }
    }

    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", tileEntity.getGraveType());
            nbt.setString("DeathText", tileEntity.getDeathText());
            nbt.setInteger("Age", tileEntity.getAge());
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
            ret.add(getBlockItemStack(world, x, y, z));
        }
        return ret;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     * (i, j, k) are the coordinates of the block and l is the block's
     * subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly,
     * and not its normal drops.
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
     * only called by clickMiddleMouseButton , and passed to
     * inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return blockID;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
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
                if (entity.getAge() != -1) {
                    entityPlayer.sendChatToPlayer("Had lived " + entity.getAge() + " days");
                }
            }
        }

        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSGraveStone(world);
    }

    /*
     * Create grave on death
     */
    public void createOnDeath(World world, int x, int y, int z, String deathText, int direction, ItemStack[] items, int age, byte entityType) {
        if (direction < 0) {
            direction = 360 + direction;
        }

        while ((world.isAirBlock(x, y - 1, z) || world.getBlockMaterial(x, y - 1, z).isLiquid() || world.getBlockMaterial(x, y - 1, z).isReplaceable()) && y > 1) {
            y--;
        }

        world.setBlock(x, y, z, GraveStoneConfig.graveStoneID, getMetadataBasedOnRotation(direction), 0x02);

        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            tileEntity.setDeathText(deathText);
            tileEntity.setItems(items);
            switch (entityType) {
                case 0:
                    tileEntity.setGraveType(GENERATED_GRAVES[rand.nextInt(GENERATED_GRAVES.length)]);
                    break;
                case 1:
                    tileEntity.setGraveType(DOG_GRAVES[rand.nextInt(DOG_GRAVES.length)]);
                    break;
                case 2:
                    tileEntity.setGraveType(CAT_GRAVES[rand.nextInt(CAT_GRAVES.length)]);
                    break;
            }
            tileEntity.setAge(age);
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
     * ejects contained items into the world, and notifies neighbours of an
     * update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        TileEntityGSGraveStone tileEntityGraveStone = (TileEntityGSGraveStone) world.getBlockTileEntity(x, y, z);

        if (tileEntityGraveStone != null) {
            tileEntityGraveStone.dropAllItems();
        }

        super.breakBlock(world, x, y, z, par5, par6);
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

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
        if (!world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.DOWN, true)) {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlock(x, y, z, 0, 0, 2);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    public int damageDropped(int metadata) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    public void getSubBlocks(int id, CreativeTabs tabs, List list) {
        for (byte j = 0; j < GRAVE_TYPE_COUNT; j++) {
            ItemStack stack = new ItemStack(id, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", j);
            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }
}
