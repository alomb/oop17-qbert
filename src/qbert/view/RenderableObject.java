package qbert.view;

import qbert.model.utilities.Position2D;

public class RenderableObject implements Renderable {
    
    private GraphicComponent graphicComponent;
    private Position2D pos;
    
    public RenderableObject(GraphicComponent g) {
        this.graphicComponent = g;
    }

    @Override
    public GraphicComponent getGraphicComponent() {
        return this.graphicComponent;
    }

    @Override
    public int getZIndex() {
        return 0;
    }

}
