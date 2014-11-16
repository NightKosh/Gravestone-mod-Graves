package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

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

    public Corridor(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
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

        this.randomlyFillWithBlocks(world, boundingBox, random, PILE_OF_BONES_GENERATION_CHANCE, 1, 1, 1, 4, 1, 4, GSBlock.pileOfBones, GSBlock.pileOfBones, false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 4, 0, 0, GSBlock.trap, GSBlock.trap, false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 4, 4, 3, Blocks.nether_brick, Blocks.nether_brick, false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 0, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 1, 4, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 0, 4, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 3, 2, 1, 3, 2, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 2, 1, 1, 2, 1, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 2, 3, 3, 2, 3, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 1, 0, 3, 1, 0, Blocks.web, Blocks.web, false);

        return true;
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}
