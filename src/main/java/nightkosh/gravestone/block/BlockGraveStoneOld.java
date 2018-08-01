package nightkosh.gravestone.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGraveStoneOld extends BlockContainer {
//TODO remove !!!!!!!!!!!!!!!!!!!!!!!
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockGraveStoneOld() {
        super(Material.ROCK);
        this.setRegistryName("GSGraveStone");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGraveStone(world);
    }
}

