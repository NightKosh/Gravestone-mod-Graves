package nightkosh.gravestone.core.proxy;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.ResourcesModels;
import nightkosh.gravestone.renderer.tileentity.TileEntityGraveStoneRenderer;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        ClientRegistry.registerTileEntity(TileEntityGraveStone.VerticalPlate.class, "GSGraveStoneVerticalPlate", new TileEntityGraveStoneRenderer.VerticalPlateRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Cross.class, "GSGraveStoneCross", new TileEntityGraveStoneRenderer.CrossRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Obelisk.class, "GSGraveStoneObelisk", new TileEntityGraveStoneRenderer.ObeliskRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CelticCross.class, "GSGraveStoneCelticCross", new TileEntityGraveStoneRenderer.CelticCrossRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.HorizontalPlate.class, "GSGraveStoneHorizontalPlate", new TileEntityGraveStoneRenderer.HorizontalPlateRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.VillagerStatue.class, "GSGraveStoneVillagerStatue", new TileEntityGraveStoneRenderer.VillagerStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.DogStatue.class, "GSGraveStoneDogStatue", new TileEntityGraveStoneRenderer.DogStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CatStatue.class, "GSGraveStoneCatStatue", new TileEntityGraveStoneRenderer.CatStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.HorseStatue.class, "GSGraveStoneHorseStatue", new TileEntityGraveStoneRenderer.HorseStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.CreeperStatue.class, "GSGraveStoneCreeperStatue", new TileEntityGraveStoneRenderer.CreeperStatueRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.StarvedCorpse.class, "GSGraveStoneStarvedCorpse", new TileEntityGraveStoneRenderer.StarvedCorpseRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.WitheredCorpse.class, "GSGraveStoneWitheredCorpse", new TileEntityGraveStoneRenderer.WitheredCorpseRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Sword.class, "GSGraveStoneSword", new TileEntityGraveStoneRenderer.SwordRenderer());
    }

    @Override
    public String getLocalizedString(String str) {
        return I18n.translateToLocal(str);
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return I18n.translateToLocal(name);
    }

    @Override
    public void registerBlocksModels() {
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(), EnumGraves.ICE_VERTICAL_PLATE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.VerticalPlate.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_CROSS.ordinal(), EnumGraves.ICE_CROSS.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.Cross.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_OBELISK.ordinal(), EnumGraves.ICE_OBELISK.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.Obelisk.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_CELTIC_CROSS.ordinal(), EnumGraves.ICE_CELTIC_CROSS.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.CelticCross.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_HORIZONTAL_PLATE.ordinal(), EnumGraves.ICE_HORIZONTAL_PLATE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.HorizontalPlate.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_VILLAGER_STATUE.ordinal(), EnumGraves.ICE_VILLAGER_STATUE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.VillagerStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_DOG_STATUE.ordinal(), EnumGraves.ICE_DOG_STATUE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.DogStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_CAT_STATUE.ordinal(), EnumGraves.ICE_CAT_STATUE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.CatStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_HORSE_STATUE.ordinal(), EnumGraves.ICE_HORSE_STATUE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.HorseStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WOODEN_CREEPER_STATUE.ordinal(), EnumGraves.ICE_CREEPER_STATUE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.CreeperStatue.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.STARVED_CORPSE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.StarvedCorpse.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.WITHERED_CORPSE.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.WitheredCorpse.class);
        ProxyHelper.registerModelsForTEBlocks(EnumGraves.SWORD.ordinal(), GSBlock.GRAVE_STONE, ResourcesModels.GRAVE_STONE_MODEL, TileEntityGraveStone.Sword.class);


    }
}
