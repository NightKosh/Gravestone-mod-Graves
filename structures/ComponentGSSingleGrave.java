package net.minecraft.GraveStone.structures;

import java.util.Random;
import net.minecraft.GraveStone.mod_GraveStone;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ComponentGSSingleGrave extends ComponentGSCemeteryCatacombs {

    public ComponentGSSingleGrave(int direction, Random random, int x, int z) {
        super(direction);
        boundingBox = getCorrectBox(direction, x + 7, 64, z + 7, 1, 1, 1);
    }

    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = this.getAverageGroundLevel(world, boundingBox);
        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + 1, 0);

        placeGrave(world, random, 0, 0, 0, random.nextInt(mod_GraveStone.graveStone.GRAVE_TYPE_COUNT) * 4 + mod_GraveStone.graveStone.getMetaDirection(coordBaseMode));

        return true;
    }
}
