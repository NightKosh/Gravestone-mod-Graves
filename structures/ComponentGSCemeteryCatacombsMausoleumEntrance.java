package GraveStone.structures;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentGSCemeteryCatacombsMausoleumEntrance extends ComponentGSCemeteryCatacombs {

    private int offsetY;

    public ComponentGSCemeteryCatacombsMausoleumEntrance(int direction, Random random, StructureBoundingBox structureBoundingBox, int offsetY) {
        super(direction);
        this.boundingBox = structureBoundingBox;
        this.offsetY = offsetY;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);
        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - this.boundingBox.minY - 1, 0);
        
        if (this.offsetY + 1 > this.boundingBox.minY) {
            this.boundingBox.offset(0, this.offsetY + 1 - this.boundingBox.minY, 0);
        }
        

        int metaTop = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 2);
        int metaBot = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 3);

        int metaRight = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 1);
        int metaLeft = this.getMetadataWithOffset(Block.stairsNetherBrick.blockID, 0);


        this.fillWithAir(world, boundingBox, 0, 0, 6, 13, 5, 13);

        // fire
        this.placeBlockAtCurrentPosition(world, Block.netherrack.blockID, 0, 4, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fire.blockID, 0, 4, 1, 9, boundingBox);

        // fire stairs
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 3, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 4, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 5, 0, 8, boundingBox);

        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft, 3, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight, 5, 0, 9, boundingBox);
        
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 3, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 4, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 5, 0, 10, boundingBox);

        
        for (int x = 3; x < 5; x++) {
            for (int z = 8; z < 10; z++) {
                this.fillCurrentPositionBlocksDownwards(world, Block.netherBrick.blockID, 0, x, -1, z, boundingBox);
            }
        }

        // fire
        this.placeBlockAtCurrentPosition(world, Block.netherrack.blockID, 0, 9, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fire.blockID, 0, 9, 1, 9, boundingBox);

        // fire stairs
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 8, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 9, 0, 8, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaBot, 10, 0, 8, boundingBox);

        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaLeft, 8, 0, 9, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaRight, 10, 0, 9, boundingBox);
        
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 8, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 9, 0, 10, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsNetherBrick.blockID, metaTop, 10, 0, 10, boundingBox);

        
        for (int x = 3; x < 6; x++) {
            for (int z = 8; z < 11; z++) {
                this.fillCurrentPositionBlocksDownwards(world, Block.netherBrick.blockID, 0, x, -1, z, boundingBox);
            }
        }
        
        for (int x = 8; x < 11; x++) {
            for (int z = 8; z < 11; z++) {
                this.fillCurrentPositionBlocksDownwards(world, Block.netherBrick.blockID, 0, x, -1, z, boundingBox);
            }
        }

        return true;
    }
}
