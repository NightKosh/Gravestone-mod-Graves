package GraveStone.structures.catacombs.components;

import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.structures.BoundingBoxHelper;
import GraveStone.structures.MobSpawnHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WitherHall extends CatacombsBaseComponent {

    public static final int X_LENGTH = 23;
    public static final int HEIGHT = 10;
    public static final int Z_LENGTH = 24;
    private int metaTop, metaBot, metaRight, metaLeft;

    public WitherHall(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 9;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = false;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        metaTop = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 2);
        metaBot = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 3);
        metaRight = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 1);
        metaLeft = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 0);
        this.fillWithAir(world, boundingBox, 1, 1, 2, 21, 9, 22);
        
        // nether floor
        this.fillWithBlocks(world, boundingBox, 10, 0, 0, 12, 0, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 17, 0, 16, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // nether ceiling
        this.fillWithBlocks(world, boundingBox, 1, 10, 1, 21, 10, 22, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 0, 1, 0, 10, 23, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 22, 0, 1, 22, 10, 23, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 1, 21, 10, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 23, 21, 10, 23, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // entrance
        this.fillWithAir(world, boundingBox, 10, 1, 0, 12, 3, 1);
        this.fillWithBlocks(world, boundingBox, 9, 0, 0, 9, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 13, 0, 0, 13, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 4, 0, 12, 4, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // lava floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 2, 9, 0, 4, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 5, 4, 0, 19, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 20, 8, 0, 22, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        this.fillWithBlocks(world, boundingBox, 13, 0, 2, 21, 0, 4, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        this.fillWithBlocks(world, boundingBox, 18, 0, 5, 21, 0, 19, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        this.fillWithBlocks(world, boundingBox, 14, 0, 20, 21, 0, 22, Block.lavaStill.blockID, Block.lavaStill.blockID, false);
        
        // lava walls
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 0, 6, 3, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 0, 6, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 0, 6, 15, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 0, 6, 21, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 22, 6, 3, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 22, 6, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 22, 6, 15, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 22, 6, 21, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 4, 6, 23, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 18, 6, 23, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 4, 6, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 18, 6, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 9, 6, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.lavaMoving.blockID, 0, 13, 6, 1, boundingBox);
        
        // fire
        buildFire(world, 6, 1, 6);
        buildFire(world, 6, 1, 12);
        buildFire(world, 6, 4, 18);
        buildFire(world, 16, 1, 6);
        buildFire(world, 16, 1, 12);
        buildFire(world, 16, 4, 18);
        
        // columns
        buildColumnLeft(world, 6);
        buildColumnLeft(world, 12);
        buildColumnLeft(world, 18);
        buildColumnRight(world, 6);
        buildColumnRight(world, 12);
        buildColumnRight(world, 18);
        
        // ceiling light
        buildLight(world, 6, 6);
        buildLight(world, 6, 12);
        buildLight(world, 6, 18);
        buildLight(world, 16, 6);
        buildLight(world, 16, 12);
        buildLight(world, 16, 18);
        
        // stairs
        this.fillWithBlocks(world, boundingBox, 9, 1, 12, 13, 1, 14, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 2, 13, 13, 2, 14, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 3, 14, 13, 3, 14, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithMetadataBlocks(world, boundingBox, 10, 1, 12, 12, 1, 14, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
        this.fillWithMetadataBlocks(world, boundingBox, 10, 2, 13, 12, 2, 14, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
        this.fillWithMetadataBlocks(world, boundingBox, 10, 3, 14, 12, 3, 14, Block.stairsNetherBrick.blockID, metaBot, Block.stairsNetherBrick.blockID, metaBot, false);
        
        // wither place
        this.fillWithBlocks(world, boundingBox, 8, 0, 15, 14, 3, 21, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 17, 7, 3, 19, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 15, 0, 17, 17, 3, 19, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 7, 0, 20, 7, 3, 20, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 15, 0, 20, 15, 3, 20, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 0, 22, 13, 2, 22, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.netherrack.blockID, 0, 9, 3, 15, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fire.blockID, 0, 9, 4, 15, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.netherrack.blockID, 0, 13, 3, 15, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fire.blockID, 0, 13, 4, 15, boundingBox);
        
        // portal
        this.fillWithBlocks(world, boundingBox, 9, 3, 22, 13, 3, 22, Block.obsidian.blockID, Block.obsidian.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 7, 22, 13, 7, 22, Block.obsidian.blockID, Block.obsidian.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 4, 22, 9, 6, 22, Block.obsidian.blockID, Block.obsidian.blockID, false);
        this.fillWithBlocks(world, boundingBox, 13, 4, 22, 13, 6, 22, Block.obsidian.blockID, Block.obsidian.blockID, false);
        this.fillWithBlocks(world, boundingBox, 10, 4, 22, 12, 6, 22, Block.portal.blockID, Block.portal.blockID, false);
        
        // spawner
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.witherSpawnerID, 0, 11, 4, 18, boundingBox);
        
        // treasure
        this.fillWithBlocks(world, boundingBox, 10, 3, 17, 12, 3, 19, Block.blockDiamond.blockID, Block.blockDiamond.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.blockEmerald.blockID, 0, 11, 3, 18, boundingBox);
        
        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);
        
        return true;
    }

    private void buildFire(World world, int x, int y, int z) {
        // fire
        this.placeBlockAtCurrentPosition(world, Block.netherrack.blockID, 0, x, y, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fire.blockID, 0, x, y + 1, z, boundingBox);
        
        // fire stairs
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, x - 1, y, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, x, y, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, x + 1, y, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft, x - 1, y, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight, x + 1, y, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, x - 1, y, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, x, y, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, x + 1, y, z + 1, boundingBox);
    }

    private void buildLight(World world, int x, int z) {
        // glowStone
        this.placeBlockAtCurrentPosition(world, Block.glowStone.blockID, 0, x, 9, z, boundingBox);
        
        // fire stairs
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, x - 1, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, x, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, x + 1, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft + 4, x - 1, 9, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight + 4, x + 1, 9, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, x - 1, 9, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, x, 9, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, x + 1, 9, z + 1, boundingBox);
    }

    private void buildColumnLeft(World world, int z) {
        this.fillWithBlocks(world, boundingBox, 1, 0, z - 1, 2, 0, z + 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, z, 1, 9, z, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // bot
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 1, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 2, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight, 2, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 1, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 2, 1, z + 1, boundingBox);
        
        // top
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, 1, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, 2, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight + 4, 2, 9, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, 1, 9, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, 2, 9, z + 1, boundingBox);
    }

    private void buildColumnRight(World world, int z) {
        this.fillWithBlocks(world, boundingBox, 20, 0, z - 1, 21, 0, z + 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 21, 1, z, 21, 9, z, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // bot
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 20, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 21, 1, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft, 20, 1, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 20, 1, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 21, 1, z + 1, boundingBox);
        
        // top
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, 20, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot + 4, 21, 9, z - 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft + 4, 20, 9, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, 20, 9, z + 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop + 4, 21, 9, z + 1, boundingBox);
    }
}
