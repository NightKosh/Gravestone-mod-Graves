package gravestone.structures.village;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageHandlerGSCemetery implements VillagerRegistry.IVillageCreationHandler {

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
        return new StructureVillagePieces.PieceWeight(ComponentGSVillageCemetery.class, 4, MathHelper.getRandomIntegerInRange(random, 0, 1));
    }

    @Override
    public Class getComponentClass() {
        return ComponentGSVillageCemetery.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
        return ComponentGSVillageCemetery.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
    }
}
