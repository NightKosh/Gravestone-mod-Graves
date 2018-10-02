package nightkosh.gravestone.core.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import nightkosh.gravestone.block.enums.EnumGraves;
import nightkosh.gravestone.core.GSBlock;
import nightkosh.gravestone.core.ResourcesModels;
import nightkosh.gravestone.renderer.item.TEISRGravestone;
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
    public void registerTERenderers() {
        ClientRegistry.registerTileEntity(TileEntityGraveStone.class, "GraveStoneTE", new TileEntityGraveStoneRenderer());
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
    public void registerTEISR() {
        GSBlock.GRAVE_STONE_IB.setTileEntityItemStackRenderer(new TEISRGravestone());
        for (int meta = EnumGraves.WOODEN_VERTICAL_PLATE.ordinal(); meta <= EnumGraves.SWORD.ordinal(); meta++) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GSBlock.GRAVE_STONE), meta, ResourcesModels.GRAVE_STONE_MODEL);
        }
    }
}
