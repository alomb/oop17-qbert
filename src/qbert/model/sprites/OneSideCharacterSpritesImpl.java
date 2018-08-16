package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * An implementation of {@link OneSideCharacterSprites}.
 */
public class OneSideCharacterSpritesImpl implements OneSideCharacterSprites {

    private final BufferedImage standSprite;
    private final BufferedImage moveSprite;

    /**
     * @param standSprite the sprite used when the character is standing
     * @param moveSprite the sprite used when the character is moving
     */
    public OneSideCharacterSpritesImpl(final BufferedImage standSprite, final BufferedImage moveSprite) {
        this.standSprite = standSprite;
        this.moveSprite = moveSprite;
    }

    @Override 
    public final BufferedImage getStandSprite() {
        return this.standSprite;
    }

    @Override 
    public final BufferedImage getMoveSprite() {
        return this.moveSprite;
    }
}
