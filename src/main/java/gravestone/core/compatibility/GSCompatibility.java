package gravestone.core.compatibility;

import net.minecraftforge.fml.common.Loader;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibility {

    private static GSCompatibility instance;

    private GSCompatibility() {
        instance = this;
    }

    public static GSCompatibility getInstance() {
        return (instance == null) ? new GSCompatibility() : instance;
    }

    public void checkMods() {
        if (isModLoaded(GSCompatibilityBattlegear.MOD_ID)) {
            GSCompatibilityBattlegear.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityTheCampingMod.MOD_ID)) {
            GSCompatibilityTheCampingMod.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityisArsMagica.MOD_ID)) {
            GSCompatibilityisArsMagica.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityBackpacksMod.MOD_ID)) {
            GSCompatibilityBackpacksMod.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityBaubles.MOD_ID)) {
            GSCompatibilityBaubles.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityMariculture.MOD_ID)) {
            GSCompatibilityMariculture.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityRpgInventory.MOD_ID)) {
            GSCompatibilityRpgInventory.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityTinkerConstruct.MOD_ID)) {
            GSCompatibilityTinkerConstruct.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityGalacticraft.MOD_ID)) {
            GSCompatibilityGalacticraft.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityEnderIO.MOD_ID)) {
            new GSCompatibilityEnderIO();
        }

        if (isModLoaded(GSCompatibilityTwilightForest.MOD_ID)) {
            GSCompatibilityTwilightForest.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityWitchery.MOD_ID)) {
            new GSCompatibilityWitchery();
        }
    }

    public boolean isModLoaded(String modId) {
        return Loader.isModLoaded(modId);
    }
}
