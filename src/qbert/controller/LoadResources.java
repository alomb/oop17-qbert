package qbert.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
        final String imgPath = System.getProperty("user.home") + "/qbert/img/";

        final File directory = new File(imgPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Dimensions.setScreenHeight(d.height);
        Dimensions.setScreenWidth(d.width);

        Dimensions.setWindowHeight(Math.round(new Float(Dimensions.getScreenHeight())));
        Dimensions.setWindowWidth(Math.round(new Float(Dimensions.getScreenWidth())));

        try {
            for (final File file: new File(imgPath + "").listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }

            convertSvgToPng("/svg/BackgroundBlue.svg", imgPath + "BackgroundBlue.png"); 
            convertSvgToPng("/svg/BackgroundBrown.svg", imgPath + "BackgroundBrown.png"); 
            convertSvgToPng("/svg/BackgroundGreen.svg", imgPath + "BackgroundGreen.png"); 
            convertSvgToPng("/svg/BackgroundGrey.svg", imgPath + "BackgroundGrey.png"); 
            convertSvgToPng("/svg/CoilyBackMove.svg", imgPath + "CoilyBackMove.png"); 
            convertSvgToPng("/svg/CoilyBackStand.svg", imgPath + "CoilyBackStand.png"); 
            convertSvgToPng("/svg/CoilyFrontMove.svg", imgPath + "CoilyFrontMove.png"); 
            convertSvgToPng("/svg/CoilyFrontStand.svg", imgPath + "CoilyFrontStand.png");
            convertSvgToPng("/svg/GreenBallMove.svg", imgPath + "GreenBallMove.png"); 
            convertSvgToPng("/svg/GreenBallStand.svg", imgPath + "GreenBallStand.png");
            convertSvgToPng("/svg/PurpleBallMove.svg", imgPath + "PurpleBallMove.png"); 
            convertSvgToPng("/svg/PurpleBallStand.svg", imgPath + "PurpleBallStand.png");
            convertSvgToPng("/svg/QbertBackMove.svg", imgPath + "QbertBackMove.png"); 
            convertSvgToPng("/svg/QbertBackStand.svg", imgPath + "QbertBackStand.png"); 
            convertSvgToPng("/svg/QbertFrontMove.svg", imgPath + "QbertFrontMove.png"); 
            convertSvgToPng("/svg/QbertFrontStand.svg", imgPath + "QbertFrontStand.png");
            convertSvgToPng("/svg/QbertDead.svg", imgPath + "QbertDead.png");
            convertSvgToPng("/svg/QbertOnDisk.svg", imgPath + "QbertOnDisk.png"); 
            convertSvgToPng("/svg/RedBallMove.svg", imgPath + "RedBallMove.png"); 
            convertSvgToPng("/svg/RedBallStand.svg", imgPath + "RedBallStand.png");
            convertSvgToPng("/svg/TileBeige.svg", imgPath + "TileBeige.png");
            convertSvgToPng("/svg/TileBlue.svg", imgPath + "TileBlue.png");
            convertSvgToPng("/svg/TileGreen.svg", imgPath + "TileGreen.png");
            convertSvgToPng("/svg/TileGrey.svg", imgPath + "TileGrey.png");
            convertSvgToPng("/svg/TilePink.svg", imgPath + "TilePink.png");
            convertSvgToPng("/svg/TileYellow.svg", imgPath + "TileYellow.png");
            convertSvgToPng("/svg/Life.svg", imgPath + "Life.png");
            convertSvgToPng("/svg/Disk1.svg", imgPath + "Disk1.png");
            convertSvgToPng("/svg/Disk2.svg", imgPath + "Disk2.png");
            convertSvgToPng("/svg/Disk3.svg", imgPath + "Disk3.png");
            convertSvgToPng("/svg/Disk4.svg", imgPath + "Disk4.png");
            convertSvgToPng("/svg/SamMove.svg", imgPath + "SamMove.png");
            convertSvgToPng("/svg/SamStand.svg", imgPath + "SamStand.png");
            convertSvgToPng("/svg/SlickMove.svg", imgPath + "SlickMove.png");
            convertSvgToPng("/svg/SlickStand.svg", imgPath + "SlickStand.png");
            convertSvgToPng("/svg/WrongWayMove.svg", imgPath + "WrongWayMove.png");
            convertSvgToPng("/svg/WrongWayStand.svg", imgPath + "WrongWayStand.png");
            convertSvgToPng("/svg/UggMove.svg", imgPath + "UggMove.png");
            convertSvgToPng("/svg/UggStand.svg", imgPath + "UggStand.png");

        } catch (Exception e) {
            System.out.println("Error convert " + e.getMessage());
        }

        try {
              Sprites.blueBackground = loadImg(imgPath + "BackgroundBlue.png");
              Sprites.brownBackground = loadImg(imgPath + "BackgroundBrown.png");
              Sprites.greenBackground = loadImg(imgPath + "BackgroundGreen.png");
              Sprites.greyBackground = loadImg(imgPath + "BackgroundGrey.png");
              Sprites.coilyBackMoving = loadImg(imgPath + "CoilyBackMove.png");
              Sprites.coilyBackStanding = loadImg(imgPath + "CoilyBackStand.png");
              Sprites.coilyFrontMoving = loadImg(imgPath + "CoilyFrontMove.png");
              Sprites.coilyFrontStanding = loadImg(imgPath + "CoilyFrontStand.png");
              Sprites.greenBallMoving = loadImg(imgPath + "GreenBallMove.png");
              Sprites.greenBallStanding = loadImg(imgPath + "GreenBallStand.png");
              Sprites.purpleBallMoving = loadImg(imgPath + "PurpleBallMove.png");
              Sprites.purpleBallStanding = loadImg(imgPath + "PurpleBallStand.png");
              Sprites.samStanding = loadImg(imgPath + "SamStand.png");
              Sprites.samMoving = loadImg(imgPath + "SamMove.png");
              Sprites.slickStanding = loadImg(imgPath + "SlickStand.png");
              Sprites.slickMoving = loadImg(imgPath + "SlickMove.png");
              Sprites.disk1 = loadImg(imgPath + "Disk1.png");
              Sprites.disk2 = loadImg(imgPath + "Disk2.png");
              Sprites.disk3 = loadImg(imgPath + "Disk3.png");
              Sprites.disk4 = loadImg(imgPath + "Disk4.png");
              Sprites.qbertBackMoving = loadImg(imgPath + "QbertBackMove.png"); 
              Sprites.qbertBackStanding = loadImg(imgPath + "QbertBackStand.png");
              Sprites.qbertFrontMoving = loadImg(imgPath + "QbertFrontMove.png"); 
              Sprites.qbertFrontStanding = loadImg(imgPath + "QbertFrontStand.png");
              Sprites.qbertDead = loadImg(imgPath + "QbertDead.png");
              Sprites.qbertOnDisk = loadImg(imgPath + "QbertOnDisk.png");
              Sprites.redBallMoving = loadImg(imgPath + "RedBallMove.png");
              Sprites.redBallStanding = loadImg(imgPath + "RedBallStand.png");
              Sprites.beigeTile = loadImg(imgPath + "TileBeige.png");
              Sprites.blueTile = loadImg(imgPath + "TileBlue.png");
              Sprites.greenTile = loadImg(imgPath + "TileGreen.png"); 
              Sprites.greyTile = loadImg(imgPath + "TileGrey.png");
              Sprites.pinkTile = loadImg(imgPath + "TilePink.png");
              Sprites.yellowTile = loadImg(imgPath + "TileYellow.png");
              Sprites.life = loadImg(imgPath + "Life.png");
              Sprites.wrongwayStanding = loadImg(imgPath + "WrongWayStand.png");
              Sprites.wrongwayMoving = loadImg(imgPath + "WrongWayMove.png");
              Sprites.uggStanding = loadImg(imgPath + "UggStand.png");
              Sprites.uggMoving = loadImg(imgPath + "UggMove.png");

        } catch (Exception e) {
            System.out.println("Error load " + e.toString());
        }

        Dimensions.setSpawningHeight(-Dimensions.getWindowHeight() / 3);
        Dimensions.setDeathHeight(Dimensions.getWindowHeight() - Dimensions.getSpawningHeight());
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
