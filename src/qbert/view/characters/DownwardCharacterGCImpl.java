package qbert.view.characters;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.ComposedAnimation;
import qbert.view.animations.BasicAnimation;
import qbert.view.animations.StandingAnimation;

/**
 * GC stands graphic component, this implementation is used to manage characters whose movements are unidirectional and 
 * downward, doesn't support upward animations.
 */
public class DownwardCharacterGCImpl extends CharacterGCImpl {

    private final BufferedImage standSprite;
    private final BufferedImage moveSprite;

    private final int jumpWidth = Dimensions.getTileWidth();
    private final int jumpHeight = Dimensions.getCubeHeight();

    private final Position2D landPos;

    /**
     * A boolean to set true when the character has moved right (up or down).
     */
    private boolean right;

    /**
     * @param standSprite the {@link BufferedImage} containing the {@link Character}'s standing sprite
     * @param moveSprite the {@link BufferedImage} containing the {@link Character}'s moving sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public DownwardCharacterGCImpl(final BufferedImage standSprite, final BufferedImage moveSprite, final Position2D startSpritePos) {
        super(standSprite, startSpritePos);
        this.standSprite = standSprite;
        this.moveSprite = moveSprite;
        this.landPos = new Position2D(this.getSpawnPosition().getX(), (Dimensions.getWindowHeight() - Dimensions.getBackgroundHeight()) / 2 + Dimensions.getCubeHeight() - this.getSpriteHeight()); 
        this.right = true;
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void setStandingAnimation() {
        this.setSprite(this.standSprite);
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new StandingAnimation(this.getPosition()));
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.moveSprite);
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new BasicAnimation.Down(this.getSpawnPosition(), this.landPos));
    }

    @Override
    public final void setFallAnimation() {
        this.setSprite(this.moveSprite);
        if (!this.right) {
            this.flipOnYImage();
        }
        this.setCurrentAnimation(new BasicAnimation.Down(this.getPosition(), new Position2D(this.getPosition().getX(), Dimensions.getDeathHeight())));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.moveSprite);
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.moveSprite);
        this.right = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }
}
