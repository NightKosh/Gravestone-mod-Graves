package gravestone.structures;

import gravestone.block.BlockGSMemorial;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MemorialGenerationHelper {

    private MemorialGenerationHelper() {
    }

    public static void placeMemorial(ComponentGraveStone component, World world, Random random, int x, int y, int z, EnumFacing facing, int memorialType) {
        IBlockState memorialState = GSBlock.memorial.getDefaultState().withProperty(BlockGSMemorial.FACING, facing);
        component.placeBlockAtCurrentPosition(world, memorialState, x, y, z, component.getBoundingBox());
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(new BlockPos(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z)));

        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
            tileEntity.setMemorialContent(random);
        }
    }

    public static void placeMemorial(ComponentGraveStone component, World world, Random random, int x, int y, int z, IBlockState memorialState, int memorialType) {
        component.placeBlockAtCurrentPosition(world, memorialState, x, y, z, component.getBoundingBox());
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(new BlockPos(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z)));

        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
            tileEntity.setMemorialContent(random);
        }
    }
}
