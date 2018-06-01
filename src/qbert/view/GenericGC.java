package qbert.view;

import java.awt.image.BufferedImage;

import qbert.model.utilities.Position2D;

public class GenericGC implements GraphicComponent {

    private BufferedImage sprite;
    private Position2D spritePos;

    public GenericGC(final BufferedImage sprite, final Position2D position) {
        this.sprite = sprite;
        this.spritePos = new Position2D(position);
    }
    
    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }

    @Override
    public int getSpriteWidth() {
        return this.sprite.getWidth();
    }

    @Override
    public Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public void setPosition(Position2D newPos) {
        return;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int getSpriteHeight() {
        return this.sprite.getHeight();
    }
}
