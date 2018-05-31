package qbert.view;

import qbert.model.utilities.Position2D;

public interface Renderable {
    
    public GraphicComponent getGraphicComponent();
    
    public Position2D getCurrentPosition();
    
}
