package nightkosh.gravestone.core;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import nightkosh.gravestone.renderer.tileentity.TileEntityGraveStoneRenderer;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSTileEntity {

    public static void registration() {
        ClientRegistry.registerTileEntity(TileEntityGraveStone.class, "GraveStoneTE", new TileEntityGraveStoneRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.VerticalPlate.class, "GraveStoneVerticalPlateTE", new TileEntityGraveStoneRenderer.VerticalPlateRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Cross.class, "GraveStoneCrossTE", new TileEntityGraveStoneRenderer.CrossRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Obelisk.class, "GraveStoneObeliskTE", new TileEntityGraveStoneRenderer.ObeliskRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CelticCross.class, "GraveStoneCelticCrossTE", new TileEntityGraveStoneRenderer.CelticCrossRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.HorizontalPlate.class, "GraveStoneHorizontalPlateTE", new TileEntityGraveStoneRenderer.HorizontalPlateRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.VillagerStatue.class, "GraveStoneVillagerStatueTE", new TileEntityGraveStoneRenderer.VillagerStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.DogStatue.class, "GraveStoneDogStatueTE", new TileEntityGraveStoneRenderer.DogStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CatStatue.class, "GraveStoneCatStatueTE", new TileEntityGraveStoneRenderer.CatStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.HorseStatue.class, "GraveStoneHorseStatueTE", new TileEntityGraveStoneRenderer.HorseStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CreeperStatue.class, "GraveStoneCreeperStatueTE", new TileEntityGraveStoneRenderer.CreeperStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.StarvedCorpse.class, "GraveStoneStarvedCorpseTE", new TileEntityGraveStoneRenderer.StarvedCorpseRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.WitheredCorpse.class, "GraveStoneWitheredCorpseTE", new TileEntityGraveStoneRenderer.WitheredCorpseRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Sword.class, "GraveStoneSwordTE", new TileEntityGraveStoneRenderer.SwordRenderer());
    }
}
