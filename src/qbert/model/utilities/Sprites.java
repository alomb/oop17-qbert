package qbert.model.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import qbert.model.sprites.ColorComposition;
import qbert.model.sprites.ColorCompositionImpl;
import qbert.model.sprites.CompleteCharacterSprites;
import qbert.model.sprites.CompleteCharacterSpritesImpl;
import qbert.model.sprites.FrontBackCharacterSprites;
import qbert.model.sprites.FrontCharacterSpritesImpl;

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
    private BufferedImage life;
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
            this.life = loadImg(imgPath + "Life.png");
            this.wrongwayStanding = loadImg(imgPath + "WrongWayStand.png");
            this.wrongwayMoving = loadImg(imgPath + "WrongWayMove.png");
            this.uggStanding = loadImg(imgPath + "UggStand.png");
            this.uggMoving = loadImg(imgPath + "UggMove.png");

      } catch (Exception e) {
          System.out.println("Error load " + e.toString());
      }

      Dimensions.setSpawningHeight(-Dimensions.getWindowHeight() / 3);
      Dimensions.setDeathHeight(Dimensions.getWindowHeight() - Dimensions.getSpawningHeight());
      Dimensions.setSideSpawningHeight(Dimensions.getWindowWidth() / 3);
      Dimensions.setSideDeathHeight(Dimensions.getWindowWidth() + Dimensions.getSideSpawningHeight());
      Dimensions.setSpawningPointLeft(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - this.blueTile.getWidth(), -500));
      Dimensions.setSpawningPointRight(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f), -500));
      Dimensions.setBackgroundHeight(this.blueBackground.getHeight());
      Dimensions.setBackgroundWidth(this.blueBackground.getWidth());
      Dimensions.setBackgroundX(Math.round(new Float(Dimensions.getWindowWidth() - Dimensions.getBackgroundWidth()) / 2f));
      Dimensions.setBackgroundY(Math.round(new Float(Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2f));
      Dimensions.setCubeHeight(Math.round(new Float(Dimensions.getBackgroundHeight()) / 7f));
      Dimensions.setCubeWidth(Math.round(new Float(Dimensions.getBackgroundWidth()) / 7f));
      Dimensions.setTileHeight(this.blueTile.getHeight());
      Dimensions.setTileWidth(this.blueTile.getWidth());
      Dimensions.setSpawningQBert(new Position2D(Math.round(new Float(Dimensions.getWindowWidth()) / 2f) - Math.round(new Float(this.qbertFrontMoving.getWidth()) / 2f), 
              Dimensions.getBackgroundY() - this.qbertFrontStanding.getHeight()));
      Dimensions.setSpawningLogPointLeft(new Position2D(Dimensions.MAP_SPAWNING_POINT_LEFT_X, Dimensions.MAP_SPAWNING_POINT_LEFT_Y));
      Dimensions.setSpawningLogPointRight(new Position2D(Dimensions.MAP_SPAWNING_POINT_RIGHT_X, Dimensions.MAP_SPAWNING_POINT_RIGHT_Y));
      Dimensions.setSpawningLogQBert(new Position2D(Dimensions.MAP_SPAWNING_QBERT_X, Dimensions.MAP_SPAWNING_QBERT_Y));

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

    private BufferedImage loadImg(final String pathOut) {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File(pathOut));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @return a {@link CompleteCharacterSprites} containing {@link Qbert}'s sprites
     */
    public CompleteCharacterSprites getQbertSprites() {
        return new CompleteCharacterSpritesImpl(this.qbertFrontStanding, this.qbertFrontMoving, this.qbertBackStanding, this.qbertBackMoving, 
                this.qbertDead, this.qbertOnDisk);
    }

    /**
     * @return a {@link CompleteCharacterSprites} containing {@link Coily}'s sprites
     */
    public FrontBackCharacterSprites getCoilySprites() {
        return new FrontBackCharacterSprites(this.coilyFrontStanding, this.coilyFrontMoving, this.coilyBackStanding, this.coilyBackMoving);
    }

    /**
     * @return {@link RedBall} sprites
     */
    public FrontCharacterSpritesImpl getRedBallSprites() {
        return new FrontCharacterSpritesImpl(this.redBallStanding, this.redBallMoving);
    }

    /**
     * @return {@link GreenBall} sprites
     */
    public FrontCharacterSpritesImpl getGreenBallSprites() {
        return new FrontCharacterSpritesImpl(this.greenBallStanding, this.greenBallMoving);
    }

    /**
     * @return {@link Coily} sprites when it's a ball
     */
    public FrontCharacterSpritesImpl getPurpleBallSprites() {
        return new FrontCharacterSpritesImpl(this.purpleBallStanding, this.purpleBallMoving);
    }

    /**
     * @return Slick ({@link SamAndSlick}) sprites
     */
    public FrontCharacterSpritesImpl getSlickSprites() {
        return new FrontCharacterSpritesImpl(this.slickStanding, this.slickMoving);
    }

    /**
     * @return Sam ({@link SamAndSlick}) sprites
     */
    public FrontCharacterSpritesImpl getSamSprites() {
        return new FrontCharacterSpritesImpl(this.samStanding, this.samMoving);
    }

    /**
     * @return {@link Wrongway} sprites
     */
    public FrontCharacterSpritesImpl getWrongwaySprites() {
        return new FrontCharacterSpritesImpl(this.wrongwayStanding, this.wrongwayMoving);
    }

    /**
     * @return {@link Ugg} sprites
     */
    public FrontCharacterSpritesImpl getUggSprites() {
        return new FrontCharacterSpritesImpl(this.uggStanding, this.uggMoving);
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

    /**
     * @return the player life's sprite
     */
    public BufferedImage getLifeSprite() {
        return this.life;
    }
}
