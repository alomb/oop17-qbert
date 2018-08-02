package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Implementation of {@link TileGC} which rotates the colors endlessly.
 */
public class ReversibleTileGC extends BaseTileGC {

    /**
     * @param sprites the map of colors orderly indexed
     */
    public ReversibleTileGC(final Map<Integer, BufferedImage> sprites) {
        super(sprites);
    }

    @Override
    public boolean setNextSprite() {
        if (this.spriteIndex < this.sprites.size() - 1) {
            this.spriteIndex++;
            return true;
        } else {
            this.spriteIndex = 0;
            return false;
        }
    }
}
