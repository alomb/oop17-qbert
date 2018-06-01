package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The implementation of {@link TileGC}.
 */
public class ReversibleTileGC extends BaseTileGC {

    /**
     * @param sprites the map of colors orderly indexed
     */
    public ReversibleTileGC(final Map<Integer, BufferedImage> sprites) {
        super(sprites);
    }
    
    @Override
    public void setNextSprite() {
        if (this.spriteIndex < this.sprites.size() - 1) {
            this.spriteIndex++;
        } else {
            this.spriteIndex = 0;
        }
    }
}
