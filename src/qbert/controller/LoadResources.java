package qbert.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Initialize variables.
     */
    public LoadResources() {
        this.loadCompleted = true;
    }

    /**
     * @return true if all the resources have been correctly istantiated
     */
    public final boolean load() {
        final String imgPath = System.getProperty("user.home") + "/qbert/img/";
        final String imageConfigFileName = "/imageconfig.txt";

        try {
            final File directory = new File(imgPath);
            if (!directory.exists()) {
                final boolean directoryCreated = directory.mkdirs();
                if (!directoryCreated) {
                    return false;
                }
            }

            final File[] files = new File(imgPath).listFiles();

            if (files != null) {
                for (final File file : files) {
                    if (!file.isDirectory() && file.exists()) {
                        this.loadCompleted |= file.delete();
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }

        new LoggerManager();

        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        Dimensions.setWindowHeight(Math.round(new Float(d.height)));
        Dimensions.setWindowWidth(Math.round(new Float(d.width)));

        final InputStream imageConfigIn = LoadResources.class.getResourceAsStream(imageConfigFileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(imageConfigIn))) {
            String line = br.readLine();
            while (line != null) {
                convertSvgToPng(line.split("\\+")[0], imgPath + line.split("\\+")[1]);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            Logger.getGlobal().log(Level.SEVERE, "Error on svg to png conversion\". Program aborted");
            this.loadCompleted = false;
        }
    }
}
