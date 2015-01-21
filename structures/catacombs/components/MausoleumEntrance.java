package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import net.minecraft.init.Blocks;
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

    public MausoleumEntrance(int direction, Random random, StructureBoundingBox structureBoundingBox, int offsetY) {
        super(direction);
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

        int metaTop = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 2);
        int metaBot = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 3);
        int metaRight = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 1);
        int metaLeft = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 0);
        this.fillWithAir(world, boundingBox, 0, 0, 6, 13, 5, 13);

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 4, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 4, 1, 9, boundingBox);
//TODO
//        // fire stairs
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 3, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 4, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 5, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaLeft, 3, 0, 9, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaRight, 5, 0, 9, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 3, 0, 10, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 4, 0, 10, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 5, 0, 10, boundingBox);
//
//        for (int x = 3; x < 5; x++) {
//            for (int z = 8; z < 10; z++) {
//                this.func_151554_b(world, Blocks.nether_brick, 0, x, -1, z, boundingBox);
//            }
//        }

        // fire
        this.placeBlockAtCurrentPosition(world, Blocks.netherrack.getDefaultState(), 9, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fire.getDefaultState(), 9, 1, 9, boundingBox);
//TODO
//        // fire stairs
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 8, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 9, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaBot, 10, 0, 8, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaLeft, 8, 0, 9, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaRight, 10, 0, 9, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 8, 0, 10, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 9, 0, 10, boundingBox);
//        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick_stairs, metaTop, 10, 0, 10, boundingBox);
//
//        for (int x = 3; x < 6; x++) {
//            for (int z = 8; z < 11; z++) {
//                this.func_151554_b(world, Blocks.nether_brick.getDefaultState(), x, -1, z, boundingBox);
//            }
//        }
//
//        for (int x = 8; x < 11; x++) {
//            for (int z = 8; z < 11; z++) {
//                this.func_151554_b(world, Blocks.nether_brick.getDefaultState(), x, -1, z, boundingBox);
//            }
//        }

        return true;
    }
}
