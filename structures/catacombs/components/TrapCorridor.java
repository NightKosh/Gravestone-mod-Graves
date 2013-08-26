package GraveStone.structures.catacombs.components;

import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.structures.BoundingBoxHelper;
import GraveStone.structures.ObjectsGenerationHelper;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TrapCorridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public TrapCorridor(int direction, Random random, int x, int y, int z) {
        super(direction);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topZEnd = Z_LENGTH - 1;
        topXEnd = 1;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
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
            ObjectsGenerationHelper.setDispenserMeta(world, x, y, z, direction);
            TileEntityDispenser dispenser = (TileEntityDispenser) world.getBlockTileEntity(x, y, z);

            if (dispenser != null) {
                ObjectsGenerationHelper.generateDispenserContents(random, dispenser);
            }
        }
    }
}
