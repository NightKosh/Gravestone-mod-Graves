package gravestone.block;

import gravestone.config.GraveStoneConfig;
import gravestone.core.GSTabs;
import gravestone.core.TimeHelper;
import gravestone.particle.EntityGreenFlameFX;
import gravestone.tileentity.TileEntityGSCandle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
//TODO
//public class BlockGSCandle extends BlockContainer implements IInfusionStabiliser {
public class BlockGSCandle extends BlockContainer {

    public BlockGSCandle() {
        super(Material.carpet);
        this.setStepSound(Block.soundTypeCloth);
        this.setUnlocalizedName("candle");
        this.setHardness(0);
        this.setLightLevel(1);
        this.setResistance(0);
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.6F, 0.6F);
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random) {
        double xPos = pos.getX() + 0.5;
        double yPos = pos.getY() + 0.5;
        double zPos = pos.getZ() + 0.5;

        long dayTime = TimeHelper.getDayTime(world);
        if (dayTime < TimeHelper.SUN_SET || dayTime > TimeHelper.SUN_RISING) {
            world.spawnParticle(EnumParticleTypes.FLAME, xPos, yPos, zPos, 0, 0, 0);
        } else {
            EntityFX entityfx = new EntityGreenFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
        }
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xPos, yPos, zPos, 0, 0, 0);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return null;
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
//    @Override
//    public boolean renderAsNormalBlock() {
//        return false;
//    }

    /**
     * The type of render function that is called for this block
     */
    //TODO
//    @Override
//    public int getRenderType() {
//        return GraveStoneConfig.candleRenderID;
//    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return canPlaceCandleOn(world, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
        if (!this.canPlaceCandleOn(world, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()))) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    /**
     * Gets if we can place a torch on a block.
     */
    private boolean canPlaceCandleOn(World world, BlockPos pos) {
        if (world.doesBlockHaveSolidTopSurface(world, pos)) {
            return true;
        } else {
            Block block = world.getBlockState(pos).getBlock();
            return (block != null && block.canPlaceTorchOnTop(world, pos));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSCandle();
    }

    //TODO
//    @Override
//    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
//        return true;
//    }
}
