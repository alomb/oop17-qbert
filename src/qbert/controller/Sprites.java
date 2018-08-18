package qbert.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final String SPRITES_PATH = System.getProperty("user.home") 
            + System.getProperty("file.separator")
            + "qbert"
            + System.getProperty("file.separator")
            + "sprites"
            + System.getProperty("file.separator");

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

    private Sprites() throws IOException {
        this.loadSprites();
    }

    /**
     * @return an instance of {@link Sprites}
     * @throws IOException if an error creating {@link Sprites} during the file reading occurs
     * @throws UnsupportedOperationException
     */
    public static Sprites getInstance() throws IOException {
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

    /**
     * @return a {@link FrontBackCharacterSprites} containing {@link Qbert}'s front sprites
     */
    public OneSideCharacterSprites getQbertFrontSprites() {
        return new OneSideCharacterSpritesImpl(qbertFrontStanding, qbertFrontMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Qbert}'s back sprites
     */
    public OneSideCharacterSprites getQbertBackSprites() {
        return new OneSideCharacterSpritesImpl(qbertBackStanding, qbertBackMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Qbert}'s special sprites
     */
    public SpecialCharacterSprites getQbertSpecialSprites() {
        return new SpecialCharacterSpritesImpl(qbertDead, qbertOnDisk);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Coily}'s front sprites
     */
    public OneSideCharacterSprites getCoilyFrontSprites() {
        return new OneSideCharacterSpritesImpl(coilyFrontStanding, coilyFrontMoving);
    }

    /**
     * @return a {@link SpecialCharacterSprites} containing {@link Coily}'s back sprites
     */
    public OneSideCharacterSprites getCoilyBackSprites() {
        return new OneSideCharacterSpritesImpl(coilyBackStanding, coilyBackMoving);
    }

    /**
     * @return {@link RedBall} sprites
     */
    public OneSideCharacterSprites getRedBallSprites() {
        return new OneSideCharacterSpritesImpl(redBallStanding, redBallMoving);
    }

    /**
     * @return {@link GreenBall} sprites
     */
    public OneSideCharacterSprites getGreenBallSprites() {
        return new OneSideCharacterSpritesImpl(greenBallStanding, greenBallMoving);
    }

    /**
     * @return {@link Coily} sprites when it's a ball
     */
    public OneSideCharacterSprites getPurpleBallSprites() {
        return new OneSideCharacterSpritesImpl(purpleBallStanding, purpleBallMoving);
    }

    /**
     * @return Slick ({@link SamAndSlick}) sprites
     */
    public OneSideCharacterSprites getSlickSprites() {
        return new OneSideCharacterSpritesImpl(slickStanding, slickMoving);
    }

    /**
     * @return Sam ({@link SamAndSlick}) sprites
     */
    public OneSideCharacterSprites getSamSprites() {
        return new OneSideCharacterSpritesImpl(samStanding, samMoving);
    }

    /**
     * @return {@link Wrongway} sprites
     */
    public OneSideCharacterSprites getWrongwaySprites() {
        return new OneSideCharacterSpritesImpl(wrongwayStanding, wrongwayMoving);
    }

    /**
     * @return {@link Ugg} sprites
     */
    public OneSideCharacterSprites getUggSprites() {
        return new OneSideCharacterSpritesImpl(uggStanding, uggMoving);
    }

    /**
     * @return {@link Disk} sprites
     */
    public Map<Integer, BufferedImage> getDiskSprites() {
        final Map<Integer, BufferedImage> sprites = new HashMap<>();
        sprites.put(0, disk1);
        sprites.put(1, disk2);
        sprites.put(2, disk3);
        sprites.put(3, disk4);
        return sprites;
    }

    /**
     * @return tiles colors associated to the blue background.
     */
    public ColorComposition getBlueColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(yellowTile);
        tiles.add(pinkTile);
        tiles.add(greyTile);
        tiles.add(blueTile);
        return new ColorCompositionImpl(blueBackground, tiles);
    }

    /**
     * @return tiles colors associated to the brown background.
     */
    public ColorComposition getBrownColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(blueTile);
        tiles.add(pinkTile);
        tiles.add(greenTile);
        tiles.add(beigeTile);
        tiles.add(greyTile);
        return new ColorCompositionImpl(brownBackground, tiles);
    }

    /**
     * @return tiles colors associated to the green background.
     */
    public ColorComposition getGreenColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(yellowTile);
        tiles.add(pinkTile);
        tiles.add(greyTile);
        tiles.add(blueTile);
        return new ColorCompositionImpl(greenBackground, tiles);
    }

    /**
     * @return tiles colors associated to the grey background.
     */
    public ColorComposition getGreyColorComposition() {
        final List<BufferedImage> tiles = new ArrayList<>();
        tiles.add(greenTile);
        tiles.add(pinkTile);
        tiles.add(beigeTile);
        tiles.add(blueTile);
        return new ColorCompositionImpl(greyBackground, tiles);
    }

    /**
     * Method used to initialize {@link Sprites}.
     * @throws IOException
     */
    private void loadSprites() throws IOException {
        blueBackground = loadImg(SPRITES_PATH + "BackgroundBlue.png");
        brownBackground = loadImg(SPRITES_PATH + "BackgroundBrown.png");
        greenBackground = loadImg(SPRITES_PATH + "BackgroundGreen.png");
        greyBackground = loadImg(SPRITES_PATH + "BackgroundGrey.png");
        coilyBackMoving = loadImg(SPRITES_PATH + "CoilyBackMove.png");
        coilyBackStanding = loadImg(SPRITES_PATH + "CoilyBackStand.png");
        coilyFrontMoving = loadImg(SPRITES_PATH + "CoilyFrontMove.png");
        coilyFrontStanding = loadImg(SPRITES_PATH + "CoilyFrontStand.png");
        greenBallMoving = loadImg(SPRITES_PATH + "GreenBallMove.png");
        greenBallStanding = loadImg(SPRITES_PATH + "GreenBallStand.png");
        purpleBallMoving = loadImg(SPRITES_PATH + "PurpleBallMove.png");
        purpleBallStanding = loadImg(SPRITES_PATH + "PurpleBallStand.png");
        samStanding = loadImg(SPRITES_PATH + "SamStand.png");
        samMoving = loadImg(SPRITES_PATH + "SamMove.png");
        slickStanding = loadImg(SPRITES_PATH + "SlickStand.png");
        slickMoving = loadImg(SPRITES_PATH + "SlickMove.png");
        disk1 = loadImg(SPRITES_PATH + "Disk1.png");
        disk2 = loadImg(SPRITES_PATH + "Disk2.png");
        disk3 = loadImg(SPRITES_PATH + "Disk3.png");
        disk4 = loadImg(SPRITES_PATH + "Disk4.png");
        qbertBackMoving = loadImg(SPRITES_PATH + "QbertBackMove.png"); 
        qbertBackStanding = loadImg(SPRITES_PATH + "QbertBackStand.png");
        qbertFrontMoving = loadImg(SPRITES_PATH + "QbertFrontMove.png"); 
        qbertFrontStanding = loadImg(SPRITES_PATH + "QbertFrontStand.png");
        qbertDead = loadImg(SPRITES_PATH + "QbertDead.png");
        qbertOnDisk = loadImg(SPRITES_PATH + "QbertOnDisk.png");
        redBallMoving = loadImg(SPRITES_PATH + "RedBallMove.png");
        redBallStanding = loadImg(SPRITES_PATH + "RedBallStand.png");
        beigeTile = loadImg(SPRITES_PATH + "TileBeige.png");
        blueTile = loadImg(SPRITES_PATH + "TileBlue.png");
        greenTile = loadImg(SPRITES_PATH + "TileGreen.png"); 
        greyTile = loadImg(SPRITES_PATH + "TileGrey.png");
        pinkTile = loadImg(SPRITES_PATH + "TilePink.png");
        yellowTile = loadImg(SPRITES_PATH + "TileYellow.png");
        wrongwayStanding = loadImg(SPRITES_PATH + "WrongWayStand.png");
        wrongwayMoving = loadImg(SPRITES_PATH + "WrongWayMove.png");
        uggStanding = loadImg(SPRITES_PATH + "UggStand.png");
        uggMoving = loadImg(SPRITES_PATH + "UggMove.png");

        Dimensions.setBackgroundHeight(blueBackground.getHeight());
        Dimensions.setBackgroundWidth(blueBackground.getWidth());
        Dimensions.setBackgroundPos(new Position2D(Math.round(new Float(Dimensions.getWindowWidth() - Dimensions.getBackgroundWidth()) / 2f), 
                Math.round(new Float(Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2f)));
        Dimensions.setCubeHeight(Math.round(new Float(Dimensions.getBackgroundHeight()) / Dimensions.MAP_TILES_ROWS));
        Dimensions.setCubeWidth(Math.round(new Float(Dimensions.getBackgroundWidth()) / Dimensions.MAP_TILES_COLUMNS));
        Dimensions.setTileHeight(blueTile.getHeight());
    }

    /**
     * @param pathOut the image's name 
     * @return the relative {@link BufferedImage}
     * @throws IOException if an error reading the file occurs
     */
    private BufferedImage loadImg(final String name) throws IOException {
        return ImageIO.read(new File(name));
    }
}
