package qbert.model.components.graphics;

/**
 * Implementation of Renderable Interface.
 */
public class RenderableObject extends RenderableBackground {

    private final GraphicComponent graphicComponent;

    /**
     * Constructor of RenderableObject class.
     * @param g {@link GraphicComponent} of the object represented by the class
     */
    public RenderableObject(final GraphicComponent g) {
        super();
        this.graphicComponent = g;
    }

    @Override
    public final GraphicComponent getGraphicComponent() {
        return this.graphicComponent;
    }

}
