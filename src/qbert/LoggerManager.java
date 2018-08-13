package qbert;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Log utility.
 */
public final class LoggerManager {
    private static final String FILENAME = "Log";
    private static final Logger LOGGER = Logger.getLogger(FILENAME);
    private static final LoggerManager INSTANCE = new LoggerManager();
    private boolean useConsole;

    private LoggerManager() {
        FileHandler fh = null;
        try {
            fh = new FileHandler(System.getProperty("user.home") + "/qbert/" + FILENAME + ".log");
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (SecurityException e) {
            this.useConsole = true;
            e.printStackTrace();
        } catch (IOException e) {
            this.useConsole = true;
            e.printStackTrace();
        }
    };

    /**
     * Return the reference to the logger.
     * @return Log 
     */
    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    /**
     * Log a message.
     * @param src the source from which the log is generate
     * @param msg the message that have to be logged
     */
    public void info(final String src, final String msg) {
        final String message = "[" + src + "] : " + msg;
        if (this.useConsole) {
            System.out.println(message);
            return;
        }
        LOGGER.info(message);
    }
}
