package gravestone.core;

import gravestone.tileentity.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        GameRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GraveStoneTE");

        GameRegistry.registerTileEntity(TileEntityGSMemorial.class, "Memorial");

        GameRegistry.registerTileEntity(TileEntityGSSpawner.class, "GS Spawner");

        GameRegistry.registerTileEntity(TileEntityGSHauntedChest.class, "GSHaunted Chest");

        GameRegistry.registerTileEntity(TileEntityGSCandle.class, "GSTECandle");

        GameRegistry.registerTileEntity(TileEntityGSSkullCandle.class, "GSSkull Candle");

        GameRegistry.registerTileEntity(TileEntityGSPileOfBones.class, "GSTEPileOfBones");

        GameRegistry.registerTileEntity(TileEntityGSAltar.class, "GSAltarTE");
    }
}
