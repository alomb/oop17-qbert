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
        Random rand = new Random();
        //Generation of random color
        this.color = rand.nextInt(3);
        this.graphicComponent = new TileGraphicComponent(this);
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
}
