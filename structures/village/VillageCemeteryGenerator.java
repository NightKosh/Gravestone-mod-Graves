package gravestone.structures.village;

import gravestone.structures.GSStructureGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageCemeteryGenerator implements GSStructureGenerator {
    private static VillageCemeteryGenerator instance;

    private VillageCemeteryGenerator() {
        instance = this;
    }

    public static VillageCemeteryGenerator getInstance() {
        if (instance == null) {
            return new VillageCemeteryGenerator();
        } else {
            return instance;
        }
    }

    @Override
    public boolean generate(World world, Random rand, int x, int z, double chance, boolean isCommand) {
        if (isCommand) {
            StructureBoundingBox boundingBox = ComponentGSVillageCemetery.getBoundingBox(x, z);
            new ComponentGSVillageCemetery(new StructureVillagePieces.Start(), 0, rand, boundingBox, EnumFacing.SOUTH)
                    .addComponentParts(world, rand, boundingBox);
            return true;
        }
        return false;
    }
}
