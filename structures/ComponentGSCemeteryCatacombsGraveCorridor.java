package net.minecraft.GraveStone.structures;

import java.util.Random;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSCemeteryCatacombsGraveCorridor extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 7;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public ComponentGSCemeteryCatacombsGraveCorridor(int direction, Random random, int x, int y, int z) {
        super(direction);

        xShift = 1;
        boundingBox = getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH);

        goTop = true;
        topXEnd = 1;
        topZEnd = Z_LENGTH - 1;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random) {
        //printCoord();

        this.fillWithAir(world, boundingBox, 1, 1, 1, 5, 3, 3);
        this.fillWithAir(world, boundingBox, 2, 1, 0, 4, 3, 0);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 1, 4, 0, 3, false, random, getCemeteryCatacombsStones());
        // trap floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);
        // neter floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 4, 5, 0, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        this.fillWithBlocks(world, boundingBox, 1, 0, 1, 1, 0, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 1, 5, 0, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 0, 4, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 1, 6, 4, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls 
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 4, 1, 3, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 4, 5, 3, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // graves
        int graveType = random.nextInt(mod_GraveStone.graveStone.GRAVE_TYPE_COUNT) * 4;
        int metaLeft = graveType + mod_GraveStone.graveStone.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = graveType + mod_GraveStone.graveStone.getMetaDirection(getRightItemDirection(coordBaseMode));

        this.fillGraves(world, random, 1, 1, 1, 1, 1, 3, metaLeft);
        this.fillGraves(world, random, 5, 1, 1, 5, 1, 3, metaRight);

        // chest
        if (random.nextInt(100) < 10) {
            generateChest(world, random, 3, 1, 2, true);
        }

        // spawn bats
        spawnBats(world, random);
        
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 2, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 4, 1, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, 3, 3, Block.web.blockID, 0);


        return true;
    }
}
