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
        //TODO
//        new CompatibilityTheCampingMod();

//        new CompatibilityBackpacksMod();

//        new CompatibilityRpgInventory();

//        new CompatibilityEnderIO();

        new CompatibilityWitchery();

//        new CompatibilityTinkerConstruct();
    }

    public static Compatibility getInstance() {
        return INSTANCE;
    }
}
