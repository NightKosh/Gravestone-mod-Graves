package gravestone.core.compatibility;

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
        new GSCompatibilityTheCampingMod();

        new GSCompatibilityisArsMagica();

        new GSCompatibilityBackpacksMod();

        new GSCompatibilityBaubles();

        new GSCompatibilityMariculture();

        new GSCompatibilityRpgInventory();

        new GSCompatibilityTinkerConstruct();

        new GSCompatibilityGalacticraft();

        new GSCompatibilityEnderIO();

        new GSCompatibilityWitchery();
    }
}
