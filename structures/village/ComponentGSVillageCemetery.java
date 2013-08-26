package GraveStone.structures.village;

import java.util.List;
import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.block.GraveStoneHelper;
import GraveStone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSVillageCemetery extends ComponentVillage {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 2;

    public ComponentGSVillageCemetery(ComponentVillageStartPiece componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(componentVillageStartPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageCemetery buildComponent(ComponentVillageStartPiece startPiece, List list, Random random, int par3, int par4, int par5, int direction, int componentType) {
        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 12, HEIGHT, 10, direction);
        return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null ? new ComponentGSVillageCemetery(startPiece, componentType, random, structureBoundingBox, direction) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs,
     * Mob Spawners, it closes Mineshafts at the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);

            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + HEIGHT - 2, 0);
        }

        this.fillWithAir(world, structureBoundingBox, 0, 1, 0, 0, 1, 10);
        this.fillWithAir(world, structureBoundingBox, 12, 1, 0, 12, 1, 10);
        this.fillWithAir(world, structureBoundingBox, 0, 1, 0, 12, 1, 0);
        this.fillWithAir(world, structureBoundingBox, 0, 1, 10, 12, 1, 10);
        this.fillWithAir(world, structureBoundingBox, 2, 1, 2, 2, 1, 8);
        this.fillWithAir(world, structureBoundingBox, 4, 1, 2, 4, 1, 8);
        this.fillWithAir(world, structureBoundingBox, 6, 1, 2, 6, 1, 8);
        this.fillWithAir(world, structureBoundingBox, 8, 1, 2, 8, 1, 8);
        this.fillWithAir(world, structureBoundingBox, 10, 1, 2, 10, 1, 8);
        this.fillWithAir(world, structureBoundingBox, 3, 1, 2, 9, 1, 2);
        this.fillWithAir(world, structureBoundingBox, 3, 1, 4, 9, 1, 4);
        this.fillWithAir(world, structureBoundingBox, 3, 1, 6, 9, 1, 6);
        this.fillWithAir(world, structureBoundingBox, 3, 1, 8, 9, 1, 8);
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
        int graveMeta = GraveStoneHelper.getMetaDirection(this.coordBaseMode);
        byte graveType = BlockGSGraveStone.GENERATED_GRAVES[random.nextInt(BlockGSGraveStone.GENERATED_GRAVES.length)];

        for (int x = 3; x < 11; x += 2) {
            for (int z = 3; z < 9; z += 2) {
                placeGrave(world, random, x, 1, z, graveMeta, graveType, structureBoundingBox);
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

    protected void placeGrave(World world, Random random, int x, int y, int z, int graveMeta, byte graveType, StructureBoundingBox structureBoundingBox) {
        int xCoord = this.getXWithOffset(x, z);
        int yCoord = this.getYWithOffset(y);
        int zCoord = this.getZWithOffset(x, z);

        if (world.getBlockId(xCoord, yCoord, zCoord) != GraveStoneConfig.graveStoneID) {
            this.placeBlockAtCurrentPosition(world, GraveStoneConfig.graveStoneID, graveMeta, x, y, z, boundingBox);
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(xCoord, yCoord, zCoord);

            if (tileEntity != null) {
                tileEntity.setGraveType(graveType);
                tileEntity.setGraveContent(random, false, true);
            }
        }
    }
}
