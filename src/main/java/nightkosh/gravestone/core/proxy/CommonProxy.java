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
    }

    public void registerTEISR() {
    }

    public String getLocalizedMaterial(EnumGraveMaterial material) {
        return getLocalizedString("material." + material.toString().toLowerCase());
    }
}
