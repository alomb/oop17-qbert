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
        if (this.getSpriteIndex() < this.getSpriteNumber() - 1) {
            this.setSprite(this.getSpriteIndex() + 1);
            return true;
        }
        return false;
    }
}
