package gravestone.structures.catacombs.components;

import gravestone.core.GSMobSpawn;
import gravestone.block.BlockGSMemorial;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MemorialGenerationHelper;
import gravestone.structures.MobSpawnHelper;
import gravestone.structures.ObjectsGenerationHelper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class StatuesHall extends CatacombsBaseComponent {

    public static final int X_LENGTH = 11;
    public static final int HEIGHT = 6;
    public static final int Z_LENGTH = 19;

    public StatuesHall(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        xShift = 3;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topXEnd = 3;
        topZEnd = Z_LENGTH - 1;
        goTop = true;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 9, 4, 17);
        this.fillWithAir(world, boundingBox, 4, 1, 0, 6, 3, 0);
        
        // blocks floor and ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 9, 0, 17, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 5, 1, 9, 5, 17, false, random, getCemeteryCatacombsStones());
        
        // blocks wall
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 0, 4, 17, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 1, 10, 4, 17, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 0, 2, 4, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 0, 9, 4, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 18, 2, 4, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 18, 9, 4, 18, false, random, getCemeteryCatacombsStones());
        
        // nether floor and ceiling
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 0, 0, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 0, 3, 0, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, 0, 0, 7, 0, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 0, 0, 10, 0, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 0, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 5, 0, 3, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, 5, 0, 7, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 5, 0, 10, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 9, 0, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 3, 9, 0, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 6, 9, 0, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 9, 9, 0, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 12, 9, 0, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 15, 9, 0, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 18, 9, 0, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 0, 9, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 3, 9, 5, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 6, 9, 5, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 9, 9, 5, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 12, 9, 5, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 15, 9, 5, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 18, 9, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // nether wall
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 3, 0, 4, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 4, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 9, 0, 4, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 12, 0, 4, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 15, 0, 4, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 18, 0, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 0, 10, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 3, 10, 4, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 6, 10, 4, 6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 9, 10, 4, 9, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 12, 10, 4, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 15, 10, 4, 15, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 1, 18, 10, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 3, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, 1, 0, 7, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 4, 0, 7, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 18, 3, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, 1, 18, 7, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 4, 18, 7, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // spawners
        ObjectsGenerationHelper.generateSpawner(this, world, 3, 0, 3, GSMobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateSpawner(this, world, 3, 0, 9, GSMobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateSpawner(this, world, 3, 0, 15, GSMobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateSpawner(this, world, 7, 0, 3, GSMobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateSpawner(this, world, 7, 0, 9, GSMobSpawn.getMobForStatueSpawner(random));
        ObjectsGenerationHelper.generateSpawner(this, world, 7, 0, 15, GSMobSpawn.getMobForStatueSpawner(random));
        
        // loot chests
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 0, 6, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 0, 12, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 7, 0, 6, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 7, 0, 12, false, ObjectsGenerationHelper.EnumChestTypes.ALL_CHESTS);
        
        // statues
        byte memorialType = BlockGSMemorial.getMemorialType(random, 5);
        int metaLeft = BlockGSMemorial.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = BlockGSMemorial.getMetaDirection(getRightItemDirection(coordBaseMode));
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 3, metaLeft, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 6, metaLeft, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 9, metaLeft, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 12, metaLeft, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 3, 1, 15, metaLeft, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 3, metaRight, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 6, metaRight, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 9, metaRight, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 12, metaRight, memorialType);
        MemorialGenerationHelper.placeMemorial(this, world, random, 7, 1, 15, metaRight, memorialType);
        
        // fill exit with random blocks
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 18, 6, 3, 18, false, random, getCemeteryCatacombsStones());
        
        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);
        
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 6, 1, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 9, 3, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 4, 2, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 1, 5, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 3, 5, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 9, 2, 5, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 8, 1, 7, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 2, 10, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 6, 1, 11, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 4, 3, 13, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 9, 1, 14, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 5, 2, 16, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 3, 17, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 6, 1, 17, Block.web.blockID, 0);
        
        return true;
    }
}
