package qbert.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import qbert.model.utilities.Position2D;

public class Game {

    private Toolkit t; 
    private Dimension d;
    
    private Level gameLevel;
   
    public Game() {
        
        gameLevel = new Level();
        
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
        Dimensions.screenHeight = d.height;
        Dimensions.screenWidth = d.width;
        Dimensions.windowHeight = 1000;
        Dimensions.windowWidth = 1500;
        Dimensions.deathHeight = Dimensions.windowHeight+200;
        Dimensions.spawingHeight = -100;
        Dimensions.spawingPointLeft = new Position2D((Dimensions.screenWidth/2)-this.loadImg("/blueTile.png").getWidth(),-100);
        Dimensions.spawingPointRight = new Position2D((Dimensions.screenWidth/2),-100);
        Dimensions.spawingQBert = new Position2D((Dimensions.screenWidth/2)-this.loadImg("/blueTile.png").getWidth()/2,-100);
        Dimensions.heightCube = this.loadImg("/background.png").getHeight()/7;
        Dimensions.heightTile = this.loadImg("/blueTile.png").getHeight();
        Dimensions.widthTile = this.loadImg("/blueTile.png").getWidth();
        Dimensions.widthTile = this.loadImg("/blueTile.png").getWidth();
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
