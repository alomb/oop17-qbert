package qbert.model;

import qbert.model.utilities.Position2D;
import qbert.model.components.graphics.DiskGC;
import qbert.model.components.graphics.RenderableBackground;

/**
 * The implementation of {@link DiskGC}.
 */
public class DiskImpl extends RenderableBackground implements Disk {

    private Position2D position;
    private final DiskGC graphics;
    private boolean dead;

    /**
     * @param position the {@link Position2D} of the disk
     * @param graphics the linked {@link DiskGC}
     */
    public DiskImpl(final Position2D position, final DiskGC graphics) {
        super();
        this.position = position;
        this.graphics = graphics;
    }

    @Override
    public final Position2D getCurrentPosition() {
        return this.position;
    }

    @Override
    public final void setCurrentPosition(final Position2D newPos) {
        this.position = newPos;
    }

    @Override
    public final DiskGC getGraphicComponent() {
        return this.graphics;
    }

    @Override
    public final void setDead() {
        this.dead = true;
    }

    @Override
    public final boolean isDead() {
        return this.dead;
    }

    @Override
    public final void update(final float elapsedTime) {
        this.graphics.update(elapsedTime);
    }
}
