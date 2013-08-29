package gravestone.structures.catacombs.components;

import java.util.Random;
import gravestone.GraveStoneConfig;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SpidersCorridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 13;

    public SpidersCorridor(int direction, Random random, int x, int y, int z) {
        super(direction);
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topXEnd = 0;
        topZEnd = Z_LENGTH - 1;
        goTop = true;
    }

    /**
     * Build component
     */
    @Override
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
        ObjectsGenerationHelper.generateSpawner(this, world, 2, 1, 6, "CaveSpider");
        return true;
    }
}
