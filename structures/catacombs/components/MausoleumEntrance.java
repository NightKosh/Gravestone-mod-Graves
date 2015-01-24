package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import net.minecraft.block.BlockStairs;
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
public class MausoleumEntrance extends CatacombsBaseComponent {

    private int offsetY;

    public MausoleumEntrance(int componentType, EnumFacing direction, Random random, StructureBoundingBox structureBoundingBox, int offsetY) {
        super(componentType, direction);
        this.boundingBox = structureBoundingBox;
        this.offsetY = offsetY;
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

        if (this.offsetY + 1 > this.boundingBox.minY) {
            this.boundingBox.offset(0, this.offsetY + 1 - this.boundingBox.minY, 0);
        }

        IBlockState netherBrickStairsState = Blocks.nether_brick_stairs.getDefaultState();
        IBlockState netherBrickStairsTopState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode.getOpposite());
        IBlockState netherBrickStairsBotState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.coordBaseMode);
        IBlockState netherBrickStairsLeftState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getLeftDirection(this.coordBaseMode));
        IBlockState netherBrickStairsRightState = netherBrickStairsState.withProperty(BlockStairs.FACING, this.getRightDirection(this.coordBaseMode));
        this.fillWithAir(world, boundingBox, 0, 0, 6, 13, 5, 13);

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 4, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 4, 1, 9, boundingBox);

        // fire stairs
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 3, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 4, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 5, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 3, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 5, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 3, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 4, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 5, 0, 10, boundingBox);

        for (int x = 3; x < 5; x++) {
            for (int z = 8; z < 10; z++) {
                this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), x, -1, z, boundingBox);
            }
        }

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 9, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 9, 1, 9, boundingBox);

        // fire stairs
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 8, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 9, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsBotState, 10, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsLeftState, 8, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsRightState, 10, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 8, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 9, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, netherBrickStairsTopState, 10, 0, 10, boundingBox);

        for (int x = 3; x < 6; x++) {
            for (int z = 8; z < 11; z++) {
                this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), x, -1, z, boundingBox);
            }
        }

        for (int x = 8; x < 11; x++) {
            for (int z = 8; z < 11; z++) {
                this.fillDownwards(world, Blocks.nether_brick.getDefaultState(), x, -1, z, boundingBox);
            }
        }

        return true;
    }
}
