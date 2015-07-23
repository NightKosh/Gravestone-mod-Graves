package gravestone.structures.village.cemetery;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
public class ComponentGSVillageCemetery extends StructureVillagePieces.Village {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 2;
    private static final int X_LENGTH = 13;
    private static final int Z_LENGTH = 11;

    public ComponentGSVillageCemetery() {
    }

    public ComponentGSVillageCemetery(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, EnumFacing direction) {
        super(startPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageCemetery buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, EnumFacing direction, int componentType) {
        StructureBoundingBox structureBoundingBox = StructureBoundingBox.func_175897_a(par3, par4, par5, 0, 0, 0, X_LENGTH, HEIGHT, Z_LENGTH, direction);
        return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(list, structureBoundingBox) == null ? new ComponentGSVillageCemetery(startPiece, componentType, random, structureBoundingBox, direction) : null;
    }

    public static StructureBoundingBox getBoundingBox(EnumFacing facing, int x, int z) {
        return BoundingBoxHelper.getCorrectBox(facing, x, 64, z, X_LENGTH, HEIGHT, Z_LENGTH, 0);
    }

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

        IBlockState wallState;
        IBlockState groundState;

        int biomeId = world.getBiomeGenForCoords(new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0))).biomeID;
        if (biomeId == BiomeGenBase.desert.biomeID || biomeId == BiomeGenBase.desertHills.biomeID) {
            wallState = Blocks.cobblestone_wall.getStateFromMeta(BlockWall.EnumType.NORMAL.getMetadata());
            groundState = Blocks.sand.getDefaultState();
        } else {
            wallState = Blocks.cobblestone_wall.getStateFromMeta(BlockWall.EnumType.MOSSY.getMetadata());
            groundState = Blocks.grass.getDefaultState();
        }

        //fillWithBlocks
        this.func_175804_a(world, structureBoundingBox, 0, -5, 0, 11, 0, 9, groundState, groundState, false);

        for (int x = 1; x < 12; x++) {
            //placeBlockAtCurrentPosition
            this.func_175811_a(world, wallState, x, 1, 1, structureBoundingBox);
            this.func_175811_a(world, wallState, x, 1, 9, structureBoundingBox);
        }

        for (int z = 1; z < 10; z++) {
            this.func_175811_a(world, wallState, 1, 1, z, structureBoundingBox);
            this.func_175811_a(world, wallState, 11, 1, z, structureBoundingBox);
        }

        IBlockState gateState = Blocks.dark_oak_fence_gate.getDefaultState().withProperty(BlockFenceGate.FACING, this.coordBaseMode);
        this.func_175811_a(world, gateState, 6, 1, 1, structureBoundingBox);
        this.func_175811_a(world, gateState, 6, 1, 9, structureBoundingBox);

        int graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getPlayerGraveTypes(world,
                new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0))), random);
        IBlockState graveState = GSBlock.graveStone.getDefaultState().withProperty(BlockGSGraveStone.FACING, this.coordBaseMode);
        for (int x = 3; x < 11; x += 2) {
            for (int z = 3; z < 9; z += 2) {
                placeGrave(world, random, x, 1, z, graveState, graveType, structureBoundingBox);
            }
        }

        for (int x = 0; x < 11; x++) {
            for (int z = 0; z < 9; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, structureBoundingBox);
                this.func_175808_b(world, groundState, x, -1, z, structureBoundingBox);
            }
        }

        return true;
    }

    protected void placeGrave(World world, Random random, int x, int y, int z, IBlockState graveState, int graveType, StructureBoundingBox structureBoundingBox) {
        int xCoord = this.getXWithOffset(x, z);
        int yCoord = this.getYWithOffset(y);
        int zCoord = this.getZWithOffset(x, z);

        this.func_175811_a(world, graveState, x, y, z, boundingBox);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(new BlockPos(xCoord, yCoord, zCoord));

        if (tileEntity != null) {
            tileEntity.setGraveType(graveType);
            tileEntity.setGraveContent(random, false, true);
        }
    }

    @Override
    public NBTTagCompound func_143010_b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("id", "GSVillageCemetery");
        nbttagcompound.setTag("BB", this.boundingBox.func_151535_h());
        nbttagcompound.setInteger("O", this.coordBaseMode == null ? -1 : this.coordBaseMode.getHorizontalIndex());
        nbttagcompound.setInteger("GD", this.componentType);
        this.writeStructureToNBT(nbttagcompound);
        return nbttagcompound;
    }
}
