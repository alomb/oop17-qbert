package qbert.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import qbert.model.Sprites;
import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class TileGraphicComponent implements GraphicComponent {

    private Tile tile;
    private Position2D spritePos;

    public TileGraphicComponent(Tile tile) {
        this.tile = tile;
    }

    @Override
    public BufferedImage getSprite() {
        switch (tile.getColor()) {
            case 0:
                return Sprites.blueTile;
            case 1:
                return Sprites.yellowTile;
            case 2:
                return Sprites.pinkTile;
            default:
                return Sprites.blueTile;
        }
    }

    @Override
    public void setSprite(BufferedImage newSprite) {
        return;
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
