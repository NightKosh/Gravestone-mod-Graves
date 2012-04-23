package GraveStone.structures;

import java.util.Random;
import GraveStone.GraveStoneConfig;
import GraveStone.mod_GraveStone;
import GraveStone.tileentity.TileEntityGSMemorial;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSMemorial extends ComponentGSCemeteryCatacombs {

    public static final int X_LENGTH = 3;
    public static final int HEIGHT = 7;
    public static final int Z_LENGTH = 3;

    public ComponentGSMemorial(int direction, Random random, int x, int z) {
        super(direction);
        boundingBox = getCorrectBox(direction, x + (16 - X_LENGTH) / 2, 64, z + (16 - Z_LENGTH) / 2, X_LENGTH, HEIGHT, Z_LENGTH);
    }

    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);
        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + HEIGHT - 1, 0);

        int groundID;
        if (world.getBiomeGenForCoords(getXWithOffset(0, 0), getZWithOffset(0, 0)).equals("desert")) {
            groundID = Block.sand.blockID;
        } else {
            groundID = Block.grass.blockID;
        }
        
        this.fillWithAir(world, boundingBox, 0, 1, 2, 0, 6, 2);
        this.fillWithBlocks(world, boundingBox, 0, 0, 0, 2, 0, 2, groundID, Block.grass.blockID, false);
        
        byte memorialType = (byte) random.nextInt(mod_GraveStone.memorial.MEMORIAL_TYPE_COUNT);
        placeMemorial(world, random, 1, 1, 1, mod_GraveStone.memorial.getMetaDirection(coordBaseMode), memorialType);
        
        for (int x = 1; x < 3; x++) {
            for (int z = 1; z < 3; z++) {
                this.fillCurrentPositionBlocksDownwards(world, groundID, 0, x, -1, z, boundingBox);
            }
        }
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                this.clearCurrentPositionBlocksUpwards(world, x, HEIGHT, z, boundingBox);
            }
        }
        
        return true;
    }
    
    

    protected void placeMemorial(World world, Random random, int x, int y, int z, int memorialMeta, byte memorialType) {
        this.placeBlockAtCurrentPosition(world, GraveStoneConfig.memorialID, memorialMeta, x, y, z, boundingBox);
        TileEntityGSMemorial tileEntity = (TileEntityGSMemorial) world.getBlockTileEntity(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (tileEntity != null) {
            tileEntity.setGraveType(memorialType);
        }
    }
}
