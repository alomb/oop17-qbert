package qbert.view.animations;

import qbert.model.utilities.Position2D;

/**
 * An animation for disks that provide a diagonal upward movement. 
 */
public class DiskAnimation extends MovementAnimation {

    /**
     * Set true when the disk must move to.
     */
    private final double angle;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public DiskAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos, targetPos);
        final Position2D direction = new Position2D(targetPos);
        direction.vectorSum(new Position2D(-this.getCurrentPosition().getX(), 
                -this.getCurrentPosition().getY()));
        this.angle = Math.atan2(direction.getY(), direction.getX());
    }

    @Override
    protected final void calculateNext() {

        this.getCurrentPosition().vectorSum(
                new Position2D((int) (this.getAnimationSpeed() * Math.cos(angle)), 
                        (int) (this.getAnimationSpeed() * Math.sin(angle))));

        if (this.getCurrentPosition().getY() < this.getTargetPosition().getY()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        }

    }

}
