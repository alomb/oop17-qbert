package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;
import qbert.view.animations.Animation;
import qbert.view.animations.Jump;
import qbert.view.animations.MoveDownAnimation;
import qbert.view.animations.StandingAnimation;

public class CharacterGraphicComponentImpl implements CharacterGraphicComponent {

    /*TODO: Replace magic numbers in animations written for testing in correct numbers*/
    private BufferedImage sprite;
    private int spriteHeight;
    private int spriteWidth;

    private Position2D spritePos;

    private Animation animation;

    public CharacterGraphicComponentImpl(final BufferedImage sprite, final Position2D startSpritePos) {
        this.sprite = sprite;
        this.spritePos = startSpritePos;
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
    public void setSpriteHeight(int spriteHeight) {
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

    @Override
    public void setStandingAnimation() {
        this.animation = new StandingAnimation(this.spritePos);
    }

    @Override
    public void setSpawnAnimation() {
        this.animation = new MoveDownAnimation(this.spritePos, new Position2D(this.spritePos.getX(), this.spritePos.getY() + 100));
    }

    @Override
    public void setFallAnimation() {
        this.animation = new MoveDownAnimation(this.spritePos, new Position2D(this.spritePos.getX(), this.spritePos.getY() + 100));
    }

    @Override
    public void setMoveDownLeftAnimation() {
        this.animation = new Jump.DownLeft(this.spritePos, new Position2D(this.spritePos.getX() - 50, this.spritePos.getY() + 50));
    }

    @Override
    public void setMoveDownRightAnimation() {
        this.animation = new Jump.DownRight(this.spritePos, new Position2D(this.spritePos.getX() + 50, this.spritePos.getY() + 50));
    }

    @Override
    public void setMoveUpLeftAnimation() {
        this.animation = new Jump.UpLeft(this.spritePos, new Position2D(this.spritePos.getX() - 50, this.spritePos.getY() - 50));
    }

    @Override
    public void setMoveUpRightAnimation() {
        this.animation = new Jump.UpRight(this.spritePos, new Position2D(this.spritePos.getX() + 50, this.spritePos.getY() - 50));
    }

    @Override
    public void updateGraphics(final float graphicsSpeed) {
        this.setPosition(this.animation.updateAnimation(graphicsSpeed));
    }
}
