package gravestone;

import cpw.mods.fml.common.FMLLog;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneLogger {

    private static final Logger logger = Logger.getLogger(ModInfo.ID);

    public static void preinit() {
        logger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {
        logger.log(logLevel, message);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }
    
    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }

    public static void logDebug(String... message) {
        System.out.print("Debugging... ");

        for (int i = 0; i < message.length; i++) {
            System.out.print(message + " ");
        }

        System.out.println();
    }
}
