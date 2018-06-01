package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The implementation of {@link TileGC}.
 */
public class ReversableTileGC extends BaseTileGC {

    /**
     * @param sprites the map of colors orderly indexed
     */
    public ReversableTileGC(final Map<Integer, BufferedImage> sprites) {
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
