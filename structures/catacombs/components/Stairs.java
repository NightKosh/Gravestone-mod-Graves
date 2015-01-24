package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.catacombs.CatacombsLevel;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Stairs extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 16;
    public static final int Z_LENGTH = 13;

    public Stairs(int componentType, EnumFacing facing, int level, Random random, int x, int y, int z) {
        super(componentType, facing, level);
        y = y - HEIGHT + 4;
        boundingBox = BoundingBoxHelper.getCorrectBox(facing, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = true;
        topXEnd = 0;
        topZEnd = Z_LENGTH;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        IBlockState netherBrickStairsBotState = Blocks.nether_brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, this.coordBaseMode.getOpposite());

        int top = boundingBox.maxY - boundingBox.minY - 1;
        int shiftY;
        int shiftZ;
        this.fillWithBlocks(world, boundingBox, 0, top + 1, 0, 4, top + 1, 0, Blocks.nether_brick.getDefaultState(), false);

        for (int i = 0; i < 3; i++) {
            shiftZ = i * 4;
            shiftY = top - i * 4 + 1;
            this.fillWithAir(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 1, shiftZ + 1);
            this.fillWithAir(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 2, shiftZ + 2);
            this.fillWithAir(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 3, shiftZ + 3);
            this.fillWithAir(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 4, shiftZ + 4);

            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY - 4, shiftZ, 0, shiftY, shiftZ, Blocks.nether_brick.getDefaultState(), false);
            this.fillWithBlocks(world, boundingBox, 4, shiftY - 4, shiftZ, 4, shiftY, shiftZ, Blocks.nether_brick.getDefaultState(), false);
            
            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 5, shiftZ + 1, 0, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 5, shiftZ + 1, 4, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 6, shiftZ + 2, 0, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 6, shiftZ + 2, 4, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 7, shiftZ + 3, 0, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 7, shiftZ + 3, 4, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());

            // nether stairs
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 4, shiftZ, netherBrickStairsBotState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 5, shiftZ + 1, netherBrickStairsBotState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 6, shiftZ + 2, netherBrickStairsBotState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 7, shiftZ + 3, netherBrickStairsBotState, false);

            Block stairsBlock = CatacombsLevel.getCatacombsStairsByLevelId(this.level);
            IBlockState stairsTopState = stairsBlock.getDefaultState().withProperty(BlockStairs.FACING, this.coordBaseMode)
                    .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);

            // block stairs
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 3, shiftY - 1, shiftZ + 1, stairsTopState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 3, shiftY - 2, shiftZ + 2, stairsTopState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 3, shiftZ + 3, 3, shiftY - 3, shiftZ + 3, stairsTopState, false);
            this.fillWithBlocks(world, boundingBox, 1, shiftY - 4, shiftZ + 4, 3, shiftY - 4, shiftZ + 4, stairsTopState, false);

            // web
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, shiftY - 3, shiftZ, 1, shiftY - 3, shiftZ, Blocks.web.getDefaultState(), false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, shiftY - 3, shiftZ + 1, 3, shiftY - 3, shiftZ + 1, Blocks.web.getDefaultState(), false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, shiftY - 5, shiftZ + 2, 2, shiftY - 5, shiftZ + 2, Blocks.web.getDefaultState(), false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, shiftY - 5, shiftZ + 3, 3, shiftY - 5, shiftZ + 3, Blocks.web.getDefaultState(), false);
        }

        this.fillWithBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 4, 0, Z_LENGTH - 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 0, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 0, Z_LENGTH - 1, 4, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        return true;
    }
}
