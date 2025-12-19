package nightkosh.gravestone.core.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

import java.io.*;
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

    private static File logFile;
    private static final String LOG_FILE_DIRECTORY = "logs/";
    private static final String LOG_FILE_NAME = "graveLogs.log";
    private static final DateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void setWorldDirectory(File worldDirectory) {
        File logsDir = new File(worldDirectory, LOG_FILE_DIRECTORY);
        logsDir.mkdir();

        StringBuilder fileName = new StringBuilder();
        fileName.append(LOG_FILE_DIRECTORY)
                .append(FILE_DATE_FORMAT.format(new Date()))
                .append(" ")
                .append(LOG_FILE_NAME);
        logFile = new File(worldDirectory, fileName.toString());
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
            StringBuilder loggedStr = new StringBuilder();
            loggedStr.append(DATE_FORMAT.format(new Date()));

            loggedStr.append(" [");
            loggedStr.append(level.toString());
            loggedStr.append("] ");

            loggedStr.append(msg.getFormattedMessage());

            final Object[] params = msg.getParameters();
            Throwable t;
            if (throwable == null && params != null && params.length != 0 && params[params.length - 1] instanceof Throwable) {
                t = (Throwable) params[params.length - 1];
            } else {
                t = throwable;
            }
            if (t != null) {
                loggedStr.append(" ");
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                t.printStackTrace(new PrintStream(baos));
                loggedStr.append(baos.toString());
            }

            try {
                PrintWriter out = new PrintWriter(new FileWriter(logFile, true), true);
                out.println(loggedStr.toString());
            } catch (IOException e) {
                LOGGER.error("Error while writing in graves log file!");
                e.printStackTrace();
            }
        } else {
            LOGGER.error("Graves logs file doesn't exists");
        }
    }

    @Override
    public Level getLevel() {
        return null;
    }
}
