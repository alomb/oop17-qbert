package qbert.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import qbert.model.utilities.Position2D;
import qbert.model.Sprites;

public class Game {

    private Toolkit t; 
    private Dimension d;
    
    private Level gameLevel;
   
    public Game() {
        
        gameLevel = new Level();
        
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
        
        Sprites.background = loadImg("/background.png"); 
        Sprites.blueTile = loadImg("/blueTile.png");
        Sprites.pinkTile = loadImg("/pinkTile.png");
        Sprites.yellowTile = loadImg("/yellowTile.png");
        Sprites.life = loadImg("/life.png");
        Sprites.blueBackground = loadImg("/blueBackground.png"); 
        Sprites.brownBackground = loadImg("/brownBackground.png"); 
        Sprites.qbertFrontMoving = loadImg("/QbertFrontMoving.png"); 
        Sprites.qbertFrontStanding = loadImg("/QbertFrontStanding.png"); 
        Sprites.redBallMoving = loadImg("/redBallMoving.png"); 
        Sprites.redBallStanding = loadImg("/redBallStanding.png"); 
        Sprites.tempTileRed = loadImg("/temp_tile_red.png"); 
        Sprites.tempTileGreen = loadImg("/temp_tile_green.png"); 
        Sprites.tempTileYellow = loadImg("/temp_tile_yellow.png"); 
        
        Dimensions.screenHeight = d.height;
        Dimensions.screenWidth = d.width;
        Dimensions.windowHeight = 1000;
        Dimensions.windowWidth = 1500;
        Dimensions.deathHeight = Dimensions.windowHeight+200;
        Dimensions.spawingHeight = -100;
        Dimensions.spawingPointLeft = new Position2D((Dimensions.screenWidth/2)-Sprites.blueTile.getWidth(),-100);
        Dimensions.spawingPointRight = new Position2D((Dimensions.screenWidth/2),-100);
        Dimensions.spawingQBert = new Position2D((Dimensions.screenWidth/2)-Sprites.blueTile.getWidth()/2,-100);
        Dimensions.heightCube = Sprites.background.getHeight()/7;
        Dimensions.heightTile = Sprites.blueTile.getHeight();
        Dimensions.widthTile = Sprites.blueTile.getWidth();
        Dimensions.widthTile = Sprites.blueTile.getWidth();
    }
    
    public Level getLevel() {
        return gameLevel;
    }
    
    public void update( float elapsed) {
        gameLevel.update(elapsed);
    }
    
    private BufferedImage loadImg(String path) {
        BufferedImage res = null;
        final URL spriteUrl = this.getClass().getResource(path);
        try {
            res = ImageIO.read(spriteUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
