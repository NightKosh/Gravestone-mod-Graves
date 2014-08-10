package gravestone.structures.memorials;

import java.util.Random;
import gravestone.block.BlockGSMemorial;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ComponentGraveStone;
import gravestone.structures.MemorialGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, 64, z, X_LENGTH, HEIGHT, Z_LENGTH, 0);
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
        Block ground, underground;
        BiomeGenBase biom = world.getBiomeGenForCoords(getXWithOffset(0, 0), getZWithOffset(0, 0));

        if (biom.biomeID == BiomeGenBase.desert.biomeID || biom.biomeID == BiomeGenBase.desertHills.biomeID || biom.biomeID == BiomeGenBase.beach.biomeID) {
            ground = Blocks.sand;
            underground = Blocks.sand;
        } else {
            ground = Blocks.grass;
            underground = Blocks.dirt;
        }

        this.fillWithAir(world, boundingBox, 0, 0, 2, 0, 6, 2);
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 2, 0, 2, ground, ground, false);
        byte memorialType = BlockGSMemorial.getMemorialType(world, this.getXWithOffset(0, 0), this.getZWithOffset(0, 0), random, 0);
        MemorialGenerationHelper.placeMemorial(this, world, random, 1, 1, 1, BlockGSMemorial.getMetaDirection(coordBaseMode), memorialType);

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.func_151554_b(world, underground, 0, x, -1, z, boundingBox);
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
