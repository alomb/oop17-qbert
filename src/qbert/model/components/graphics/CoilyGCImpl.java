package qbert.model.components.graphics;

import qbert.model.components.graphics.animations.StraightMovementAnimation;
import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * The implementation of {@link CoilyGC}.
 */
public class CoilyGCImpl extends DownUpwardCharacterGCImpl implements CoilyGC {

    private final OneSideCharacterSprites adultSprites;

    private final Position2D landPos;

    /**
     * @param ballSprites the {@link OneSideCharacterSprites} containing the {@link Character}'s standing front sprite when he's in his ball form
     * @param adultSprites the {@link OneSideCharacterSprites} containing the {@link Character}'s standing front sprite when he's in his adult form
     * @param adultBackSprites the {@link OneSideCharacterSprites} containing the {@link Character}'s standing front sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public CoilyGCImpl(final OneSideCharacterSprites ballSprites, final OneSideCharacterSprites adultSprites, final OneSideCharacterSprites adultBackSprites, final Position2D startSpritePos) {
        super(ballSprites, adultBackSprites, startSpritePos);
        this.adultSprites = adultSprites;
        this.landPos = new Position2D(this.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - this.getSpriteHeight());
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void transform() {
        this.getPosition().setY(
                this.getPosition().getY() + this.getFrontSprites().getStandSprite().getHeight() - this.adultSprites.getStandSprite().getHeight());
        this.setFrontSprites(this.adultSprites);
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.getFrontSprites().getMoveSprite());
        if (!this.isRight()) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getSpawnPosition(), this.landPos));
    }
}
