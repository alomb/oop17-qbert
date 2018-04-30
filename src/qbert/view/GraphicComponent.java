package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;

/**
 * This interface models a generic graphic component containing information on the sprite 
 * and its position in the scene (pixels).
 */
public interface GraphicComponent {

    /**
     * @return the current sprite
     */
    BufferedImage getSprite();

    /**
     * @return the current sprite height
     */
    int getSpriteHeight();

    /**
     * @return the current sprite width
     */
    int getSpriteWidth();

    /**
     * @return the current sprite position ({@link Position2D}) in the space 
     */
    Position2D getPosition();

    /**
     * @param newPos the new sprite position ({@link Position2D}) in the space 
     */
    void setPosition(Position2D newPos);
}
