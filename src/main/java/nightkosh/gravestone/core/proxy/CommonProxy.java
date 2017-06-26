package nightkosh.gravestone.core.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;
import nightkosh.gravestone.api.grave.EnumGraveMaterial;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommonProxy {

    public String getLocalizedString(String str) {
        return str;
    }

    public String getLocalizedEntityName(String str) {
        return str;
    }

    public void registerTERenderers() {
        GameRegistry.registerTileEntity(TileEntityGraveStone.class, "GraveStoneTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.VerticalPlate.class, "GraveStoneVerticalPlateTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.Cross.class, "GraveStoneCrossTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.Obelisk.class, "GraveStoneObeliskTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.CelticCross.class, "GraveStoneCelticCrossTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.HorizontalPlate.class, "GraveStoneHorizontalPlateTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.VillagerStatue.class, "GraveStoneVillagerStatueTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.DogStatue.class, "GraveStoneDogStatueTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.CatStatue.class, "GraveStoneCatStatueTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.HorseStatue.class, "GraveStoneHorseStatueTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.CreeperStatue.class, "GraveStoneCreeperStatueTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.StarvedCorpse.class, "GraveStoneStarvedCorpseTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.WitheredCorpse.class, "GraveStoneWitheredCorpseTE");
        GameRegistry.registerTileEntity(TileEntityGraveStone.Sword.class, "GraveStoneSwordTE");
    }

    public void registerBlocksModels() {
    }

    public String getLocalizedMaterial(EnumGraveMaterial material) {
        return getLocalizedString("material." + material.toString().toLowerCase());
    }
}
