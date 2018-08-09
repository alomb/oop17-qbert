package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Implementation of {@link TileGC} which rotates the colors endlessly.
 */
public class ReversibleTileGC extends AbstractTileGC {

    /**
     * @param sprites the map of colors orderly indexed
     */
    public ReversibleTileGC(final Map<Integer, BufferedImage> sprites) {
        super(sprites);
    }

    @Override
    public final boolean setNextSprite() {
        if (this.getSpriteIndex() < this.getSpriteNumber() - 1) {
            this.setSprite(this.getSpriteIndex());
            return true;
        } else {
            this.setSprite(0);
            return false;
        }
    }
}
