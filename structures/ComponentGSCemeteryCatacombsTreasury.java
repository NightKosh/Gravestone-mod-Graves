package net.minecraft.GraveStone.structures;

import java.util.Random;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSCemeteryCatacombsTreasury extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 7;
    private static final int[] columnBlocks = {
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockGold.blockID, Block.blockLapis.blockID, Block.blockRedstone.blockID,
        Block.blockDiamond.blockID, Block.blockEmerald.blockID
    };

    public ComponentGSCemeteryCatacombsTreasury(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 1;
        boundingBox = getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH);
        goTop = false;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
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
        byte graveType = (byte) random.nextInt(mod_GraveStone.graveStone.GRAVE_TYPE_COUNT);
        int metaLeft = mod_GraveStone.graveStone.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = mod_GraveStone.graveStone.getMetaDirection(getRightItemDirection(coordBaseMode));

        this.placeGrave(world, random, 1, 1, 2, metaLeft, graveType);
        this.placeGrave(world, random, 1, 1, 4, metaLeft, graveType);
        this.placeGrave(world, random, 1, 1, 6, metaLeft, graveType);

        this.placeGrave(world, random, 5, 1, 2, metaRight, graveType);
        this.placeGrave(world, random, 5, 1, 4, metaRight, graveType);
        this.placeGrave(world, random, 5, 1, 6, metaRight, graveType);
        
        // TNT
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 1, 0, 3, Block.tnt.blockID, Block.tnt.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 5, 1, 0, 5, Block.tnt.blockID, Block.tnt.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.tnt.blockID, 0, 0, 0, 4, boundingBox);
        
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 6, 0, 3, Block.tnt.blockID, Block.tnt.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 6, 0, 5, Block.tnt.blockID, Block.tnt.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.tnt.blockID, 0, 6, 0, 4, boundingBox);
        
        this.fillWithBlocks(world, boundingBox, 3, 0, 6, 3, 0, 7, Block.tnt.blockID, Block.tnt.blockID, false);


        // treasury chests
        generateChest(world, random, 1, 1, 3, false);
        generateChest(world, random, 1, 1, 5, false);

        generateChest(world, random, 5, 1, 3, false);
        generateChest(world, random, 5, 1, 5, false);

        generateChest(world, random, 3, 1, 6, false);

        // treasury column
        int blockId = columnBlocks[random.nextInt(columnBlocks.length)];
        this.fillWithBlocks(world, boundingBox, 3, 1, 4, 3, 3, 4, blockId, blockId, false);

        return true;
    }
}
