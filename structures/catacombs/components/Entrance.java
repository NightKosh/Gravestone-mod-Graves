package gravestone.structures.catacombs.components;

import java.util.Random;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.BoundingBoxHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Entrance extends CatacombsBaseComponent {

    private int stairsLength;
    private int corridorLength;
    public static final int X_LENGTH = 4;

    public Entrance(int direction, Random random, int x, int y, int z) {
        super(direction);
        stairsLength = 4 + random.nextInt(4);
        corridorLength = 2 + random.nextInt(2);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y - stairsLength * 3, z, X_LENGTH, stairsLength * 3, (stairsLength + corridorLength) * 3 + 5, xShift);

        switch (direction) {
            case 0:
                leftXEnd = 3;
                leftZEnd = (stairsLength + corridorLength) * 3;
                rightXEnd = 0;
                rightZEnd = leftZEnd;
                break;
            case 1:
                leftXEnd = 3;
                leftZEnd = (stairsLength + corridorLength) * 3 + 4;
                rightXEnd = 0;
                rightZEnd = leftZEnd;
                break;
            case 2:
                leftXEnd = 0;
                leftZEnd = (stairsLength + corridorLength) * 3 + 4;
                rightXEnd = 3;
                rightZEnd = leftZEnd;
                break;
            case 3:
                leftXEnd = 0;
                leftZEnd = (stairsLength + corridorLength) * 3;
                rightXEnd = 3;
                rightZEnd = leftZEnd;
        }
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int top = boundingBox.maxY - boundingBox.minY - 1;
        int metaBot = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 2);
        int metaTop = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 3) + 4;
        int shiftY = top;
        int shiftZ = 0;
        int i;

        for (i = 0; i < stairsLength; i++) {
            shiftZ = i * 3;
            shiftY = top - i * 3 + 1;
            this.fillWithAir(world, boundingBox, 1, shiftY, shiftZ, 2, shiftY, shiftZ + 3);
            this.fillWithAir(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 2, shiftY - 1, shiftZ + 4);
            this.fillWithAir(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 2, shiftY - 2, shiftZ + 5);
            
            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY, shiftZ, 0, shiftY + 4, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            this.fillWithBlocks(world, boundingBox, 3, shiftY, shiftZ, 3, shiftY + 4, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            
            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 2, shiftZ + 1, 0, shiftY + 3, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 3, shiftY - 2, shiftZ + 1, 3, shiftY + 3, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            
            // nether stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY, shiftZ, 2, shiftY, shiftZ, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 2, shiftY - 1, shiftZ + 1, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 2, shiftY - 2, shiftZ + 2, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
            
            // block stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY, shiftZ + 4, 2, shiftY, shiftZ + 4, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 5, 2, shiftY - 1, shiftZ + 5, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 6, 2, shiftY - 2, shiftZ + 6, Block.stairsStoneBrick.blockID, metaTop, Block.stairsStoneBrick.blockID, metaTop, false);
        }

        shiftY += 1;
        shiftZ += 3;
        int zLength = corridorLength * 3;
        this.fillWithAir(world, boundingBox, 1, shiftY - 2, shiftZ, 2, shiftY - 1, shiftZ);
        this.fillWithAir(world, boundingBox, 1, shiftY - 3, shiftZ + 1, 2, shiftY - 1, shiftZ + zLength + 4);
        this.placeBlockAtCurrentPosition(world, Block.netherBrick.blockID, 0, 0, shiftY, shiftZ, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.netherBrick.blockID, 0, 3, shiftY, shiftZ, boundingBox);
        
        // ceiling
        this.fillWithBlocks(world, boundingBox, 0, shiftY, shiftZ + 1, 3, shiftY, shiftZ + zLength + 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, shiftY - 4, shiftZ, 3, shiftY - 4, shiftZ + zLength + 4, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);

        for (int j = 0; j < corridorLength; j++) {
            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY - 3, shiftZ, 0, shiftY - 1, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            this.fillWithBlocks(world, boundingBox, 3, shiftY - 3, shiftZ, 3, shiftY - 1, shiftZ, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            
            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 3, shiftZ + 1, 0, shiftY - 1, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 3, shiftY - 3, shiftZ + 1, 3, shiftY - 1, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            
            // web
            this.randomlyPlaceBlock(world, boundingBox, random, 0.3F, shiftY - 1, shiftY - 3, shiftZ + 1, Block.web.blockID, 0);
            this.randomlyPlaceBlock(world, boundingBox, random, 0.3F, shiftY - 2, shiftY - 3, shiftZ + 2, Block.web.blockID, 0);
            shiftZ += 3;
        }

        shiftZ += 4;
        this.fillWithRandomizedBlocks(world, boundingBox, 1, shiftY - 3, shiftZ, 2, shiftY - 1, shiftZ, false, random, getCemeteryCatacombsStones());
        
        return true;
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}