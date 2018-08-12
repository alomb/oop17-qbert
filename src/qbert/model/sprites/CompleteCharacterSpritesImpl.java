package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * Implementation of {@link CompleteCharacterSprites}.
 */
public class CompleteCharacterSpritesImpl extends FrontBackCharacterSprites implements CompleteCharacterSprites {

    private final BufferedImage deathSprite;
    private final BufferedImage onDiskSprite;

    /**
     * @param frontStandSprite the sprite used when the character is standing front
     * @param frontMoveSprite the sprite used when the character is moving front
     * @param backStandSprite the sprite used when the character is standing back
     * @param backMoveSprite the sprite used when the character is moving back
     * @param deathSprite the sprite used when the character is dead
     * @param onDiskSprite the sprite used when the character is surfing the disk
     */
    public CompleteCharacterSpritesImpl(final BufferedImage frontStandSprite, final BufferedImage frontMoveSprite, 
            final BufferedImage backStandSprite, final BufferedImage backMoveSprite, final BufferedImage deathSprite,
            final BufferedImage onDiskSprite) {
        super(frontStandSprite, frontMoveSprite, backStandSprite, backMoveSprite);
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
