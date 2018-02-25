package qbert.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class TileGraphicComponent implements GraphicComponent {

    private BufferedImage sprite;
    private int spriteHeight;
    private int spriteWidth;    

    private Tile tile;
    private Position2D spritePos;
    
    public TileGraphicComponent(Tile tile) {
        this.tile = tile;
    }
    
    @Override
    public BufferedImage getSprite() {
        this.colorChange(this.tile.getColor());
        return this.sprite;
    }

    @Override
    public void setSprite(BufferedImage newSprite) {
        this.sprite = newSprite;
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
        return this.spriteHeight;
    }

    @Override
    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    private void colorChange(int color) {
        //TODO: Trovare un metodo per caricare tutte le immagini disponibili una volta sola
        URL spriteUrl = null;
        switch (color) {
        case 0: //Red
            spriteUrl = this.getClass().getResource("/temp_tile_red.png");
            break;
        case 1: //Green
            spriteUrl = this.getClass().getResource("/temp_tile_green.png");
            break;
        case 2: //Yellow
            spriteUrl = this.getClass().getResource("/temp_tile_yellow.png");
            break;
        }
        try {
            this.sprite = ImageIO.read(spriteUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
