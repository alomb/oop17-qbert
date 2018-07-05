package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;

/**
 * Generic implementation of GraphicComponent interface.
 */
public class GenericGC implements GraphicComponent {

    private final BufferedImage sprite;
    private final Position2D spritePos;

    /**
     * Constructor of the class GenericGC.
     * @param sprite Sprite representing the {@link GameObject}
     * @param position Pixel position of the Sprite
     */
    public GenericGC(final BufferedImage sprite, final Position2D position) {
        this.sprite = sprite;
        this.spritePos = new Position2D(position);
    }

    @Override
    public final BufferedImage getSprite() {
        return this.sprite;
    }

    @Override
    public final int getSpriteWidth() {
        return this.sprite.getWidth();
    }

    @Override
    public final Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public final void setPosition(final Position2D newPos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final int getSpriteHeight() {
        return this.sprite.getHeight();
    }
}
