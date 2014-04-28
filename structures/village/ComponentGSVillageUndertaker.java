package gravestone.structures.village;

import java.util.List;
import java.util.Random;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSVillageUndertaker extends StructureVillagePieces.Village {

    private int averageGroundLevel = -1;

    public ComponentGSVillageUndertaker() {
    }

    public ComponentGSVillageUndertaker(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(startPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageUndertaker buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, int par6, int par7) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 10, 6, 7, par6);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentGSVillageUndertaker(startPiece, par7, random, structureboundingbox, par6) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs,
     * Mob Spawners, it closes Mineshafts at the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);

            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }

        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 9, 4, 6, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 9, 0, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, boundingBox, 0, 4, 0, 9, 4, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, boundingBox, 0, 5, 0, 9, 5, 6, Blocks.stone_slab, Blocks.stone_slab, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 1, 8, 5, 5, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 2, 3, 0, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 4, 0, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 3, 4, 0, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 6, 0, 4, 6, Blocks.planks, Blocks.planks, false);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 3, 3, 1, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 1, 2, 3, 3, 2, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 4, 1, 3, 4, 3, 3, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 0, 1, 1, 0, 3, 5, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 6, 5, 3, 6, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.fence, Blocks.fence, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 4, 5, 3, 5, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 3, 5, 3, 3, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 6, 5, 4, 6, Blocks.planks, Blocks.planks, false);
        // fence
        this.fillWithBlocks(world, boundingBox, 9, 1, 0, 9, 3, 0, Blocks.fence, Blocks.fence, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 6, 9, 3, 6, Blocks.fence, Blocks.fence, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 1, 9, 1, 5, Blocks.fence, Blocks.fence, false);
        this.fillWithBlocks(world, boundingBox, 6, 1, 6, 8, 1, 6, Blocks.fence, Blocks.fence, false);
        // torch
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 9, 2, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 9, 2, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 3, 4, boundingBox);
        // door
        this.placeDoorAtCurrentPosition(world, boundingBox, random, 3, 1, 1, this.getMetadataWithOffset(Blocks.wooden_door, 2));
        // graves
        int graveMeta = GraveStoneHelper.getMetaDirection(this.coordBaseMode);
        this.fillWithMetadataBlocks(world, boundingBox, 6, 1, 5, 8, 1, 5, GSBlock.graveStone, graveMeta, GSBlock.graveStone, graveMeta, false);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 0, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 0, 2, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 2, 2, 6, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 4, 2, 6, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 1, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 2, 2, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 1, 1, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2, 1, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 1), 1, 1, 4, boundingBox);
        int i;
        int j;

        for (i = 6; i <= 8; ++i) {
            if (this.getBlockAtCurrentPosition(world, i, 0, -1, boundingBox).equals(Blocks.air) && !this.getBlockAtCurrentPosition(world, i, -1, -1, boundingBox).equals(Blocks.air)) {
                this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), i, 0, -1, boundingBox);
            }
        }

        for (i = 0; i < 7; ++i) {
            for (j = 0; j < 10; ++j) {
                this.clearCurrentPositionBlocksUpwards(world, j, 6, i, boundingBox);
                this.func_151554_b(world, Blocks.cobblestone, 0, j, -1, i, boundingBox);
            }
        }

        this.spawnVillagers(world, boundingBox, 7, 1, 1, 1);
        return true;
    }

    /**
     * Returns the villager type to spawn in this component, based on the number
     * of villagers already spawned.
     */
    @Override
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
