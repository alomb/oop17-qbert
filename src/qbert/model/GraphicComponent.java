package qbert.model;

import java.awt.image.BufferedImage;

import model.utilities.Position2D;

/**
 *
 */
public interface GraphicComponent {

    /**
     * @return the {@link Character} sprite
     */
    BufferedImage getSprite();

    /**
     * @param image the {@link Character} new sprite
     */
    void setSprite(BufferedImage image);

    /**
     * @return {@link Character} sprite height
     */
    int getSpriteHeight();

    /**
     * @param spriteHeight the {@link Character} new sprite height
     */
    void setSpriteHeight(int spriteHeight);

    /**
     * @return {@link Character} sprite width
     */
    int getSpriteWidth();

    /**
     * @param spriteWidth the {@link Character} new sprite width
     */
    void setSpriteWidth(int spriteWidth);

    /**
     * @return the {@link Character} position ({@link Position2D}) in the space 
     */
    Position2D getPosition();

    /**
     * @param newPos the new {@link Character} position ({@link Position2D}) in the space 
     */
    void setPosition(Position2D newPos);

    /**
     * @param dt the time elapsed from the last game cycle
     */
    void updateGraphics(float dt);
}
