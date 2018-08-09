package qbert.model;

import qbert.view.DiskGC;

/**
 * An extension of {@link GameObject} for disks.
 */
public interface Disk extends GameObject {

    /**
     * @return the linked {@link DiskGC}
     */
    DiskGC getGraphicComponent();

    /**
     * set the disk dead (unusable).
     */
    void setDead();

    /**
     * @return true if the disk is dead
     */
    boolean isDead();

    /**
     * @param elapsedTime the time elapsed since the last game cycle
     */
    void update(float elapsedTime);
}
