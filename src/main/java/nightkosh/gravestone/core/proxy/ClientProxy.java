package nightkosh.gravestone.core.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
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
        // graves renderer
        ClientRegistry.registerTileEntity(TileEntityGraveStone.class, "GSGraveStone", new TileEntityGraveStoneRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.VerticalPlate.class, "GSGraveStoneVerticalPlate", new TileEntityGraveStoneRenderer.VerticalPlateRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Cross.class, "GSGraveStoneCross", new TileEntityGraveStoneRenderer.CrossRenderer());
        ClientRegistry.registerTileEntity(TileEntityGraveStone.Obelisk.class, "GSGraveStoneObelisk", new TileEntityGraveStoneRenderer.ObeliskRenderer());
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
        //graves
        registerModelsForTEBlocks(0, 1, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.class);
        registerModelsForTEBlocks(1, 17, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.VerticalPlate.class);
        registerModelsForTEBlocks(17, 34, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.Cross.class);
        registerModelsForTEBlocks(34, 51, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.Obelisk.class);
        registerModelsForTEBlocks(51, 68, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.HorizontalPlate.class);
        registerModelsForTEBlocks(68, 85, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.VillagerStatue.class);
        registerModelsForTEBlocks(85, 102, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.DogStatue.class);
        registerModelsForTEBlocks(102, 119, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.CatStatue.class);
        registerModelsForTEBlocks(119, 136, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.HorseStatue.class);
        registerModelsForTEBlocks(136, 153, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.CreeperStatue.class);
        registerModelsForTEBlocks(153, 154, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGraveStone.Sword.class);
    }

    private void registerModelsForTEBlocks(int startMeta, int endMeta, Block block, ModelResourceLocation model, Class TEClass) {
        for (int num = startMeta; num < endMeta; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), num, model);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(block), num, TEClass);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), num, model);
        }
    }
}
