package gravestone.structures.village;

import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSSkullCandle;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
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
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 12, 7, 6, par6);
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

        this.fillWithBlocks(world, boundingBox, 0, 1, 0, 13, 7, 6, Blocks.air, Blocks.air, false);


        int darkClayMeta = 15;
        int brownClayMeta = 12;
        int darkGlassMeta = 15;
        int darkSlabMeta = 5;
        int graveMeta = GraveStoneHelper.getMetaDirection(this.coordBaseMode);

        // ground
        this.fillWithMetadataBlocks(world, boundingBox, 1, 0, 0, 11, 0, 5, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 2, 4, 0, 4, Blocks.soul_sand, Blocks.soul_sand, false);

        // fence
        this.fillWithBlocks(world, boundingBox, 1, 1, 1, 1, 1, 4, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 2, 1, 5, 4, 1, 5, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 5, 1, 3, 5, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);

        this.fillWithBlocks(world, boundingBox, 6, 1, 0, 7, 1, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 9, 1, 0, 10, 1, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);
        this.fillWithBlocks(world, boundingBox, 11, 1, 0, 11, 3, 0, Blocks.nether_brick_fence, Blocks.nether_brick_fence, false);

        // candles
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 1, 2, 1, boundingBox);
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 1, 2, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 2, 2, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 4, 2, 5, boundingBox);
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 7, 2, 0, boundingBox);
        this.placeBlockAtCurrentPosition(world, GSBlock.candle, 0, 9, 2, 0, boundingBox);

        // graves
        this.fillWithMetadataBlocks(world, boundingBox, 2, 1, 4, 4, 1, 4, GSBlock.graveStone, graveMeta, GSBlock.graveStone, graveMeta, false);

        // walls
        this.fillWithMetadataBlocks(world, boundingBox, 5, 1, 2, 5, 3, 2, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 5, 1, 5, 5, 3, 5, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 11, 1, 2, 11, 3, 2, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 11, 1, 5, 11, 3, 5, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 7, 1, 2, 7, 3, 2, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 9, 1, 2, 9, 3, 2, Blocks.stained_hardened_clay, darkClayMeta, Blocks.stained_hardened_clay, darkClayMeta, false);
        this.placeBlockAtCurrentPosition(world, Blocks.stained_hardened_clay, darkClayMeta, 8, 3, 2, boundingBox);

        this.fillWithMetadataBlocks(world, boundingBox, 5, 1, 3, 5, 3, 4, Blocks.stained_hardened_clay, brownClayMeta, Blocks.stained_hardened_clay, brownClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 11, 1, 3, 11, 3, 4, Blocks.stained_hardened_clay, brownClayMeta, Blocks.stained_hardened_clay, brownClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 6, 1, 5, 10, 3, 5, Blocks.stained_hardened_clay, brownClayMeta, Blocks.stained_hardened_clay, brownClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 6, 1, 2, 6, 3, 2, Blocks.stained_hardened_clay, brownClayMeta, Blocks.stained_hardened_clay, brownClayMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 10, 1, 2, 10, 3, 2, Blocks.stained_hardened_clay, brownClayMeta, Blocks.stained_hardened_clay, brownClayMeta, false);

        this.placeBlockAtCurrentPosition(world, Blocks.stained_glass_pane, darkGlassMeta, 6, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.stained_glass_pane, darkGlassMeta, 10, 2, 2, boundingBox);

        // door
        this.placeDoorAtCurrentPosition(world, boundingBox, random, 8, 1, 2, this.getMetadataWithOffset(Blocks.wooden_door, 3));

        // book shelf
        this.fillWithBlocks(world, boundingBox, 6, 1, 3, 6, 1, 4, Blocks.bookshelf, Blocks.bookshelf, false);
        this.placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 10, 1, 4, boundingBox);

        // candle
        this.generateSkullCandle(world, boundingBox, 0, 10, 2, 4, coordBaseMode);

        // painting
        this.generatePainting(world, 5, 2, 3, coordBaseMode);

        // bed
        this.generateBed(world, 9, 1, 3, boundingBox);

        // roof
        int stairsMeta = this.getMetadataWithOffset(Blocks.stone_stairs, 3);
        int backStairsMeta = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
        this.fillWithMetadataBlocks(world, boundingBox, 1, 4, 1, 11, 4, 5, Blocks.wooden_slab, darkSlabMeta, Blocks.wooden_slab, darkSlabMeta, false);

        this.fillWithMetadataBlocks(world, boundingBox, 0, 4, 0, 12, 4, 0, Blocks.dark_oak_stairs, stairsMeta, Blocks.dark_oak_stairs, stairsMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 0, 4, 6, 12, 4, 6, Blocks.dark_oak_stairs, backStairsMeta, Blocks.dark_oak_stairs, backStairsMeta, false);

        this.fillWithMetadataBlocks(world, boundingBox, 0, 5, 1, 12, 5, 1, Blocks.dark_oak_stairs, stairsMeta, Blocks.wooden_slab, stairsMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 0, 5, 5, 12, 5, 5, Blocks.dark_oak_stairs, backStairsMeta, Blocks.dark_oak_stairs, backStairsMeta, false);

        this.fillWithMetadataBlocks(world, boundingBox, 0, 6, 2, 12, 6, 2, Blocks.dark_oak_stairs, stairsMeta, Blocks.dark_oak_stairs, stairsMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 0, 6, 4, 12, 6, 4, Blocks.dark_oak_stairs, backStairsMeta, Blocks.dark_oak_stairs, backStairsMeta, false);

        this.fillWithMetadataBlocks(world, boundingBox, 0, 7, 3, 12, 7, 3, Blocks.wooden_slab, darkSlabMeta, Blocks.wooden_slab, darkSlabMeta, false);


        this.fillWithMetadataBlocks(world, boundingBox, 1, 4, 1, 1, 4, 5, Blocks.planks, darkSlabMeta, Blocks.planks, darkSlabMeta, false);
        this.fillWithMetadataBlocks(world, boundingBox, 11, 4, 1, 11, 4, 5, Blocks.planks, darkSlabMeta, Blocks.planks, darkSlabMeta, false);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 1, 5, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 1, 5, 4, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 11, 5, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 11, 5, 4, boundingBox);

        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 1, 6, 3, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, darkSlabMeta, 11, 6, 3, boundingBox);

        this.placeBlockAtCurrentPosition(world, Blocks.stained_glass_pane, darkGlassMeta, 1, 5, 3, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.stained_glass_pane, darkGlassMeta, 11, 5, 3, boundingBox);


        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                this.clearCurrentPositionBlocksUpwards(world, j, 8, i, boundingBox);
                this.func_151554_b(world, Blocks.cobblestone, 0, j, -1, i, boundingBox);
            }
        }

        this.spawnVillagers(world, boundingBox, 8, 1, 3, 1);
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

    protected void generateBed(World world, int x, int y, int z, StructureBoundingBox boundingBox) {
        int bedMeta = getBedMeta(this.coordBaseMode);
        this.placeBlockAtCurrentPosition(world, Blocks.bed, bedMeta, x, y, z, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.bed, bedMeta + 8, x + 1, y, z, boundingBox);
    }

    public static int getBedMeta(int direction) {
        switch (direction) {
            case 0: // S
                return 3;
            case 1: // W
                return 0;
            case 3: // E
                return 0;
            case 2: // N
            default:
                return 3;
        }
    }

    public byte getSkullCandleDirection(int direction) {
        byte meta;
        switch (direction) {
            case 0: // S
                meta = 3;
            case 1: // W
                meta = 5;
            case 3: // E
                meta = 3;
            case 2: // N
            default:
                meta = 1;
        }
        return (byte) meta;
    }

    protected void generateSkullCandle(World world, StructureBoundingBox boundingBox, int meta, int x, int y, int z, int direction) {
        if (world.getBlock(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z)) != GSBlock.skullCandle) {
            this.placeBlockAtCurrentPosition(world, GSBlock.skullCandle, meta, x, y, z, boundingBox);
            TileEntityGSSkullCandle tileEntity = (TileEntityGSSkullCandle) world.getTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
            if (tileEntity != null) {
                tileEntity.setRotation(getSkullCandleDirection(direction));
            }
        }
    }

    private EntityPainting.EnumArt[] paintings = {
            EntityPainting.EnumArt.SkullAndRoses,
            EntityPainting.EnumArt.Wither,
            EntityPainting.EnumArt.Stage
    };

    protected void generatePainting(World world, int x, int y, int z, int direction) {
        z += getPaintingZCoordinateShift(direction);
        int xCoord = this.getXWithOffset(x, z);
        int yCoord = this.getYWithOffset(y);
        int zCoord = this.getZWithOffset(x, z);

        if (checkPainting(world, xCoord, yCoord, zCoord)) {
            EntityPainting painting = new EntityPainting(world, xCoord, yCoord, zCoord, getPaintingDirection(direction));
            painting.art = paintings[world.rand.nextInt(paintings.length)];
            world.spawnEntityInWorld(painting);
        }
    }

    public static int getPaintingZCoordinateShift(int direction) {
        if (direction == 0 || direction == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getPaintingDirection(int direction) {
        switch (direction) {
            case 0: // S
                return 3;
            case 1: // W
                return 0;
            case 3: // E
                return 0;
            case 2: // N
            default:
                return 3;
        }
    }

    public static boolean checkPainting(World world, int x, int y, int z) {
        return world.getEntitiesWithinAABB(EntityPainting.class, AxisAlignedBB.getAABBPool().getAABB(x, y, z,
                x, y, z).expand(1, 1, 1)).size() == 0;
    }
}
