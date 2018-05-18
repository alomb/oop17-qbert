package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import qbert.model.Tile;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;

public class TileGraphicComponent implements TileGC {

    private Position2D spritePos;
    private Map<Integer, BufferedImage> sprites;
    private int spriteIndex;

    public TileGraphicComponent(Map<Integer, BufferedImage> sprites) {
        this.sprites = sprites;
        this.spriteIndex = 0;
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprites.get(this.spriteIndex);
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

    @Override
    public void setNextSprite() {
        if(this.spriteIndex < this.sprites.size()) {
            this.spriteIndex++;        
        }
    }

    @Override
    public void setPreviousSprite() {
        if(this.spriteIndex > 0) {   
            this.spriteIndex--;
        }
    }

    @Override
    public void setSprite(int index) {
        if(index < this.sprites.size() && index > 0) {
            this.spriteIndex = index;        
        }
    }

    @Override
    public int getSpriteIndex() {
        return this.spriteIndex;
    }
}
