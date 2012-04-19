
package net.minecraft.GraveStone.structures;

import java.util.Random;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSCemeteryCatacombsBridge extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 13;
    public static final int HEIGHT = 14;
    public static final int Z_LENGTH = 7;
    

    public ComponentGSCemeteryCatacombsBridge(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 4;
        y = y - HEIGHT + 6;
        boundingBox = getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH);
        
        yEnd = 8;
        topXEnd = 4;
        topZEnd = Z_LENGTH;
        
        goTop = true;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random) {
        //printCoord();

        this.fillWithAir(world, boundingBox, 3, 3, 1, 9, 12, 6);
        this.fillWithAir(world, boundingBox, 1, 9, 1, 2, 10, 6);
        this.fillWithAir(world, boundingBox, 10, 9, 1, 11, 10, 6);

        // neter floor and ceiling
        this.fillWithBlocks(world, boundingBox, 2, 0, 0, 10, 0, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 2, 13, 0, 10, 13, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // nether walls 
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 9, 12, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 7, 9, 12, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 2, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 11, 0, 2, 12, 7, false, random, getCemeteryCatacombsStones());
        
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 0, 10, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 11, 0, 10, 12, 7, false, random, getCemeteryCatacombsStones());
        
        // graves floor & ceilng
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 8, 0, 1, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 11, 0, 1, 11, 7, false, random, getCemeteryCatacombsStones());
        
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 8, 0, 11, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 11, 0, 11, 11, 7, false, random, getCemeteryCatacombsStones());
        
        // graves walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 9, 0, 0, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 9, 0, 12, 10, 7, false, random, getCemeteryCatacombsStones());
        
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 0, 2, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 0, 2, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 7, 2, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 7, 2, 10, 7, false, random, getCemeteryCatacombsStones());
        
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 0, 11, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 0, 11, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 7, 11, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 7, 11, 10, 7, false, random, getCemeteryCatacombsStones());
        
        // graves
        byte graveType = (byte) random.nextInt(mod_GraveStone.graveStone.GRAVE_TYPE_COUNT);
        int metaLeft = mod_GraveStone.graveStone.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = mod_GraveStone.graveStone.getMetaDirection(getRightItemDirection(coordBaseMode));

        this.fillGraves(world, random, 1, 9, 1, 1, 9, 6, metaLeft, graveType);
        this.fillGraves(world, random, 11, 9, 1, 11, 9, 6, metaRight, graveType);
        
        // lava
        this.fillWithBlocks(world, boundingBox, 3, 1, 1, 9, 2, 6, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        
        // bridge
        this.fillWithMetadataBlocks(world, boundingBox, 6, 8, 1, 6, 8, 6, Block.stoneSingleSlab.blockID, 14, Block.stoneSingleSlab.blockID, 14, false);
        
        if (random.nextInt(10) < 4 ) {
            this.placeBlockAtCurrentPosition(world, 0, 0, 6, 8, 5, boundingBox);
        }
        
        // block exit wall
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 9, 7, 7, 11, 7, false, random, getCemeteryCatacombsStones());
        
        this.fillWithAir(world, boundingBox, 5, 9, 0, 7, 11, 0);
        
        // spawn bats
        spawnBats(world, random);
        
        return true;
    }
}

