package gravestone.structures.catacombs.components;

import java.util.Random;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnderHall extends CatacombsBaseComponent {

    public static final int X_LENGTH = 12;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 18;

    public EnderHall(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 4;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topXEnd = 4;
        topZEnd = Z_LENGTH;
        goTop = true;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 11, 3, 17);
        
        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 0, 12, 0, 18, false, random, getCemeteryCatacombsStones());
        
        // neter floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 12, 0, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 6, 12, 0, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 9, 12, 0, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 12, 12, 0, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 15, 12, 0, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 1, 3, 0, 17, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 0, 1, 6, 0, 17, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 0, 1, 9, 0, 17, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 12, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 0, 0, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 1, 0, 12, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 0, 2, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 0, 11, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 18, 2, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 18, 11, 3, 18, false, random, getCemeteryCatacombsStones());
        
        // nether walls
        this.fillWithBlocks(world, boundingBox, 3, 0, 0, 9, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 18, 9, 3, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 3, 0, 3, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 3, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 9, 0, 3, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 12, 0, 3, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 15, 0, 3, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 3, 12, 3, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 6, 12, 3, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 9, 12, 3, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 12, 12, 3, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 15, 12, 3, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // nether columns
        this.fillWithBlocks(world, boundingBox, 3, 1, 3, 3, 3, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 6, 3, 3, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 9, 3, 3, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 12, 3, 3, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 15, 3, 3, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 3, 6, 3, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 6, 3, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 9, 6, 3, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 12, 6, 3, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 15, 6, 3, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 3, 9, 3, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 6, 9, 3, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 9, 9, 3, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 12, 9, 3, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 15, 9, 3, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // trap floor
        this.fillWithBlocks(world, boundingBox, 4, 0, 3, 8, 0, 3, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);
        this.fillWithBlocks(world, boundingBox, 4, 0, 15, 8, 0, 15, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);
        
        // spawner
        ObjectsGenerationHelper.generateSpawner(this, world, 3, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateSpawner(this, world, 9, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateSpawner(this, world, 6, 0, 9, "Enderman");
        ObjectsGenerationHelper.generateSpawner(this, world, 3, 0, 15, "Enderman");
        ObjectsGenerationHelper.generateSpawner(this, world, 9, 0, 15, "Enderman");
        
        // fiil exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 18, 7, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithAir(world, boundingBox, 5, 1, 0, 7, 3, 0);
        
        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);
        
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 2, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 3, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 11, 2, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 10, 1, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 2, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 1, 4, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 8, 3, 4, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 2, 7, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 3, 7, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 10, 1, 7, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 7, 2, 8, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 11, 2, 8, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 2, 10, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 1, 10, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 10, 3, 10, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 7, 2, 11, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 8, 3, 11, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 4, 2, 12, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 10, 3, 12, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 1, 13, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 9, 2, 13, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 6, 3, 14, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 11, 1, 14, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 1, 15, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 10, 2, 15, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 3, 16, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 2, 17, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 8, 1, 17, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 11, 3, 17, Block.web.blockID, 0);
        
        return true;
    }
}
