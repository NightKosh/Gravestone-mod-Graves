package GraveStone.structures;

import java.util.Random;
import GraveStone.GraveStoneConfig;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSCemeteryCatacombsSpidersCorridor extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 13;

    public ComponentGSCemeteryCatacombsSpidersCorridor(int direction, Random random, int x, int y, int z) {
        super(direction);
        boundingBox = getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH);

        topXEnd = 0;
        topZEnd = Z_LENGTH - 1;

        goTop = true;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 0, 3, 3, Z_LENGTH - 1);

        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 3, 3, Z_LENGTH - 1, Block.web.blockID, Block.web.blockID, false);

        for (int i = 0; i < 3; i++) {
            int z = i * 4;
            // block floor
            this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1 + z, 4, 0, 3 + z, false, random, getCemeteryCatacombsStones());
            // neter ceiling
            this.fillWithBlocks(world, boundingBox, 0, 4, 0 + z, 4, 4, 3 + z, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            // trap floor 
            this.fillWithBlocks(world, boundingBox, 0, 0, 0 + z, 4, 0, 0 + z, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);

            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1 + z, 0, 3, 3 + z, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 1 + z, 4, 3, 3 + z, false, random, getCemeteryCatacombsStones());
            // nether walls 
            this.fillWithBlocks(world, boundingBox, 0, 1, 0 + z, 0, 3, 0 + z, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            this.fillWithBlocks(world, boundingBox, 4, 1, 0 + z, 4, 3, 0 + z, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        }
        
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 12, 4, 4, 12, false, random, getCemeteryCatacombsStones());

        // spawner
        generateSpawner(world, 2, 1, 6, "CaveSpider");

        return true;
    }
}
