package gravestone.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
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
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

//    @Override
//    public boolean isPassable(IBlockAccess world, BlockPos pos) {
//        return false;
//    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }
    @Override
    public float getExplosionResistance(Entity entity) {
        return 18000000;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
}
