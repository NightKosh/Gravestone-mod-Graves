package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

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

    public EnderHall(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
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
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 12, 0, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 6, 12, 0, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 9, 12, 0, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 12, 12, 0, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 15, 12, 0, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 1, 3, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 0, 1, 6, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 0, 1, 9, 0, 17, Blocks.nether_brick, Blocks.nether_brick, false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 12, 4, 18, Blocks.nether_brick, Blocks.nether_brick, false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 0, 0, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 1, 0, 12, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 0, 2, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 0, 11, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 18, 2, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 18, 11, 3, 18, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 3, 0, 0, 9, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 18, 9, 3, 18, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 3, 0, 3, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 3, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 9, 0, 3, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 12, 0, 3, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 15, 0, 3, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 3, 12, 3, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 6, 12, 3, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 9, 12, 3, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 12, 12, 3, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 15, 12, 3, 15, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether columns
        this.fillWithBlocks(world, boundingBox, 3, 1, 3, 3, 3, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 6, 3, 3, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 9, 3, 3, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 12, 3, 3, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 15, 3, 3, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 3, 6, 3, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 6, 3, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 9, 6, 3, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 12, 6, 3, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 15, 6, 3, 15, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 3, 9, 3, 3, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 6, 9, 3, 6, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 9, 9, 3, 9, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 12, 9, 3, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 15, 9, 3, 15, Blocks.nether_brick, Blocks.nether_brick, false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 4, 0, 3, 8, 0, 3, GSBlock.trap, GSBlock.trap, false);
        this.fillWithBlocks(world, boundingBox, 4, 0, 15, 8, 0, 15, GSBlock.trap, GSBlock.trap, false);

        // spawner
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 9, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 6, 0, 9, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 15, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 9, 0, 15, "Enderman");

        // fiil exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 18, 7, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithAir(world, boundingBox, 5, 1, 0, 7, 3, 0);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 2, 1, 1, 2, 1, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 3, 1, 2, 3, 1, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 11, 2, 1, 11, 2, 1, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 1, 2, 10, 1, 2, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 2, 2, 5, 2, 2, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 1, 4, 2, 1, 4, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 8, 3, 4, 8, 3, 4, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 2, 7, 1, 2, 7, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 3, 7, 5, 3, 7, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 1, 7, 10, 1, 7, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 7, 2, 8, 7, 2, 8, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 11, 2, 8, 11, 2, 8, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 2, 10, 2, 2, 10, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 1, 10, 5, 1, 10, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 3, 10, 10, 3, 10, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 7, 2, 11, 7, 2, 11, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 8, 3, 11, 8, 3, 11, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 4, 2, 12, 4, 2, 12, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 3, 12, 10, 3, 12, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 1, 13, 2, 1, 13, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 9, 2, 13, 9, 2, 13, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 6, 3, 14, 6, 3, 14, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 11, 1, 14, 11, 1, 14, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 5, 1, 15, 5, 1, 15, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 10, 2, 15, 10, 2, 15, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 3, 16, 1, 3, 16, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 2, 17, 2, 2, 17, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 8, 1, 17, 8, 1, 17, Blocks.web, Blocks.web, true);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 11, 3, 17, 11, 3, 17, Blocks.web, Blocks.web, true);

        return true;
    }
}
