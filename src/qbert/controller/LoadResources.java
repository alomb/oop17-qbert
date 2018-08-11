package qbert.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class LoadResources {

    private final Toolkit t; 
    private final Dimension d;

    public LoadResources() {
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
    }

    public void load() {
        String PATH = System.getProperty("user.home") + "/qbert/img/";

        final File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Dimensions.setScreenHeight(d.height);
        Dimensions.setScreenWidth(d.width);

        Dimensions.setWindowHeight(Math.round(new Float(Dimensions.getScreenHeight())));
        Dimensions.setWindowWidth(Math.round(new Float(Dimensions.getScreenWidth())));

        try {
            for (final File file: new File(PATH + "").listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }

            convertSvgToPng("/svg/BackgroundBlue.svg", PATH + "BackgroundBlue.png"); 
            convertSvgToPng("/svg/BackgroundBrown.svg", PATH + "BackgroundBrown.png"); 
            convertSvgToPng("/svg/BackgroundGreen.svg", PATH + "BackgroundGreen.png"); 
            convertSvgToPng("/svg/BackgroundGrey.svg", PATH + "BackgroundGrey.png"); 
            convertSvgToPng("/svg/CoilyBackMove.svg", PATH + "CoilyBackMove.png"); 
            convertSvgToPng("/svg/CoilyBackStand.svg", PATH + "CoilyBackStand.png"); 
            convertSvgToPng("/svg/CoilyFrontMove.svg", PATH + "CoilyFrontMove.png"); 
            convertSvgToPng("/svg/CoilyFrontStand.svg", PATH + "CoilyFrontStand.png");
            convertSvgToPng("/svg/GreenBallMove.svg", PATH + "GreenBallMove.png"); 
            convertSvgToPng("/svg/GreenBallStand.svg", PATH + "GreenBallStand.png");
            convertSvgToPng("/svg/PurpleBallMove.svg", PATH + "PurpleBallMove.png"); 
            convertSvgToPng("/svg/PurpleBallStand.svg", PATH + "PurpleBallStand.png");
            convertSvgToPng("/svg/QbertBackMove.svg", PATH + "QbertBackMove.png"); 
            convertSvgToPng("/svg/QbertBackStand.svg", PATH + "QbertBackStand.png"); 
            convertSvgToPng("/svg/QbertFrontMove.svg", PATH + "QbertFrontMove.png"); 
            convertSvgToPng("/svg/QbertFrontStand.svg", PATH + "QbertFrontStand.png");
            convertSvgToPng("/svg/QbertDead.svg", PATH + "QbertDead.png");
            convertSvgToPng("/svg/QbertOnDisk.svg", PATH + "QbertOnDisk.png"); 
            convertSvgToPng("/svg/RedBallMove.svg", PATH + "RedBallMove.png"); 
            convertSvgToPng("/svg/RedBallStand.svg", PATH + "RedBallStand.png");
            convertSvgToPng("/svg/TileBeige.svg", PATH + "TileBeige.png");
            convertSvgToPng("/svg/TileBlue.svg", PATH + "TileBlue.png");
            convertSvgToPng("/svg/TileGreen.svg", PATH + "TileGreen.png");
            convertSvgToPng("/svg/TileGrey.svg", PATH + "TileGrey.png");
            convertSvgToPng("/svg/TilePink.svg", PATH + "TilePink.png");
            convertSvgToPng("/svg/TileYellow.svg", PATH + "TileYellow.png");
            convertSvgToPng("/svg/Life.svg", PATH + "Life.png");
            convertSvgToPng("/svg/Disk1.svg", PATH + "Disk1.png");
            convertSvgToPng("/svg/Disk2.svg", PATH + "Disk2.png");
            convertSvgToPng("/svg/Disk3.svg", PATH + "Disk3.png");
            convertSvgToPng("/svg/Disk4.svg", PATH + "Disk4.png");
            convertSvgToPng("/svg/SamMove.svg", PATH + "SamMove.png");
            convertSvgToPng("/svg/SamStand.svg", PATH + "SamStand.png");
            convertSvgToPng("/svg/SlickMove.svg", PATH + "SlickMove.png");
            convertSvgToPng("/svg/SlickStand.svg", PATH + "SlickStand.png");
            convertSvgToPng("/svg/WrongWayMove.svg", PATH + "WrongWayMove.png");
            convertSvgToPng("/svg/WrongWayStand.svg", PATH + "WrongWayStand.png");
            convertSvgToPng("/svg/UggMove.svg", PATH + "UggMove.png");
            convertSvgToPng("/svg/UggStand.svg", PATH + "UggStand.png");

        } catch (Exception e) {
            System.out.println("Error convert " + e.getMessage());
        }

        try {
              Sprites.blueBackground = loadImg(PATH + "BackgroundBlue.png");
              Sprites.brownBackground = loadImg(PATH + "BackgroundBrown.png");
              Sprites.greenBackground = loadImg(PATH + "BackgroundGreen.png");
              Sprites.greyBackground = loadImg(PATH + "BackgroundGrey.png");
              Sprites.coilyBackMoving = loadImg(PATH + "CoilyBackMove.png");
              Sprites.coilyBackStanding = loadImg(PATH + "CoilyBackStand.png");
              Sprites.coilyFrontMoving = loadImg(PATH + "CoilyFrontMove.png");
              Sprites.coilyFrontStanding = loadImg(PATH + "CoilyFrontStand.png");
              Sprites.greenBallMoving = loadImg(PATH + "GreenBallMove.png");
              Sprites.greenBallStanding = loadImg(PATH + "GreenBallStand.png");
              Sprites.purpleBallMoving = loadImg(PATH + "PurpleBallMove.png");
              Sprites.purpleBallStanding = loadImg(PATH + "PurpleBallStand.png");
              Sprites.samStanding = loadImg(PATH + "SamStand.png");
              Sprites.samMoving = loadImg(PATH + "SamMove.png");
              Sprites.slickStanding = loadImg(PATH + "SlickStand.png");
              Sprites.slickMoving = loadImg(PATH + "SlickMove.png");
              Sprites.disk1 = loadImg(PATH + "Disk1.png");
              Sprites.disk2 = loadImg(PATH + "Disk2.png");
              Sprites.disk3 = loadImg(PATH + "Disk3.png");
              Sprites.disk4 = loadImg(PATH + "Disk4.png");
              Sprites.qbertBackMoving = loadImg(PATH + "QbertBackMove.png"); 
              Sprites.qbertBackStanding = loadImg(PATH + "QbertBackStand.png");
              Sprites.qbertFrontMoving = loadImg(PATH + "QbertFrontMove.png"); 
              Sprites.qbertFrontStanding = loadImg(PATH + "QbertFrontStand.png");
              Sprites.qbertDead = loadImg(PATH + "QbertDead.png");
              Sprites.qbertOnDisk = loadImg(PATH + "QbertOnDisk.png");
              Sprites.redBallMoving = loadImg(PATH + "RedBallMove.png");
              Sprites.redBallStanding = loadImg(PATH + "RedBallStand.png");
              Sprites.beigeTile = loadImg(PATH + "TileBeige.png");
              Sprites.blueTile = loadImg(PATH + "TileBlue.png");
              Sprites.greenTile = loadImg(PATH + "TileGreen.png"); 
              Sprites.greyTile = loadImg(PATH + "TileGrey.png");
              Sprites.pinkTile = loadImg(PATH + "TilePink.png");
              Sprites.yellowTile = loadImg(PATH + "TileYellow.png");
              Sprites.life = loadImg(PATH + "Life.png");
              Sprites.wrongwayStanding = loadImg(PATH + "WrongWayStand.png");
              Sprites.wrongwayMoving = loadImg(PATH + "WrongWayMove.png");
              Sprites.uggStanding = loadImg(PATH + "UggStand.png");
              Sprites.uggMoving = loadImg(PATH + "UggMove.png");

        } catch (Exception e) {
            System.out.println("Error load " + e.toString());
        }

        Dimensions.setSpawningHeight(-Dimensions.getWindowHeight() / 3);
        Dimensions.setDeathHeight(Dimensions.getWindowHeight() + Dimensions.getSpawningHeight());
        Dimensions.setSideSpawningHeight(Dimensions.getWindowWidth() / 3);
        Dimensions.setSideDeathHeight(Dimensions.getWindowWidth() + Dimensions.getSideSpawningHeight());
        Dimensions.setSpawningPointLeft(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - Sprites.blueTile.getWidth(), -500));
        Dimensions.setSpawningPointRight(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f), -500));
        Dimensions.setBackgroundHeight(Sprites.blueBackground.getHeight());
        Dimensions.setBackgroundWidth(Sprites.blueBackground.getWidth());
        Dimensions.setBackgroundX(Math.round(new Float(Dimensions.getWindowWidth() - Dimensions.getBackgroundWidth()) / 2f));
        Dimensions.setBackgroundY(Math.round(new Float(Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2f));
        Dimensions.setCubeHeight(Math.round(new Float(Dimensions.getBackgroundHeight()) / 7f));
        Dimensions.setCubeWidth(Math.round(new Float(Dimensions.getBackgroundWidth()) / 7f));
        Dimensions.setTileHeight(Sprites.blueTile.getHeight());
        Dimensions.setTileWidth(Sprites.blueTile.getWidth());
        Dimensions.setSpawningQBert(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - Math.round(new Float(Sprites.qbertFrontMoving.getWidth()) / 2f), 
                Dimensions.getBackgroundY() - Sprites.qbertFrontStanding.getHeight()));
        Dimensions.setSpawningLogPointLeft(new Position2D(Dimensions.MAP_SPAWNING_POINT_LEFT_X, Dimensions.MAP_SPAWNING_POINT_LEFT_Y));
        Dimensions.setSpawningLogPointRight(new Position2D(Dimensions.MAP_SPAWNING_POINT_RIGHT_X, Dimensions.MAP_SPAWNING_POINT_RIGHT_Y));
        Dimensions.setSpawningLogQBert(new Position2D(Dimensions.MAP_SPAWNING_QBERT_X, Dimensions.MAP_SPAWNING_QBERT_Y));

    }

    private BufferedImage loadImg(final String pathOut) throws Exception {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File(pathOut));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void convertSvgToPng(final String svg, final String png) throws Exception {

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
    }
}
