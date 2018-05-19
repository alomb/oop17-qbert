package qbert.view;

/**
 * The extension of {@link TileGC} to manage disk graphics that includes a method to provide animations.
 */
public interface DiskGC extends TileGC {

    /**
     * @param elapsedTime the time elapsed since the last game loop
     */
    void update(float elapsedTime);

}
