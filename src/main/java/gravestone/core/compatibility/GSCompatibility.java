package gravestone.core.compatibility;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibility {

    private static final GSCompatibility INSTANCE = new GSCompatibility();

    private GSCompatibility() {
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

    public static GSCompatibility getInstance() {
        return INSTANCE;
    }
}
