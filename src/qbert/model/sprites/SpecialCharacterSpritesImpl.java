package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * Implementation of {@link SpecialCharacterSprites}.
 */
public class SpecialCharacterSpritesImpl implements SpecialCharacterSprites {

    private final BufferedImage deathSprite;
    private final BufferedImage onDiskSprite;

    /**
     * @param deathSprite the sprite used when the character is dead
     * @param onDiskSprite the sprite used when the character is surfing the disk
     */
    public SpecialCharacterSpritesImpl(final BufferedImage deathSprite, final BufferedImage onDiskSprite) {
        this.deathSprite = deathSprite;
        this.onDiskSprite = onDiskSprite;
    }

    @Override
    public final BufferedImage getDeathSprite() {
        return this.deathSprite;
    }

    @Override
    public final BufferedImage getOnDiskSprite() {
        return this.onDiskSprite;
    }

}
