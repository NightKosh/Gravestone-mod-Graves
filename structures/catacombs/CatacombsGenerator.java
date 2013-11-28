package gravestone.structures.catacombs;

import gravestone.GraveStoneBiomes;
import gravestone.GraveStoneConfig;
import gravestone.GraveStoneLogger;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsGenerator {
    // chance to generate a structure

    private static final double CHANCE = 0.0005D;
    protected static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
    
    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateCatacombs && canSpawnStructureAtCoords(world, x, z, chance)) {
            int direction = rand.nextInt(4);
            CatacombsSurface surface = new CatacombsSurface(world, rand, x, z, direction);
            GraveStoneLogger.logInfo("Catacombs " + x + "x" + z);

            if (surface.getMausoleumY() > 55) {
                new CatacombsUnderground(world, rand, direction, surface.getMausoleumX(), surface.getMausoleumY(), surface.getMausoleumZ());
            }

            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }

        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && GraveStoneBiomes.getCatacombsBiomes().contains(world.getBiomeGenForCoords(x, z).biomeID) && noAnyInRange(x, z, 700);
    }

    protected boolean noAnyInRange(int x, int z, int range) {
        for (ChunkCoordIntPair position : structuresList) {
            if (position.chunkXPos > x - range && position.chunkXPos < x + range
                    && position.chunkZPos > z - range && position.chunkZPos < z + range) {
                return false;
            }
        }

        return true;
    }

    public static LinkedList<ChunkCoordIntPair> getStructuresList() {
        return structuresList;
    }
    /*
    private void componentRegistration() {
        MapGenStructureIO.func_143034_b(Bridge.class, "GSCMausoleum");
        MapGenStructureIO.func_143031_a(Bridge.class, "GSCBridge");
        MapGenStructureIO.func_143031_a(Corridor.class, "GSCCorridor");
        MapGenStructureIO.func_143031_a(CreeperRoom.class, "GSCCreeperRoom");
        MapGenStructureIO.func_143031_a(Crossing.class, "GSCCrossing");
        MapGenStructureIO.func_143031_a(EnderHall.class, "GSCEnderHall");
        MapGenStructureIO.func_143031_a(Entrance.class, "GSCEntrance");
        MapGenStructureIO.func_143031_a(Fence.class, "GSCFence");
        MapGenStructureIO.func_143031_a(GraveCorridor.class, "GSCGraveCorridor");
        MapGenStructureIO.func_143031_a(GraveHall.class, "GSCGraveHall");
        MapGenStructureIO.func_143031_a(GraveYard.class, "GSCGraveYard");
        //MapGenStructureIO.func_143031_a(Mausoleum.class, "GSCMausoleum");
        MapGenStructureIO.func_143031_a(MausoleumEntrance.class, "GSCMausoleumEntrance");
        MapGenStructureIO.func_143031_a(SpidersCorridor.class, "GSCSpidersCorridor");
        MapGenStructureIO.func_143031_a(Stairs.class, "GSCStairs");
        MapGenStructureIO.func_143031_a(StatuesHall.class, "GSCStatuesHall");
        MapGenStructureIO.func_143031_a(TrapCorridor.class, "GSCTrapCorridor");
        MapGenStructureIO.func_143031_a(Treasury.class, "GSCTreasury");
        MapGenStructureIO.func_143031_a(WitherHall.class, "GSCWitherHall");
    }
    */
}
