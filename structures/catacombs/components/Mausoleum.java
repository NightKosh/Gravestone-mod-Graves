package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Mausoleum extends CatacombsBaseComponent {

    public Mausoleum(int componentType, EnumFacing direction, Random random, StructureBoundingBox structureBoundingBox) {
        super(componentType, direction);
        this.boundingBox = structureBoundingBox;
        topXEnd = 5;
        topZEnd = 5;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = BoundingBoxHelper.getAverageGroundLevel(world, boundingBox);

        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - this.boundingBox.minY - 1, 0);
        this.fillWithAir(world, boundingBox, 2, 1, 2, 11, 5, 11);
        this.fillWithAir(world, boundingBox, 1, 1, 0, 12, 5, 1);
        this.fillWithAir(world, boundingBox, 6, 0, 6, 7, 0, 8);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, WEB_GENERATION_CHANCE, 4, 1, 4, 9, 3, 9, Blocks.web.getDefaultState(), false);
        // brick floor
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 3, 10, 0, 10, false, random, getCemeteryCatacombsStones());

        // nether floor
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 5, 0, 10, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 3, 8, 0, 10, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 5, 5, 0, 5, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 5, 10, 0, 5, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 8, 5, 0, 8, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 8, 10, 0, 8, Blocks.nether_brick.getDefaultState(), false);

        // nether walls
        this.fillWithBlocks(world, boundingBox, 2, 0, 1, 2, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 2, 1, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 1, 5, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 1, 8, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 1, 11, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 12, 0, 2, 12, 4, 2, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 11, 2, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 11, 1, 4, 11, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 11, 5, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 11, 8, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 12, 11, 4, 12, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 11, 12, 4, 11, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 5, 2, 4, 5, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 5, 12, 4, 5, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 8, 2, 4, 8, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 8, 12, 4, 8, Blocks.nether_brick.getDefaultState(), false);

        // brick walls
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 2, 4, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 3, 2, 7, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 2, 10, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 11, 4, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 11, 7, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 11, 10, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 3, 2, 4, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 6, 2, 4, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 9, 2, 4, 10, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 3, 11, 4, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 6, 11, 4, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 9, 11, 4, 10, false, random, getCemeteryCatacombsStones());

        // trap floor
        this.fillWithBlocks(world, boundingBox, 6, 0, 2, 7, 0, 2, GSBlock.trap.getDefaultState(), false);

        // brick columns
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 5, 5, 4, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 5, 8, 4, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 8, 5, 4, 8, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 8, 8, 4, 8, false, random, getCemeteryCatacombsStones());

        IBlockState netherSlabsState = Blocks.stone_slab.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.NETHERBRICK);
        IBlockState netherSlabsBotState = netherSlabsState.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        IBlockState netherSlabsTopState = netherSlabsState.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
        // roof 1
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 0, 5, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 13, 5, 0, 13, 5, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 2, 5, 0, 11, 5, 0, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 2, 5, 13, 11, 5, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 1, 12, 5, 12, Blocks.nether_brick.getDefaultState(), false);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 1, 5, 0, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 12, 5, 0, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 1, 5, 13, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick.getDefaultState(), 12, 5, 13, boundingBox);

        // roof 2
        this.fillWithBlocks(world, boundingBox, 2, 6, 0, 2, 6, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 11, 6, 0, 11, 6, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 3, 6, 0, 3, 6, 13, netherSlabsTopState, false);
        this.fillWithBlocks(world, boundingBox, 10, 6, 0, 10, 6, 13, netherSlabsTopState, false);
        this.fillWithBlocks(world, boundingBox, 3, 6, 1, 10, 6, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 3, 6, 12, 10, 6, 12, Blocks.nether_brick.getDefaultState(), false);

        // roof 3
        this.fillWithBlocks(world, boundingBox, 4, 7, 0, 4, 7, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 9, 7, 0, 9, 7, 13, netherSlabsBotState, false);
        this.fillWithBlocks(world, boundingBox, 5, 7, 0, 5, 7, 13, netherSlabsTopState, false);
        this.fillWithBlocks(world, boundingBox, 8, 7, 0, 8, 7, 13, netherSlabsTopState, false);
        this.fillWithBlocks(world, boundingBox, 5, 7, 1, 8, 7, 1, Blocks.nether_brick.getDefaultState(), false);
        this.fillWithBlocks(world, boundingBox, 5, 7, 12, 8, 7, 12, Blocks.nether_brick.getDefaultState(), false);

        // roof 4
        this.fillWithBlocks(world, boundingBox, 6, 8, 0, 7, 8, 13, netherSlabsBotState, false);

        for (int x = 2; x < 12; x++) {
            for (int z = 2; z < 12; z++) {
                this.fillDownwards(world, Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), x, -1, z, boundingBox);
            }
        }

        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 2, -1, 1, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 5, -1, 1, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 8, -1, 1, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 11, -1, 1, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 2, -1, 12, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 5, -1, 12, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 8, -1, 12, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 11, -1, 12, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 1, -1, 2, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 1, -1, 5, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 1, -1, 8, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 1, -1, 11, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 12, -1, 2, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 12, -1, 5, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 12, -1, 8, boundingBox);
        this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), 12, -1, 11, boundingBox);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        return true;
    }
}
