package qbert.view;

/**
 * The extension of {@link GraphicComponent} to manage disk graphics.
 */
public interface DiskGC extends TileGC {
    
    /**
     * @param elapsedTime the time elapsed since the last game loop
     */
    void update(float elapsedTime);

}
