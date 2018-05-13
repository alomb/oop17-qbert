package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;


import qbert.model.Tile;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class TileGraphicComponent implements GraphicComponent {

    private Tile tile;
    private Position2D spritePos;
    private Map<Integer, BufferedImage> currentSprites;

    public TileGraphicComponent(Tile tile, Map<Integer, BufferedImage> sprites) {
        this.currentSprites = sprites;
        this.tile = tile;
    }

    @Override
    public BufferedImage getSprite() {
        return currentSprites.get(tile.getColor());
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
