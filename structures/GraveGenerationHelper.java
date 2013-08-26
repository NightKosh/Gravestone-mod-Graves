package GraveStone.structures;

import GraveStone.GraveStoneConfig;
import GraveStone.block.GraveStoneHelper;
import GraveStone.tileentity.GSGraveStoneItems;
import GraveStone.tileentity.TileEntityGSGraveStone;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

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
     * @param world World object
     * @param random
     * @param x X coord
     * @param y Y coord
     * @param z Z coord
     * @param graveMeta Grave metadata
     * @param graveType Grave type
     */
    public static void placeGrave(ComponentGraveStone component, World world, Random random, int x, int y, int z, int graveMeta, byte graveType, boolean allLoot) {
        component.placeBlockAtCurrentPosition(world, GraveStoneConfig.graveStoneID, graveMeta, x, y, z, component.getBoundingBox());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z));

        if (tileEntity != null) {
            if (GraveStoneHelper.isSwordGrave(graveType)) {
                tileEntity.setSword(GraveStoneHelper.graveTypeToSwordType(graveType));
                tileEntity.setDamage(GSGraveStoneItems.getRandomDamage(random, 50));
            }

            tileEntity.setGraveType(graveType);
            tileEntity.setGraveContent(random, GraveStoneHelper.isPetGrave(graveType), allLoot);
        }
    }

    /**
     * Fill squer with graves
     *
     * @param component Component instance
     * @param world World object
     * @param random
     * @param xStart Start X coord
     * @param yStart Start Y coord
     * @param zStart Start Z coord
     * @param xEnd End X coord
     * @param yEnd End Y coord
     * @param zEnd End Z coord
     * @param graveMeta Grave metadata
     * @param graveType Grave type
     */
    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd, int graveMeta, byte graveType, boolean allLoot) {
        for (int y = yStart; y <= yEnd; ++y) {
            for (int x = xStart; x <= xEnd; ++x) {
                for (int z = zStart; z <= zEnd; ++z) {
                    placeGrave(component, world, random, x, y, z, graveMeta, graveType, allLoot);
                }
            }
        }
    }

    /**
     * Check can be grave placed here
     *
     * @param world World object
     * @param x X coord
     * @param minY Min y coord
     * @param z Z coord
     * @param maxY Max y coord
     */
    public static boolean canPlaceGrave(World world, int x, int minY, int z, int maxY) {
        int blockId;

        for (int y = maxY; y >= minY - 1; y--) {
            blockId = world.getBlockId(x, y, z);

            if (blockId > 0) {
                if (blockId == Block.waterStill.blockID || blockId == Block.lavaStill.blockID) {
                    return false;
                } else if (GraveStoneHelper.canPlaceBlockAt(blockId)) {
                    return true;
                }
            }
        }

        return false;
    }
}
