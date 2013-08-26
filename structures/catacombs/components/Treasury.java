package GraveStone.structures.catacombs.components;

import GraveStone.block.BlockGSGraveStone;
import GraveStone.structures.BoundingBoxHelper;
import GraveStone.structures.GraveGenerationHelper;
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
public class Treasury extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 7;

    public Treasury(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = false;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        //printCoord();
        this.fillWithAir(world, boundingBox, 1, 1, 2, 5, 3, 6);
        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 6, 0, 7, false, random, getCemeteryCatacombsStones());
        //block ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 4, 1, 6, 4, 7, false, random, getCemeteryCatacombsStones());
        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 2, 0, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 1, 2, 6, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 6, 3, 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 7, 6, 3, 7, false, random, getCemeteryCatacombsStones());
        // clear entrance
        this.fillWithAir(world, boundingBox, 2, 1, 1, 4, 3, 1);
        // block entrance
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 4, 3, 0, false, random, getCemeteryCatacombsStones());
        // nether entrance
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 2, 2, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 4, 1, 3, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 4, 3, 5, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 2, 1, 4, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 1, 2, 5, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.4F, 2, 3, 6, Block.web.blockID, 0);
        // graves
        byte graveType = BlockGSGraveStone.getGraveType(random, BlockGSGraveStone.EnumGraveType.ALL_GRAVES);
        int metaLeft = BlockGSGraveStone.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = BlockGSGraveStone.getMetaDirection(getRightItemDirection(coordBaseMode));
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 4, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 6, metaLeft, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 2, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 4, metaRight, graveType, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 6, metaRight, graveType, true);
        // TNT
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 1, 0, 3, Block.tnt.blockID, Block.tnt.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 5, 1, 0, 5, Block.tnt.blockID, Block.tnt.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.tnt.blockID, 0, 0, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 6, 0, 3, Block.tnt.blockID, Block.tnt.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 6, 0, 5, Block.tnt.blockID, Block.tnt.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.tnt.blockID, 0, 6, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 0, 6, 3, 0, 7, Block.tnt.blockID, Block.tnt.blockID, false);
        // treasury chests
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 3, false);
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 5, false);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 3, false);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 5, false);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 1, 6, false);
        // treasury column
        int blockId = getValuableBlock(random);
        this.fillWithBlocks(world, boundingBox, 3, 1, 4, 3, 3, 4, blockId, blockId, false);
        return true;
    }
}
