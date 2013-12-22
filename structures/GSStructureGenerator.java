/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravestone.structures;

import java.util.Random;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface GSStructureGenerator {
    public boolean generate(World world, Random rand, int x, int z, double chance, boolean isCommand);
}
