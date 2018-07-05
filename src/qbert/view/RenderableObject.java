package qbert.view;

/**
 * Implementation of Renderable Interface.
 */
public class RenderableObject implements Renderable {

    private final GraphicComponent graphicComponent;

    /**
     * Constructor of RenderableObject class.
     * @param g {@link GraphicComponent} of the object represented by the class
     */
    public RenderableObject(final GraphicComponent g) {
        this.graphicComponent = g;
    }

    @Override
    public final GraphicComponent getGraphicComponent() {
        return this.graphicComponent;
    }

    //TODO: Maybe to be moved in an abstract class
    @Override
    public int getZIndex() {
        return 0;
    }

}
