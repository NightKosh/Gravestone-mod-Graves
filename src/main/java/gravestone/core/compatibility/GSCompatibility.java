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
        if (Loader.isModLoaded("battlegear2")) {
            GSCompatibilityBattlegear.isInstalled = true;
        }

        if (Loader.isModLoaded("camping")) {
            GSCompatibilityTheCampingMod.isInstalled = true;
        }

        if (Loader.isModLoaded("arsmagica2")) {
            GSCompatibilityisArsMagica.isInstalled = true;
        }

        if (Loader.isModLoaded("Backpack")) {
            GSCompatibilityBackpacksMod.isInstalled = true;
        }

        if (Loader.isModLoaded("Baubles")) {
            GSCompatibilityBaubles.isInstalled = true;
        }

        if (Loader.isModLoaded("Mariculture")) {
            GSCompatibilityMariculture.isInstalled = true;
        }

        if (Loader.isModLoaded("rpginventorymod")) {
            GSCompatibilityRpgInventory.isInstalled = true;
        }

        if (Loader.isModLoaded("TConstruct")) {
            GSCompatibilityTinkerConstruct.isInstalled = true;
        }

        if (Loader.isModLoaded("GalacticraftCore")) {
            GSCompatibilityGalacticraft.isInstalled = true;
        }

        if (Loader.isModLoaded("EnderIO")) {
            GSCompatibilityEnderIO.isInstalled = true;
        }

        if (Loader.isModLoaded("TwilightForest")) {
            GSCompatibilityTwilightForest.isInstalled = true;
        }

        if (Loader.isModLoaded("witchery")) {
            GSCompatibilityWitchery.isInstalled = true;
        }
    }
}
