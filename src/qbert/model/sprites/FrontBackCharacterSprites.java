package qbert.model.sprites;

import java.awt.image.BufferedImage;

/**
 * The implementation of {@link FrontBackCharacterSprites}.
 */
public class FrontBackCharacterSprites extends FrontCharacterSpritesImpl implements FrontCharacterSprites, BackCharacterSprites {

    private final BufferedImage backStandSprite;
    private final BufferedImage backMoveSprite;

    /**
     * @param frontStandSprite the sprite used when the character is standing front
     * @param frontMoveSprite the sprite used when the character is moving front
     * @param backStandSprite the sprite used when the character is standing back
     * @param backMoveSprite the sprite used when the character is moving back
     */
    public FrontBackCharacterSprites(final BufferedImage frontStandSprite, final BufferedImage frontMoveSprite, 
            final BufferedImage backStandSprite, final BufferedImage backMoveSprite) {
        super(frontStandSprite, frontMoveSprite);
        this.backStandSprite = backStandSprite;
        this.backMoveSprite = backMoveSprite;
    }

    @Override 
    public final BufferedImage getBackStandSprite() {
        return this.backStandSprite;
    }

    @Override 
    public final BufferedImage getBackMoveSprite() {
        return this.backMoveSprite;
    }
}
