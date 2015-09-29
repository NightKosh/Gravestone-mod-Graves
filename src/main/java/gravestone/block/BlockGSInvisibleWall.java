package gravestone.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSInvisibleWall extends Block {
    public BlockGSInvisibleWall() {
        super(Material.rock);
        this.setResistance(100500);
        this.setStepSound(Block.soundTypeStone);
        this.setBlockBounds(0, 0, 0, 0, 0, 0);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
        return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {
        return false;
    }

    @Override
    public float getExplosionResistance(Entity entity) {
        return 18000000;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
}
