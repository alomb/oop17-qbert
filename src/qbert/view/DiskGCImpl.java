package qbert.view;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The implementation of {@link DiskGC}.
 */
public class DiskGCImpl extends TileGCImpl implements DiskGC {

    private int timeBuffer;
    private int speed;

    /**
     * @param sprites the set of sprites to be shown
     * @param speed the time in milliseconds to change from a sprite to another.
     */
    public DiskGCImpl(Map<Integer, BufferedImage> sprites, int speed) {
        super(sprites);
        this.timeBuffer = 0;
        this.speed = speed;
    }

    @Override
    public void update(float elapsedTime) {
        if((this.timeBuffer+=elapsedTime) > speed) {
            this.timeBuffer = 0;
            this.setNextSprite();
        }
    }
}
