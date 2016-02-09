package nightkosh.gravestone.core.logger;

import nightkosh.gravestone.api.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSLogger {

    private static Logger logger = LogManager.getLogger(ModInfo.ID);
    private static Logger graveLogger = new GravesLogger();

    public static void log(Level logLevel, String message) {
        logger.log(logLevel, message);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logError(String message) {
        logger.log(Level.ERROR, message);
    }

    public static void logGrave(Level logLevel, String message) {
        graveLogger.log(logLevel, message);
    }

    public static void logInfoGrave(String message) {
        graveLogger.log(Level.INFO, message);
    }

    public static void logErrorGrave(String message) {
        graveLogger.log(Level.ERROR, message);
    }
}