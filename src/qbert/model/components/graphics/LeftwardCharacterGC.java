package qbert.model.components.graphics;

import qbert.model.components.graphics.animations.ComposedAnimation;
import qbert.model.components.graphics.animations.StandingAnimation;
import qbert.model.components.graphics.animations.StraightMovementAnimation;
import qbert.model.sprites.OneSideCharacterSprites;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;

/**
 * GC stands graphic component, this implementation is used to manage characters whose movements are unidirectional and 
 * leftward, doesn't support rightward animations.
 */
public class LeftwardCharacterGC extends CharacterGCImpl {

    private final OneSideCharacterSprites sprites;

    private final int jumpWidth = Dimensions.getCubeHeight();
    private final int jumpHeight = Dimensions.getCubeWidth() / 2;

    private final Position2D landPos;

    /**
     * A boolean to set true when the character is facing up.
     */
    private boolean up;

    /**
     * @param sprites the {@link Character} sprites container
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public LeftwardCharacterGC(final OneSideCharacterSprites sprites, final Position2D startSpritePos) {
        super(sprites.getStandSprite(), startSpritePos);
        this.sprites = sprites;
        this.landPos = new Position2D(Dimensions.getBackgroundPos().getX() + Dimensions.getBackgroundWidth() - this.jumpHeight, this.getSpawnPosition().getY()); 
        this.up = true;
    }

    @Override
    public final void setDeathAnimation() {
        this.setStandingAnimation();
    }

    @Override
    public final void setStandingAnimation() {
        this.setSprite(this.sprites.getStandSprite());
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StandingAnimation(this.getPosition()));
    }

    @Override
    public final void setSpawnAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getSpawnPosition(), this.landPos));
    }

    @Override
    public final void setFallAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        if (!this.up) {
            this.flipOnXImage();
        }
        this.setCurrentAnimation(new StraightMovementAnimation(this.getPosition(), 
                new Position2D(-this.getSpriteWidth(), this.getPosition().getY())));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        this.up = false;
        this.flipOnXImage();
        this.setCurrentAnimation(new ComposedAnimation.JumpDownLeftLeftward(this.getPosition(), 
                new Position2D(this.getPosition().getX() - this.jumpHeight, this.getPosition().getY() + this.jumpWidth)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.sprites.getMoveSprite());
        this.up = true;
        this.setCurrentAnimation(new ComposedAnimation.JumpDownRightLeftward(this.getPosition(), 
                new Position2D(this.getPosition().getX() - this.jumpHeight, this.getPosition().getY() - this.jumpWidth)));
    }
}
