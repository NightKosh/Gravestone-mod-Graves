package nightkosh.gravestone.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.ResourcesModels;
import nightkosh.gravestone.renderer.tileentity.TileEntityGraveStoneRenderer;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import org.apache.commons.lang3.StringUtils;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        ClientRegistry.registerTileEntity(TileEntityGraveStone.class, "GSGraveStone", new TileEntityGraveStoneRenderer());
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
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Sword.class, "GSGraveStoneSword", new TileEntityGraveStoneRenderer.SwordRenderer());
    }

    @Override
    public String getLocalizedString(String str) {
        String localizedString = null;
        try {
            localizedString = LanguageRegistry.instance().getStringLocalization(str);
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(localizedString)) {
            return LanguageRegistry.instance().getStringLocalization(str, "en_US");
        } else {
            return localizedString;
        }
    }

    @Override
    public String getLocalizedEntityName(String name) {
        return StatCollector.translateToLocal(name);
    }

    @Override
    public void registerHandlers() {
    }

    @Override
    public void registerBlocksModels() {
        registerModelsForTEBlocks(0, ResourcesModels.graveStoneModel, TileEntityGraveStone.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_VERTICAL_PLATE.ordinal() + 1, EnumGraves.ICE_VERTICAL_PLATE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.VerticalPlate.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_CROSS.ordinal(), EnumGraves.ICE_CROSS.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.Cross.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_OBELISK.ordinal(), EnumGraves.ICE_OBELISK.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.Obelisk.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_CELTIC_CROSS.ordinal(), EnumGraves.ICE_CELTIC_CROSS.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.CelticCross.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_HORIZONTAL_PLATE.ordinal(), EnumGraves.ICE_HORIZONTAL_PLATE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.HorizontalPlate.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_VILLAGER_STATUE.ordinal(), EnumGraves.ICE_VILLAGER_STATUE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.VillagerStatue.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_DOG_STATUE.ordinal(), EnumGraves.ICE_DOG_STATUE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.DogStatue.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_CAT_STATUE.ordinal(), EnumGraves.ICE_CAT_STATUE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.CatStatue.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_HORSE_STATUE.ordinal(), EnumGraves.ICE_HORSE_STATUE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.HorseStatue.class);
        registerModelsForTEBlocks(EnumGraves.WOODEN_CREEPER_STATUE.ordinal(), EnumGraves.ICE_CREEPER_STATUE.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.CreeperStatue.class);
        registerModelsForTEBlocks(EnumGraves.SWORD.ordinal(), ResourcesModels.graveStoneModel, TileEntityGraveStone.Sword.class);
    }

    private void registerModelsForTEBlocks(int startMeta, int endMeta, ModelResourceLocation model, Class TEClass) {
        for (int meta = startMeta; meta <= endMeta; meta++) {
            registerModelsForTEBlocks(meta, model, TEClass);
        }
    }

    private void registerModelsForTEBlocks(int meta, ModelResourceLocation model, Class TEClass) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(GSBlock.graveStone), meta, model);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(GSBlock.graveStone), meta, TEClass);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.graveStone), meta, model);
    }
}
