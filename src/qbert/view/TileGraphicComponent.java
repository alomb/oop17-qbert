package qbert.view;

import java.awt.image.BufferedImage;

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

}
