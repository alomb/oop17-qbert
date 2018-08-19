package qbert.controller;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import qbert.model.utilities.Dimensions;

/**
 * The class used to load resources at the beginning of the program.
 */
public class LoadResources {

    private boolean loadCompleted;

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String QBERT_FOLDER = USER_HOME 
            + SEPARATOR
            + "qbert";
    private static final String SPRITES_FOLDER = QBERT_FOLDER
            + SEPARATOR
            + "sprites";
    private static final String IMAGE_CONFIG_FILE_NAME = "/imageconfig.txt";

    private static final Logger LOGGER = Logger.getGlobal();
    private static final String LOGGER_FILE_NAME = "Log.xml";

    /**
     * Initialize variables.
     */
    public LoadResources() {
        this.loadCompleted = true;
    }

    /**
     * @return true if all the resources have been correctly instantiated
     */
    public final boolean load() {

        try {
            final File directory = new File(SPRITES_FOLDER);
            if (!directory.exists()) {
                final boolean directoryCreated = directory.mkdirs();
                if (!directoryCreated) {
                    return false;
                }
            }

            final File[] files = new File(SPRITES_FOLDER).listFiles();

            if (files != null) {
                for (final File file : files) {
                    if (!file.isDirectory() && file.exists()) {
                        this.loadCompleted |= file.delete();
                    }
                }
            }
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }

        for (final Handler h : LOGGER.getHandlers()) {
            LOGGER.removeHandler(h);
        }

        FileHandler fh = null;
        try {
            fh = new FileHandler(QBERT_FOLDER + SEPARATOR + LOGGER_FILE_NAME);
            fh.setFormatter(new XMLFormatter());
            LOGGER.addHandler(fh);
        } catch (SecurityException e) {
            this.setConsoleHandler();
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            this.setConsoleHandler();
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        LOGGER.setLevel(Level.ALL);

        final InputStream imageConfigIn = LoadResources.class.getResourceAsStream(IMAGE_CONFIG_FILE_NAME);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(imageConfigIn))) {
            String line = br.readLine();
            while (line != null) {
                convertSvgToPng(line.split("\\+")[0], SPRITES_FOLDER + SEPARATOR + line.split("\\+")[1]);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return this.loadCompleted;
    }

    private void convertSvgToPng(final String svg, final String png) {
        try {
            final String uriSvg = getClass().getResource(svg).toString();
            final TranscoderInput imageSvg = new TranscoderInput(uriSvg);
            final OutputStream streamPng = new FileOutputStream(png);
            final TranscoderOutput outputImagePng = new TranscoderOutput(streamPng);
            final PNGTranscoder converter = new PNGTranscoder();

            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build(uriSvg);

            final Element root = document.getRootElement();
            final float width = Float.valueOf(root.getAttributeValue("width"));
            final float height = Float.valueOf(root.getAttributeValue("height"));
            final float yFactor = Dimensions.getWindowHeight() / 810f;
            final float xFactor = Dimensions.getWindowWidth() / 1440f;

            final float newWidth = width * Math.min(xFactor, yFactor);
            final float newHeight = height * Math.min(xFactor, yFactor);


            converter.addTranscodingHint(PNGTranscoder.KEY_WIDTH, newWidth);
            converter.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, newHeight);
            converter.addTranscodingHint(PNGTranscoder.KEY_AOI, new Rectangle(0, 0, Math.round(width), Math.round(height)));

            converter.transcode(imageSvg, outputImagePng);
            streamPng.flush();
            streamPng.close();
        } catch (final Exception e) {
            LOGGER.log(Level.SEVERE, "Application aborted. Error on svg to png conversion");
            this.loadCompleted = false;
        }
    }

    /**
     * Add the logger a console handler.
     */
    private void setConsoleHandler() {
        LOGGER.addHandler(new ConsoleHandler());
        LOGGER.log(Level.WARNING, "Errors during log file creation, only console handling");
    }
}
