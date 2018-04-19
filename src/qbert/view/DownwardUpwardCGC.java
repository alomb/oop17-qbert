package qbert.view;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.DisplaceAnimation;
import qbert.view.animations.Jump;
import qbert.view.animations.MoveAnimation;
import qbert.view.animations.StandingAnimation;

/**
 * CGC stands for CharacterGraphicComponent, this implementation is used to manage characters whose movements are bidirectional and 
 * whose spawn is instant on some position, like Coily (adult) and QBert.
 */
public class DownwardUpwardCGC extends CharacterGraphicComponentImpl {

    private final BufferedImage frontStandSprite;
    private final BufferedImage frontMoveSprite;
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
    public DownwardUpwardCGC(final BufferedImage frontStandSprite, final BufferedImage frontMoveSprite, 
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
    public final void setSpawnAnimation() {
        this.setSprite(this.frontStandSprite);
        this.right = true;
        this.front = true;
        this.setCurrentAnimation(new DisplaceAnimation(this.getPosition(), this.getSpawnPosition()));
    }

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

        this.setCurrentAnimation(new MoveAnimation.Down(this.getPosition(), new Position2D(this.getPosition().getX(), Dimensions.deathHeight)));
    }

    @Override
    public final void setMoveDownLeftAnimation() {
        this.setSprite(this.frontMoveSprite);
        this.front = true;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new Jump.DownLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveDownRightAnimation() {
        this.setSprite(this.frontMoveSprite);
        this.front = true;
        this.right = true;
        this.setCurrentAnimation(new Jump.DownRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() + this.jumpHeight)));
    }

    @Override
    public final void setMoveUpLeftAnimation() {
        this.setSprite(this.backMoveSprite);
        this.front = false;
        this.right = false;
        this.flipOnYImage();
        this.setCurrentAnimation(new Jump.UpLeft(this.getPosition(), new Position2D(this.getPosition().getX() - this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }

    @Override
    public final void setMoveUpRightAnimation() {
        this.setSprite(this.backMoveSprite);
        this.front = false;
        this.right = true;
        this.setCurrentAnimation(new Jump.UpRight(this.getPosition(), new Position2D(this.getPosition().getX() + this.jumpWidth / 2, this.getPosition().getY() - this.jumpHeight)));
    }

    /**
     * A method to flip on Y axis the current sprite.
     */
    private void flipOnYImage() {
        final AffineTransform transformation = AffineTransform.getScaleInstance(-1, 1);
        transformation.translate(-this.getSpriteWidth(), 0);
        final AffineTransformOp operation = new AffineTransformOp(transformation, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        this.setSprite(operation.filter(this.getSprite(), null));
    }
}
