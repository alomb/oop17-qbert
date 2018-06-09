package qbert.model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class Game {

    private Toolkit t; 
    private Dimension d;

    private Level gameLevel;

    public Game() {
        String PATH = "../img/png/";

        File directory = new File(PATH);
        if (! directory.exists()){
            directory.mkdirs();
        }
        
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
        try {
            if(new File("../img/png/").listFiles().length != new File("res/svg/").listFiles().length) {
                for (File file: new File("../img/png/").listFiles()) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
      
                convertSvgToPng("res/svg/BackgroundBlue.svg", "../img/png/BackgroundBlue.png"); 
                convertSvgToPng("res/svg/BackgroundBrown.svg", "../img/png/BackgroundBrown.png"); 
                convertSvgToPng("res/svg/BackgroundGreen.svg", "../img/png/BackgroundGreen.png"); 
                convertSvgToPng("res/svg/BackgroundGrey.svg", "../img/png/BackgroundGrey.png"); 
                convertSvgToPng("res/svg/CoilyBackMove.svg", "../img/png/CoilyBackMove.png"); 
                convertSvgToPng("res/svg/CoilyBackStand.svg", "../img/png/CoilyBackStand.png"); 
                convertSvgToPng("res/svg/CoilyFrontMove.svg", "../img/png/CoilyFrontMove.png"); 
                convertSvgToPng("res/svg/CoilyFrontStand.svg", "../img/png/CoilyFrontStand.png");
                convertSvgToPng("res/svg/GreenBallMove.svg", "../img/png/GreenBallMove.png"); 
                convertSvgToPng("res/svg/GreenBallStand.svg", "../img/png/GreenBallStand.png");
                convertSvgToPng("res/svg/PurpleBallMove.svg", "../img/png/PurpleBallMove.png"); 
                convertSvgToPng("res/svg/PurpleBallStand.svg", "../img/png/PurpleBallStand.png");
                convertSvgToPng("res/svg/QbertBackMove.svg", "../img/png/QbertBackMove.png"); 
                convertSvgToPng("res/svg/QbertBackStand.svg", "../img/png/QbertBackStand.png"); 
                convertSvgToPng("res/svg/QbertFrontMove.svg", "../img/png/QbertFrontMove.png"); 
                convertSvgToPng("res/svg/QbertFrontStand.svg", "../img/png/QbertFrontStand.png");
                convertSvgToPng("res/svg/QbertDead.svg", "../img/png/QbertDead.png");
                convertSvgToPng("res/svg/QbertOnDisk.svg", "../img/png/QbertOnDisk.png"); 
                convertSvgToPng("res/svg/RedBallMove.svg", "../img/png/RedBallMove.png"); 
                convertSvgToPng("res/svg/RedBallStand.svg", "../img/png/RedBallStand.png");
                convertSvgToPng("res/svg/TileBeige.svg", "../img/png/TileBeige.png");
                convertSvgToPng("res/svg/TileBlue.svg", "../img/png/TileBlue.png");
                convertSvgToPng("res/svg/TileGreen.svg", "../img/png/TileGreen.png");
                convertSvgToPng("res/svg/TileGrey.svg", "../img/png/TileGrey.png");
                convertSvgToPng("res/svg/TilePink.svg", "../img/png/TilePink.png");
                convertSvgToPng("res/svg/TilePurple.svg", "../img/png/TilePurple.png");
                convertSvgToPng("res/svg/TileYellow.svg", "../img/png/TileYellow.png");
                convertSvgToPng("res/svg/Life.svg", "../img/png/Life.png");
                convertSvgToPng("res/svg/Disk1.svg", "../img/png/Disk1.png");
                convertSvgToPng("res/svg/Disk2.svg", "../img/png/Disk2.png");
                convertSvgToPng("res/svg/Disk3.svg", "../img/png/Disk3.png");
                convertSvgToPng("res/svg/Disk4.svg", "../img/png/Disk4.png");
                convertSvgToPng("res/svg/SamMove.svg", "../img/png/SamMove.png");
                convertSvgToPng("res/svg/SamStand.svg", "../img/png/SamStand.png");
                convertSvgToPng("res/svg/SlickMove.svg", "../img/png/SlickMove.png");
                convertSvgToPng("res/svg/SlickStand.svg", "../img/png/SlickStand.png");
            }
              

        } catch (Exception e) {
            System.out.println("Error convert " + e.getMessage());
        }

        try {
              Sprites.blueBackground = loadImg("../img/png/BackgroundBlue.png");
              Sprites.brownBackground = loadImg("../img/png/BackgroundBrown.png");
              Sprites.greenBackground = loadImg("../img/png/BackgroundGreen.png");
              Sprites.greyBackground = loadImg("../img/png/BackgroundGrey.png");
              Sprites.coilyBackMoving = loadImg("../img/png/CoilyBackMove.png");
              Sprites.coilyBackStanding = loadImg("../img/png/CoilyBackStand.png");
              Sprites.coilyFrontMoving = loadImg("../img/png/CoilyFrontMove.png");
              Sprites.coilyFrontStanding = loadImg("../img/png/CoilyFrontStand.png");
              Sprites.greenBallMoving = loadImg("../img/png/GreenBallMove.png");
              Sprites.greenBallStanding = loadImg("../img/png/GreenBallStand.png");
              Sprites.purpleBallMoving = loadImg("../img/png/PurpleBallMove.png");
              Sprites.purpleBallStanding = loadImg("../img/png/PurpleBallStand.png");
              Sprites.samStanding = loadImg("../img/png/SamStand.png");
              Sprites.samMoving = loadImg("../img/png/SamMove.png");
              Sprites.slickStanding = loadImg("../img/png/SlickStand.png");
              Sprites.slickMoving = loadImg("../img/png/SlickMove.png");
              Sprites.disk1 = loadImg("../img/png/Disk1.png");
              Sprites.disk2 = loadImg("../img/png/Disk2.png");
              Sprites.disk3 = loadImg("../img/png/Disk3.png");
              Sprites.disk4 = loadImg("../img/png/Disk4.png");
              Sprites.qbertBackMoving = loadImg("../img/png/QbertBackMove.png"); 
              Sprites.qbertBackStanding = loadImg("../img/png/QbertBackStand.png");
              Sprites.qbertFrontMoving = loadImg("../img/png/QbertFrontMove.png"); 
              Sprites.qbertFrontStanding = loadImg("../img/png/QbertFrontStand.png");
              Sprites.qbertDead = loadImg("../img/png/QbertDead.png");
              Sprites.qbertOnDisk = loadImg("../img/png/QbertOnDisk.png");
              Sprites.redBallMoving = loadImg("../img/png/RedBallMove.png");
              Sprites.redBallStanding = loadImg("../img/png/RedBallStand.png");
              Sprites.beigeTile = loadImg("../img/png/TileBeige.png");
              Sprites.blueTile = loadImg("../img/png/TileBlue.png");
              Sprites.greenTile = loadImg("../img/png/TileGreen.png"); 
              Sprites.greyTile = loadImg("../img/png/TileGrey.png");
              Sprites.pinkTile = loadImg("../img/png/TilePink.png");
              Sprites.purpleTile = loadImg("../img/png/TilePurple.png");
              Sprites.yellowTile = loadImg("../img/png/TileYellow.png");
              Sprites.life = loadImg("../img/png/Life.png"); 

        } catch (Exception e) {
            System.out.println("Error load " + e.toString());
            
        }
        
        Dimensions.screenHeight = d.height;
        Dimensions.screenWidth = d.width;
        Dimensions.windowHeight = Dimensions.screenHeight * 3 / 4;
        Dimensions.windowWidth = Dimensions.screenWidth * 3 / 4;
        Dimensions.deathHeight = Dimensions.windowHeight + 200;
        Dimensions.spawningHeight = -100;
        Dimensions.spawningPointLeft = new Position2D((Dimensions.windowWidth / 2) - Sprites.blueTile.getWidth(), -500);
        Dimensions.spawningPointRight = new Position2D((Dimensions.windowWidth / 2), -500);
        Dimensions.backgroundHeight = Sprites.blueBackground.getHeight();
        Dimensions.backgroundWidth = Sprites.blueBackground.getWidth();
        Dimensions.backgroundX = (Dimensions.windowWidth - Dimensions.backgroundWidth) / 2;
        Dimensions.backgroundY = (Dimensions.windowHeight - Dimensions.backgroundHeight) / 2;
        Dimensions.cubeHeight = Sprites.blueBackground.getHeight() / 7;
        Dimensions.tileHeight = Sprites.blueTile.getHeight();
        Dimensions.tileWidth = Sprites.blueTile.getWidth();
        Dimensions.spawningQBert = new Position2D(Dimensions.windowWidth / 2 - Sprites.qbertFrontMoving.getWidth() / 2, Dimensions.backgroundY - Sprites.qbertFrontStanding.getHeight());

        gameLevel = new Level();
    }

    public Level getLevel() {
        return gameLevel;
    }

    public void update(final float elapsed) {
        gameLevel.update(elapsed);
    }

    private BufferedImage loadImg(String pathOut) throws Exception{
        BufferedImage res = null;
        final URL spriteUrl = new URL(new URL("file:"),pathOut);
        System.out.println(spriteUrl.toString());
        try {
            res = ImageIO.read(spriteUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static void convertSvgToPng(String svg, String png) throws Exception {

        String uriSvg = Paths.get(svg).toUri().toURL().toString();
        TranscoderInput imageSvg = new TranscoderInput(uriSvg);
        OutputStream streamPng = new FileOutputStream(png);
        TranscoderOutput outputImagePng = new TranscoderOutput(streamPng);
        PNGTranscoder converter = new PNGTranscoder();

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(svg);

        final Element root = document.getRootElement();
        float width = Float.valueOf(root.getAttributeValue("width"));
        float height = Float.valueOf(root.getAttributeValue("height"));
        converter.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(20*width));
        converter.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(20*height));
        converter.addTranscodingHint(PNGTranscoder.KEY_AOI, new Rectangle(0, 0, Math.round(width), Math.round(height)));

        converter.transcode(imageSvg, outputImagePng);
        streamPng.flush();
        streamPng.close();
    }

}
