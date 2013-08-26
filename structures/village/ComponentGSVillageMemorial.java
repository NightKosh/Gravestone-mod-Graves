package GraveStone.structures.village;

import GraveStone.GraveStoneConfig;
import GraveStone.block.BlockGSMemorial;
import GraveStone.tileentity.TileEntityGSMemorial;
import java.util.List;
import java.util.Random;
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
public class ComponentGSVillageMemorial extends ComponentVillage {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 6;

    public ComponentGSVillageMemorial(ComponentVillageStartPiece componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(componentVillageStartPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageMemorial buildComponent(ComponentVillageStartPiece startPiece, List list, Random random, int par3, int par4, int par5, int direction, int componentType) {
        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 5, HEIGHT, 5, direction);
        return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null ? new ComponentGSVillageMemorial(startPiece, componentType, random, structureBoundingBox, direction) : null;
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

        //this.fillWithAir(world, structureBoundingBox, 0, 1, 0, 5, HEIGHT, 5);
        int groundID;

        if (this.startPiece.inDesert) {
            groundID = Block.sand.blockID;
        } else {
            groundID = Block.grass.blockID;
        }

        this.fillWithBlocks(world, structureBoundingBox, 0, -5, 0, 5, 0, 5, groundID, groundID, false);
        int memorialMeta = BlockGSMemorial.getMetaDirection(this.coordBaseMode);
        byte memorialType = BlockGSMemorial.getMemorialType(random, 0);
        placeMemorial(world, random, 2, 1, 2, memorialMeta, memorialType);

        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, structureBoundingBox);
                this.fillCurrentPositionBlocksDownwards(world, groundID, 0, x, -1, z, structureBoundingBox);
            }
        }

        return true;
    }

    protected void placeMemorial(World world, Random random, int x, int y, int z, int memorialMeta, byte memorialType) {
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.memorialID, memorialMeta, x, y, z, boundingBox);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getBlockTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
            tileEntity.setMemorialContent(random);
        }
    }
}
