package gravestone.entity.item;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGhostPainting extends EntityPainting {

    public EntityGhostPainting(World world) {
        super(world);
    }

    public EntityGhostPainting(World world, int par2, int par3, int par4, int par5) {
        super(world, par2, par3, par4, par5);
    }
}
