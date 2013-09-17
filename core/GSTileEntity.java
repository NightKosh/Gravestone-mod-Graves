
package gravestone.core;

import cpw.mods.fml.common.registry.GameRegistry;
import gravestone.tileentity.TileEntityGSGhostlyChest;
import gravestone.tileentity.TileEntityGSGraveStone;
import gravestone.tileentity.TileEntityGSMemorial;
import gravestone.tileentity.TileEntityGSSkullCandle;
import gravestone.tileentity.TileEntityGSGraveStone;
import gravestone.tileentity.TileEntityGSMemorial;
import gravestone.tileentity.TileEntityGSWitherSpawner;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTileEntity {

    private GSTileEntity() {
        
    }
    
    public static void registration() {
        // register GraveStone tile entity
        GameRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GraveStoneTE");
        
        // register Memorial tile entity
        GameRegistry.registerTileEntity(TileEntityGSMemorial.class, "Memorial");
        
        // register Wither Spawner tile entity
        GameRegistry.registerTileEntity(TileEntityGSWitherSpawner.class, "GSWither Spawner");
        
        // register Wither Spawner tile entity
        GameRegistry.registerTileEntity(TileEntityGSGhostlyChest.class, "GSGhostly Chest");
        
        // register Wither Spawner tile entity
        GameRegistry.registerTileEntity(TileEntityGSSkullCandle.class, "GSSkull Candle");
    }
}
