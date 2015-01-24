package gravestone.structures.catacombs;

import gravestone.config.GraveStoneConfig;
import gravestone.structures.catacombs.components.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CatacombsSurface {

    private final EnumFacing DIRECTION;
    private final int X, Z;
    private int mausoleumX, mausoleumY, mausoleumZ;

    public CatacombsSurface(World world, Random rand, int x, int z, EnumFacing direction) {
        DIRECTION = direction;
        X = x;
        Z = z;
        build(world, rand);
    }

    private void build(World world, Random rand) {
        buildMausoleum(world, rand);
        int xCoord = 0;
        int zCoord = 0;

        switch (DIRECTION) {
            case SOUTH:
                xCoord = X;
                zCoord = Z - 14;
                break;
            case NORTH:
                xCoord = X;
                zCoord = Z + 14;
                break;
            case EAST:
                xCoord = X - 14;
                zCoord = Z;
                break;
            case WEST:
                xCoord = X + 14;
                zCoord = Z;
                break;
        }

        new MausoleumEntrance(0, DIRECTION, rand,
                new StructureBoundingBox(xCoord, 60, zCoord, 13 + xCoord, 90, 13 + zCoord),
                mausoleumY).addComponentParts(world, rand);

        if (GraveStoneConfig.generateCatacombsGraveyard) {
            switch (DIRECTION) {
                case SOUTH:
                    buildGraveYard(world, rand, X + 15, Z + 2);
                    buildGraveYard(world, rand, X - 12, Z + 2);
                    buildGraveYard(world, rand, X + 2, Z + 15);
                    break;
                case NORTH:
                    buildGraveYard(world, rand, X + 15, Z + 2);
                    buildGraveYard(world, rand, X - 12, Z + 2);
                    buildGraveYard(world, rand, X + 2, Z - 13);
                    break;
                case EAST:
                    buildGraveYard(world, rand, X + 2, Z + 15);
                    buildGraveYard(world, rand, X + 2, Z - 12);
                    buildGraveYard(world, rand, X + 15, Z + 2);
                    break;
                case WEST:
                    buildGraveYard(world, rand, X + 2, Z + 15);
                    buildGraveYard(world, rand, X + 2, Z - 12);
                    buildGraveYard(world, rand, X - 11, Z + 2);
                    break;
            }

            for (byte shiftX = 1; shiftX < 4; shiftX++) {
                for (byte shiftZ = 1; shiftZ < 4; shiftZ++) {
                    xCoord = X + shiftX * 12 + 3;
                    zCoord = Z + shiftZ * 12 + 3;
                    buildGraveYard(world, rand, xCoord, zCoord);
                    xCoord = X - shiftX * 12;
                    zCoord = Z - shiftZ * 12;
                    buildGraveYard(world, rand, xCoord, zCoord);
                    xCoord = X + shiftX * 12 + 3;
                    zCoord = Z - shiftZ * 12;
                    buildGraveYard(world, rand, xCoord, zCoord);
                    xCoord = X - shiftX * 12;
                    zCoord = Z + shiftZ * 12 + 3;
                    buildGraveYard(world, rand, xCoord, zCoord);
                }
            }

            buildGraveYard(world, rand, X + 27, Z + 2);
            buildGraveYard(world, rand, X - 24, Z + 2);
            buildGraveYard(world, rand, X + 2, Z + 27);
            buildGraveYard(world, rand, X + 2, Z - 24);
            buildGraveYard(world, rand, X + 39, Z + 2);
            buildGraveYard(world, rand, X - 36, Z + 2);
            buildGraveYard(world, rand, X + 2, Z + 39);
            buildGraveYard(world, rand, X + 2, Z - 36);


            // fence
            if (DIRECTION == EnumFacing.SOUTH || DIRECTION == EnumFacing.NORTH) {
                if (DIRECTION == EnumFacing.SOUTH) {
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z - 38, X + 51, 240, Z - 38), true, true).addComponentParts(world, rand);
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z + 51, X + 51, 240, Z + 51), false, true).addComponentParts(world, rand);
                } else {
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z - 38, X + 51, 240, Z - 38), false, true).addComponentParts(world, rand);
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z + 51, X + 51, 240, Z + 51), true, true).addComponentParts(world, rand);
                }

                new Fence(0, CatacombsBaseComponent.getLeftDirection(DIRECTION), rand, new StructureBoundingBox(X - 38, 0, Z - 38, X - 38, 255, Z + 51), false, false).addComponentParts(world, rand);
                new Fence(0, CatacombsBaseComponent.getLeftDirection(DIRECTION), rand, new StructureBoundingBox(X + 51, 0, Z - 38, X + 51, 255, Z + 51), false, false).addComponentParts(world, rand);
            } else {
                if (DIRECTION == EnumFacing.WEST) {
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X + 51, 0, Z - 38, X + 51, 255, Z + 51), true, false).addComponentParts(world, rand);
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z - 38, X - 38, 255, Z + 51), false, false).addComponentParts(world, rand);
                } else {
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X + 51, 0, Z - 38, X + 51, 255, Z + 51), false, false).addComponentParts(world, rand);
                    new Fence(0, DIRECTION, rand, new StructureBoundingBox(X - 38, 0, Z - 38, X - 38, 255, Z + 51), true, false).addComponentParts(world, rand);
                }

                new Fence(0, CatacombsBaseComponent.getLeftDirection(DIRECTION), rand, new StructureBoundingBox(X - 38, 0, Z - 38, X + 51, 240, Z - 38), false, true).addComponentParts(world, rand);
                new Fence(0, CatacombsBaseComponent.getLeftDirection(DIRECTION), rand, new StructureBoundingBox(X - 38, 0, Z + 51, X + 51, 240, Z + 51), false, true).addComponentParts(world, rand);
            }
        }
    }

    private void buildMausoleum(World world, Random rand) {
        CatacombsBaseComponent mausoleum = new Mausoleum(0, DIRECTION, rand, new StructureBoundingBox(X, 60, Z, 13 + X, 90, 13 + Z));
        mausoleum.addComponentParts(world, rand);
        mausoleumX = mausoleum.getTopXEnd();
        mausoleumY = mausoleum.getYEnd();
        mausoleumZ = mausoleum.getTopZEnd();
    }

    private void buildGraveYard(World world, Random rand, int x, int z) {
        new GraveYard(0, DIRECTION, rand, new StructureBoundingBox(x, 4, z, x + 11, 255, z + 11)).addComponentParts(world, rand);
    }

    public int getMausoleumX() {
        return mausoleumX;
    }

    public int getMausoleumY() {
        return mausoleumY;
    }

    public int getMausoleumZ() {
        return mausoleumZ;
    }
}
