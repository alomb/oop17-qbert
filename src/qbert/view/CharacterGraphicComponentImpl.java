package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.view.animations.Animation;
import qbert.view.animations.Jump;
import qbert.view.animations.MoveAnimation;
import qbert.view.animations.StandingAnimation;

/**
 * A generic implementation of {@link CharacterGraphicComponent}.
 */
public class CharacterGraphicComponentImpl implements CharacterGraphicComponent {

    private BufferedImage sprite;
    private int spriteHeight;
    private int spriteWidth;

    private Position2D spritePos;

    private Animation animation;
    
    private final int jumpWidth = Dimensions.tileWidth;
    private final int jumpHeight = Dimensions.cubeHeight;
    private Position2D spawnPos;
    private final Position2D landPos;

    /**
     * @param sprite the {@link BufferedImage} containing the {@link Character}'s sprite
     * @param startSpritePos the first position (physic) of the {@link Character}
     */
    public CharacterGraphicComponentImpl(final BufferedImage sprite, final Position2D startSpritePos) {
        this.sprite = sprite;
        
        this.spriteHeight = sprite.getHeight();
        this.spriteWidth = sprite.getWidth();

        this.spritePos = new Position2D(startSpritePos);
        this.spawnPos = new Position2D(startSpritePos);
        this.landPos = new Position2D(this.spawnPos.getX(), (Dimensions.windowHeight - Dimensions.heightBackground)/2 + Dimensions.heightCube - this.spriteHeight); 
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }

    @Override
    public void setSprite(final BufferedImage newSprite) {
        this.sprite = newSprite;
    }

    @Override
    public int getSpriteHeight() {
        return this.spriteHeight;
    }

    @Override
    public void setSpriteHeight(final int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    @Override
    public int getSpriteWidth() {
        return this.spriteWidth;
    }

    @Override
    public void setSpriteWidth(final int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    @Override
    public Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public void setPosition(final Position2D newPos) {
        this.spritePos = newPos;
    }

    @Override
    public Animation getCurrentAnimation() {
        return this.animation;
    }

    /**
     * This method provide extension classes to modify the private field animation.
     * @param animation the new animation to set
     */
    public void setCurrentAnimation(final Animation animation) {
        this.animation = animation;
    }

    @Override
    public void setStandingAnimation() {
        this.animation = new StandingAnimation(this.spritePos);
    }

    @Override
    public void setSpawnPosToCurrentPos() {
        this.spawnPos = new Position2D(this.spritePos.getX(), this.spritePos.getY());
    }

    @Override
    public void setSpawnAnimation() {
        this.animation = new MoveAnimation.Down(this.spawnPos, this.landPos);
    }

    @Override
    public void setFallAnimation() {
        this.animation = new MoveAnimation.Down(this.spritePos, new Position2D(this.spritePos.getX(), Dimensions.deathHeight));
    }

    @Override
    public void setMoveDownLeftAnimation() {
        this.animation = new Jump.DownLeft(this.spritePos, new Position2D(this.spritePos.getX() - this.jumpWidth/2, this.spritePos.getY() + this.jumpHeight));
    }

    @Override
    public void setMoveDownRightAnimation() {
        this.animation = new Jump.DownRight(this.spritePos, new Position2D(this.spritePos.getX() + this.jumpWidth/2, this.spritePos.getY() + this.jumpHeight));
    }

    @Override
    public void setMoveUpLeftAnimation() {
        this.animation = new Jump.UpLeft(this.spritePos, new Position2D(this.spritePos.getX() - this.jumpWidth/2, this.spritePos.getY() - this.jumpHeight));
    }

    @Override
    public void setMoveUpRightAnimation() {
        this.animation = new Jump.UpRight(this.spritePos, new Position2D(this.spritePos.getX() + this.jumpWidth/2, this.spritePos.getY() - this.jumpHeight));
    }

    @Override
    public void updateGraphics(final float graphicsSpeed) {
        this.setPosition(this.animation.updateAnimation(graphicsSpeed));
    }
}
