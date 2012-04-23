package GraveStone.block;

import GraveStone.GraveStoneConfig;
import GraveStone.mod_GraveStone;
import GraveStone.tileentity.TileEntityGSMemorial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockGSMemorial extends BlockContainer {

    private static final Random rand = new Random();
    public static String[] blockNames = {"Cross Memorial", "Obelisk"};
    private static Icon texture;
    public static final int MEMORIAL_TYPE_COUNT = 2;

    public BlockGSMemorial(int par1) {
        super(par1, Material.rock);

        this.isBlockContainer = true;

        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Memorial");
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
        int direction = MathHelper.floor_float(player.rotationYaw);
        if (direction < 0) {
            direction = 360 + direction;
        }

        int metadata = getMetadataBasedOnRotation(direction);
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);

        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            if (itemStack.stackTagCompound != null) {
                if (itemStack.stackTagCompound.hasKey("DeathText")) {
                    tileEntity.setDeathText(itemStack.stackTagCompound.getString("DeathText"));
                }
                if (itemStack.stackTagCompound.hasKey("GraveType")) {
                    tileEntity.setGraveType(itemStack.stackTagCompound.getByte("GraveType"));
                } else {
                    tileEntity.setGraveType((byte) 0);
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
        for (byte i = 0; i < 5; i++) {
            for (byte j = -1; j < 2; j++) {
                for (byte k = -1; k < 2; k++) {
                    if (world.getBlockId(x + k, y + i, z + j) != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return blockID;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
        int meta = access.getBlockMetadata(x, y, z);
        byte memorialType = 0;
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) access.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            memorialType = tileEntity.getGraveType();
        }
        switch (memorialType) {
            case 0: // STONE_CROSS
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
        }
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 2);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random random, int par3) {
        return 0;
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
        return GraveStoneConfig.memorialRenderID;
    }

    public int quantityDropped(int par1) {
        return 1;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityGSMemorial entity = (TileEntityGSMemorial) world.getBlockTileEntity(x, y, z);
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
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSMemorial(world);
    }

    /*
     * Return memorial metadata by direction 
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
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte j = 0; j < MEMORIAL_TYPE_COUNT; j++) {
            ItemStack stack = new ItemStack(id, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("GraveType", j);
            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        player.addExhaustion(0.025F);

        if (EnchantmentHelper.getSilkTouchModifier(player)) {
            ItemStack itemStack = getBlockItemStack(world, x, y, z);

            if (itemStack != null) {
                this.dropBlockAsItem_do(world, x, y, z, itemStack);
            }
        }
    }

    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);

        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("DeathText", tileEntity.getDeathText());
            nbt.setByte("GraveType", tileEntity.getGraveType());
            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }
}
