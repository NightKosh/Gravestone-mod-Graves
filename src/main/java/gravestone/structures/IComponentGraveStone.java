package gravestone.structures;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IComponentGraveStone {
    public void placeBlockAtCurrentPosition(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox);

    public StructureBoundingBox getBoundingBox();

    public int getXWithOffset(int x, int z);

    public int getYWithOffset(int y);

    public int getZWithOffset(int x, int z);
}
