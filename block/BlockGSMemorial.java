package GraveStone.block;

import GraveStone.GraveStoneConfig;
import GraveStone.ModGraveStone;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockGSMemorial extends BlockContainer {

    public static final String[] NAMES = {
        "Cross Memorial", "Obelisk",
        "Steve statue", "Villager statue", "Angel statue",
        "Dog statue", "Cat statue", "Creeper Statue"
    };
    public static final byte[] GENERATED_MEMORIALS = {0, 1, 2, 3, 4, 5, 6};
    public static final byte[] PETS_MEMORIALS = {5, 6};
    public static final byte[] DOG_MEMORIALS = {5};
    public static final byte[] CAT_MEMORIALS = {6};
    public static final byte[] CREEPER_MEMORIALS = {7};

    public BlockGSMemorial(int par1) {
        super(par1, Material.rock);

        this.isBlockContainer = true;

        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Memorial");
        this.setHardness(4.5F);
        this.setResistance(5F);
        this.setCreativeTab(ModGraveStone.creativeTab);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("stone");
    }

    /**
     * Called when the block is placed in the world
     */
    @Override
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

    /**
     * only called by clickMiddleMouseButton , and passed to
     * inventory.setCurrentItem (along with isCreative)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return blockID;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
        byte memorialType = 0;
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) access.getBlockTileEntity(x, y, z);
        if (tileEntity != null) {
            memorialType = tileEntity.getGraveType();
        }
        switch (memorialType) {
            case 0: // STONE_CROSS
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
            case 1: // STONE_CROSS
                this.setBlockBounds(-1, 0, -1, 2, 5, 2);
                break;
            case 2: // STEVE_STATUE
            case 3: // VILLAGER_STATUE
            case 4: // ANGEL_STATUE
                this.setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 2.5F, 0.9375F);
                break;
            case 5: // DOG_STATUE
            case 6: // CAT_STAUTE
                this.setBlockBounds(0.125F, 0, 0.125F, 0.875F, 2, 0.875F);
                break;
            case 7: // CREEPER_STATUE
                this.setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 2, 0.9375F);
                break;

        }
    }


    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 2);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly,
     * and not its normal drops.
     */
    @Override
    public boolean canSilkHarvest() {
        return true;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random random, int par3) {
        return 0;
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
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.memorialRenderID;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            TileEntityGSMemorial entity = (TileEntityGSMemorial) world.getBlockTileEntity(x, y, z);
            if (entity != null) {
                String deathText = entity.getDeathText();

                if (!deathText.equals("")) {
                    entityPlayer.sendChatToPlayer(deathText);
                }
            }
        }
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
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
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int metadata) {
        return 0;
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte j = 0; j < NAMES.length; j++) {
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
    @Override
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
     * Called when the player destroys a block with an item that can harvest it.
     * (i, j, k) are the coordinates of the block and l is the block's
     * subtype/damage.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Return random memorial type
     * memorialTypetype - type of memorial
     * 0 - all memorials(20% for pets graves), except creeper
     * 1 - only pets memorials
     * 2 - only dogs memorials
     * 3 - only cats memorials
     */
    public static byte getMemorialType(Random random, int memorialType) {
        switch (memorialType) {
            default:
            case 0:
                return GENERATED_MEMORIALS[random.nextInt(GENERATED_MEMORIALS.length)];
            case 1:
                return PETS_MEMORIALS[random.nextInt(PETS_MEMORIALS.length)];
            case 2:
                return DOG_MEMORIALS[random.nextInt(DOG_MEMORIALS.length)];
            case 3:
                return CAT_MEMORIALS[random.nextInt(CAT_MEMORIALS.length)];
            case 4:
                return CREEPER_MEMORIALS[random.nextInt(CREEPER_MEMORIALS.length)];
        }
    }

    /*
     * Drop sword as item
     */
    public void dropCreeperMemorial(World world, int x, int y, int z) {
        byte memorialType = BlockGSMemorial.getMemorialType(new Random(), 4);
        ItemStack itemStack = new ItemStack(ModGraveStone.memorial);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", memorialType);

        itemStack.setTagCompound(nbt);
        this.dropBlockAsItem_do(world, x, y, z, itemStack);
    }
}
