package qbert.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.GraphicComponent;
import qbert.view.TileGraphicComponent;

/**
 * .
 */
public class Tile implements GameObject {

    private int color;
    private Position2D position;
    private GraphicComponent graphicComponent;

    /**
     * 
     */
    public Tile(final int x, final int y) {
        this.color = 0;
        Map<Integer, BufferedImage> m = new HashMap<>();
        m.put(0, Sprites.blueTile);
        m.put(1, Sprites.yellowTile);
        m.put(2, Sprites.pinkTile);
        
        //Temporary management of graphic component generation
        this.graphicComponent = new TileGraphicComponent(this, m);
        this.graphicComponent.setPosition(new Position2D(x, y));
    }

    @Override
    public Position2D getCurrentPosition() {
        return this.position;
    }

    @Override
    public void setCurrentPosition(Position2D currentGridPos) {
        this.position = currentGridPos;
    }

    public int getColor() {
        return this.color;
    }

    public GraphicComponent getGraphicComponent() {
        return this.graphicComponent;
    }
    
    public void reset() {
        this.color = 0;
    }
    
    public void increment() {
        this.color++;
    }
    
    public void decrement() {
        this.color--;
    }
}
