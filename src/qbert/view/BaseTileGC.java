package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * Implementation of {@link TileGC}.
 */
public class BaseTileGC implements TileGC {

    protected Position2D spritePos;
    protected final Map<Integer, BufferedImage> sprites;
    protected int spriteIndex;

    /**
     * @param sprites the map of colors orderly indexed
     */
    public BaseTileGC(final Map<Integer, BufferedImage> sprites) {
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
            Dimensions.backgroundX + Dimensions.tileWidth / 2 * (logicPos.getX() + 1) - Dimensions.tileWidth / 2,
            Dimensions.backgroundY + Dimensions.backgroundHeight - (Dimensions.cubeHeight * (logicPos.getY() + 1)) - (Dimensions.tileHeight / 2)
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
    public boolean setNextSprite() {
        if (this.spriteIndex < this.sprites.size() - 1) {
            this.spriteIndex++;
            return true;
        }
        return false;
    }

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
}
