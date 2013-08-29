package gravestone.structures.memorials;

import java.util.Random;
import gravestone.block.BlockGSMemorial;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.MemorialGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSMemorial extends ComponentGraveStone {

    public static final int X_LENGTH = 3;
    public static final int HEIGHT = 7;
    public static final int Z_LENGTH = 3;

    public ComponentGSMemorial(int direction, Random random, int x, int z) {
        super(direction);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x + (16 - X_LENGTH) / 2, 64, z + (16 - Z_LENGTH) / 2, X_LENGTH, HEIGHT, Z_LENGTH, 0);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = BoundingBoxHelper.getAverageGroundLevel(world, boundingBox);

        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + HEIGHT - 1, 0);
        int groundID, undergroundID;
        BiomeGenBase biom = world.getBiomeGenForCoords(getXWithOffset(0, 0), getZWithOffset(0, 0));

        if (biom.biomeID == BiomeGenBase.desert.biomeID || biom.biomeID == BiomeGenBase.desertHills.biomeID || biom.biomeID == BiomeGenBase.beach.biomeID) {
            groundID = Block.sand.blockID;
            undergroundID = Block.sand.blockID;
        } else {
            groundID = Block.grass.blockID;
            undergroundID = Block.dirt.blockID;
        }

        this.fillWithAir(world, boundingBox, 0, 0, 2, 0, 6, 2);
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 2, 0, 2, groundID, groundID, false);
        byte memorialType = BlockGSMemorial.getMemorialType(random, 0);
        MemorialGenerationHelper.placeMemorial(this, world, random, 1, 1, 1, BlockGSMemorial.getMetaDirection(coordBaseMode), memorialType);

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.fillCurrentPositionBlocksDownwards(world, undergroundID, 0, x, -1, z, boundingBox);
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, boundingBox);
            }
        }

        return true;
    }
}
