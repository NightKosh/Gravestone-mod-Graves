package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSSkullCandle;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSkullCandle extends BlockContainer {

    public BlockGSSkullCandle(int id) {
        super(id, Material.circuits);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("Skull Candle");
        this.setHardness(1.0F);
        this.setResistance(5F);
        this.setLightValue(1);
        this.setTextureName("snow");
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
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
        return GraveStoneConfig.skullCandleRenderID;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSSkullCandle();
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        float skullRotation = entity.rotationYaw - 180 - 22.5F;
        if (skullRotation < 0) {
            skullRotation = 360 + skullRotation;
        }
        int rotation = MathHelper.ceiling_double_int(skullRotation * 8 / 360.0F);
        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);

        TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            if (stack.stackTagCompound != null) {
                if (stack.stackTagCompound.hasKey("SkullType")) {
                    tileEntity.setSkullType(stack.stackTagCompound.getByte("SkullType"));
                } else {
                    tileEntity.setSkullType((byte) 0);
                }
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and
     * wood.
     */
    @Override
    public int damageDropped(int damage) {
        return 0;
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {
        for (byte j = 0; j < EnumSkullCandle.values().length; j++) {
            ItemStack stack = new ItemStack(id, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("SkullType", j);
            stack.setTagCompound(nbt);
            list.add(stack);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        double xPos = x + 0.5F;
        double yPos = y + 0.85;
        double zPos = z + 0.5F;

        double rotation = Math.toRadians(world.getBlockMetadata(x, y, z) * 360 / 8F);
        double d = 0.07;
        double dx = -Math.sin(rotation) * d;
        double dz = Math.cos(rotation) * d;

        world.spawnParticle("smoke", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", xPos + dx, yPos, zPos + dz, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Called when the player destroys a block with an item that can harvest it.
     * (x, y, z) are the coordinates of the block and metadata is the block's
     * subtype/damage.
     */
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) {
        player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        player.addExhaustion(0.025F);
        ItemStack itemStack = getBlockItemStack(world, x, y, z);
        
        if (itemStack != null) {
            this.dropBlockAsItem_do(world, x, y, z, itemStack);
        }
    }

    /**
     * Get chest block as item block
     */
    private ItemStack getBlockItemStack(World world, int x, int y, int z) {
        ItemStack itemStack = this.createStackedBlock(0);
        TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setByte("SkullType", tileEntity.getSkullType());

            itemStack.setTagCompound(nbt);
        }

        return itemStack;
    }
    
    public static ItemStack getSkullCandle(EnumSkullCandle skullType) {
        ItemStack stack = new ItemStack(GSBlock.skullCandle, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("SkullType", (byte) skullType.ordinal());
        stack.setTagCompound(nbt);
        return stack;
    }
}
