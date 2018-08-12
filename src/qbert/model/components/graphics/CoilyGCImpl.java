package qbert.model.components.graphics;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.characters.StraightMovementAnimation;

/**
 * The implementation of {@link CoilyGC}.
 */
public class CoilyGCImpl extends DownUpwardCharacterGCImpl implements CoilyGC {

    private final BufferedImage adultFrontStandSprite;
    private final BufferedImage adultMoveStandSprite;

    private final Position2D landPos;

    /**
     * @param frontStandSprite the {@link BufferedImage} containing the {@link Character}'s standing front sprite
     * @param adultFrontStandSprite {@link BufferedImage} containing the {@link Character}'s adult standing front sprite
     * @param adultMoveStandSprite {@link BufferedImage} containing the {@link Character}'s adult moving front sprite
     * @param frontMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving front sprite
     * @param backStandSprite the {@link BufferedImage} containing the {@link Character}'s standing back sprite
     * @param backMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving back sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public CoilyGCImpl(final BufferedImage frontStandSprite, final BufferedImage adultFrontStandSprite, final BufferedImage adultMoveStandSprite, 
            final BufferedImage frontMoveSprite, final BufferedImage backStandSprite, final BufferedImage backMoveSprite, final Position2D startSpritePos) {
        super(frontStandSprite, frontMoveSprite, backStandSprite, backMoveSprite, startSpritePos);
        this.adultFrontStandSprite = adultFrontStandSprite;
        this.adultMoveStandSprite = adultMoveStandSprite;
        this.landPos = new Position2D(this.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - this.getSpriteHeight());
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void transform() {
        this.getPosition().setY(
                this.getPosition().getY() + this.getFrontStandSprite().getHeight() - adultFrontStandSprite.getHeight());
        this.setFrontStandSprite(this.adultFrontStandSprite);
        this.setFrontMoveSprite(this.adultMoveStandSprite);
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.getFrontMoveSprite());
        if (!this.isRight()) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getSpawnPosition(), this.landPos));
    }
}
