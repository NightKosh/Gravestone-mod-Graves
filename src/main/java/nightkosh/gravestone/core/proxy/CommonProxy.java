package nightkosh.gravestone.core.proxy;

import nightkosh.gravestone.api.grave.EnumGraveMaterial;

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


    //TODO
//    public void registerTERenderers() {
//        GameRegistry.registerTileEntity(GraveStoneBlockEntity.class, "GraveStoneTE");
//    }

    public String getLocalizedMaterial(EnumGraveMaterial material) {
        return getLocalizedString("material." + material.toString().toLowerCase());
    }

}
