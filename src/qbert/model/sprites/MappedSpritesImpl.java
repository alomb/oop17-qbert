package qbert.model.sprites;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Map;

/**
 * An implementation of {@link MappedSprites}.
 */
public class MappedSpritesImpl implements MappedSprites {

    private final Map<Integer, BufferedImage> sprites;

    /**
     * @param sprites the sprites mapped
     */
    public MappedSpritesImpl(final Map<Integer, BufferedImage> sprites) {
        this.sprites = sprites;
    }

    @Override
    public final Map<Integer, BufferedImage> getSprites() {
        return Collections.unmodifiableMap(this.sprites);
    }

}
