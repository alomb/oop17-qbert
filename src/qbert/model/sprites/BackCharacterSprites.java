package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * Interface o allow the storage of sprites for character who moves back.
 */
public interface BackCharacterSprites {

    /**
     * @return the sprite used when the character when is standing facing back
     */
    BufferedImage getBackStandSprite();

    /**
     *  @return the sprite used when the character when is moving facing back
     */
    BufferedImage getBackMoveSprite();
}
