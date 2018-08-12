package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * An implementation of {@link FrontCharacterSprites}.
 */
public class FrontCharacterSpritesImpl implements FrontCharacterSprites {

    private final BufferedImage standSprite;
    private final BufferedImage moveSprite;

    /**
     * @param standSprite the sprite used when the character is standing
     * @param moveSprite the sprite used when the character is moving
     */
    public FrontCharacterSpritesImpl(final BufferedImage standSprite, final BufferedImage moveSprite) {
        this.standSprite = standSprite;
        this.moveSprite = moveSprite;
    }

    @Override 
    public final BufferedImage getFrontStandSprite() {
        return this.standSprite;
    }

    @Override 
    public final BufferedImage getFrontMoveSprite() {
        return this.moveSprite;
    }
}
