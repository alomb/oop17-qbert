package qbert.model;

import qbert.model.components.PointComponent;
import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.RenderableBackground;
import qbert.model.components.graphics.TileGC;

/**
 * Class representing the single {@link Tile} in which the player needs to land to score points.
 */
public class Tile extends RenderableBackground implements GameObject {
    private Position2D position;
    private final TileGC graphicComponent;

    /**
     * Constructor of Tile class.
     * @param pos Position of the {@link Tile} in the map
     * @param graphics Component dealing the tile graphics
     */
    public Tile(final Position2D pos, final TileGC graphics) {
        super();
        this.graphicComponent = graphics;
        this.graphicComponent.setPosition(new Position2D(pos));
        this.position = new Position2D(pos);
    }

    @Override
    public final Position2D getCurrentPosition() {
        return this.position;
    }

    @Override
    public final void setCurrentPosition(final Position2D currentGridPos) {
        this.position = currentGridPos;
    }

    /**
     * @return Current color index of the Tile
     */
    public int getColor() {
        return this.graphicComponent.getSpriteIndex();
    }

    /**
     * @return Tile's GraphicComponent
     */
    public TileGC getGraphicComponent() {
        return this.graphicComponent;
    }

    /**
     * Sets Tile's sprite to its original one.
     */
    public void reset() {
        this.graphicComponent.setSprite(0);
    }

    /**
     * Changes the Tile's sprite.
     * @return Number of points given by the action
     */
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
}
