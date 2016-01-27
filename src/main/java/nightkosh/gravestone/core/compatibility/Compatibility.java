package nightkosh.gravestone.core.compatibility;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Compatibility {

    private static final Compatibility INSTANCE = new Compatibility();

    private Compatibility() {
        new CompatibilityTheCampingMod();

        new CompatibilityisArsMagica();

        new CompatibilityBackpacksMod();

        new CompatibilityBaubles();

        new CompatibilityMariculture();

        new CompatibilityRpgInventory();

        new CompatibilityTinkerConstruct();

        new CompatibilityGalacticraft();

        new CompatibilityEnderIO();

        new CompatibilityWitchery();
    }

    public static Compatibility getInstance() {
        return INSTANCE;
    }
}
