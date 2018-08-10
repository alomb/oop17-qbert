package qbert.view.characters;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.ComposedAnimation;
import qbert.view.animations.StraightMovementAnimation;
import qbert.view.animations.StandingAnimation;

/**
 * GC stands graphic component, this implementation is used to manage characters whose movements are unidirectional and 
 * leftward, doesn't support rightward animations.
 */
public class LeftwardCharacterGC extends CharacterGCImpl {

    private final BufferedImage standSprite;
    private final BufferedImage moveSprite;

    private final int jumpWidth = Dimensions.getCubeHeight();
    private final int jumpHeight = Dimensions.getTileWidth() / 2;

    private final Position2D landPos;

    /**
     * A boolean to set true when the character is facing up.
     */
    private boolean up;

    /**
     * @param standSprite the {@link BufferedImage} containing the {@link Character}'s standing sprite
     * @param moveSprite the {@link BufferedImage} containing the {@link Character}'s moving sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public LeftwardCharacterGC(final BufferedImage standSprite, final BufferedImage moveSprite, final Position2D startSpritePos) {
        super(standSprite, startSpritePos);
        this.standSprite = standSprite;
        this.moveSprite = moveSprite;
        this.landPos = new Position2D(Dimensions.getBackgroundX() + Dimensions.getBackgroundWidth() - this.jumpHeight, this.getSpawnPosition().getY()); 
        this.up = true;
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void setStandingAnimation() {
        this.setSprite(this.standSprite);
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StandingAnimation(this.getPosition()));
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.moveSprite);
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getSpawnPosition(), this.landPos));
    }

    @Override
    public final void setFallAnimation() {
        this.setSprite(this.moveSprite);
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getPosition(), new Position2D(this.getPosition().getX() - Dimensions.getSideDeathHeight(), this.getPosition().getY())));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.moveSprite);
        this.up = false;
        this.flipOnXImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeftLeftward(this.getPosition(), 
                new Position2D(this.getPosition().getX() - this.jumpHeight, this.getPosition().getY() + this.jumpWidth)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.moveSprite);
        this.up = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRightLeftward(this.getPosition(), 
                new Position2D(this.getPosition().getX() - this.jumpHeight, this.getPosition().getY() - this.jumpWidth)));
    }
}
