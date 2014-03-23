package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.ModGraveStone;
import gravestone.config.GraveStoneConfig;
import gravestone.tileentity.TileEntityGSCandle;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSCandle extends BlockContainer {

    public BlockGSCandle(int id) {
        super(id, Material.materialCarpet);
        this.setStepSound(Block.soundClothFootstep);
        this.setUnlocalizedName("candle");
        this.setHardness(0);
        this.setLightValue(1);
        this.setResistance(0);
        this.setCreativeTab(ModGraveStone.creativeTab);
        this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.6F, 0.6F);
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        double xPos = x + 0.5;
        double yPos = y + 0.5;
        double zPos = z + 0.5;

        world.spawnParticle("smoke", xPos, yPos, zPos, 0, 0, 0);
        world.spawnParticle("flame", xPos, yPos, zPos, 0, 0, 0);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
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
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.candleRenderID;
    }

    /**
     * Checks to see if its valid to put this block at the specified
     * coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canPlaceCandleOn(world, x, y - 1, z);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if (!this.canPlaceCandleOn(world, x, y - 1, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    /**
     * Gets if we can place a torch on a block.
     */
    private boolean canPlaceCandleOn(World world, int x, int y, int z) {
        if (world.doesBlockHaveSolidTopSurface(x, y, z)) {
            return true;
        } else {
            int blockId = world.getBlockId(x, y, z);
            return (Block.blocksList[blockId] != null && Block.blocksList[blockId].canPlaceTorchOnTop(world, x, y, z));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityGSCandle();
    }
}
