package GraveStone.structures.catacombs.components;

import GraveStone.block.BlockGSMemorial;
import GraveStone.structures.BoundingBoxHelper;
import GraveStone.structures.MemorialGenerationHelper;
import GraveStone.structures.ObjectsGenerationHelper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CreeperRoom extends CatacombsBaseComponent {

    public static final int X_LENGTH = 11;
    public static final int HEIGHT = 14;
    public static final int Z_LENGTH = 11;

    public CreeperRoom(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 3;
        y = y - HEIGHT + 6;
        yEnd = 8;
        topXEnd = 3;
        topZEnd = 10;

        switch (direction) {
            case 0:
                leftXEnd = 10;
                leftZEnd = 3;
                rightXEnd = 0;
                rightZEnd = 3;
                break;

            case 1:
                leftXEnd = 10;
                leftZEnd = 7;
                rightXEnd = 0;
                rightZEnd = 7;
                break;

            case 2:
                leftXEnd = 0;
                leftZEnd = 7;
                rightXEnd = 10;
                rightZEnd = 7;
                break;

            case 3:
                leftXEnd = 0;
                leftZEnd = 3;
                rightXEnd = 10;
                rightZEnd = 3;
        }

        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 9, 12, 9);
        buildTopPart(world, random, 8);
        // columns
        this.fillWithBlocks(world, boundingBox, 4, 1, 4, 4, 8, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 6, 4, 8, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 4, 6, 8, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 6, 8, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // bottom
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 10, 0, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // lava
        this.fillWithBlocks(world, boundingBox, 1, 1, 1, 9, 2, 9, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        // bottom walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 10, 8, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 10, 10, 8, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 1, 0, 8, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 1, 10, 8, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        return true;
    }

    private void buildTopPart(World world, Random random, int yStart) {
        // nether floor
        this.fillWithBlocks(world, boundingBox, 4, yStart, 1, 4, yStart, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, yStart, 1, 6, yStart, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 4, yStart, 6, 4, yStart, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, yStart, 6, 6, yStart, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, yStart, 4, 3, yStart, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, yStart, 4, 9, yStart, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, yStart, 6, 3, yStart, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, yStart, 6, 9, yStart, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // stoneBrick floor
        this.fillWithRandomizedBlocks(world, boundingBox, 5, yStart, 1, 5, yStart, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, yStart, 6, 5, yStart, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart, 5, 4, yStart, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, yStart, 5, 9, yStart, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart, 1, 3, yStart, 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 7, yStart, 1, 9, yStart, 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart, 2, 1, yStart, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, yStart, 2, 9, yStart, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart, 9, 3, yStart, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 7, yStart, 9, 9, yStart, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart, 7, 1, yStart, 8, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, yStart, 7, 9, yStart, 8, false, random, getCemeteryCatacombsStones());
        // spawner
        ObjectsGenerationHelper.generateSpawner(this, world, 5, yStart, 5, "Creeper");
        // nether ceiling
        int ceilingLevel = yStart + 5;
        this.fillWithBlocks(world, boundingBox, 4, ceilingLevel, 1, 4, ceilingLevel, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, ceilingLevel, 1, 6, ceilingLevel, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 4, ceilingLevel, 6, 4, ceilingLevel, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, ceilingLevel, 6, 6, ceilingLevel, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, ceilingLevel, 4, 3, ceilingLevel, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, ceilingLevel, 4, 9, ceilingLevel, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, ceilingLevel, 6, 3, ceilingLevel, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, ceilingLevel, 6, 9, ceilingLevel, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, ceilingLevel, 0, 10, ceilingLevel, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, ceilingLevel, 10, 10, ceilingLevel, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, ceilingLevel, 0, 9, ceilingLevel, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, ceilingLevel, 10, 9, ceilingLevel, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // stoneBrick ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 5, ceilingLevel, 1, 5, ceilingLevel, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, ceilingLevel, 6, 5, ceilingLevel, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, ceilingLevel, 5, 4, ceilingLevel, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, ceilingLevel, 5, 9, ceilingLevel, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, ceilingLevel, 1, 3, ceilingLevel, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 7, ceilingLevel, 1, 9, ceilingLevel, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, ceilingLevel, 7, 3, ceilingLevel, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 7, ceilingLevel, 7, 9, ceilingLevel, 9, false, random, getCemeteryCatacombsStones());
        // valueable block
        int valueableBlock = getValuableBlock(random);
        this.placeBlockAtCurrentPosition(world, valueableBlock, 0, 5, ceilingLevel, 5, boundingBox);
        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, yStart + 1, 0, 0, yStart + 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, yStart + 1, 0, 10, yStart + 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, yStart + 1, 10, 0, yStart + 4, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, yStart + 1, 10, 10, yStart + 4, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // stoneBrick walls
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart + 1, 0, 2, yStart + 4, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, yStart + 1, 0, 9, yStart + 4, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, yStart + 1, 10, 2, yStart + 4, 10, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, yStart + 1, 10, 9, yStart + 4, 10, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, yStart + 1, 1, 0, yStart + 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, yStart + 1, 8, 0, yStart + 4, 9, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, yStart + 1, 1, 10, yStart + 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, yStart + 1, 8, 10, yStart + 4, 9, false, random, getCemeteryCatacombsStones());
        // doors
        this.fillWithBlocks(world, boundingBox, 3, yStart + 1, 0, 7, yStart + 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, yStart + 1, 10, 7, yStart + 4, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, yStart + 1, 3, 0, yStart + 4, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, yStart + 1, 3, 10, yStart + 4, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithRandomizedBlocks(world, boundingBox, 4, yStart + 1, 10, 6, yStart + 3, 10, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, yStart + 1, 4, 0, yStart + 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, yStart + 1, 4, 10, yStart + 3, 6, false, random, getCemeteryCatacombsStones());
        // fill exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, yStart + 1, 12, 7, yStart + 3, 12, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, yStart + 1, 5, 0, yStart + 3, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, yStart + 1, 5, 12, yStart + 3, 7, false, random, getCemeteryCatacombsStones());
        // creeper statue
        MemorialGenerationHelper.placeMemorial(this, world, random, 5, yStart + 1, 5, BlockGSMemorial.getMetaDirection(coordBaseMode), BlockGSMemorial.getMemorialType(random, 4));
        // clear enter
        this.fillWithAir(world, boundingBox, 4, yStart + 1, 0, 6, yStart + 3, 0);
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}
