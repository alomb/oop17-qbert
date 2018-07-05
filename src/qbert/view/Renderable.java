package qbert.view;

/**
 * Interface used for  objects which can be rendered graphically in the game area
 */
public interface Renderable {

    /**
     * Gets the {@link GraphicComponent} of the game object
     * @return
     */
    public GraphicComponent getGraphicComponent();

    /**
     * Gets the Z Axis index
     * @return An integer used for ordering the rendering of objects
     */
    public int getZIndex();
    
}
