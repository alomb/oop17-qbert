package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * Implementation of {@link TileGC}.
 */
public abstract class AbstractTileGC implements TileGC {

    /**
     * Physical Position of the sprite in the screen.
     */
    private Position2D spritePos;

    /**
     * Map of available sprite for the tile.
     */
    private final Map<Integer, BufferedImage> sprites;

    /**
     * Index of the active sprite.
     */
    private int spriteIndex;

    /**
     * @param sprites the map of colors orderly indexed
     */
    public AbstractTileGC(final Map<Integer, BufferedImage> sprites) {
        this.sprites = sprites;
        this.spriteIndex = 0;
    }

    @Override
    public final BufferedImage getSprite() {
        return this.sprites.get(this.spriteIndex);
    }

    @Override
    public final Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public final void setPosition(final Position2D logicPos) {
            this.spritePos = new Position2D(
                    Dimensions.getBackgroundX() + (Dimensions.getCubeWidth() / 2) * ((logicPos.getX() - 1) / 2),
                    (Dimensions.getBackgroundY() + Dimensions.getBackgroundHeight()) - (Dimensions.getCubeHeight() * (((logicPos.getY() - 1) / 2) + 1) + Dimensions.getTileHeight() / 2)
            );
    }

    @Override
    public final int getSpriteHeight() {
        return this.getSprite().getHeight();
    }

    @Override
    public final int getSpriteWidth() {
        return this.getSprite().getWidth();
    }

    @Override
    public abstract boolean setNextSprite();

    @Override
    public final void setSprite(final int index) {
        if (index < this.sprites.size() && index >= 0) {
            this.spriteIndex = index;
        }
    }

    @Override
    public final int getSpriteIndex() {
        return this.spriteIndex;
    }

    @Override 
    public final boolean isTargetColor() {
        return this.spriteIndex == this.sprites.size() - 1;
    }

    /**
     * @return The number of available sprite.
     */
    public final int getSpriteNumber() {
        return this.sprites.size();
    }
}
