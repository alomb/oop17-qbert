package qbert.view.characters;

import java.awt.image.BufferedImage;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.ComposedAnimation;
import qbert.view.animations.BasicAnimation;
import qbert.view.animations.StandingAnimation;

/**
 * GC stands for graphic component, this implementation is used to manage characters whose movements are bidirectional and 
 * whose spawn is instant on some position, like characters of type {@link DownUpwardCharacter}.
 */
public abstract class DownUpwardCharacterGCImpl extends CharacterGCImpl implements DownUpwardCharacterGC {

    private BufferedImage frontStandSprite;
    private BufferedImage frontMoveSprite;
    private final BufferedImage backStandSprite;
    private final BufferedImage backMoveSprite;

    /**
     * A boolean to set true when the character has moved front (down) or is standing front.
     */
    private boolean front;

    /**
     * A boolean to set true when the character has moved right (up or down).
     */
    private boolean right;

    private final int jumpWidth = Dimensions.tileWidth;
    private final int jumpHeight = Dimensions.cubeHeight;

    /**
     * @param frontStandSprite the {@link BufferedImage} containing the {@link Character}'s standing front sprite
     * @param frontMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving front sprite
     * @param backStandSprite the {@link BufferedImage} containing the {@link Character}'s standing back sprite
     * @param backMoveSprite the {@link BufferedImage} containing the {@link Character}'s moving back sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public DownUpwardCharacterGCImpl(final BufferedImage frontStandSprite, final BufferedImage frontMoveSprite, 
            final BufferedImage backStandSprite, final BufferedImage backMoveSprite, final Position2D startSpritePos) {
        super(frontStandSprite, startSpritePos);
        this.front = true;
        this.right = true;
        this.backStandSprite = backStandSprite;
        this.backMoveSprite = backMoveSprite;
        this.frontStandSprite = frontStandSprite;
        this.frontMoveSprite = frontMoveSprite;
    }
 
    @Override
    public final boolean isFront() {
        return front;
    }

    @Override
    public final void setFront(final boolean front) {
        this.front = front;
    }

    @Override
    public final boolean isRight() {
        return front;
    }

    @Override
    public final void setRight(final boolean right) {
        this.right = right;
    }

    @Override
    public final BufferedImage getFrontStandSprite() {
        return this.frontStandSprite;
    }

    @Override
    public final void setFrontStandSprite(final BufferedImage frontStandSprite) {
        this.frontStandSprite = frontStandSprite;
    }

    @Override
    public final BufferedImage getFrontMoveSprite() {
        return this.frontMoveSprite;
    }

    @Override
    public final void setFrontMoveSprite(final BufferedImage frontMoveSprite) {
        this.frontMoveSprite = frontMoveSprite;
    }

    @Override
    public abstract void setDeathAnimation();

    @Override
    public final void setStandingAnimation() {
        if (this.front) {
            this.setSprite(this.frontStandSprite);
        } else {
            this.setSprite(this.backStandSprite);
        }

        if (!this.right) {
            this.flipOnYImage();
        }

        this.setCurrentAnimation(new StandingAnimation(this.getPosition()));
    }

    @Override
    public abstract void setSpawnAnimation();

    @Override
    public final void setFallAnimation() {
        if (this.front) {
            this.setSprite(this.frontMoveSprite);
        } else {
            this.setSprite(this.backMoveSprite);
        }

        if (!this.right) {
            this.flipOnYImage();
        }

        this.setCurrentAnimation(new BasicAnimation.Down(this.getPosition(), new Position2D(this.getPosition().getX(), Dimensions.deathHeight)));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.frontMoveSprite);
        this.front = true;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.frontMoveSprite);
        this.front = true;
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveUpLeftAnimation() {
        this.setSprite(this.backMoveSprite);
        this.front = false;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpUpLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }

    @Override
    public final void setMoveUpRightAnimation() {
        this.setSprite(this.backMoveSprite);
        this.front = false;
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpUpRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }
}
