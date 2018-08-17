package qbert.controller;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Initialize the application {@link Logger}.
 */
public final class LoggerManager {
    private static final String FILENAME = "Log";
    private static final Logger LOGGER = Logger.getGlobal();

    /**
     * Initialize the logger.
     */
    public LoggerManager() {

        for (final Handler h : LOGGER.getHandlers()) {
            LOGGER.removeHandler(h);
        }

        FileHandler fh = null;
        try {
            fh = new FileHandler(System.getProperty("user.home") + "/qbert/" + FILENAME + ".xml");
            fh.setFormatter(new XMLFormatter());
            LOGGER.addHandler(fh);
        } catch (SecurityException e) {
            this.setConsoleHandler();
            e.printStackTrace();
        } catch (IOException e) {
            this.setConsoleHandler();
            e.printStackTrace();
        }
        LOGGER.setLevel(Level.ALL);
    };

    private void setConsoleHandler() {
        LOGGER.addHandler(new ConsoleHandler());
        LOGGER.log(Level.WARNING, "Errors during log file creation, only console handling");
    }
}
