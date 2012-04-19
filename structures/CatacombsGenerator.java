package net.minecraft.GraveStone.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.GraveStone.GraveStoneConfig;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class CatacombsGenerator {

    // list of allowed bioms for structure generator
    private static final ArrayList<BiomeGenBase> ALLOWED_BIOMS = new ArrayList<BiomeGenBase>(Arrays.asList(BiomeGenBase.plains, BiomeGenBase.forest, BiomeGenBase.taiga, BiomeGenBase.icePlains));
    // chance to generate a structure
    private static final double CHANCE = 0.0005D;
    protected static LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();

    public boolean generate(World world, Random rand, int x, int z, double chance) {
        if (GraveStoneConfig.generateCatacombs && canSpawnStructureAtCoords(world, x, z, chance)) {
            int direction = rand.nextInt(4);
            StructureGSCemeteryCatacombsSurface surface = new StructureGSCemeteryCatacombsSurface(world, rand, x, z, direction);
            System.out.println("Catacombs " + x + "x" + z);
            
            if (surface.getMausoleumY() > 55) {
                new StructureGSCemeteryCatacombsUnderground(world, rand, direction, surface.getMausoleumX(), surface.getMausoleumY(), surface.getMausoleumZ());
            }
            
            structuresList.add(new ChunkCoordIntPair(x, z));
            return true;
        }
        return false;
    }

    protected boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < CHANCE && ALLOWED_BIOMS.contains(world.getBiomeGenForCoords(x, z)) && noAnyInRange(x, z, 700);
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
}
