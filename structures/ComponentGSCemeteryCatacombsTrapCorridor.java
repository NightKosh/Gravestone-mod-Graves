package GraveStone.structures;

import java.util.Random;
import GraveStone.GraveStoneConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ComponentGSCemeteryCatacombsTrapCorridor extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;
    private static final int[] POTIONS = {32764, 32692};

    public ComponentGSCemeteryCatacombsTrapCorridor(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 1;
        boundingBox = getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH);

        topZEnd = Z_LENGTH - 1;
        topXEnd = 1;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World world, Random random) {
        //printCoord();

        this.fillWithAir(world, boundingBox, 2, 1, 0, 4, 3, 3);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 5, 0, 3, false, random, getCemeteryCatacombsStones());
        // trap floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, GraveStoneConfig.timeTrapID, GraveStoneConfig.timeTrapID, false);
        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 1, 1, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 1, 5, 3, 3, false, random, getCemeteryCatacombsStones());

        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 4, 5, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls 
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        // blocks
        this.placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 0, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 6, 1, 2, boundingBox);

        // tripWire hook
        this.placeBlockAtCurrentPosition(world, Block.tripWireSource.blockID, getRightItemDirection(coordBaseMode), 1, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Block.tripWireSource.blockID, getLeftItemDirection(coordBaseMode), 5, 1, 2, boundingBox);

        // tripWire
        this.fillWithBlocks(world, boundingBox, 2, 1, 2, 4, 1, 2, Block.tripWire.blockID, Block.tripWire.blockID, false);

        // dispencer
        this.generateDispenser(world, boundingBox, random, 0, 2, 2, getLeftItemDirection(coordBaseMode));
        this.generateDispenser(world, boundingBox, random, 6, 2, 2, getRightItemDirection(coordBaseMode));

        this.placeBlockAtCurrentPosition(world, 0, 0, 1, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, 0, 0, 5, 2, 2, boundingBox);
        
        // web
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 2, 1, 1, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 4, 2, 2, Block.web.blockID, 0);
        this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, 3, 3, 3, Block.web.blockID, 0);
        
        return true;
    }

    /**
     * Used to generate dispenser contents for structures. ex: Jungle Temples.
     */
    protected void generateDispenser(World world, StructureBoundingBox boundingBox, Random random, int xCoord, int yCoord, int zCoord, int direction) {
        int x = this.getXWithOffset(xCoord, zCoord);
        int y = this.getYWithOffset(yCoord);
        int z = this.getZWithOffset(xCoord, zCoord);

        if (boundingBox.isVecInside(x, y, z) && world.getBlockId(x, y, z) != Block.dispenser.blockID) {
            world.setBlock(x, y, z, Block.dispenser.blockID);
            setDispenserMeta(world, x, y, z, direction);
            TileEntityDispenser dispenser = (TileEntityDispenser) world.getBlockTileEntity(x, y, z);

            if (dispenser != null) {
                generateDispenserContents(random, dispenser);
            }
        }
    }

    /**
     * Generates the Dispenser contents.
     */
    public static void generateDispenserContents(Random random, TileEntityDispenser dispenserEntity) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = new ItemStack(Item.potion.itemID, getRandomCount(random), POTIONS[random.nextInt(POTIONS.length)]);
            dispenserEntity.setInventorySlotContents(i, stack);
        }
    }

    private static int getRandomCount(Random random) {
        return 10 + random.nextInt(64);
    }

    /*
     * set dispenser metadata
     */
    private void setDispenserMeta(World world, int x, int y, int z, int direction) {
        switch (direction) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                break;
        }
    }
}
