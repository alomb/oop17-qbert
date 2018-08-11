package qbert.model.components.graphics;

/**
 * Abstract class for Renderable items that stand in the background.
 */
public abstract class RenderableBackground implements Renderable {

    @Override
    public final int getZIndex() {
        return 0;
    }

}
