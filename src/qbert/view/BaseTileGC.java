package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The implementation of {@link TileGC}.
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
    public BufferedImage getSprite() {
        return this.sprites.get(this.spriteIndex);
    }

    @Override
    public Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public void setPosition(final Position2D logicPos) {
        this.spritePos = new Position2D(
            Dimensions.backgroundX + Dimensions.tileWidth / 2 * (logicPos.getX() + 1) - Dimensions.tileWidth / 2,
            Dimensions.backgroundY + Dimensions.backgroundHeight - (Dimensions.cubeHeight * (logicPos.getY() + 1)) - (Dimensions.tileHeight / 2)
        );
    }

    @Override
    public int getSpriteHeight() {
        return this.getSprite().getHeight();
    }

    @Override
    public int getSpriteWidth() {
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
    public void setPreviousSprite() {
        if (this.spriteIndex > 0) {
            this.spriteIndex--;
        }
    }

    @Override
    public void setSprite(final int index) {
        if (index < this.sprites.size() && index >= 0) {
            this.spriteIndex = index;
        }
    }

    @Override
    public int getSpriteIndex() {
        return this.spriteIndex;
    }
    
    @Override 
    public boolean isTargetColor() {
        return this.spriteIndex == this.sprites.size() - 1;
    }
}
