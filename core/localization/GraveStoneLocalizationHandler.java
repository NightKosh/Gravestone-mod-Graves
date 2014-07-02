package gravestone.core.localization;

import gravestone.core.Resources;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gravestone.GraveStoneLogger;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneLocalizationHandler {

    private static String[] localeNames = {
        "en_US",
        "ru_RU",
        "de_DE",
        "fr_FR",
        "es_ES",
        "zh_CN",
        "zh_TW",
        "cs_CZ"
    };

    public static void init() {
        
//        URL urlResource = GraveStoneLocalizationHandler.class.getResource(Resources.LOCALIZATION_LOCATION);
//        try {
//            if (urlResource != null) {
//                GraveStoneLogger.logInfo(urlResource.getPath());
//                GraveStoneLogger.logInfo(URLEncoder.encode(urlResource.getPath(), "UTF-8"));
//                GraveStoneLogger.logInfo(URLDecoder.decode(urlResource.getPath(), "UTF-8"));
//                
//                File folder = new File(URLDecoder.decode(urlResource.getPath(), "UTF-8"));
//
//                GraveStoneLogger.logInfo("dfdf!!! " + folder.exists());
//                for (final File file : folder.listFiles()) {
//                    if (file.isFile() && file.getName().endsWith(".lang")) {
//                        GraveStoneLogger.logInfo(file.getName());
////                        LanguageRegistry.instance().loadLocalization(Resources.LOCALIZATION_LOCATION + file.getName(),
//                                //getFileName(file.getName()), false);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        for (String localeName : localeNames) {
            LanguageRegistry.instance().loadLocalization(Resources.LOCALIZATION_LOCATION + localeName + ".lang", localeName, false);
        }
    }

    private static String getFileName(String name) {
        return name.substring(0, name.indexOf(".lang"));
    }
}
