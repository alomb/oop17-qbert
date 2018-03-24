package qbert.model;

import java.awt.Dimension;
import java.awt.Toolkit;

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
    }
    
    public Level getLevel() {
        return gameLevel;
    }
    
    public void update( float elapsed) {
        gameLevel.update(elapsed);
    }
}
