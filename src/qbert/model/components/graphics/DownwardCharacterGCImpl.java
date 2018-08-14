package qbert.model.components.graphics;

import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.characters.ComposedAnimation;
import qbert.view.characters.StraightMovementAnimation;
import qbert.view.characters.StandingAnimation;

/**
 * GC stands graphic component, this implementation is used to manage characters whose movements are unidirectional and 
 * downward, doesn't support upward animations.
 */
public class DownwardCharacterGCImpl extends CharacterGCImpl {

    private final OneSideCharacterSprites sprites;

    private final int jumpWidth = Dimensions.getCubeWidth();
    private final int jumpHeight = Dimensions.getCubeHeight();

    private final Position2D landPos;

    /**
     * A boolean to set true when the character has moved right (up or down).
     */
    private boolean right;

    /**
     * @param sprites the {@link Character} sprites container
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public DownwardCharacterGCImpl(final OneSideCharacterSprites sprites, final Position2D startSpritePos) {
        super(sprites.getStandSprite(), startSpritePos);
        this.sprites = sprites;
        this.landPos = new Position2D(this.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - this.getSpriteHeight()); 
        this.right = true;
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void setStandingAnimation() {
        this.setSprite(this.sprites.getStandSprite());
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StandingAnimation(this.getPosition()));
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getSpawnPosition(), this.landPos));
    }

    @Override
    public final void setFallAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getPosition(), new Position2D(this.getPosition().getX(), Dimensions.getWindowHeight() + this.getSpriteHeight())));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }
}
