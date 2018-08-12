package qbert.model.components.graphics;

import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.characters.ComposedAnimation;
import qbert.view.characters.StraightMovementAnimation;
import qbert.view.characters.StandingAnimation;

/**
 * GC stands for graphic component, this implementation is used to manage characters whose movements are bidirectional and 
 * whose spawn is instant on some position, like characters of type {@link DownUpwardCharacter}.
 */
public abstract class DownUpwardCharacterGCImpl extends CharacterGCImpl implements DownUpwardCharacterGC {

    private final OneSideCharacterSprites backSprites;
    private OneSideCharacterSprites frontSprites;


    /**
     * A boolean to set true when the character has moved front (down) or is standing front.
     */
    private boolean front;

    /**
     * A boolean to set true when the character has moved right (up or down).
     */
    private boolean right;

    private final int jumpWidth = Dimensions.getTileWidth();
    private final int jumpHeight = Dimensions.getCubeHeight();

    /**
     * @param frontSprites the {@link OneSideCharacterSprites} containing the {@link Character}'s standing front sprite
     * @param backSprites the {@link OneSideCharacterSprites} containing the {@link Character}'s standing front sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public DownUpwardCharacterGCImpl(final OneSideCharacterSprites frontSprites, final OneSideCharacterSprites backSprites, final Position2D startSpritePos) {
        super(frontSprites.getStandSprite(), startSpritePos);
        this.frontSprites = frontSprites;
        this.backSprites = backSprites;

        this.front = true;
        this.right = true;
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
    public final OneSideCharacterSprites getFrontSprites() {
        return this.frontSprites;
    }

    @Override
    public final void setFrontSprites(final OneSideCharacterSprites frontSprites) {
        this.frontSprites = frontSprites;
    }

    @Override
    public abstract void setDeathAnimation();

    @Override
    public final void setStandingAnimation() {
        if (this.front) {
            this.setSprite(this.frontSprites.getStandSprite());
        } else {
            this.setSprite(this.backSprites.getStandSprite());
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
            this.setSprite(this.frontSprites.getMoveSprite());
        } else {
            this.setSprite(this.backSprites.getMoveSprite());
        }

        if (!this.right) {
            this.flipOnYImage();
        }

        this.setCurrentAnimation(new StraightMovementAnimation(this.getPosition(), new Position2D(this.getPosition().getX(), Dimensions.getDeathHeight())));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.frontSprites.getMoveSprite());
        this.front = true;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.frontSprites.getMoveSprite());
        this.front = true;
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveUpLeftAnimation() {
        this.setSprite(this.backSprites.getMoveSprite());
        this.front = false;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpUpLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }

    @Override
    public final void setMoveUpRightAnimation() {
        this.setSprite(this.backSprites.getMoveSprite());
        this.front = false;
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpUpRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }
}
