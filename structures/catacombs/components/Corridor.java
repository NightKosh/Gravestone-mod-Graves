package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
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
public class Corridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public Corridor(int componentType, EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(componentType, facing, level);
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topXEnd = 0;
        topZEnd = Z_LENGTH - 1;

        switch (facing) {
            case SOUTH:
                leftXEnd = X_LENGTH - 1;
                leftZEnd = 0;
                rightXEnd = 0;
                rightZEnd = 0;
                break;
            case NORTH:
                leftXEnd = 0;
                leftZEnd = 0;
                rightXEnd = X_LENGTH - 1;
                rightZEnd = 0;
                break;
            case WEST:
                leftXEnd = X_LENGTH - 1;
                leftZEnd = 4;
                rightXEnd = 0;
                rightZEnd = 4;
                break;
            case EAST:
                leftXEnd = 0;
                leftZEnd = 4;
                rightXEnd = X_LENGTH - 1;
                rightZEnd = 4;
                break;
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

        this.randomlyFillWithBlocks(world, boundingBox, random, PILE_OF_BONES_GENERATION_CHANCE, 1, 1, 1, 4, 1, 4, GSBlock.pileOfBones.getDefaultState(), GSBlock.pileOfBones.getDefaultState(), false);

        // trap floor
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 4, 0, 0, GSBlock.trap.getDefaultState(), GSBlock.trap.getDefaultState(), false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 4, 4, 3, Blocks.nether_brick.getDefaultState(), false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 0, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 1, 1, 4, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 4, 4, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 3, 0, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 0, 4, 3, 0, Blocks.nether_brick.getDefaultState(), false);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 3, 2, 1, 3, 2, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, 2, 1, 1, 2, 1, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 2, 3, 3, 2, 3, Blocks.web.getDefaultState(), false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 1, 0, 3, 1, 0, Blocks.web.getDefaultState(), false);

        return true;
    }

    @Override
    public boolean canGoOnlyTop() {
        return false;
    }
}
