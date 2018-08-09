package qbert.view;

/**
 * Interface used for  objects which can be rendered graphically in the game area.
 */
public interface Renderable {

    /**
     * @return The Graphic Component of the Game Object
     */
    GraphicComponent getGraphicComponent();

    /**
     * Gets the Z Axis index.
     * @return An integer used for ordering the rendering of objects.
     */
    int getZIndex();

}
