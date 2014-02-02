package gravestone.structures.catacombs.components;

import java.util.Random;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Corridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public Corridor(int direction, Random random, int x, int y, int z) {
        super(direction);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topXEnd = 0;
        topZEnd = Z_LENGTH - 1;

        switch (direction) {
            case 0:
                leftXEnd = X_LENGTH - 1;
                leftZEnd = 0;
                rightXEnd = 0;
                rightZEnd = 0;
                break;
            case 1:
                leftXEnd = X_LENGTH - 1;
                leftZEnd = 4;
                rightXEnd = 0;
                rightZEnd = 4;
                break;
            case 2:
                leftXEnd = 0;
                leftZEnd = 4;
                rightXEnd = X_LENGTH - 1;
                rightZEnd = 4;
                break;
            case 3:
                leftXEnd = 0;
                leftZEnd = 0;
                rightXEnd = X_LENGTH - 1;
                rightZEnd = 0;
        }
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 0, 3, 3, 3);
        
        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 4, 0, 3, false, random, getCemeteryCatacombsStones());
        
        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 4, 0, 0, GraveStoneConfig.trapID, GraveStoneConfig.trapID, false);
        
        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 4, 4, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 0, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 1, 4, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());
        
        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 0, 4, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        
        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);
        
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 3, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 1, 2, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, 2, 3, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, 1, 0, Block.web.blockID, 0);
        
        return true;
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}
