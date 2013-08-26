package GraveStone.structures.catacombs.components;

import GraveStone.structures.BoundingBoxHelper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Stairs extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 16;
    public static final int Z_LENGTH = 13;

    public Stairs(int direction, Random random, int x, int y, int z) {
        super(direction);
        y = y - HEIGHT + 4;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = true;
        topXEnd = 0;
        topZEnd = Z_LENGTH;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int metaBot = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 2);
        int metaTop = this.getMetadataWithOffset(Block.stairsStoneBrick.blockID, 3) + 4;
        int top = boundingBox.maxY - boundingBox.minY - 1;
        int shiftY;
        int shiftZ;
        this.fillWithBlocks(world, boundingBox, 0, top + 1, 0, 4, top + 1, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        for (int i = 0; i < 3; i++) {
            shiftZ = i * 4;
            shiftY = top - i * 4 + 1;
            this.fillWithAir(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 1, shiftZ + 1);
            this.fillWithAir(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 2, shiftZ + 2);
            this.fillWithAir(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 3, shiftZ + 3);
            this.fillWithAir(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 4, shiftZ + 4);
            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY - 4, shiftZ, 0, shiftY, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            this.fillWithBlocks(world, boundingBox, 4, shiftY - 4, shiftZ, 4, shiftY, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 5, shiftZ + 1, 0, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 5, shiftZ + 1, 4, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 6, shiftZ + 2, 0, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 6, shiftZ + 2, 4, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 7, shiftZ + 3, 0, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 7, shiftZ + 3, 4, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());
            // nether stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 4, shiftZ, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 5, shiftZ + 1, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 6, shiftZ + 2, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 7, shiftZ + 3, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            // block stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 3, shiftY - 1, shiftZ + 1, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 3, shiftY - 2, shiftZ + 2, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 3, shiftZ + 3, 3, shiftY - 3, shiftZ + 3, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 4, shiftZ + 4, 3, shiftY - 4, shiftZ + 4, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            // web
            this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, shiftY - 3, shiftZ, Block.web.blockID, 0);
            this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, shiftY - 3, shiftZ + 1, Block.web.blockID, 0);
            this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, shiftY - 5, shiftZ + 2, Block.web.blockID, 0);
            this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, shiftY - 5, shiftZ + 3, Block.web.blockID, 0);
        }

        this.fillWithBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 4, 0, Z_LENGTH - 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 0, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 0, Z_LENGTH - 1, 4, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        return true;
    }
}
