package gravestone.core.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
//        if (!logsDir..exists()) {
//            try {
                logsDir.mkdir();
//            } catch (IOException e) {
//                GSLogger.logError("Can't create graves logger directory!");
//                e.printStackTrace();
//            }
//        }

        StringBuilder fileName = new StringBuilder();
        fileName.append(LOG_FILE_DIRECTORY)
                .append(FILE_DATE_FORMAT.format(new Date()))
                .append(" ")
                .append(LOG_FILE_NAME);
        logFile = new File(worldDirectory, fileName.toString());
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, Message data, Throwable t) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, Object data, Throwable t) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data, Object... p1) {
        return true;
    }

    @Override
    protected boolean isEnabled(Level level, Marker marker, String data, Throwable t) {
        return true;
    }

    @Override
    public void log(Marker marker, String fqcn, Level level, Message msg, Throwable throwable) {
        if (logFile != null) {
            StringBuilder loggedStr = new StringBuilder();
            loggedStr.append(DATE_FORMAT.format(new Date()));

            loggedStr.append(" [");
            loggedStr.append(level.toString());
            loggedStr.append("] ");

            loggedStr.append(msg.getFormattedMessage());

            final Object[] params = msg.getParameters();
            Throwable t;
            if (throwable == null && params != null && params[params.length - 1] instanceof Throwable) {
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
                FileOutputStream fileoutputstream = new FileOutputStream(logFile);
                fileoutputstream.write(loggedStr.toString().getBytes());
                fileoutputstream.close();
            } catch (IOException e) {
                GSLogger.logError("Error while writing in graves log file!");
                e.printStackTrace();
            }
        } else {
            GSLogger.logError("Graves logs file doesn't exists");
        }

    }
}
