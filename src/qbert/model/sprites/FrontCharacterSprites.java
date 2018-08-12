package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * An interface that allows sprites for mono directional movements.
 */
public interface FrontCharacterSprites {

    /**
     * @return the sprite used when the character is standing
     */
    BufferedImage getFrontStandSprite();

    /**
     * @return the sprite used when the character is standing
     */
    BufferedImage getFrontMoveSprite();
}
