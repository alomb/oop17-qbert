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
        

        
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
         
        Sprites.blueTile = loadImg("/blueTile.png");
        Sprites.pinkTile = loadImg("/pinkTile.png");
        Sprites.yellowTile = loadImg("/yellowTile.png");
        Sprites.life = loadImg("/life.png");
        Sprites.blueBackground = loadImg("/blueBackground.png"); 
        Sprites.brownBackground = loadImg("/brownBackground.png"); 
        Sprites.qbertFrontMoving = loadImg("/QbertFrontMoving.png"); 
        Sprites.qbertFrontStanding = loadImg("/QbertFrontStanding.png"); 
        Sprites.RedBallMoving = loadImg("/redBallMoving.png"); 
        Sprites.RedBallStanding = loadImg("/redBallStanding.png"); 
        
        Dimensions.screenHeight = d.height;
        Dimensions.screenWidth = d.width;
        Dimensions.windowHeight = Dimensions.screenHeight*3/4;
        Dimensions.windowWidth = Dimensions.screenWidth*3/4;
        Dimensions.deathHeight = Dimensions.windowHeight+200;
        Dimensions.spawingHeight = -100;
        Dimensions.spawingPointLeft = new Position2D((Dimensions.windowWidth/2)-Sprites.blueTile.getWidth(),-500);
        Dimensions.spawingPointRight = new Position2D((Dimensions.windowWidth/2),-500);
        Dimensions.spawingQBert = new Position2D((Dimensions.windowWidth/2)-Sprites.blueTile.getWidth()/2,-500);
        Dimensions.backgroundHeight = Sprites.blueBackground.getHeight();
        Dimensions.backgroundWidth = Sprites.blueBackground.getWidth();
        Dimensions.backgroundX = (Dimensions.windowWidth-Dimensions.backgroundWidth)/2;
        Dimensions.backgroundY = (Dimensions.windowHeight-Dimensions.backgroundHeight)/2;
        Dimensions.cubeHeight = Sprites.blueBackground.getHeight()/7;
        Dimensions.tileHeight = Sprites.blueTile.getHeight();
        Dimensions.tileWidth = Sprites.blueTile.getWidth();
        Dimensions.tileWidth = Sprites.blueTile.getWidth();
        
        gameLevel = new Level();
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
