package qbert.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class TileGraphicComponent implements GraphicComponent {

    private BufferedImage[] sprites;
    private URL[] resources = {
        this.getClass().getResource("/temp_tile_yellow.png"),
        this.getClass().getResource("/temp_tile_red.png"),
        this.getClass().getResource("/temp_tile_green.png"),
    };
    private int spriteHeight;
    private int spriteWidth;

    private Tile tile;
    private Position2D spritePos;

    public TileGraphicComponent(Tile tile) {
        this.sprites = new BufferedImage[3];
        try {
            int i = 0;
            for (URL res : this.resources) {
                this.sprites[i++] = ImageIO.read(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tile = tile;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprites[tile.getColor()];
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
    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }
}
