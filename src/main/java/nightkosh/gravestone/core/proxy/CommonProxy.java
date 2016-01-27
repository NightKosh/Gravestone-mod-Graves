package nightkosh.gravestone.core.proxy;

import nightkosh.gravestone.api.grave.EnumGraveMaterial;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommonProxy {

    public void registerRenderers() {
    }

    public String getLocalizedString(String str) {
        return str;
    }

    public String getLocalizedEntityName(String str) {
        return str;
    }

    public void registerHandlers() {
    }

    public void registerBlocksModels() {
    }

    public String getLocalizedMaterial(EnumGraveMaterial material) {
        return getLocalizedString("material." + material.toString().toLowerCase());
    }
}
