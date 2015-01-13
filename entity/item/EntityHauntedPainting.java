package gravestone.entity.item;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityHauntedPainting extends EntityPainting {

    public EntityHauntedPainting(World world) {
        super(world);
    }

    public EntityHauntedPainting(World world, BlockPos pos, EnumFacing facing) {
        super(world, pos, facing);
    }
}
