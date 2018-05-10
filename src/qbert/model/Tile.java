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
    private LevelSettings settings;

    /**
     * 
     */
    public Tile(final int x, final int y, final LevelSettings settings) {
        this.color = 0;
        this.settings = settings;

        //Temporary management of graphic component generation
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

    public void incrementColor() {
        if (this.color < this.settings.getColorNumber()) {
            this.color++;
        } else {
            if (this.settings.getColorReversable()) {
                this.resetColor();
            }
        }
    }
    
    public void resetColor() {
        this.color = 0;
    }
}
