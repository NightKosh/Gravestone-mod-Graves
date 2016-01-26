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
        if (isModLoaded("battlegear2")) {
            GSCompatibilityBattlegear.isInstalled = true;
        }

        if (isModLoaded("camping")) {
            GSCompatibilityTheCampingMod.isInstalled = true;
        }

        if (isModLoaded("arsmagica2")) {
            GSCompatibilityisArsMagica.isInstalled = true;
        }

        if (isModLoaded("Backpack")) {
            GSCompatibilityBackpacksMod.isInstalled = true;
        }

        if (isModLoaded("Baubles")) {
            GSCompatibilityBaubles.isInstalled = true;
        }

        if (isModLoaded("Mariculture")) {
            GSCompatibilityMariculture.isInstalled = true;
        }

        if (isModLoaded("rpginventorymod")) {
            GSCompatibilityRpgInventory.isInstalled = true;
        }

        if (isModLoaded("TConstruct")) {
            GSCompatibilityTinkerConstruct.isInstalled = true;
        }

        if (isModLoaded("GalacticraftCore")) {
            GSCompatibilityGalacticraft.isInstalled = true;
        }

        if (isModLoaded(GSCompatibilityEnderIO.MOD_ID)) {
            new GSCompatibilityEnderIO();
        }

        if (isModLoaded("TwilightForest")) {
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
