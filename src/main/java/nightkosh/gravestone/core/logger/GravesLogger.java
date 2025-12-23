package nightkosh.gravestone.core.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static nightkosh.gravestone.ModGraveStone.LOGGER;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravesLogger extends AbstractLogger {

    private static Path logFile;
    private static final String LOG_FILE_DIRECTORY = "logs/";
    private static final String LOG_FILE_NAME = "graveLogs.log";
    private static final DateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void setWorldDirectory(Path worldDirectory) {
        try {
            var logsDir = worldDirectory.resolve(LOG_FILE_DIRECTORY);
            Files.createDirectories(logsDir);

            String fileName = FILE_DATE_FORMAT.format(new Date()) + "_" + LOG_FILE_NAME;
            logFile = logsDir.resolve(fileName);

            if (Files.notExists(logFile)) {
                Files.createFile(logFile);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to init graves logger file", e);
            logFile = null;
        }
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, Message message, Throwable t) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, CharSequence message, Throwable t) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, Object message, Throwable t) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Throwable t) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object... params) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return true;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return true;
    }

    @Override
    public void logMessage(String fqcn, Level level, Marker marker, Message msg, Throwable throwable) {
        if (logFile != null) {
            var builder = new StringBuilder()
                    .append(DATE_FORMAT.format(new Date()))
                    .append(" [")
                    .append(level)
                    .append("] ")
                    .append(msg.getFormattedMessage());

            final Object[] params = msg.getParameters();
            var t = throwable;
            if (throwable == null &&
                    params != null &&
                    params.length != 0 &&
                    params[params.length - 1] instanceof Throwable newThrowable) {
                t = newThrowable;
            }
            if (t != null) {
                var sw = new StringWriter();
                t.printStackTrace(new PrintWriter(sw));
                builder.append(System.lineSeparator())
                        .append(sw);
            }

            builder.append(System.lineSeparator());

            try {
                Files.writeString(
                        logFile,
                        builder.toString(),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.APPEND
                );
            } catch (Exception e) {
                LOGGER.error("Error while writing in graves log file!", e);
            }
        } else {
            LOGGER.error("Graves logs file is not initialized");
        }
    }

    @Override
    public Level getLevel() {
        return org.apache.logging.log4j.Level.ALL;
    }

}
