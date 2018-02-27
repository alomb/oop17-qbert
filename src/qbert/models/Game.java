package qbert.models;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Game  {

    private Toolkit t; 
    private Dimension d;
    private int screenHeight;
    private int screenWidth;
   
    public Game() {
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
        screenHeight = d.height;
        screenWidth = d.width;
    }
    
    public int getScreenHeight() {
        return screenHeight;
    }
    
    public int getScreenHeight() {
        return screenHeight;
    }
}
