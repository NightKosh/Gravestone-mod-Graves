package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSTabs;
import gravestone.core.TimeHelper;
import gravestone.particle.EntityGreenFlameFX;
import gravestone.tileentity.TileEntityGSCandle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSCandle extends BlockContainer implements IInfusionStabiliser {

    public BlockGSCandle() {
        super(Material.carpet);
        this.setStepSound(Block.soundTypeCloth);
        this.setBlockName("candle");
        this.setHardness(0);
        this.setLightLevel(1);
        this.setResistance(0);
        this.setBlockTextureName("snow");
        this.setCreativeTab(GSTabs.otherItemsTab);
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

        long dayTime = TimeHelper.getDayTime(world);
        if (dayTime < TimeHelper.SUN_SET || dayTime > TimeHelper.SUN_RISING) {
            world.spawnParticle("flame", xPos, yPos, zPos, 0, 0, 0);
        } else {
            EntityFX entityfx = new EntityGreenFlameFX(world, xPos, yPos, zPos, 0, 0, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
        }
        world.spawnParticle("smoke", xPos, yPos, zPos, 0, 0, 0);
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canPlaceCandleOn(world, x, y - 1, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    /**
     * Gets if we can place a torch on a block.
     */
    private boolean canPlaceCandleOn(World world, int x, int y, int z) {
        if (world.doesBlockHaveSolidTopSurface(world, x, y, z)) {
            return true;
        } else {
            Block block = world.getBlock(x, y, z);
            return (block != null && block.canPlaceTorchOnTop(world, x, y, z));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSCandle();
    }

    @Override
    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return true;
    }
}
