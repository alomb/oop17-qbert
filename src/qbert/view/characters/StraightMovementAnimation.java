package qbert.view.characters;

import qbert.model.utilities.Position2D;

/**
 * An animation that provides a straight movement to a target point. 
 */
public class StraightMovementAnimation extends MovementAnimation {

    private final double angle;

    /*
     * Variables calculated from the given positions used to determine the animation
     * directions in both axis.
     */
    private final boolean upward;
    private final boolean rightward;

    /**
     * @param startPos the first {@link Position2D}
     * @param targetPos the last {@link Position2D}
     */
    public StraightMovementAnimation(final Position2D startPos, final Position2D targetPos) {
        super(startPos, targetPos);

        if (targetPos.getY() < startPos.getY()) {
            this.upward = true;
        } else {
            this.upward = false;
        }

        if (targetPos.getX() > startPos.getX()) {
            this.rightward = true;
        } else {
            this.rightward = false;
        }

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

        if (this.upward && this.getCurrentPosition().getY() < this.getTargetPosition().getY()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        } else if (!this.upward && this.getCurrentPosition().getY() > this.getTargetPosition().getY()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        }

        if (this.rightward && this.getCurrentPosition().getX() > this.getTargetPosition().getX()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        } else if (!this.rightward && this.getCurrentPosition().getX() < this.getTargetPosition().getX()) {
            this.setCurrentPosition(new Position2D(this.getTargetPosition()));
        }
    }
}
