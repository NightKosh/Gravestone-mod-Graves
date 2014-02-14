package gravestone.core.localization;

import gravestone.core.Resources;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneLocalizationHandler {

    /**
     * Array of which localization files have to be loaded.
     */
    private static String[] localeFiles = {
        Resources.LOCALIZATION_LOCATION + "en_US.lang",
        Resources.LOCALIZATION_LOCATION + "ru_RU.lang",
        Resources.LOCALIZATION_LOCATION + "de_DE.lang",
        Resources.LOCALIZATION_LOCATION + "fr_FR.lang",
        Resources.LOCALIZATION_LOCATION + "es_ES.lang",
        Resources.LOCALIZATION_LOCATION + "zh_CN.lang"
    };

    /**
     * Gets the language from filename. EG. en_US.xml will return en_US
     *
     * @param Name of the file which needs to be checked.
     * @return Language substring.
     */
    private static String getLocaleFromFileName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
    }

    public static void init() {
        /**
         * For all files registered in Localizations.java, add them to the
         * Localization Library. This way we can use this file to add
         * localizations (names).
         */
        for (String localeFile : localeFiles) {
            LanguageRegistry.instance().loadLocalization(localeFile, getLocaleFromFileName(localeFile), false);
        }
    }
}
