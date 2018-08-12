package qbert.model.sprites;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * An interface to contain mapped sprites.
 */
public interface MappedSprites {

    /**
     * @return a {@link Map} consisting of indexes and sprites
     */
    Map<Integer, BufferedImage> getSprites();

}
