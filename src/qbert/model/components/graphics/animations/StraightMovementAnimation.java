package qbert.model.components.graphics.animations;

import qbert.model.utilities.Position2D;

/**
 * An animation that provides a straight movement to a target point. 
 */
public class StraightMovementAnimation extends MovementAnimationImpl {

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
    public final Position2D next() {

        final Position2D nextPos = new Position2D(this.getCurrentPosition().getX() + ((int) Math.cos(angle)), 
                this.getCurrentPosition().getY() + ((int) Math.sin(angle)));

        if (this.upward && nextPos.getY() < this.getTargetPosition().getY()) {
            return new Position2D(this.getTargetPosition());
        } else if (!this.upward && nextPos.getY() > this.getTargetPosition().getY()) {
            return new Position2D(this.getTargetPosition());
        }

        if (this.rightward && nextPos.getX() > this.getTargetPosition().getX()) {
            return new Position2D(this.getTargetPosition());
        } else if (!this.rightward && nextPos.getX() < this.getTargetPosition().getX()) {
            return new Position2D(this.getTargetPosition());
        }

        return nextPos;

    }
}
