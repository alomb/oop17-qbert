package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;
import qbert.view.animations.Animation;
import qbert.view.animations.StraightAnimation;
import qbert.view.animations.StandingAnimation;

public class CharacterGraphicComponentImpl implements CharacterGraphicComponent {

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
    
    public void setStandingAnimation() {
        this.animation = new StandingAnimation(this.spritePos);
    }

    @Override
    public void updateGraphics(final float graphicsSpeed) {
        this.animation.updateAnimation(graphicsSpeed);
    }

}
