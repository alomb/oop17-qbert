package qbert.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import qbert.model.sprites.ColorComposition;
import qbert.model.sprites.ColorCompositionImpl;
import qbert.model.sprites.SpecialCharacterSprites;
import qbert.model.sprites.SpecialCharacterSpritesImpl;
import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.sprites.OneSideCharacterSpritesImpl;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * A Singleton used to load images and retrieve group of them for different purposes.
 */
public final class Sprites {

    private BufferedImage blueBackground;
    private BufferedImage brownBackground;
    private BufferedImage greenBackground;
    private BufferedImage greyBackground;
    private BufferedImage coilyBackMoving;
    private BufferedImage coilyBackStanding;
    private BufferedImage coilyFrontMoving;
    private BufferedImage coilyFrontStanding;
    private BufferedImage greenBallMoving;
    private BufferedImage greenBallStanding;
    private BufferedImage purpleBallMoving;
    private BufferedImage purpleBallStanding;
    private BufferedImage samStanding;
    private BufferedImage samMoving;
    private BufferedImage slickStanding;
    private BufferedImage slickMoving;
    private BufferedImage disk1;
    private BufferedImage disk2;
    private BufferedImage disk3;
    private BufferedImage disk4;
    private BufferedImage qbertBackMoving;
    private BufferedImage qbertBackStanding;
    private BufferedImage qbertFrontMoving;
    private BufferedImage qbertFrontStanding;
    private BufferedImage qbertDead;
    private BufferedImage qbertOnDisk;
    private BufferedImage redBallMoving;
    private BufferedImage redBallStanding;
    private BufferedImage beigeTile;
    private BufferedImage blueTile;
    private BufferedImage greenTile;
    private BufferedImage greyTile;
    private BufferedImage pinkTile;
    private BufferedImage yellowTile;
    private BufferedImage wrongwayStanding;
    private BufferedImage wrongwayMoving;
    private BufferedImage uggStanding;
    private BufferedImage uggMoving;

    private static volatile Sprites instance;
    private static Object mutex = new Object();

    private Sprites() {
        final String imgPath = System.getProperty("user.home") + "/qbert/img/";

        try {
            this.blueBackground = loadImg(imgPath + "BackgroundBlue.png");
            this.brownBackground = loadImg(imgPath + "BackgroundBrown.png");
            this.greenBackground = loadImg(imgPath + "BackgroundGreen.png");
            this.greyBackground = loadImg(imgPath + "BackgroundGrey.png");
            this.coilyBackMoving = loadImg(imgPath + "CoilyBackMove.png");
            this.coilyBackStanding = loadImg(imgPath + "CoilyBackStand.png");
            this.coilyFrontMoving = loadImg(imgPath + "CoilyFrontMove.png");
            this.coilyFrontStanding = loadImg(imgPath + "CoilyFrontStand.png");
            this.greenBallMoving = loadImg(imgPath + "GreenBallMove.png");
            this.greenBallStanding = loadImg(imgPath + "GreenBallStand.png");
            this.purpleBallMoving = loadImg(imgPath + "PurpleBallMove.png");
            this.purpleBallStanding = loadImg(imgPath + "PurpleBallStand.png");
            this.samStanding = loadImg(imgPath + "SamStand.png");
            this.samMoving = loadImg(imgPath + "SamMove.png");
            this.slickStanding = loadImg(imgPath + "SlickStand.png");
            this.slickMoving = loadImg(imgPath + "SlickMove.png");
            this.disk1 = loadImg(imgPath + "Disk1.png");
            this.disk2 = loadImg(imgPath + "Disk2.png");
            this.disk3 = loadImg(imgPath + "Disk3.png");
            this.disk4 = loadImg(imgPath + "Disk4.png");
            this.qbertBackMoving = loadImg(imgPath + "QbertBackMove.png"); 
            this.qbertBackStanding = loadImg(imgPath + "QbertBackStand.png");
            this.qbertFrontMoving = loadImg(imgPath + "QbertFrontMove.png"); 
            this.qbertFrontStanding = loadImg(imgPath + "QbertFrontStand.png");
            this.qbertDead = loadImg(imgPath + "QbertDead.png");
            this.qbertOnDisk = loadImg(imgPath + "QbertOnDisk.png");
            this.redBallMoving = loadImg(imgPath + "RedBallMove.png");
            this.redBallStanding = loadImg(imgPath + "RedBallStand.png");
            this.beigeTile = loadImg(imgPath + "TileBeige.png");
            this.blueTile = loadImg(imgPath + "TileBlue.png");
            this.greenTile = loadImg(imgPath + "TileGreen.png"); 
            this.greyTile = loadImg(imgPath + "TileGrey.png");
            this.pinkTile = loadImg(imgPath + "TilePink.png");
            this.yellowTile = loadImg(imgPath + "TileYellow.png");
            this.wrongwayStanding = loadImg(imgPath + "WrongWayStand.png");
            this.wrongwayMoving = loadImg(imgPath + "WrongWayMove.png");
            this.uggStanding = loadImg(imgPath + "UggStand.png");
            this.uggMoving = loadImg(imgPath + "UggMove.png");

      } catch (IOException e) {
          Logger.getGlobal().log(Level.SEVERE, "Error during images loading", e);
      }

      Dimensions.setBackgroundHeight(this.blueBackground.getHeight());
      Dimensions.setBackgroundWidth(this.blueBackground.getWidth());
      Dimensions.setBackgroundPos(new Position2D(Math.round(new Float(Dimensions.getWindowWidth() - Dimensions.getBackgroundWidth()) / 2f), 
              Math.round(new Float(Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2f)));
      Dimensions.setCubeHeight(Math.round(new Float(Dimensions.getBackgroundHeight()) / Dimensions.MAP_TILES_ROWS));
      Dimensions.setCubeWidth(Math.round(new Float(Dimensions.getBackgroundWidth()) / Dimensions.MAP_TILES_COLUMNS));
      Dimensions.setTileHeight(this.blueTile.getHeight());
    }

    /**
     * @return the unique instance of this class
     */
    public static Sprites getInstance() {
        Sprites result = Sprites.instance;
        if (result == null) {
                synchronized (mutex) {
                    result = Sprites.instance;
                    if (result == null) {
                        result = new Sprites();
                        Sprites.instance = result;
                    }
                }
        }
        return result;
    }

    private BufferedImage loadImg(final String pathOut) throws IOException {
        return ImageIO.read(new File(pathOut));
    }

    /**
     * @return a {@link FrontBackCharacterSprites} containing {@link Qbert}'s front sprites
     */
    public OneSideCharacterSprites getQbertFrontSprites() {
        return new OneSideCharacterSpritesImpl(this.qbertFrontStanding, this.qbertFrontMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Qbert}'s back sprites
     */
    public OneSideCharacterSprites getQbertBackSprites() {
        return new OneSideCharacterSpritesImpl(this.qbertBackStanding, this.qbertBackMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Qbert}'s special sprites
     */
    public SpecialCharacterSprites getQbertSpecialSprites() {
        return new SpecialCharacterSpritesImpl(this.qbertDead, this.qbertOnDisk);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Coily}'s front sprites
     */
    public OneSideCharacterSprites getCoilyFrontSprites() {
        return new OneSideCharacterSpritesImpl(this.coilyFrontStanding, this.coilyFrontMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Coily}'s back sprites
     */
    public OneSideCharacterSprites getCoilyBackSprites() {
        return new OneSideCharacterSpritesImpl(this.coilyBackStanding, this.coilyBackMoving);
    }

    /**
     * @return {@link RedBall} sprites
     */
    public OneSideCharacterSprites getRedBallSprites() {
        return new OneSideCharacterSpritesImpl(this.redBallStanding, this.redBallMoving);
    }

    /**
     * @return {@link GreenBall} sprites
     */
    public OneSideCharacterSprites getGreenBallSprites() {
        return new OneSideCharacterSpritesImpl(this.greenBallStanding, this.greenBallMoving);
    }

    /**
     * @return {@link Coily} sprites when it's a ball
     */
    public OneSideCharacterSprites getPurpleBallSprites() {
        return new OneSideCharacterSpritesImpl(this.purpleBallStanding, this.purpleBallMoving);
    }

    /**
     * @return Slick ({@link SamAndSlick}) sprites
     */
    public OneSideCharacterSprites getSlickSprites() {
        return new OneSideCharacterSpritesImpl(this.slickStanding, this.slickMoving);
    }

    /**
     * @return Sam ({@link SamAndSlick}) sprites
     */
    public OneSideCharacterSprites getSamSprites() {
        return new OneSideCharacterSpritesImpl(this.samStanding, this.samMoving);
    }

    /**
     * @return {@link Wrongway} sprites
     */
    public OneSideCharacterSprites getWrongwaySprites() {
        return new OneSideCharacterSpritesImpl(this.wrongwayStanding, this.wrongwayMoving);
    }

    /**
     * @return {@link Ugg} sprites
     */
    public OneSideCharacterSprites getUggSprites() {
        return new OneSideCharacterSpritesImpl(this.uggStanding, this.uggMoving);
    }

    /**
     * @return {@link Disk} sprites
     */
    public Map<Integer, BufferedImage> getDiskSprites() {
        final Map<Integer, BufferedImage> sprites = new HashMap<>();
        sprites.put(0, this.disk1);
        sprites.put(1, this.disk2);
        sprites.put(2, this.disk3);
        sprites.put(3, this.disk4);
        return sprites;
    }

    /**
     * @return tiles colors associated to the blue background.
     */
    public ColorComposition getBlueColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(this.yellowTile);
        tiles.add(this.pinkTile);
        tiles.add(this.greyTile);
        tiles.add(this.blueTile);
        return new ColorCompositionImpl(this.blueBackground, tiles);
    }

    /**
     * @return tiles colors associated to the brown background.
     */
    public ColorComposition getBrownColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(this.blueTile);
        tiles.add(this.pinkTile);
        tiles.add(this.greenTile);
        tiles.add(this.beigeTile);
        tiles.add(this.greyTile);
        return new ColorCompositionImpl(this.brownBackground, tiles);
    }

    /**
     * @return tiles colors associated to the green background.
     */
    public ColorComposition getGreenColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(this.yellowTile);
        tiles.add(this.pinkTile);
        tiles.add(this.greyTile);
        tiles.add(this.blueTile);
        return new ColorCompositionImpl(this.greenBackground, tiles);
    }

    /**
     * @return tiles colors associated to the grey background.
     */
    public ColorComposition getGreyColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(this.greenTile);
        tiles.add(this.pinkTile);
        tiles.add(this.beigeTile);
        tiles.add(this.blueTile);
        return new ColorCompositionImpl(this.greyBackground, tiles);
    }
}
