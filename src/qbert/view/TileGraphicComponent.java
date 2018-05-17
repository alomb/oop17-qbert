package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;


import qbert.model.Tile;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class TileGraphicComponent implements GraphicComponent {

    private Position2D spritePos;
    private BufferedImage sprite;

    public TileGraphicComponent(BufferedImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }

    @Override
    public Position2D getPosition() {
        return this.spritePos;
    }

    @Override
    public void setPosition(Position2D newPos) {
        this.spritePos = newPos;
    }

    @Override
    public int getSpriteHeight() {
        return this.getSprite().getHeight();
    }

    @Override
    public int getSpriteWidth() {
        return this.getSprite().getWidth();
    }
}
