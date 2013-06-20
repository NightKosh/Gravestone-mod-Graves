package GraveStone.structures.catacombs;

import GraveStone.structures.catacombs.components.ComponentGSCemeteryCatacombs;
import GraveStone.structures.catacombs.components.ComponentGSCemeteryCatacombsEntrance;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class StructureGSCemeteryCatacombsUnderground {

    private final int direction;
    private ComponentGSCemeteryCatacombs entrance;

    public StructureGSCemeteryCatacombsUnderground(World world, Random rand, int direction, int x, int y, int z) {
        this.direction = direction;

        prepareStructurePieces(rand, x, y, z);
        build(world, rand);
    }

    /**
     * sets up Arrays with the Structure pieces and their weights
     */
    private void prepareStructurePieces(Random rand, int x, int y, int z) {
            entrance = new ComponentGSCemeteryCatacombsEntrance(direction, rand, x, y, z);
    }

    public final void build(World world, Random rand) {
            entrance.addComponentParts(world, rand);

            LinkedList<ComponentGSCemeteryCatacombs> startComponents = new LinkedList();
            startComponents.add(entrance);

            StructureGSCemeteryCatacombsLevel level = new StructureGSCemeteryCatacombsLevel(startComponents, 1, world, rand);
            level = new StructureGSCemeteryCatacombsLevel(level.getEndParts(), 2, world, rand);
            level = new StructureGSCemeteryCatacombsLevel(level.getEndParts(), 3, world, rand);
            new StructureGSCemeteryCatacombsLevel(level.getEndParts(), 4, world, rand);
    }
}
