package nightkosh.gravestone.core.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ProxyHelper {
    public static void registerModelsForTEBlocks(int startMeta, int endMeta, Block block, ModelResourceLocation model, Class TEClass) {
        for (int meta = startMeta; meta <= endMeta; meta++) {
            registerModelsForTEBlocks(meta, block, model, TEClass);
        }
    }

    public static void registerModelsForTEBlocks(int meta, Block block, ModelResourceLocation model, Class TEClass) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, model);
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(block), meta, TEClass);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
    }
}
