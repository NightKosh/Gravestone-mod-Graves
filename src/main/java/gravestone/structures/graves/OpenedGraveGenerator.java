package gravestone.structures.graves;

import gravestone.config.GSConfig;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class OpenedGraveGenerator extends SingleGraveGenerator {
    private static OpenedGraveGenerator instance = new OpenedGraveGenerator();

    private OpenedGraveGenerator() {
    }

    public static OpenedGraveGenerator getInstance() {
        return instance;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (!isCommand) {
            x += 7;
            z += 7;
        }
        if (isCommand || (GSConfig.generateSingleGraves && canSpawnStructureAtCoords(world, x, z, chance))) {
            new ComponentGSOpenedGrave(0, direction, rand, x, z).addComponentParts(world, rand);
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }

        return false;
    }
}
