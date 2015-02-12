package gravestone.structures.village.memorial;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageHandlerGSMemorial implements VillagerRegistry.IVillageCreationHandler {

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieces.PieceWeight(ComponentGSVillageMemorial.class, 3, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    @Override
    public Class getComponentClass() {
        return ComponentGSVillageMemorial.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return ComponentGSVillageMemorial.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }
}
