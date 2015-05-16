package gravestone.structures;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface GSStructureGenerator {
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand);
}
