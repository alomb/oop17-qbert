package qbert.model;

import qbert.model.utilities.Position2D;

/**
 * .
 */
public class Tile implements GameObject {

    private Position2D position;
    private int color;

    /**
     * 
     */
    public Tile(final double x, final double y) {
        this.position = new Position2D(x, y);
        this.color = 0;
    }

    @Override
    public Position2D getCurrentPosition() {
        return this.position;
    }

    @Override
    public void setCurrentPosition(Position2D currentGridPos) {
        this.position = currentGridPos;
    }

    @Override
    public String toString() {
        return "Tile [Color: " + this.color + "]";
    }
}
