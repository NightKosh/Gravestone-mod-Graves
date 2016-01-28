package nightkosh.gravestone.helper;

import nightkosh.gravestone.tileentity.ISpawnerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GroupOfGravesSpawnerHelper extends Entity implements ISpawnerEntity {

    public GroupOfGravesSpawnerHelper(World worldIn) {
        super(worldIn);
    }
}
