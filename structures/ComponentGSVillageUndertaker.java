package GraveStone.structures;

import java.util.List;
import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.ModGraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentGSVillageUndertaker extends ComponentVillage {

    private int averageGroundLevel = -1;

    public ComponentGSVillageUndertaker(ComponentVillageStartPiece componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(componentVillageStartPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageUndertaker buildComponent(ComponentVillageStartPiece startPiece, List list, Random random, int par3, int par4, int par5, int par6, int par7) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 10, 6, 7, par6);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentGSVillageUndertaker(startPiece, par7, random, structureboundingbox, par6) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);

            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }

        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 9, 4, 6, 0, 0, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 9, 0, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 9, 4, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 9, 5, 6, Block.stoneSingleSlab.blockID, Block.stoneSingleSlab.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 1, 8, 5, 5, 0, 0, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 2, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 3, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 4, 6, Block.wood.blockID, Block.wood.blockID, false);
        this.placeBlockAtCurrentPosition(world, Block.planks.blockID, 0, 3, 3, 1, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 1, 2, 3, 3, 2, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 3, 4, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 1, 0, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 6, 5, 3, 6, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
        
        this.fillWithBlocks(world, boundingBox, 5, 1, 4, 5, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 3, 5, 3, 3, Block.wood.blockID, Block.wood.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 6, 5, 4, 6, Block.wood.blockID, Block.wood.blockID, false);
        
        // fence
        this.fillWithBlocks(world, boundingBox, 9, 1, 0, 9, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 6, 9, 3, 6, Block.fence.blockID, Block.fence.blockID, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 1, 9, 1, 5, Block.fence.blockID, Block.fence.blockID, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 8, 1, 6, Block.fence.blockID, Block.fence.blockID, false);
        
        // torch
        this.placeBlockAtCurrentPosition(world, Block.torchWood.blockID, 0, 9, 2, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.torchWood.blockID, 0, 9, 2, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.torchWood.blockID, 0, 1, 3, 4, boundingBox);
        
        // door
        this.placeDoorAtCurrentPosition(world, boundingBox, random, 3, 1, 1, this.getMetadataWithOffset(Block.doorWood.blockID, 2));
        
        // graves
        int graveMeta = ModGraveStone.graveStone.getMetaDirection(this.coordBaseMode);
        this.fillWithMetadataBlocks(world, boundingBox, 6, 1, 5, 8, 1, 5, GraveStoneConfig.graveStoneID, graveMeta, GraveStoneConfig.graveStoneID, graveMeta, false);
        
        this.placeBlockAtCurrentPosition(world, Block.thinGlass.blockID, 0, 0, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.thinGlass.blockID, 0, 0, 2, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.thinGlass.blockID, 0, 2, 2, 6, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.thinGlass.blockID, 0, 4, 2, 6, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 2, 1, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.pressurePlatePlanks.blockID, 0, 2, 2, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.planks.blockID, 0, 1, 1, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 3), 2, 1, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 1), 1, 1, 4, boundingBox);
        int i;
        int j;

        for (i = 6; i <= 8; ++i) {
            if (this.getBlockIdAtCurrentPosition(world, i, 0, -1, boundingBox) == 0 && this.getBlockIdAtCurrentPosition(world, i, -1, -1, boundingBox) != 0) {
                this.placeBlockAtCurrentPosition(world, Block.stairsCobblestone.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), i, 0, -1, boundingBox);
            }
        }

        for (i = 0; i < 7; ++i) {
            for (j = 0; j < 10; ++j) {
                this.clearCurrentPositionBlocksUpwards(world, j, 6, i, boundingBox);
                this.fillCurrentPositionBlocksDownwards(world, Block.cobblestone.blockID, 0, j, -1, i, boundingBox);
            }
        }

        this.spawnVillagers(world, boundingBox, 7, 1, 1, 1);
        return true;
    }
    
    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int par1) {
        return 385;
    }
    
    public static int getLeftItemDirection(int direction) {
        if (direction == 0 || direction == 1) {
            direction -= 1;
            if (direction < 0) {
                direction = 3;
            }
        } else {
            direction += 1;
            if (direction > 3) {
                direction = 0;
            }
        }
        return direction;
    }
}
