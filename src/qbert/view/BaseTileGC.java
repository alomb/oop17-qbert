package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Implementation of {@link TileGC}.
 */
public class BaseTileGC extends AbstractTileGC {

    /**
     * @param sprites the map of colors orderly indexed
     */
    public BaseTileGC(final Map<Integer, BufferedImage> sprites) {
        super(sprites);
    }

    @Override
    public final boolean setNextSprite() {
        if (this.spriteIndex < this.sprites.size() - 1) {
            this.spriteIndex++;
            return true;
        }
        return false;
    }
}
