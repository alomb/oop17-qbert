package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;

/**
 * The simplest implementation of {@link GraphicComponent}.
 */
public class GraphicComponentImpl implements GraphicComponent {

    private final BufferedImage image;
    private Position2D position;

    /**
     * @param image the spriteto draw
     * @param position the position
     */
    public GraphicComponentImpl(final BufferedImage image, final Position2D position) {
        this.image = image;
        this.position = position;
    }

    @Override
    public final BufferedImage getSprite() {
        System.out.println("opapapa");
        return this.image;
    }

    @Override
    public final int getSpriteHeight() {
        return this.image.getHeight();
    }

    @Override
    public final int getSpriteWidth() {
        return this.image.getWidth();
    }

    @Override
    public final Position2D getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Position2D newPos) {
        this.position = newPos;
    }
}
