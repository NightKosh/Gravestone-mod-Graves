package gravestone.structures.village;

import gravestone.structures.GSStructureGenerator;
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
public class VillageUndertakerGenerator implements GSStructureGenerator {
    private static VillageUndertakerGenerator instance;

    private VillageUndertakerGenerator() {
        instance = this;
    }

    public static VillageUndertakerGenerator getInstance() {
        if (instance == null) {
            return new VillageUndertakerGenerator();
        } else {
            return instance;
        }
    }

    @Override
    public boolean generate(World world, Random rand, int x, int z, double chance, boolean isCommand) {
        if (isCommand) {
            StructureBoundingBox boundingBox = ComponentGSVillageUndertaker.getBoundingBox(x, z);
            new ComponentGSVillageUndertaker(new StructureVillagePieces.Start(), 0, rand, boundingBox, 0)
                    .addComponentParts(world, rand, boundingBox);
            return true;
        }
        return false;
    }
}