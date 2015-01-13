package gravestone.structures;

import gravestone.block.GraveStoneHelper;
import gravestone.core.GSBlock;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveGenerationHelper {

    private GraveGenerationHelper() {
    }

    /**
     * Place component
     *
     * @param component Component instance
     * @param world     World object
     * @param random
     * @param x         X coord
     * @param y         Y coord
     * @param z         Z coord
     * @param graveMeta Grave metadata
     * @param graveType Grave type
     */
    // TODO
    public static void placeGrave(ComponentGraveStone component, World world, Random random, int x, int y, int z, int graveMeta, byte graveType, Item sword, boolean allLoot) {
        component.placeBlockAtCurrentPosition(world, GSBlock.graveStone, graveMeta, x, y, z, component.getBoundingBox());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(new BlockPos(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z)));

        if (tileEntity != null) {
            if (GraveStoneHelper.isSwordGrave(graveType)) {
                tileEntity.setSword(new ItemStack(sword));
            }

            tileEntity.setGraveType(graveType);
            tileEntity.setGraveContent(random, GraveStoneHelper.isPetGrave(graveType), allLoot);
        }
    }

    /**
     * Fill squer with graves
     *
     * @param component Component instance
     * @param world     World object
     * @param random
     * @param xStart    Start X coord
     * @param yStart    Start Y coord
     * @param zStart    Start Z coord
     * @param xEnd      End X coord
     * @param yEnd      End Y coord
     * @param zEnd      End Z coord
     * @param graveMeta Grave metadata
     * @param graveType Grave type
     */
    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd, int graveMeta, byte graveType, Item sword, boolean allLoot) {
        for (int y = yStart; y <= yEnd; ++y) {
            for (int x = xStart; x <= xEnd; ++x) {
                for (int z = zStart; z <= zEnd; ++z) {
                    placeGrave(component, world, random, x, y, z, graveMeta, graveType, sword, allLoot);
                }
            }
        }
    }

    /**
     * Check can be grave placed here
     *
     * @param world World object
     * @param x     X coord
     * @param minY  Min y coord
     * @param z     Z coord
     * @param maxY  Max y coord
     */
    public static boolean canPlaceGrave(World world, int x, int minY, int z, int maxY) {
        Block block;

        for (int y = maxY; y >= minY - 1; y--) {
            BlockPos pos = new BlockPos(x, y, z);
            block = world.getBlockState(pos).getBlock();

            if (block != null) {
                if (block.equals(Blocks.water) || block.equals(Blocks.lava)) {
                    return false;
                } else if (GraveStoneHelper.canPlaceBlockAt(world, block, pos)) {
                    return true;
                }
            }
        }

        return false;
    }
}
