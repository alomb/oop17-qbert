package qbert.model;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Game {

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

    public int getScreenWidth() {
        return screenWidth;
    }

}
