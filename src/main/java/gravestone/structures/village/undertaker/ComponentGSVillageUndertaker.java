package gravestone.structures.village.undertaker;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.BlockGSSkullCandle;
import gravestone.block.GraveStoneHelper;
import gravestone.block.enums.EnumSkullCandle;
import gravestone.core.GSBlock;
import gravestone.entity.helper.EntityGroupOfGravesMobSpawnerHelper;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.IComponentGraveStone;
import gravestone.tileentity.TileEntityGSSkullCandle;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
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
public class ComponentGSVillageUndertaker extends StructureVillagePieces.Village implements IComponentGraveStone {

    private int averageGroundLevel = -1;
    private static final int HEIGHT = 7;
    private static final int X_LENGTH = 12;
    private static final int Z_LENGTH = 14;

    public ComponentGSVillageUndertaker() {
    }

    public ComponentGSVillageUndertaker(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, EnumFacing direction) {
        super(startPiece, componentType);
        this.coordBaseMode = direction;
        this.boundingBox = structureBoundingBox;
    }

    public static ComponentGSVillageUndertaker buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, EnumFacing facing, int par7) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(par3, par4, par5, 0, 0, 0, X_LENGTH, HEIGHT, Z_LENGTH, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentGSVillageUndertaker(startPiece, par7, random, structureboundingbox, facing) : null;
    }

    public static StructureBoundingBox getBoundingBox(EnumFacing facing, int x, int z) {
        return BoundingBoxHelper.getCorrectBox(facing, x, 64, z, X_LENGTH, HEIGHT, Z_LENGTH, 0);
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);

            if (this.averageGroundLevel < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }

        this.fillWithAir(world, boundingBox, 0, 1, 0, X_LENGTH, HEIGHT, Z_LENGTH);

        IBlockState groundState;
        int biomeId = world.getBiomeGenForCoords(new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0))).biomeID;
        if (biomeId == BiomeGenBase.desert.biomeID || biomeId == BiomeGenBase.desertHills.biomeID) {
            groundState = Blocks.sand.getDefaultState();
        } else {
            groundState = Blocks.grass.getDefaultState();
        }
        this.func_175804_a(world, boundingBox, 0, 0, 6, X_LENGTH, 0, Z_LENGTH, groundState, groundState, false);

        IBlockState glassState = Blocks.stained_glass_pane.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.GRAY);
        IBlockState darkClayState = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BLACK);
        IBlockState brownClayState = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);

        // ground
        this.func_175804_a(world, boundingBox, 1, 0, 0, 11, 0, 5, darkClayState, darkClayState, false);

        // fence
        this.func_175804_a(world, boundingBox, 1, 1, 1, 1, 1, 13, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 2, 1, 5, 4, 1, 5, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 1, 1, 5, 1, 3, 5, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);

        this.func_175804_a(world, boundingBox, 2, 1, 13, 11, 1, 13, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 11, 1, 6, 11, 1, 12, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);

        this.func_175804_a(world, boundingBox, 6, 1, 0, 7, 1, 0, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 9, 1, 0, 10, 1, 0, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
        this.func_175804_a(world, boundingBox, 11, 1, 0, 11, 3, 0, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);

        //gate
        IBlockState gateState = Blocks.dark_oak_fence_gate.getDefaultState().withProperty(BlockFenceGate.FACING, this.coordBaseMode);
        this.func_175811_a(world, gateState, 3, 1, 5, boundingBox);

        // candles
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 1, 2, 1, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 1, 2, 4, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 2, 2, 5, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 4, 2, 5, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 7, 2, 0, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 9, 2, 0, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 1, 2, 13, boundingBox);
        this.func_175811_a(world, GSBlock.candle.getDefaultState(), 11, 2, 13, boundingBox);

        // graves
        int graveType = GraveStoneHelper.getRandomGrave(GraveStoneHelper.getPlayerGraveTypes(world,
                new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0))), random);
        EntityGroupOfGravesMobSpawnerHelper spawnerHelper = GraveGenerationHelper.createSpawnerHelper(world, boundingBox);
        IBlockState graveState = GSBlock.graveStone.getDefaultState().withProperty(BlockGSGraveStone.FACING, this.coordBaseMode.getOpposite());

        for (int x = 3; x < 11; x += 2) {
            for (int z = 7; z < 13; z += 2) {
                GraveGenerationHelper.placeGrave(this, world, random, x, 1, z, graveState, graveType, null, spawnerHelper, true);
            }
        }

        // walls
        this.func_175804_a(world, boundingBox, 5, 1, 2, 5, 3, 2, darkClayState, darkClayState, false);
        this.func_175804_a(world, boundingBox, 5, 1, 5, 5, 3, 5, darkClayState, darkClayState, false);
        this.func_175804_a(world, boundingBox, 11, 1, 2, 11, 3, 2, darkClayState, darkClayState, false);
        this.func_175804_a(world, boundingBox, 11, 1, 5, 11, 3, 5, darkClayState, darkClayState, false);
        this.func_175804_a(world, boundingBox, 7, 1, 2, 7, 3, 2, darkClayState, darkClayState, false);
        this.func_175804_a(world, boundingBox, 9, 1, 2, 9, 3, 2, darkClayState, darkClayState, false);
        this.func_175811_a(world, darkClayState, 8, 3, 2, boundingBox);

        this.func_175804_a(world, boundingBox, 5, 1, 3, 5, 3, 4, brownClayState, brownClayState, false);
        this.func_175804_a(world, boundingBox, 11, 1, 3, 11, 3, 4, brownClayState, brownClayState, false);
        this.func_175804_a(world, boundingBox, 6, 1, 5, 10, 3, 5, brownClayState, brownClayState, false);
        this.func_175804_a(world, boundingBox, 6, 1, 2, 6, 3, 2, brownClayState, brownClayState, false);
        this.func_175804_a(world, boundingBox, 10, 1, 2, 10, 3, 2, brownClayState, brownClayState, false);

        this.func_175811_a(world, glassState, 6, 2, 2, boundingBox);
        this.func_175811_a(world, glassState, 10, 2, 2, boundingBox);

        // door
        //placeDoorAtCurrentPosition
        this.func_175810_a(world, boundingBox, random, 8, 1, 2, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.dark_oak_door, 3)));

        // book shelf
        this.func_175804_a(world, boundingBox, 6, 1, 3, 6, 1, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
        this.func_175811_a(world, Blocks.bookshelf.getDefaultState(), 10, 1, 4, boundingBox);

        // candle
        this.generateSkullCandle(world, boundingBox, 10, 2, 4, coordBaseMode);

        // painting
        this.generatePainting(world, 5, 2, 3, coordBaseMode);

        // bed
        this.generateBed(world, 9, 1, 3, boundingBox);

        // roof
        IBlockState slabState = Blocks.wooden_slab.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        IBlockState stairsState = Blocks.dark_oak_stairs.getDefaultState().withProperty(BlockStairs.FACING, coordBaseMode);
        IBlockState stairsBackState = Blocks.dark_oak_stairs.getDefaultState().withProperty(BlockStairs.FACING, coordBaseMode.getOpposite());
        this.func_175804_a(world, boundingBox, 1, 4, 1, 11, 4, 5, slabState, slabState, false);

        this.func_175804_a(world, boundingBox, 0, 4, 0, 12, 4, 0, stairsState, stairsState, false);
        this.func_175804_a(world, boundingBox, 0, 4, 6, 12, 4, 6, stairsBackState, stairsBackState, false);

        this.func_175804_a(world, boundingBox, 0, 5, 1, 12, 5, 1, stairsState, stairsState, false);
        this.func_175804_a(world, boundingBox, 0, 5, 5, 12, 5, 5, stairsBackState, stairsBackState, false);

        this.func_175804_a(world, boundingBox, 0, 6, 2, 12, 6, 2, stairsState, stairsState, false);
        this.func_175804_a(world, boundingBox, 0, 6, 4, 12, 6, 4, stairsBackState, stairsBackState, false);

        this.func_175804_a(world, boundingBox, 0, 7, 3, 12, 7, 3, slabState, slabState, false);

        IBlockState planksState = Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        this.func_175804_a(world, boundingBox, 1, 4, 1, 1, 4, 5, planksState, planksState, false);
        this.func_175804_a(world, boundingBox, 11, 4, 1, 11, 4, 5, planksState, planksState, false);
        this.func_175811_a(world, planksState, 1, 5, 2, boundingBox);
        this.func_175811_a(world, planksState, 1, 5, 4, boundingBox);
        this.func_175811_a(world, planksState, 11, 5, 2, boundingBox);
        this.func_175811_a(world, planksState, 11, 5, 4, boundingBox);

        this.func_175811_a(world, planksState, 1, 6, 3, boundingBox);
        this.func_175811_a(world, planksState, 11, 6, 3, boundingBox);

        this.func_175811_a(world, glassState, 1, 5, 3, boundingBox);
        this.func_175811_a(world, glassState, 11, 5, 3, boundingBox);


        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Z_LENGTH; j++) {
                this.clearCurrentPositionBlocksUpwards(world, i, 8, j, boundingBox);
                this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), i, -1, j, boundingBox);
            }
        }

        this.spawnVillagers(world, boundingBox, 8, 1, 3, 1);
        return true;
    }

    @Override
    protected void func_175811_a(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox) {
        int xPos = this.getXWithOffset(x, z);
        int yPos = this.getYWithOffset(y);
        int zPos = this.getZWithOffset(x, z);

        BlockPos pos = new BlockPos(xPos, yPos, zPos);
        if (boundingBox.func_175898_b(pos)) {
            world.setBlockState(pos, blockState, 2);
        }
    }

    /**
     * Returns the villager type to spawn in this component, based on the number
     * of villagers already spawned.
     */
    @Override
    protected int func_180779_c(int par1, int par2) {
        return VillageHandlerGSUndertaker.UNDERTAKER_ID;
    }

    protected void generateBed(World world, int x, int y, int z, StructureBoundingBox boundingBox) {
        EnumFacing facing = getBedMeta(this.coordBaseMode);
        IBlockState bedState = Blocks.bed.getDefaultState().withProperty(BlockBed.FACING, facing);
        this.func_175811_a(world, bedState.withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), x, y, z, boundingBox);
        this.func_175811_a(world, bedState.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), x + 1, y, z, boundingBox);
    }

    public static EnumFacing getBedMeta(EnumFacing direction) {
        switch (direction) {
            case WEST:
            case EAST:
                return EnumFacing.SOUTH;
            case SOUTH:
            case NORTH:
            default:
                return EnumFacing.EAST;
        }
    }

    public byte getSkullCandleDirection(EnumFacing direction) {
        switch (direction) {
            case SOUTH:
            case EAST:
                return 3;
            case WEST:
                return 5;
            case NORTH:
            default:
                return 1;
        }
    }

    protected void generateSkullCandle(World world, StructureBoundingBox boundingBox, int x, int y, int z, EnumFacing direction) {
        BlockPos pos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (world.getBlockState(pos).getBlock() != GSBlock.skullCandle) {
            IBlockState skullCandleState = GSBlock.skullCandle.getDefaultState().withProperty(BlockGSSkullCandle.VARIANT, EnumSkullCandle.SKELETON_SKULL);
            this.func_175811_a(world, skullCandleState, x, y, z, boundingBox);
            TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getTileEntity(pos);
            if (tileEntity != null) {
                tileEntity.setRotation(getSkullCandleDirection(direction));
            }
        }
    }

    private EntityPainting.EnumArt[] paintings = {
            EntityPainting.EnumArt.SKULL_AND_ROSES,
            EntityPainting.EnumArt.WITHER,
            EntityPainting.EnumArt.STAGE
    };

    protected void generatePainting(World world, int x, int y, int z, EnumFacing direction) {
        z += getPaintingZCoordinateShift(direction);
        int xCoord = this.getXWithOffset(x, z);
        int yCoord = this.getYWithOffset(y);
        int zCoord = this.getZWithOffset(x, z);

        if (checkPainting(world, xCoord, yCoord, zCoord)) {
            EntityPainting painting = new EntityPainting(world, new BlockPos(xCoord, yCoord, zCoord), getPaintingDirection(direction));
            painting.art = paintings[world.rand.nextInt(paintings.length)];
            world.spawnEntityInWorld(painting);
        }
    }

    public static int getPaintingZCoordinateShift(EnumFacing direction) {
        if (direction == EnumFacing.SOUTH || direction == EnumFacing.NORTH) {
            return 1;
        } else {
            return 0;
        }
    }

    public static EnumFacing getPaintingDirection(EnumFacing direction) {
        switch (direction) {
            case WEST:
            case EAST:
                return EnumFacing.SOUTH;
            case SOUTH:
            case NORTH:
            default:
                return EnumFacing.EAST;
        }
    }

    public static boolean checkPainting(World world, int x, int y, int z) {
        return world.getEntitiesWithinAABB(EntityPainting.class, AxisAlignedBB.fromBounds(x, y, z,
                x, y, z).expand(1, 1, 1)).size() == 0;
    }

    @Override
    public NBTTagCompound func_143010_b() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("id", "GSVillageUndertaker");
        nbttagcompound.setTag("BB", this.boundingBox.func_151535_h());
        nbttagcompound.setInteger("O", this.coordBaseMode == null ? -1 : this.coordBaseMode.getHorizontalIndex());
        nbttagcompound.setInteger("GD", this.componentType);
        this.writeStructureToNBT(nbttagcompound);
        return nbttagcompound;
    }

    @Override
    public void placeBlockAtCurrentPosition(World world, IBlockState blockState, int x, int y, int z, StructureBoundingBox boundingBox) {
        func_175811_a(world, blockState, x, y, z, boundingBox);
    }

    @Override
    public int getXWithOffset(int x, int z) {
        return super.getXWithOffset(x, z);
    }

    @Override
    public int getYWithOffset(int y) {
        return super.getYWithOffset(y);
    }

    @Override
    public int getZWithOffset(int x, int z) {
        return super.getZWithOffset(x, z);
    }
}
