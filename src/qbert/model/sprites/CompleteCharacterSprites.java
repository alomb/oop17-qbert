package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * An interface that allows all possible sprites.
 */
public interface CompleteCharacterSprites extends FrontCharacterSprites, BackCharacterSprites {

    /**
     * @return the sprite for the character when is dead
     */
    BufferedImage getDeathSprite();

    /**
     *  @return the sprite for the character when is surfing the disk
     */
    BufferedImage getOnDiskSprite();
}
