package gravestone.core.proxy;

import gravestone.core.GSBlock;
import gravestone.core.ResourcesModels;
import gravestone.core.event.GSRenderEventHandler;
import gravestone.renderer.item.ItemGSGraveStoneRenderer;
import gravestone.renderer.tileentity.TileEntityGSGraveStoneRenderer;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
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
        // blocks renderers

        // graves renderer
        ClientRegistry.registerTileEntity(TileEntityGSGraveStone.class, "GSGraveStone", new TileEntityGSGraveStoneRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GSBlock.graveStone), new ItemGSGraveStoneRenderer());
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
        MinecraftForge.EVENT_BUS.register(new GSRenderEventHandler());
    }

    @Override
    public void registerBlocksModels() {
        //graves
        registerModelsForTEBlocks(1, GSBlock.graveStone, ResourcesModels.graveStoneModel, TileEntityGSGraveStone.class);
    }

    private void registerModelsForTEBlocks(int length, Block block, ModelResourceLocation model, Class TEClass) {
        for (int num = 0; num < length; num++) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), num, model);
            ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(block), num, TEClass);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), num, model);
        }
    }
}
