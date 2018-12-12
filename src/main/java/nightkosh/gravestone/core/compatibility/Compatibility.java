package nightkosh.gravestone.core.compatibility;

import net.minecraftforge.fml.common.Loader;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Compatibility {

    private static final Compatibility INSTANCE = new Compatibility();

    public static boolean IS_WOLF_ARMOR_INSTALLED;

    private Compatibility() {
        new CompatibilityTheCampingMod();

//        new CompatibilityisArsMagica();

//        new CompatibilityBackpacksMod();

//        new CompatibilityBaubles();

//        new CompatibilityMariculture();

//        new CompatibilityRpgInventory();

//        new CompatibilityGalacticraft();

//        new CompatibilityEnderIO();

        new CompatibilityWitchery();

//        new CompatibilityTinkerConstruct();

        IS_WOLF_ARMOR_INSTALLED = Loader.isModLoaded("wolfarmor");
    }

    public static Compatibility getInstance() {
        return INSTANCE;
    }
}
