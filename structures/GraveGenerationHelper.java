
package GraveStone.structures;

import GraveStone.GraveStoneConfig;
import GraveStone.block.BlockGSGraveStone;
import GraveStone.tileentity.GSGraveStoneItems;
import GraveStone.tileentity.TileEntityGSGraveStone;
import java.util.Random;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class GraveGenerationHelper {
    
    /**
     * Place component
     * @param component Component instance
     * @param world World object
     * @param random 
     * @param x X coord
     * @param y Y coord
     * @param z Z coord
     * @param graveMeta Grave metadata
     * @param graveType Grave type
     */
    public static void placeGrave(ComponentGraveStone component, World world, Random random, int x, int y, int z, int graveMeta, byte graveType) {
        component.placeBlockAtCurrentPosition(world, GraveStoneConfig.graveStoneID, graveMeta, x, y, z, component.getBoundingBox());
        TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getBlockTileEntity(component.getXWithOffset(x, z), component.getYWithOffset(y), component.getZWithOffset(x, z));
        if (tileEntity != null) {
            if (BlockGSGraveStone.isSwordGrave(graveType)) {
                tileEntity.setSword(BlockGSGraveStone.graveTypeToSwordType(graveType));
                tileEntity.setDamage(GSGraveStoneItems.getRandomDamage(random, 50));
            }
            tileEntity.setGraveType(graveType);
            tileEntity.setGraveContent(random);
        }
    }

    /**
     * Fill squer with graves
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
    public static void fillGraves(ComponentGraveStone component, World world, Random random, int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd, int graveMeta, byte graveType) {
        for (int y = yStart; y <= yEnd; ++y) {
            for (int x = xStart; x <= xEnd; ++x) {
                for (int z = zStart; z <= zEnd; ++z) {
                    placeGrave(component, world, random, x, y, z, graveMeta, graveType);
                }
            }
        }
    }
}
