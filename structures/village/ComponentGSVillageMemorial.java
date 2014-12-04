package gravestone.structures.village;

import gravestone.block.BlockGSMemorial;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSMemorial;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ComponentGSVillageMemorial extends StructureVillagePieces.Village {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 6;

    public ComponentGSVillageMemorial() {
    }

    public ComponentGSVillageMemorial(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction) {
        super(startPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageMemorial buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, int direction, int componentType) {
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

        Block ground;
        int biomeId = world.getBiomeGenForCoords(this.getXWithOffset(0, 0), this.getZWithOffset(0, 0)).biomeID;;
        if (biomeId == BiomeGenBase.desert.biomeID || biomeId == BiomeGenBase.desertHills.biomeID) {
            ground = Blocks.sand;
        } else {
            ground = Blocks.grass;
        }

        this.fillWithBlocks(world, structureBoundingBox, 0, -5, 0, 5, 0, 5, ground, ground, false);
        placeMemorial(world, random, 2, 1, 2);

        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, structureBoundingBox);
                this.func_151554_b(world, ground, 0, x, -1, z, structureBoundingBox);
            }
        }

        return true;
    }

    protected void placeMemorial(World world, Random random, int x, int y, int z) {
        int memorialMeta = BlockGSMemorial.getMetaDirection(this.coordBaseMode);
        byte memorialType;
        boolean isTortureMemorial = random.nextInt(4) == 0;
        if (isTortureMemorial) {
            memorialType = (byte) BlockGSMemorial.TORTURE_MEMORIALS[random.nextInt(BlockGSMemorial.TORTURE_MEMORIALS.length)].ordinal();
        } else {
            memorialType = BlockGSMemorial.getMemorialType(world, this.getXWithOffset(0, 0), this.getZWithOffset(0, 0), random, 0);
        }
        this.placeBlockAtCurrentPosition(world, GSBlock.memorial, memorialMeta, x, y, z, boundingBox);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
            if (isTortureMemorial) {
                tileEntity.setRandomMob(random);
            } else {
                tileEntity.setMemorialContent(random);
            }
        }
    }
}
