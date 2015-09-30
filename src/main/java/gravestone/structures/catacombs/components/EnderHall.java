package gravestone.structures.catacombs.components;

import gravestone.config.GSConfig;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
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

    public EnderHall(EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(0, facing, level);
        xShift = 4;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        frontXEnd = 4;
        frontZEnd = Z_LENGTH;
        goForward = true;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 1, 11, 3, 17);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 0, 12, 0, 18, false, random, getCemeteryCatacombsStones());

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 1, 1, 1, 11, 3, 17, Blocks.web.getDefaultState(), false);
        // piles of bones
        if (GSConfig.generatePilesOfBones) {
            this.fillWithRandomizedPilesOfBones(world, boundingBox, 1, 1, 1, 11, 1, 17, false, random);
        }

        // neter floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 12, 0, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 6, 12, 0, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 9, 12, 0, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 12, 12, 0, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 15, 12, 0, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 1, 3, 0, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 0, 1, 6, 0, 17, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 0, 1, 9, 0, 17, Blocks.nether_brick.getDefaultState(), false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 12, 4, 18, Blocks.nether_brick.getDefaultState(), false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 0, 0, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 1, 0, 12, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 0, 2, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 0, 11, 3, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 18, 2, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 18, 11, 3, 18, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 3, 0, 0, 9, 3, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 18, 9, 3, 18, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 3, 0, 3, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 3, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 9, 0, 3, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 12, 0, 3, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 15, 0, 3, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 3, 12, 3, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 6, 12, 3, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 9, 12, 3, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 12, 12, 3, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 1, 15, 12, 3, 15, Blocks.nether_brick.getDefaultState(), false);

        // nether columns
        this.fillWithBlocks(world, boundingBox, 3, 1, 3, 3, 3, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 6, 3, 3, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 9, 3, 3, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 12, 3, 3, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 15, 3, 3, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 3, 6, 3, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 6, 3, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 9, 6, 3, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 12, 6, 3, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 15, 6, 3, 15, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 3, 9, 3, 3, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 6, 9, 3, 6, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 9, 9, 3, 9, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 12, 9, 3, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 15, 9, 3, 15, Blocks.nether_brick.getDefaultState(), false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 4, 0, 3, 8, 0, 3, GSBlock.trap.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 4, 0, 15, 8, 0, 15, GSBlock.trap.getDefaultState(), false);

        // spawner
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 9, 0, 3, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 6, 0, 9, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 3, 0, 15, "Enderman");
        ObjectsGenerationHelper.generateMinecraftSpawner(this, world, 9, 0, 15, "Enderman");

        // fill exit
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 18, 7, 3, 18, false, random, getCemeteryCatacombsStones());
        this.fillWithAir(world, boundingBox, 5, 1, 0, 7, 3, 0);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        return true;
    }
}
