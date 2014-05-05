package gravestone;

import gravestone.core.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneLogger {

    private static Logger logger = LogManager.getLogger(ModInfo.ID);

    public static void log(Level logLevel, String message) {
        logger.log(logLevel, message);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logError(String message) {
        logger.log(Level.ERROR, message);
    }
}
