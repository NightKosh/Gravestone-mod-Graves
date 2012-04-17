package net.minecraft.GraveStone.structures;

import java.util.List;
import java.util.Random;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentGSVillageCemetery extends ComponentVillage {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 2;

    public ComponentGSVillageCemetery(ComponentVillageStartPiece componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(componentVillageStartPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageCemetery buildComponent(ComponentVillageStartPiece startPiece, List par1List, Random random, int par3, int par4, int par5, int par6, int par7) {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 12, HEIGHT, 10, par6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new ComponentGSVillageCemetery(startPiece, par7, random, var8, par6) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);
            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + HEIGHT - 2, 0);
        }

        int graveMeta = mod_GraveStone.graveStone.getMetaDirection(this.coordBaseMode) + random.nextInt(mod_GraveStone.graveStone.GRAVE_TYPE_COUNT) * 4;
        int fenceMeta;
        if (this.coordBaseMode == 1 || this.coordBaseMode == 3) {
            fenceMeta = 1;
        } else {
            fenceMeta = 0;
        }

        int wallID = Block.cobblestoneWall.blockID;
        int wallMeta;
        int groundID;
        if (this.startPiece.inDesert) {
            wallMeta = 0;
            groundID = Block.sand.blockID;
        } else {
            wallMeta = 1;
            groundID = Block.grass.blockID;
        }

        this.fillWithAir(world, structureBoundingBox, 0, 1, 0, 12, 5, 10);
        this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 11, 1, 9, 0, 0, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, -5, 0, 11, 0, 9, groundID, groundID, false);

        for (int x = 1; x < 12; x++) {
            this.placeBlockAtCurrentPosition(world, wallID, wallMeta, x, 1, 1, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, wallID, wallMeta, x, 1, 9, structureBoundingBox);
        }
        for (int z = 1; z < 10; z++) {
            this.placeBlockAtCurrentPosition(world, wallID, wallMeta, 1, 1, z, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, wallID, wallMeta, 11, 1, z, structureBoundingBox);
        }

        this.placeBlockAtCurrentPosition(world, Block.fenceGate.blockID, fenceMeta, 6, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Block.fenceGate.blockID, fenceMeta, 6, 1, 9, structureBoundingBox);

        for (int x = 3; x < 11; x += 2) {
            for (int z = 3; z < 9; z += 2) {
                placeGrave(world, random, x, 1, z, graveMeta, structureBoundingBox);
            }
        }
        for (int x = 0; x < 11; x++) {
            for (int z = 0; z < 9; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, structureBoundingBox);
                this.fillCurrentPositionBlocksDownwards(world, groundID, 0, x, -1, z, structureBoundingBox);
            }
        }

        return true;
    }

    protected void placeGrave(World world, Random random, int x, int y, int z, int graveMeta, StructureBoundingBox structureBoundingBox) {
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.graveStoneID, graveMeta, x, y, z, boundingBox);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (tileEntity != null) {
            tileEntity.setGraveContent();
        }
    }
}
