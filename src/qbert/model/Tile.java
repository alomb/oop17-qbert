package qbert.model;

import qbert.model.components.PointComponent;
import qbert.model.utilities.Position2D;
import qbert.view.Renderable;
import qbert.view.TileGC;

/**
 * Class representing the single {@link Tile} in which the player needs to land to score points
 */
public class Tile implements GameObject, Renderable {
    private Position2D position;
    private final TileGC graphicComponent;

    /**
     * Constructor of Tile class
     * @param pos Position of the {@link Tile} in the map
     * @param graphics Component dealing the tile graphics
     */
    public Tile(final Position2D pos, final TileGC graphics) {
        this.graphicComponent = graphics;
        this.graphicComponent.setPosition(new Position2D(pos));
        this.position = new Position2D(pos);
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
        return this.graphicComponent.getSpriteIndex();
    }

    public TileGC getGraphicComponent() {
        return this.graphicComponent;
    }

    public void reset() {
        this.graphicComponent.setSprite(0);
    }

    public int increment() {
        if (this.graphicComponent.setNextSprite()) {
            if (this.graphicComponent.isTargetColor()) {
                return PointComponent.TARGET_COLOR_SCORE;
            } else {
                return PointComponent.INTERMEDIATE_COLOR_SCORE;
            }
        }
        return 0;
    }

    //Forse da spostare in una classe astratta
    @Override
    public int getZIndex() {
        return 0;
    }
}
