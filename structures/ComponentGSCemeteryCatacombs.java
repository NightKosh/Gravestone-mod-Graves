package GraveStone.structures;

import GraveStone.GraveStoneConfig;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.tileentity.GSGraveStoneItems;
import GraveStone.tileentity.TileEntityGSGraveStone;
import GraveStone.tileentity.TileEntityGSMemorial;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
abstract class ComponentGSCemeteryCatacombs extends StructureComponent {

    private static final StructureGSCemeteryCatacombsStones cemeteryCatacombsStones = new StructureGSCemeteryCatacombsStones();
    protected int leftXEnd = 0;
    protected int rightXEnd = 0;
    protected int topXEnd = 0;
    protected int leftZEnd = 0;
    protected int rightZEnd = 0;
    protected int topZEnd = 0;
    protected int yEnd = 0;
    protected int xShift = 0;
    protected int zShift = 0;
    protected boolean goTop = true;
    protected ComponentGSCemeteryCatacombs prevComponent;
    protected ComponentGSCemeteryCatacombs[] nextComponents;

    protected ComponentGSCemeteryCatacombs(int direction) {
        super(direction);
        coordBaseMode = direction;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        return true;
    }

    public boolean addComponentParts(World world, Random random) {
        return true;
    }

    /*
     * return bounding Box for structure component
     */
    protected StructureBoundingBox getCorrectBox(int direction, int x, int y, int z, int xLength, int height, int zLength) {
        int minX = 0;
        int maxX = 0;

        int minY = y;
        int maxY = y + height;

        int minZ = 0;
        int maxZ = 0;

        switch (direction) {
            case 0:
                minX = x - xShift;
                maxX = x - xShift + xLength;

                minZ = z;
                maxZ = z + zLength;
                break;
            case 1:
                minX = x - zLength;
                maxX = x;

                minZ = z - xShift;
                maxZ = z - xShift + xLength;
                break;
            case 2:
                minX = x - xShift;
                maxX = x - xShift + xLength;

                minZ = z - zLength;
                maxZ = z;
                break;
            case 3:
                minX = x;
                maxX = x + zLength;

                minZ = z - xShift;
                maxZ = z - xShift + xLength;
                break;
        }
        return new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the
     * supplied BoundingBox. (A median of all the levels in the BB's horizontal
     * rectangle).
     */
    protected int getAverageGroundLevel(World world, StructureBoundingBox structureBoundingBox) {
        int height = 0;
        int count = 0;

        for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; ++z) {
            for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; ++x) {
                if (structureBoundingBox.isVecInside(x, 64, z)) {
                    height += Math.max(world.getTopSolidOrLiquidBlock(x, z), world.provider.getAverageGroundLevel());
                    count++;
                }
            }
        }

        if (count == 0) {
            return -1;
        } else {
            return height / count;
        }
    }

    /*
     * return ground level at x z coordinates
     */
    protected int getGroundLevel(World world, int x, int z) {
        return world.getTopSolidOrLiquidBlock(x, z);
    }

    protected int invertDirection(int direction) {

        return 0;
    }

    public int getLeftXEnd() {
        return this.getXWithOffset(leftXEnd, leftZEnd);
    }

    public int getLeftZEnd() {
        return this.getZWithOffset(leftXEnd, leftZEnd);
    }

    public int getRightXEnd() {
        return this.getXWithOffset(rightXEnd, rightZEnd);
    }

    public int getRightZEnd() {
        return this.getZWithOffset(rightXEnd, rightZEnd);
    }

    public int getTopZEnd() {
        return this.getZWithOffset(topXEnd, topZEnd);
    }

    public int getTopXEnd() {
        return this.getXWithOffset(topXEnd, topZEnd);
    }

    public int getYEnd() {
        return this.boundingBox.minY + yEnd;
    }

    public static int getLeftDirection(int direction) {
        direction -= 1;
        if (direction < 0) {
            direction = 3;
        }
        return direction;
    }

    public static int getRightDirection(int direction) {
        direction += 1;
        if (direction > 3) {
            direction = 0;
        }
        return direction;
    }

    public static int getInvertDirection(int direction) {
        direction += 2;
        if (direction > 3) {
            direction -= 4;
        }
        return direction;
    }

    public static int getLeftItemDirection(int direction) {
        if (direction == 0 || direction == 1) {
            direction += 1;
            if (direction > 3) {
                direction = 0;
            }
        } else {
            direction -= 1;
            if (direction < 0) {
                direction = 3;
            }
        }
        return direction;
    }

    public static int getRightItemDirection(int direction) {
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

    protected void placeMemorial(World world, Random random, int x, int y, int z, int memorialMeta, byte memorialType) {
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.memorialID, memorialMeta, x, y, z, boundingBox);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getBlockTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
            tileEntity.setMemorialContent(random);
        }
    }

    protected void placeGrave(World world, Random random, int x, int y, int z, int graveMeta, byte graveType) {
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.graveStoneID, graveMeta, x, y, z, boundingBox);
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (tileEntity != null) {
            if (BlockGSGraveStone.isSwordGrave(graveType)) {
                tileEntity.setSword(BlockGSGraveStone.graveTypeToSwordType(graveType));
                tileEntity.setDamage(GSGraveStoneItems.getRandomDamage(random, 50));
            }
            tileEntity.setGraveType(graveType);
            tileEntity.setGraveContent(random);
        }
    }

    protected void fillGraves(World world, Random random, int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd, int graveMeta, byte graveType) {
        for (int y = yStart; y <= yEnd; ++y) {
            for (int x = xStart; x <= xEnd; ++x) {
                for (int z = zStart; z <= zEnd; ++z) {
                    this.placeGrave(world, random, x, y, z, graveMeta, graveType);
                }
            }
        }
    }

    protected void spawnBats(World world, Random random) {
        EntityBat bat;
        EntityLiving livingEntity;
        int batsCount = 3 + random.nextInt(8);
        for (byte i = 0; i < batsCount; i++) {
            bat = new EntityBat(world);
            bat.setLocationAndAngles(boundingBox.getCenterX() - 1.5 + random.nextInt(5), boundingBox.getCenterY(),
                    boundingBox.getCenterZ() - 1.5 + random.nextInt(5), 0.0F, 0.0F);

            livingEntity = (EntityLiving) bat;
            if (livingEntity.getCanSpawnHere()) {
                world.spawnEntityInWorld(bat);
            }
        }
    }

    public static StructureGSCemeteryCatacombsStones getCemeteryCatacombsStones() {
        return cemeteryCatacombsStones;
    }

    public boolean canBePlacedHere(StructureBoundingBox boundingBox) {
        if (coordBaseMode == 0 || coordBaseMode == 2) {
            return this.boundingBox.maxX > boundingBox.minX && this.boundingBox.minX < boundingBox.maxX
                    && this.boundingBox.maxZ - 1 > boundingBox.minZ && this.boundingBox.minZ + 1 < boundingBox.maxZ;
        } else {
            return this.boundingBox.maxX - 1 > boundingBox.minX && this.boundingBox.minX + 1 < boundingBox.maxX
                    && this.boundingBox.maxZ > boundingBox.minZ && this.boundingBox.minZ < boundingBox.maxZ;
        }
    }

    public boolean canGoOnlyTop() {
        return true;
    }

    /*
     * Generate chest with random loot type
     */
    protected void generateChest(World world, Random random, int xCoord, int yCoord, int zCoord, boolean defaultChest) {
        int y = this.getYWithOffset(yCoord);
        int x = this.getXWithOffset(xCoord, zCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        if (boundingBox.isVecInside(x, y, z)) {
            ChestGenHooks chest;
            switch (random.nextInt(8)) {
                case 1:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
                    break;
                case 2:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
                    break;
                case 3:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
                    break;
                case 4:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
                    break;
                case 5:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING);
                    break;
                case 6:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY);
                    break;
                case 7:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
                    break;
                default:
                    chest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR);
            }
            if (defaultChest) {
                this.generateStructureChestContents(world, boundingBox, random, xCoord, yCoord, zCoord, chest.getItems(random), chest.getCount(random));
            } else {
                this.generateTrappedChestContents(world, boundingBox, random, xCoord, yCoord, zCoord, chest.getItems(random), chest.getCount(random));
            }
        }
    }

    /**
     * Used to generate chests with items in it. ex: Temple Chests, Village
     * Blacksmith Chests, Mineshaft Chests.
     */
    protected boolean generateTrappedChestContents(World world, StructureBoundingBox boundingBox, Random random, int xCoord, int yCoord, int zCoord, WeightedRandomChestContent[] par7ArrayOfWeightedRandomChestContent, int par8) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        if (boundingBox.isVecInside(x, y, z) && world.getBlockId(x, y, z) != Block.chestTrapped.blockID) {
            world.setBlock(x, y, z, Block.chestTrapped.blockID, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, y, z);

            if (tileentitychest != null) {
                WeightedRandomChestContent.generateChestContents(random, par7ArrayOfWeightedRandomChestContent, tileentitychest, par8);
            }

            return true;
        } else {
            return false;
        }
    }

    /*
     * generate spawner
     */
    protected void generateSpawner(World world, int xCoord, int yCoord, int zCoord, String spawnerType) {
        int y = this.getYWithOffset(yCoord);
        int x = this.getXWithOffset(xCoord, zCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        if (boundingBox.isVecInside(x, y, z)) {
            world.setBlock(x, y, z, Block.mobSpawner.blockID);
            TileEntityMobSpawner tileEntity = (TileEntityMobSpawner) world.getBlockTileEntity(x, y, z);

            if (tileEntity != null) {
                tileEntity.func_98049_a().setMobID(spawnerType);
            }
        }
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at
     * hitting anything else.
     */
    @Override
    protected void fillCurrentPositionBlocksDownwards(World world, int blockId, int metadata, int xCoord, int yCoord, int zCoord, StructureBoundingBox boundingBox) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        while ((world.isAirBlock(x, y, z) || world.getBlockMaterial(x, y, z).isLiquid() || world.getBlockMaterial(x, y, z).isReplaceable()) && y > 1) {
            world.setBlock(x, y, z, blockId, metadata, 2);
            --y;
        }
    }

    protected int getDirection() {
        return coordBaseMode;
    }
}
