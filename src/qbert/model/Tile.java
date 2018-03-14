package qbert.model;

import java.util.Random;

import qbert.model.utilities.Position2D;
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
    public Tile(final double x, final double y) {
        this.color = 0;

        //Temporary management of graphic component generation
        this.graphicComponent = new TileGraphicComponent(this);
        this.graphicComponent.setPosition(new Position2D(x, y));
        this.graphicComponent.setSpriteHeight(41);
        this.graphicComponent.setSpriteWidth(71);
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
    
    public void incrementColor() {
        this.color++;
    }
    
    public void resetColor() {
        this.color = 0;
    }
}
