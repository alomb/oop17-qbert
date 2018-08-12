package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * An interface that allows sprites for one character's direction.
 */
public interface OneSideCharacterSprites {

    /**
     * @return the sprite used when the character is standing
     */
    BufferedImage getStandSprite();

    /**
     * @return the sprite used when the character is moving
     */
    BufferedImage getMoveSprite();
}
